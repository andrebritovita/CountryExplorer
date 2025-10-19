package com.andrebritovita.countryexplorer.ui.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrebritovita.countryexplorer.domain.model.CountryDetail
import com.andrebritovita.countryexplorer.domain.usecase.GetCountryDetailUseCase
import com.andrebritovita.countryexplorer.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel @Inject constructor(
    private val getCountryDetailUseCase: GetCountryDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow<Resource<CountryDetail?>>(Resource.Loading)
    val state: StateFlow<Resource<CountryDetail?>> = _state

    init {

        savedStateHandle.get<String>("countryName")?.let { countryName ->
            getCountryDetail(countryName)
        }
    }

    private fun getCountryDetail(name: String) {
        viewModelScope.launch {
            _state.value = Resource.Loading
            _state.value = getCountryDetailUseCase(name)
        }
    }
}
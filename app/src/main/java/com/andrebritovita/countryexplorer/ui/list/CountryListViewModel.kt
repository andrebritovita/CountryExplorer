package com.andrebritovita.countryexplorer.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrebritovita.countryexplorer.domain.repository.CountryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch
import android.util.Log
import com.andrebritovita.countryexplorer.domain.model.CountryList
import com.andrebritovita.countryexplorer.domain.usecase.GetAllCountriesUseCase
import com.andrebritovita.countryexplorer.domain.usecase.GetCountriesByNameUseCase
import com.andrebritovita.countryexplorer.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val getAllCountriesUseCase: GetAllCountriesUseCase,
    private val getCountriesByNameUseCase: GetCountriesByNameUseCase
) : ViewModel() {

    private val _countriesState = MutableStateFlow<Resource<List<CountryList>>>(Resource.Loading)
    val countriesState: StateFlow<Resource<List<CountryList>>> = _countriesState.asStateFlow()

    init {
        getAllCountries()
    }

    private fun getAllCountries() {
        viewModelScope.launch {
            _countriesState.value = Resource.Loading
            _countriesState.value = getAllCountriesUseCase()
        }
    }

    fun searchCountries(name: String) {
        viewModelScope.launch {
            _countriesState.value = Resource.Loading
            if (name.isBlank()) {
                getAllCountries()
            } else {
                _countriesState.value = getCountriesByNameUseCase(name)
            }
        }
    }
}
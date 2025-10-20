package com.andrebritovita.countryexplorer.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrebritovita.countryexplorer.domain.model.CountryList
import com.andrebritovita.countryexplorer.domain.usecase.GetAllCountriesUseCase
import com.andrebritovita.countryexplorer.domain.usecase.GetCountriesByNameUseCase
import com.andrebritovita.countryexplorer.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(
    private val getAllCountriesUseCase: GetAllCountriesUseCase,
    private val getCountriesByNameUseCase: GetCountriesByNameUseCase
) : ViewModel() {

    private val _countriesState = MutableStateFlow<Resource<List<CountryList>>>(Resource.Loading)
    val countriesState: StateFlow<Resource<List<CountryList>>> = _countriesState

    private var allCountries: List<CountryList> = emptyList()

    init {
        getAllCountries()
    }

    private fun getAllCountries() {
        viewModelScope.launch {
            _countriesState.value = Resource.Loading
            when (val result = getAllCountriesUseCase()) {
                is Resource.Success -> {
                    allCountries = result.data
                    _countriesState.value = Resource.Success(allCountries)
                }
                is Resource.Error -> _countriesState.value = result
                is Resource.Loading -> {}
            }
        }
    }

    fun searchCountries(name: String) {
        viewModelScope.launch {
            _countriesState.value = Resource.Loading
            if (name.isBlank()) {
                _countriesState.value = Resource.Success(allCountries)
            } else {
                _countriesState.value = getCountriesByNameUseCase(name)
            }
        }
    }

    fun filterByRegion(region: String) {
        if (region == "All") {
            _countriesState.value = Resource.Success(allCountries)
        } else {
            val filteredList = allCountries.filter { it.region == region }
            _countriesState.value = Resource.Success(filteredList)
        }
    }
}
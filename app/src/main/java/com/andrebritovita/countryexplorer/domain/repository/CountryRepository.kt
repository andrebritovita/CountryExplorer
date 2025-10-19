package com.andrebritovita.countryexplorer.domain.repository


import com.andrebritovita.countryexplorer.domain.model.CountryDetail
import com.andrebritovita.countryexplorer.domain.model.CountryList
import com.andrebritovita.countryexplorer.utils.Resource

interface CountryRepository {
    suspend fun getAllCountries(): Resource<List<CountryList>>
    suspend fun getCountriesByName(name: String): Resource<List<CountryList>>
    suspend fun getCountryDetail(name: String): Resource<CountryDetail?>
}
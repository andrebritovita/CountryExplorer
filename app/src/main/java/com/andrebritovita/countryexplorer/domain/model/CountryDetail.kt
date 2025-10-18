package com.andrebritovita.countryexplorer.domain.model

data class CountryDetail(
    val name: String,
    val flagUrl: String?,
    val region: String?,
    val capital: String?,
    val population: Long?,
    val languages: String?,
    val currencies: String?,
    val borders: List<String>?,
)

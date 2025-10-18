package com.andrebritovita.countryexplorer.data.remote.dto



data class CountryDto(
    val flags: FlagsDto? = null,
    val name: NameDto? = null,
    val currencies: Map<String, CurrencyDto>? = null,
    val capital: List<String>? = null,
    val region: String? = null,
    val languages: Map<String, String>? = null,
    val borders: List<String>? = null,
    val population: Long? = null
)
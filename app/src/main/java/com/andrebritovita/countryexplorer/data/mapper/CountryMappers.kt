package com.andrebritovita.countryexplorer.data.mapper

import com.andrebritovita.countryexplorer.data.remote.dto.CountryDto
import com.andrebritovita.countryexplorer.domain.model.CountryDetail
import com.andrebritovita.countryexplorer.domain.model.CountryList


fun CountryDto.toCountryDetail(): CountryDetail {
    val displayName = this.name?.common ?: "Unknown"
    val flagUrl = this.flags?.png ?: flags?.svg
    val region = this.region?: "N/A"
    val capital = this.capital?.firstOrNull()?: "N/A"
    val population = this.population?: 0L
    val languages = this.languages?.values?.joinToString(", ")?: "N/A"
    val currencies = this.currencies?.values?.map {"${it.name ?: ""}${it.symbol?.let{ " ($it)" } ?: ""}".trim() }?.joinToString(", ")?: "N/A"
    val borders = this.borders?: emptyList()

    return CountryDetail (
        displayName,
        flagUrl,
        region,
        capital,
        population,
        languages,
        currencies,
        borders
    )
}

fun CountryDto.toCountryList(): CountryList {
    val displayName = this.name?.common?: "Unknown"
    val flagUrl = this.flags?.png?: flags?.svg
    val region = this.region?:"N/A"
    val capital = this.capital?.firstOrNull()?: "N/A"

    return CountryList(
        displayName,
        flagUrl,
        region,
        capital
    )
}
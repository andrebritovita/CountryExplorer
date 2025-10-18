package com.andrebritovita.countryexplorer.data.remote.api

import com.andrebritovita.countryexplorer.data.remote.dto.CountryDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CountryApiService {
    @GET("all?fields=name,population,flags,capital,languages,currencies,borders,region")
    suspend fun getAllCountries(): Response<List<CountryDto>>

    @GET("name/ {name}")
    suspend fun getCountriesByName (@Path("name") name: String): Response<List<CountryDto>>

    @GET("region/ {region}")
    suspend fun getCountriesByRegion(@Path("region") region: String): Response<List<CountryDto>>

    @GET("all")
    suspend fun getAllCountriesFields(@Query("fields") fields: String): Response<List<CountryDto>>
}
package com.andrebritovita.countryexplorer.data.repository


import com.andrebritovita.countryexplorer.data.mapper.toCountryDetail
import com.andrebritovita.countryexplorer.data.mapper.toCountryList
import com.andrebritovita.countryexplorer.data.remote.api.CountryApiService
import com.andrebritovita.countryexplorer.domain.model.CountryDetail
import com.andrebritovita.countryexplorer.domain.model.CountryList
import com.andrebritovita.countryexplorer.domain.repository.CountryRepository
import com.andrebritovita.countryexplorer.utils.Resource
import java.io.IOException

class CountryRepositoryImpl(private val api: CountryApiService) : CountryRepository {
    override suspend fun getAllCountries(): Resource<List<CountryList>> {
       return try {
           val response = api.getAllCountries().map { it.toCountryList() }
           Resource.Success(response)
       } catch (error: IOException){
           Resource.Error("Erro de conexão. Verifique sua internet.", error)
       } catch (error: Exception){
           Resource.Error("Erro inesperado: ${error.localizedMessage}", error)
       }
    }

    override suspend fun getCountriesByName(name: String): Resource<List<CountryList>> {
        return try {
            val response = api.getCountriesByName(name).map { it.toCountryList() }
            Resource.Success(response)
        } catch (error: IOException) {
            Resource.Error("Erro de conexão. Verifique sua internet.", error)
        } catch (error: Exception) {
            Resource.Error("Erro inesperado: ${error.localizedMessage}", error)
        }
    }

    override suspend fun getCountryDetail(name: String): Resource<CountryDetail?> {
        return try {
            // Retorna o primeiro elemento da lista ou null caso a lista esteja vazia.
            // ?. Só executa o método se o valor não for nulo.
            val response = api.getCountriesByName(name).firstOrNull()?.toCountryDetail()
            if (response != null) {
                Resource.Success(response)
            } else {
                Resource.Error("País não encontrado.")
            }
        } catch (error: IOException) {
            Resource.Error("Erro de conexão. Verifique sua internet.", error)
        } catch (error: Exception) {
            Resource.Error("Erro inesperado: " +
                    "${error.localizedMessage}", error)
        }
    }

}
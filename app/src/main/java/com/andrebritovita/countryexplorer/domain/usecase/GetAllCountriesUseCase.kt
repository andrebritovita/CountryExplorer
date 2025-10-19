package com.andrebritovita.countryexplorer.domain.usecase

import com.andrebritovita.countryexplorer.domain.model.CountryList
import com.andrebritovita.countryexplorer.domain.repository.CountryRepository
import com.andrebritovita.countryexplorer.utils.Resource
import javax.inject.Inject

class GetAllCountriesUseCase @Inject constructor(
    private val repository: CountryRepository
) {
    suspend operator fun invoke(): Resource<List<CountryList>> {
        return repository.getAllCountries()
    }
}
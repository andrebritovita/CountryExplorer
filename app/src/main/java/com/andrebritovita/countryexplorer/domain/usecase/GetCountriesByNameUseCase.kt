package com.andrebritovita.countryexplorer.domain.usecase

import com.andrebritovita.countryexplorer.domain.model.CountryList
import com.andrebritovita.countryexplorer.domain.repository.CountryRepository
import com.andrebritovita.countryexplorer.utils.Resource
import javax.inject.Inject

class GetCountriesByNameUseCase @Inject constructor(
    private val repository: CountryRepository
) {
    suspend operator fun invoke(name: String): Resource<List<CountryList>> {
        return repository.getCountriesByName(name)
    }
}

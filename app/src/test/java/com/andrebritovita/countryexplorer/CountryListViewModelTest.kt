package com.andrebritovita.countryexplorer

import org.junit.Test
import org.junit.Assert.*
import com.andrebritovita.countryexplorer.domain.model.CountryList
import com.andrebritovita.countryexplorer.domain.usecase.GetAllCountriesUseCase
import com.andrebritovita.countryexplorer.domain.usecase.GetCountriesByNameUseCase
import com.andrebritovita.countryexplorer.ui.list.CountryListViewModel
import com.andrebritovita.countryexplorer.utils.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CountryListViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Mock
    private lateinit var getAllCountriesUseCase: GetAllCountriesUseCase
    @Mock
    private lateinit var getCountriesByNameUseCase: GetCountriesByNameUseCase
    private lateinit var viewModel: CountryListViewModel

    @Test
    fun `quando o viewmodel é iniciado, deve carregar os países com sucesso`() = runTest {
        val fakeCountries = listOf(CountryList("Brazil", null, "Americas", "Brasília"))
        whenever(getAllCountriesUseCase()).thenReturn(Resource.Success(fakeCountries))
        viewModel = CountryListViewModel(getAllCountriesUseCase, getCountriesByNameUseCase)

        val expectedState = Resource.Success(fakeCountries)
        assertEquals(expectedState, viewModel.countriesState.value)
    }

    @Test
    fun `quando o usecase falha, deve emitir um estado de erro`() = runTest {
        val errorMessage = "Network Error"
        whenever(getAllCountriesUseCase()).thenReturn(Resource.Error(errorMessage))
        viewModel = CountryListViewModel(getAllCountriesUseCase, getCountriesByNameUseCase)
        val expectedState = Resource.Error(errorMessage)
        assertEquals(expectedState, viewModel.countriesState.value)
    }
}
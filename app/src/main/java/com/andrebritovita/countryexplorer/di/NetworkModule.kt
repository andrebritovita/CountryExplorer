package com.andrebritovita.countryexplorer.di

import com.andrebritovita.countryexplorer.data.remote.api.CountryApiService
import com.andrebritovita.countryexplorer.data.repository.CountryRepositoryImpl
import com.andrebritovita.countryexplorer.domain.repository.CountryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import retrofit2.Retrofit
import javax.inject.Singleton
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://restcountries.com/v3.1/"
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    // Configuração do Retrofit
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
    }


    @Provides
    @Singleton
    fun provideCountryApiService(retrofit: Retrofit): CountryApiService {
        return retrofit.create(CountryApiService::class.java)
    }

    // Ensina o Hilt e criar a interface CountryRepository
    @Provides
    @Singleton
    fun provideCountryRepository(api: CountryApiService): CountryRepository {
        return CountryRepositoryImpl(api)
    }
}
package com.andrebritovita.countryexplorer.data.remote.dto

data class NameDto(
    val common: String? = null,
    val official: String? = null,
    val nativeName: Map<String, NativeNameDto>? = null
)

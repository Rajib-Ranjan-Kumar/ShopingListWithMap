package com.example.theshoppinglist.data

data class GeocodingResponse(

    val results: List<GeocodingResult>,
    val status: String
)
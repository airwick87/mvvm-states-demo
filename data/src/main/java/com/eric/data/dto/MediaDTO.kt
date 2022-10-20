package com.eric.data.dto

data class MediaDTO(
    val images : ImageListDTO
)

data class ImageListDTO(
    val urls: List<String>
)
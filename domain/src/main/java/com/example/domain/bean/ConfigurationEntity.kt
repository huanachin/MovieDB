package com.example.domain.bean

import com.example.themovieapp.data.entity.ImageEntity

data class Configuration(
    val imageEntity: ImageEntity?,
    val changeKeys: List<String>?
)
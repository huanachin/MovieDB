package com.example.themovieapp.data.response

import com.example.themovieapp.data.entity.ImageEntity
import com.google.gson.annotations.SerializedName

data class ConfigurationResponse(
    @SerializedName("images") val imageEntity: ImageEntity?,
    @SerializedName("change_keys") val changeKeys: List<String>?
)
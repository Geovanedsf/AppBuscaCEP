package com.geovane.appapigit.model

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("logradouro")
    val address: String,
    @SerializedName("bairro")
    val bairro: String,
    @SerializedName("localidade")
    val city: String,
    @SerializedName("uf")
    val state: String
)

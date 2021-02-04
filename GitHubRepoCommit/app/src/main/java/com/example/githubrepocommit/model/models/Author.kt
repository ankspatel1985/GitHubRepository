package com.example.githubrepocommit.model.models

import com.google.gson.annotations.SerializedName

data class Author (
    @SerializedName("name") val name: String
)
package com.example.githubrepocommit.model.models

import com.google.gson.annotations.SerializedName

data class Commit (
    @SerializedName("message") val message: String,
    @SerializedName("author") val author: Author
)
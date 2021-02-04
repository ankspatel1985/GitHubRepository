package com.example.githubrepocommit.model.models

import com.google.gson.annotations.SerializedName

data class GitHubCommit (
    @SerializedName("sha") val sha: String,
    @SerializedName("commit") val commit: Commit
)
package com.example.githubrepocommit.model.apis

import com.example.githubrepocommit.model.models.GitHubCommit
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interface for github api end points.
 */
interface GitHubApiEndPoints {
    /**
     * Github api to get commits information for the repository of User.
     */
    @GET("/repos/{user}/{repo}/commits")
    fun getCommits(
        @Path("user") user: String?, @Path("repo") repo: String?, @Query(
            "per_page"
        ) per_page: Int
    ): Call<List<GitHubCommit?>?>
}
package com.example.githubrepocommit.model.modules

import com.example.githubrepocommit.model.apis.GitHubApiEndPoints
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Network dependency module.
 */
@Module
internal class NetworkModule(private val baseUrl: String) {
    /**
     * Create the [Retrofit] instance using the configured values.
     */
    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Create an implementation of the API endpoints defined by the [GitHubApiEndPoints] interface.
     */
    @Provides
    @Singleton
    fun providesGitHubApi(retrofit: Retrofit): GitHubApiEndPoints {
        return retrofit.create(GitHubApiEndPoints::class.java)
    }
}
package com.example.githubrepocommit.model.modules

import com.example.githubrepocommit.model.apis.GitHubApiEndPoints
import com.example.githubrepocommit.viewModel.repositories.GitHubRepository
import com.example.githubrepocommit.viewModel.repositories.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger test module to provide [GitHubRepository].
 */
@Module
class GitHubRepositoryModuleTest {

    @Provides
    @Singleton
    fun providesRepository(gitHubApiEndPoints: GitHubApiEndPoints): Repository {
        return GitHubRepository(gitHubApiEndPoints)
    }
}
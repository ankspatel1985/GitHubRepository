package com.example.githubrepocommit.model.modules

import com.example.githubrepocommit.model.apis.GitHubApiEndPoints
import com.example.githubrepocommit.viewModel.repositories.GitHubRepository
import com.example.githubrepocommit.viewModel.repositories.Repository
import dagger.Module
import dagger.Provides

/**
 * Dagger module to provide [GitHubRepository].
 */
@Module
class GitHubRepositoryModule {

    @Provides
    @ActivityScope
    fun providesRepository(gitHubApiEndPoints: GitHubApiEndPoints): Repository {
        return GitHubRepository(gitHubApiEndPoints)
    }
}
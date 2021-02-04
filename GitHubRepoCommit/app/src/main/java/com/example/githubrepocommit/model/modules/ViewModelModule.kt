package com.example.githubrepocommit.model.modules

import com.example.githubrepocommit.viewModel.GitHubCommitsViewModelFactory
import com.example.githubrepocommit.viewModel.repositories.Repository
import dagger.Module
import dagger.Provides

/**
 * Dagger module to provide [GitHubCommitsViewModelFactory].
 */
@Module
class ViewModelModule {

    @Provides
    fun providesGitHubCommitsViewModelFactory(repository: Repository): GitHubCommitsViewModelFactory {
        return GitHubCommitsViewModelFactory(repository)

    }
}
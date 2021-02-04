package com.example.githubrepocommit.model.modules

import com.example.githubrepocommit.view.fragments.GitHubCommitsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Dagger module to provide [GitHubCommitsFragment].
 */
@Module
abstract class GitHubCommitsFragmentBuilder {
    @ActivityScope
    @ContributesAndroidInjector(modules = [GitHubRepositoryModule::class])
    abstract fun gitHubCommitsFragment(): GitHubCommitsFragment
}
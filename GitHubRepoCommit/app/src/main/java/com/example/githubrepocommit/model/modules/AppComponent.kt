package com.example.githubrepocommit.model.modules

import com.example.githubrepocommit.view.GitHubApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Main dependency component.
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class
    ]
)
interface AppComponent {
    fun inject(app: GitHubApplication)
}
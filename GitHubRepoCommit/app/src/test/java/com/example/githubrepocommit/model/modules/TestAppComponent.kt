package com.example.githubrepocommit.model.modules

import com.example.githubrepocommit.BaseTestApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Main dependency component for test.
 */
@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        GitHubRepositoryModuleTest::class,
        ViewModelModule::class
    ]
)
interface TestAppComponent {
    fun inject(baseTestApplication: BaseTestApplication)
}
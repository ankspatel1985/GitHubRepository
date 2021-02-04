package com.example.githubrepocommit.view

import android.app.Application
import androidx.annotation.VisibleForTesting
import com.example.githubrepocommit.model.modules.AppComponent
import com.example.githubrepocommit.model.modules.DaggerAppComponent
import com.example.githubrepocommit.model.modules.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

lateinit var component: AppComponent

/**
 * Main application class.
 */
class GitHubApplication : Application(), HasAndroidInjector {
    /**
     * Github api url.
     */
    val GITHUB_BASE_URL = "https://api.github.com"

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent
            .builder()
            .networkModule(NetworkModule(GITHUB_BASE_URL))
            .build()
        component.inject(this)
    }
}
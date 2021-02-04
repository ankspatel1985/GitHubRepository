package com.example.githubrepocommit.view

import android.app.Application
import com.example.githubrepocommit.model.modules.AppComponent
import com.example.githubrepocommit.model.modules.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

lateinit var component: AppComponent

/**
 * Main application class.
 */
class GitHubApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent
            .builder()
            .build()
        component.inject(this)
    }
}
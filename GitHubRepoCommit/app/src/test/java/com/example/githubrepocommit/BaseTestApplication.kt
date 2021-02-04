package com.example.githubrepocommit

import com.example.githubrepocommit.model.modules.DaggerTestAppComponent
import com.example.githubrepocommit.model.modules.TestAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import org.junit.Before
import javax.inject.Inject

/**
 * Base test application class.
 */
abstract class BaseTestApplication : HasAndroidInjector {
    lateinit var testAppComponent: TestAppComponent

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    @Before
    open fun setUp() {
        testAppComponent = DaggerTestAppComponent
            .builder()
            .build()
        testAppComponent.inject(this)
    }
}
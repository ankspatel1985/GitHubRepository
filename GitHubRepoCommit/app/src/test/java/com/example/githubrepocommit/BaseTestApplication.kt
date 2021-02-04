package com.example.githubrepocommit

import com.example.githubrepocommit.model.modules.DaggerTestAppComponent
import com.example.githubrepocommit.model.modules.NetworkModule
import com.example.githubrepocommit.model.modules.TestAppComponent
import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import org.junit.After
import org.junit.Before
import java.io.File
import javax.inject.Inject

/**
 * Base test application class.
 */
abstract class BaseTestApplication : HasAndroidInjector {
    lateinit var testAppComponent: TestAppComponent

    /**
     * Mock web server.
     */
    lateinit var mockServer: MockWebServer

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    @Before
    open fun setUp() {
        mockServer = MockWebServer()
        mockServer.start()
        testAppComponent = DaggerTestAppComponent
            .builder()
            .networkModule(NetworkModule(mockServer.url("/").toString()))
            .build()
        testAppComponent.inject(this)
    }

    @After
    open fun tearDown() {
        mockServer.shutdown()
    }

    /**
     * Mock github commit response using mock web server.
     */
    open fun mockResponse(fileName: String, responseCode: Int) = mockServer.enqueue(
        MockResponse()
            .setResponseCode(responseCode)
            .setBody(getCommitJsonString(fileName))
    )

    private fun getCommitJsonString(path: String): String {
        val uri = this.javaClass.classLoader!!.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }
}
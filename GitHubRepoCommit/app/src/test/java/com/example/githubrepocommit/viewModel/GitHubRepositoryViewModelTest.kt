package com.example.githubrepocommit.viewModel

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.example.githubrepocommit.BaseTestApplication
import com.example.githubrepocommit.model.models.Author
import com.example.githubrepocommit.model.models.Commit
import com.example.githubrepocommit.model.models.GitHubCommit
import com.example.githubrepocommit.viewModel.repositories.GitHubRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.net.HttpURLConnection

/**
 * Test cases for GitHubRepository and GitHubCommitsViewModel.
 */
@RunWith(RobolectricTestRunner::class)
@Config(maxSdk = Build.VERSION_CODES.P, minSdk = Build.VERSION_CODES.P)
class GitHubRepositoryViewModelTest : BaseTestApplication() {
    private val TEST_USER = "test_user"
    private val TEST_REPO = "test_repo"
    private val TEST_JSON_FILE = "commit_sample.json"
    private val MOCK_COMMIT = GitHubCommit("66577", Commit("test_sha", Author("test_user")))

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private lateinit var activity: FragmentActivity
    private lateinit var viewModel: GitHubCommitsViewModel
    private lateinit var repository: GitHubRepository

    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        repository = GitHubRepository(gitHubApi)
        this.activity = Robolectric.setupActivity(FragmentActivity::class.java)
        viewModel = ViewModelProviders.of(activity, factory).get(GitHubCommitsViewModel::class.java)
        viewModel.init()
    }

    @After
    override fun tearDown() {
        super.tearDown()
    }

    @Test
    fun fetchCommit_whenSuccessResponse_return200() {
        mockResponse(TEST_JSON_FILE, HttpURLConnection.HTTP_OK)
        val actualResponse = repository.getCommitsDetails(TEST_USER, TEST_REPO).execute()
        Assert.assertTrue(
            actualResponse.code().toString().contains(HttpURLConnection.HTTP_OK.toString())
        )
    }

    @Test
    fun fetchCommit_whenSuccessResponse_returnValidCommitInformation() {
        mockResponse(TEST_JSON_FILE, HttpURLConnection.HTTP_OK)
        val actualResponse = repository.getCommitsDetails(TEST_USER, TEST_REPO).execute()
        val actualCommitObj = actualResponse.body()?.get(0)
        verifyGitHubCommit(actualCommitObj!!)
    }

    @Test
    fun fetchCommit_whenFailedResponse_return400() {
        mockResponse(TEST_JSON_FILE, HttpURLConnection.HTTP_BAD_REQUEST)
        val actualResponse = repository.getCommitsDetails(TEST_USER, TEST_REPO).execute()
        Assert.assertTrue(
            actualResponse.toString().contains(HttpURLConnection.HTTP_BAD_REQUEST.toString())
        )
    }

    @Test
    fun getCommit_whenSuccessResponse_returnValidGitHubCommitsResponseLiveData() {
        viewModel.gitHubCommitResponseLiveData.observeForever {}
        mockResponse(TEST_JSON_FILE, HttpURLConnection.HTTP_OK)
        Assert.assertNull(viewModel.gitHubCommitResponseLiveData.value)
        viewModel.getCommits(TEST_USER, TEST_REPO)
        runBlocking {
            CoroutineScope(Dispatchers.Main).launch {
                val value = viewModel.gitHubCommitResponseLiveData.value
                Assert.assertNotNull(value)
                verifyGitHubCommit(value!!.get(0))
            }
        }
    }

    private fun verifyGitHubCommit(actualCommitObj: GitHubCommit?) {
        assertEquals(MOCK_COMMIT.sha, actualCommitObj?.sha)
        assertEquals(MOCK_COMMIT.commit.message, actualCommitObj?.commit?.message)
        assertEquals(MOCK_COMMIT.commit.author.name, actualCommitObj?.commit?.author?.name)
    }
}
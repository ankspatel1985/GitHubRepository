package com.example.githubrepocommit.viewModel.repositories

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubrepocommit.model.apis.GitHubApiEndPoints
import com.example.githubrepocommit.model.models.GitHubCommit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Class to call github api endpoints and get response.
 */
class GitHubRepository(private val gitHubApiEndPoints: GitHubApiEndPoints) : Repository {
    /**
     * MutableLiveData to provide github commit response.
     */
    private val gitHubCommitResponseLiveData: MutableLiveData<List<GitHubCommit?>?> = MutableLiveData()

    override fun getCommits(user: String, repo: String) {
        getCommitsDetails(user, repo).enqueue(commitCallBack)
    }

    override fun gitHubCommitsResponseLiveData(): LiveData<List<GitHubCommit?>?> {
        return gitHubCommitResponseLiveData
    }

    /**
     * Callback to handle github commit response.
     */
    private val commitCallBack = object : Callback<List<GitHubCommit?>?> {
        override fun onResponse(call: Call<List<GitHubCommit?>?>, response: Response<List<GitHubCommit?>?>) {
            if (response.body() != null) {
                gitHubCommitResponseLiveData.postValue(response.body())
            } else {
                gitHubCommitResponseLiveData.postValue(null)
            }
        }

        override fun onFailure(call: Call<List<GitHubCommit?>?>, t: Throwable) {
            gitHubCommitResponseLiveData.postValue(null)
        }
    }

    /**
     * Sends a request to a github webserver and returns a response.
     *
     * @param user the name of github user
     * @param repo the name of github repository
     */
    @VisibleForTesting
    fun getCommitsDetails(user: String, repo: String): Call<List<GitHubCommit?>?> {
        return gitHubApiEndPoints.getCommits(user, repo, PER_PAGE_RESULT);
    }
}

/**
 * Maximum per page result for github commit.
 */
@VisibleForTesting
const val PER_PAGE_RESULT = 25
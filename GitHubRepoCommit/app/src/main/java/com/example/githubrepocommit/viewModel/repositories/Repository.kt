package com.example.githubrepocommit.viewModel.repositories

import androidx.lifecycle.LiveData
import com.example.githubrepocommit.model.models.GitHubCommit

/**
 * Repository for gitHub api
 */
interface Repository {
    /**
     * Get github commits.
     *
     * @param user the name of github user
     * @param repo the name of github repository
     */
    fun getCommits(user: String, repo: String)

    /**
     * Github commits live data.
     */
    fun gitHubCommitsResponseLiveData(): LiveData<List<GitHubCommit?>?>
}
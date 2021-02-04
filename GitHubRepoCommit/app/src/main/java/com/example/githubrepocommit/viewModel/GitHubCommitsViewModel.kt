package com.example.githubrepocommit.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubrepocommit.model.models.GitHubCommit
import com.example.githubrepocommit.viewModel.repositories.Repository

/**
 * Github commits view model class.
 */
class GitHubCommitsViewModel(private val gitHubRepository: Repository) : ViewModel() {
    /**
     * MutableLiveData to provide github commit response.
     */
    var gitHubCommitResponseLiveData: LiveData<List<GitHubCommit?>?> = MutableLiveData()

    fun init() {
        gitHubCommitResponseLiveData = gitHubRepository.gitHubCommitsResponseLiveData()
    }

    /**
     * Call github repository to get github commits.
     *
     * @param user the name of github user
     * @param repo the name of github repository
     */
    fun getCommits(user: String?, repo: String?) {
        gitHubRepository.getCommits(user!!, repo!!)
    }
}

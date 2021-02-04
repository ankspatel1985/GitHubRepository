package com.example.githubrepocommit.viewModel.repositories

import androidx.lifecycle.LiveData
import com.example.githubrepocommit.model.models.GitHubCommit

class GitHubRepository: Repository {
    override fun getCommits(user: String, repo: String) {
        TODO("Not yet implemented")
    }

    override fun gitHubCommitsResponseLiveData(): LiveData<List<GitHubCommit?>?> {
        TODO("Not yet implemented")
    }
}
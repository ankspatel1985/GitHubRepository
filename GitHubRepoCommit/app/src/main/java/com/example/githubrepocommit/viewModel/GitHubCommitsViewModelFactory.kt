package com.example.githubrepocommit.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubrepocommit.viewModel.repositories.Repository
import javax.inject.Inject

/**
 * Factory class to provide [GitHubCommitsViewModel].
 */
@Suppress("UNCHECKED_CAST")
class GitHubCommitsViewModelFactory @Inject constructor(private val repository: Repository)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GitHubCommitsViewModel::class.java)) {
            return GitHubCommitsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
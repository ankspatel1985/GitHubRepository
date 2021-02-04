package com.example.githubrepocommit.view.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.githubrepocommit.R
import com.example.githubrepocommit.model.models.GitHubCommit
import com.example.githubrepocommit.view.adapters.GitHubCommitsAdapter
import com.example.githubrepocommit.viewModel.GitHubCommitsViewModel
import com.example.githubrepocommit.viewModel.GitHubCommitsViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * Github commits fragment class.
 */
class GitHubCommitsFragment : Fragment() {
    @Inject
    lateinit var factory: GitHubCommitsViewModelFactory

    @VisibleForTesting
    lateinit var viewModel: GitHubCommitsViewModel
    private lateinit var adapter: GitHubCommitsAdapter

    @BindView(R.id.fragment_commit_searchResultsRecyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.txt_user)
    lateinit var userEditText: TextInputEditText

    @BindView(R.id.txt_repo)
    lateinit var repoEditText: TextInputEditText

    @BindView(R.id.lbl_result_header)
    lateinit var resultHeader: TextView

    @BindView(R.id.btn_search)
    lateinit var searchButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
        adapter = GitHubCommitsAdapter()
        viewModel = ViewModelProviders.of(this, factory).get(GitHubCommitsViewModel::class.java)
        viewModel.init()
        viewModel.gitHubCommitResponseLiveData.observe(this,
            Observer { gitHubCommits: List<GitHubCommit?>? ->
                if (gitHubCommits != null) {
                    resultHeader.visibility = View.VISIBLE
                    adapter.setResults(gitHubCommits.toMutableList())
                } else {
                    resultHeader.visibility = View.GONE
                    adapter.clearResult()
                    Toast.makeText(context, getString(R.string.no_result_found_message), Toast.LENGTH_SHORT).show()
                }
            })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.commit_search_fragment, container, false)
        ButterKnife.bind(this, view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        searchButton.setOnClickListener {
            it.hideKeyboard()
            getCommits()
        }

        return view
    }

    /**
     * Call github view model to get github commits.
     */
    private fun getCommits() {
        val user = userEditText.editableText.toString()
        val repo = repoEditText.editableText.toString()
        viewModel.getCommits(user, repo)
    }

    /**
     * Hide keyboard.
     */
    private fun View.hideKeyboard() {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        inputMethodManager!!.hideSoftInputFromWindow(windowToken, 0)
    }
}

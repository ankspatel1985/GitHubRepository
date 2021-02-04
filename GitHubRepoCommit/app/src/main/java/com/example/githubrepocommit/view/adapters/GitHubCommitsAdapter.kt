package com.example.githubrepocommit.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.githubrepocommit.R
import com.example.githubrepocommit.model.models.GitHubCommit
import java.util.ArrayList

/**
 * Github commits recycler view adapter class.
 */
class GitHubCommitsAdapter : RecyclerView.Adapter<GitHubCommitsAdapter.CommitsResultHolder>() {
    /**
     * Github commits result list.
     */
    private var results: MutableList<GitHubCommit?> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommitsResultHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.commit_item, parent, false)
        return CommitsResultHolder(itemView)
    }

    override fun onBindViewHolder(holder: CommitsResultHolder, position: Int) {
        val commit = results.get(position)
        if (commit != null) {
            holder.authorTextView.text = commit.commit.author.name
            holder.shaTextView.text = commit.sha
            holder.messageTextView.text = commit.commit.message
        }
    }

    override fun getItemCount(): Int {
        return results.size
    }

    /**
     * Set result list and notify it.
     *
     * @param results: github commits result list
     */
    fun setResults(results: MutableList<GitHubCommit?>) {
        this.results = results
        notifyDataSetChanged()
    }

    /**
     * Clear result and notify it.
     */
    fun clearResult() {
        results.clear()
        notifyDataSetChanged()
    }

    /**
     * ViewHolder class for the [RecyclerView] in [GitHubCommitsAdapter].
     */
    inner class CommitsResultHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.commit_author)
        lateinit var authorTextView: TextView

        @BindView(R.id.commit_sha)
        lateinit var shaTextView: TextView

        @BindView(R.id.commit_message)
        lateinit var messageTextView: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }
}

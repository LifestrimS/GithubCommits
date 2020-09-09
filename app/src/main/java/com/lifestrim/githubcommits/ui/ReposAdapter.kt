package com.lifestrim.githubcommits.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.bold
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lifestrim.githubcommits.R
import com.lifestrim.githubcommits.data.entities.Repo
import kotlinx.android.synthetic.main.item_repo.view.*
import kotlin.collections.ArrayList

class ReposAdapter(private val listener: RepoItemListener) : RecyclerView.Adapter<RepoViewHolder>() {

    interface RepoItemListener {
        fun onClickedRepo(bundle: Bundle)
    }

    private val items = ArrayList<Repo>()

    fun setItems(items: ArrayList<Repo>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(items[position])
    }
}

class RepoViewHolder(private val view: View, private val listener: ReposAdapter.RepoItemListener) : RecyclerView.ViewHolder(view), View.OnClickListener {

    private lateinit var repo: Repo

    init {
        view.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: Repo) {
        this.repo = item

        /*view.tv_schedule_viewing.setOnClickListener {
            NotificationUtils().setDateTimeForNotification(view, item.title)
        }*/

        val repoName = SpannableStringBuilder()
            .append("Repository name: ")
            .bold { append(item.name) }

        val ownerName = SpannableStringBuilder()
            .append("Owner name: ")
            .bold { append(item.owner.login) }

        view.repo_name.text = repoName
        view.repo_owner_name.text = ownerName

        Glide.with(view)
            .load(item.owner.avatar_url)
            .into(view.owner_avatar)

    }

    override fun onClick(v: View?) {
        val bundle = Bundle()
        bundle.putInt("REPOID", repo.id)
        bundle.putString("RepoName", repo.name)
        bundle.putString("OwnerName", repo.owner.login)
        bundle.putString("OwnerAvatar", repo.owner.avatar_url)
        bundle.putString("RepoCommitURL", cutCommitURL(repo.commits_url))
        Log.d("TAG", "cutURL: ${cutCommitURL(repo.commits_url)}")
        listener.onClickedRepo(bundle)
    }

    fun cutCommitURL(fullURL: String): String {
        return fullURL.dropLast(6)
    }

}

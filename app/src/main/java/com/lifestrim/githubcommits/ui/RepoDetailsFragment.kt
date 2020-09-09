package com.lifestrim.githubcommits.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.lifestrim.githubcommits.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_repo_details.view.*
import kotlinx.android.synthetic.main.item_repo.view.repo_name
import kotlinx.android.synthetic.main.item_repo.view.repo_owner_name

@AndroidEntryPoint
class RepoDetailsFragment : Fragment() {

    private val viewModel: ReposViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_repo_details, container, false)

        view.repo_name.text = arguments?.getString("RepoName")
        view.repo_owner_name.text = arguments?.getString("OwnerName")
        view.repo_last_commit.text = arguments?.getString("RepoCommitURL")

        Glide.with(view)
            .load(arguments?.getString("OwnerAvatar"))
            .into(view.repo_owner_avatar)

        //viewModel = ViewModelProvider(this).get(ReposViewModel::class.java)

        val commitUrl = arguments?.getString("RepoCommitURL")
        //var commit = " "
        if (commitUrl != null) {
            //val commit = viewModel.getCommit(commitUrl)


            /*viewModel.repos.observe(viewLifecycleOwner, Observer {
                when (it.status) {
                    Resource.Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        if (!it.data.isNullOrEmpty()) commit = it.data.toString()
                    }
                    Resource.Status.ERROR ->
                        Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                    Resource.Status.LOADING ->
                        progress_bar.visibility = View.VISIBLE
                }
            })*/

            //Log.d("TAG", "CommitURL: $commitUrl \n commit: $commit.")
        }



        return view
    }

}
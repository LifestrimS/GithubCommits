package com.lifestrim.githubcommits.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lifestrim.githubcommits.R
import com.lifestrim.githubcommits.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.repos_fragment.*
import kotlinx.android.synthetic.main.repos_fragment.view.*

@AndroidEntryPoint
class ReposFragment : Fragment(), ReposAdapter.RepoItemListener {

    private val viewModel: ReposViewModel by viewModels()
    private lateinit var adapter: ReposAdapter

    lateinit var context: AppCompatActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context as AppCompatActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.repos_fragment, container, false)
        setupRecyclerView(view)
        setupObservers()
        return view
    }

    private fun setupRecyclerView(view: View) {
        adapter = ReposAdapter(this)
        view.movies_rv.layoutManager = LinearLayoutManager(activity)
        view.movies_rv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.repos.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    progress_bar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setItems(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    progress_bar.visibility = View.VISIBLE
            }
        })
    }


    override fun onClickedRepo(bundle: Bundle) {
        context.replaceFragment(RepoDetailsFragment(), bundle)
    }
}


package com.lifestrim.githubcommits.ui


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifestrim.githubcommits.data.RepoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReposViewModel @ViewModelInject constructor(
    repository: RepoRepository
) : ViewModel() {

    val repos = repository.getRepos()
}


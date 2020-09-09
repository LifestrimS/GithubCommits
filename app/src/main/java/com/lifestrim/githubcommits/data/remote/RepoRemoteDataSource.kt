package com.lifestrim.githubcommits.data.remote

import javax.inject.Inject

class RepoRemoteDataSource @Inject constructor(
    private val repoService: RepoService
) : BaseDataSource() {

    suspend fun getAllRepos() = getResult { repoService.getAllRepos() }

}
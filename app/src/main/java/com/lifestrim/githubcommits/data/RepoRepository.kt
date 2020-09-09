package com.lifestrim.githubcommits.data


import com.lifestrim.githubcommits.data.local.RepoDao
import com.lifestrim.githubcommits.data.remote.RepoRemoteDataSource
import com.lifestrim.githubcommits.util.performGetOperation
import javax.inject.Inject

class RepoRepository @Inject constructor(
    private val remoteDataSource: RepoRemoteDataSource,
    private val localDataSource: RepoDao
) {

    fun getRepos() = performGetOperation(
        databaseQuery = { localDataSource.getAllRepos() },
        networkCall = { remoteDataSource.getAllRepos() },
        saveCallResult = { localDataSource.insertAll(it) }
    )

}
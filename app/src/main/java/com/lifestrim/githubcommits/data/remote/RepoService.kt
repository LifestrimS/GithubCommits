package com.lifestrim.githubcommits.data.remote

import com.lifestrim.githubcommits.data.entities.Commit
import com.lifestrim.githubcommits.data.entities.Repo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RepoService {
    @GET("repositories")
    suspend fun getAllRepos(): Response<List<Repo>>

    @GET("{commitPath}")
    suspend fun getCommit( @Path("commitPath") commitPath: String): Response<Commit>
}
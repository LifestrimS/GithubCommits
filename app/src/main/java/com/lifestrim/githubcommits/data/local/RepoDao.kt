package com.lifestrim.githubcommits.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lifestrim.githubcommits.data.entities.Commit
import com.lifestrim.githubcommits.data.entities.Repo

@Dao
interface RepoDao {
    @Query("SELECT * FROM repositories")
    fun getAllRepos(): LiveData<List<Repo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(repo: List<Repo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repo: Repo)

    /*@Query("INSERT INTO commits VALUES (:commit)")
    suspend fun insertCommit(commit: Commit)*/


}
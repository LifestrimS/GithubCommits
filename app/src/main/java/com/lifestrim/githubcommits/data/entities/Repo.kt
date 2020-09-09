package com.lifestrim.githubcommits.data.entities

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repositories")
data class Repo(
    @PrimaryKey
    val id: Int,
    val name: String,
    @Embedded
    val owner: RepoOwner,
    val commits_url: String
)
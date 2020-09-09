package com.lifestrim.githubcommits.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "commits")
data class Commit (
    @PrimaryKey
    val node_id: String,

    //commit.message
    //val message: String
    val sha: String

    //commit.author.name
    //commit.author.date - dd.MM.YYYY
    //parents.sha
)
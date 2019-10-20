package com.example.android.swagger.network

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "artist_table")
data class Artist(
        @PrimaryKey(autoGenerate = true)
        var artistID: Long = 0L,

        val image: List<Image>,

        @ColumnInfo(name = "listeners")
        val listeners: String,

        @ColumnInfo(name = "listeners")
        val mbid: String,

        @ColumnInfo(name = "name")
        val name: String,

        @ColumnInfo(name = "playcount")
        val playcount: String,

        @ColumnInfo(name = "streamable")
        val streamable: String,

        @ColumnInfo(name = "url")
        val url: String


)
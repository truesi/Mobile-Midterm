package com.example.android.swagger.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.android.swagger.network.Artist

@Dao
interface DatabaseDao {
    @Insert
    fun insert(artist: Artist)

    @Update
    fun update(artist: Artist)

    //@Query("")
    //fun get(key: Long): Artist


}
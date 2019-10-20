package com.example.android.swagger.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.swagger.network.Artist

@Database(entities = [Artist::class], version = 1, exportSchema = false)
abstract class DataBase: RoomDatabase(){

    abstract val databaseDao: DatabaseDao

    companion object{
        @Volatile
        private var INSTANCE: DataBase? = null

        fun getInstance(context: Context): DataBase{
            synchronized(this){
                var instance = INSTANCE

                if(instance == null){
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            DataBase::class.java,
                            "artists_database"
                    )
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }

                return instance
            }
        }
    }
}
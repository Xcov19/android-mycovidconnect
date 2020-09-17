package com.ht117.yukute.data

import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ht117.yukute.data.dao.UserDao
import com.ht117.yukute.data.model.User

@androidx.room.Database(
    entities = [
        User::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateConverter::class)
abstract class Database : RoomDatabase() {

    abstract fun userDao(): UserDao
}
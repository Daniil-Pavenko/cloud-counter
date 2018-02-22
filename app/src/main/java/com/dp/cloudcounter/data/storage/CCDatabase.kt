package com.dp.cloudcounter.data.storage

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.dp.cloudcounter.data.entity.CounterEntity
import com.dp.cloudcounter.data.entity.dao.CounterDao

const val DB_NAME = "cc_database"

@Database(entities = [(CounterEntity::class)], version = 1)
abstract class CCDatabase : RoomDatabase() {

    abstract fun counterDao(): CounterDao
}
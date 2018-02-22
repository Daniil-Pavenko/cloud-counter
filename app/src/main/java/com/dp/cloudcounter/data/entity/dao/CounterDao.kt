package com.dp.cloudcounter.data.entity.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.dp.cloudcounter.data.entity.CounterEntity

@Dao
interface CounterDao {

    @Query("SELECT * FROM counter")
    fun getAllCounters(): List<CounterEntity>

    @Insert
    fun addCounter(counterEntity: CounterEntity)

    @Insert
    fun addCounters(countersEntity: List<CounterEntity>)

    @Delete
    fun deleteCounter(counter: CounterEntity)

    @Query("SELECT * FROM counter WHERE label LIKE :label LIMIT 1")
    fun hasCounter(label: String): CounterEntity?
}
package com.dp.cloudcounter.data.entity.dao

import android.arch.persistence.room.*
import com.dp.cloudcounter.data.entity.CounterEntity

@Dao
interface CounterDao {

    @Query("SELECT * FROM counterEntity")
    fun getAllCounters(): List<CounterEntity>

    @Insert
    fun addCounter(counterEntity: CounterEntity)

    @Insert
    fun addCounters(countersEntity: List<CounterEntity>)

    @Delete
    fun deleteCounter(counter: CounterEntity)

    @Query("SELECT * FROM counterEntity WHERE label LIKE :label LIMIT 1")
    fun hasCounter(label: String): CounterEntity?

    @Update
    fun updateCounter(counterEntity: CounterEntity)
}
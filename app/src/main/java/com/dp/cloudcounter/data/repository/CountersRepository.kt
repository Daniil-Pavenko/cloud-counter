package com.dp.cloudcounter.data.repository

import com.dp.cloudcounter.data.entity.CounterEntity

interface CountersRepository {

    fun fetchAllCounters(): List<CounterEntity>

    fun addCounter(counterEntity: CounterEntity)

    fun deleteCounter(counterEntity: CounterEntity)
}
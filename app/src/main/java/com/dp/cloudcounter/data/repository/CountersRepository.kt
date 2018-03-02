package com.dp.cloudcounter.data.repository

import com.dp.cloudcounter.data.entity.CounterEntity

interface CountersRepository {

    fun fetchAllCounters(callback: ((List<CounterEntity>) -> Unit)? = null)

    fun addCounter(counterEntity: CounterEntity)

    fun deleteCounter(counterEntity: CounterEntity)

    fun updateCounter(counterEntity: CounterEntity, callback: ((Boolean) -> Unit)? = null)
}
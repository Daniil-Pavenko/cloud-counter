package com.dp.cloudcounter.data.repository.datasource

import com.dp.cloudcounter.data.entity.CounterEntity

interface CountersDataSource {

    fun addCounter(counterEntity: CounterEntity, callback: ((Boolean, CounterEntity) -> Unit)?)

    fun deleteCounter(counterEntity: CounterEntity, callback: ((Boolean) -> Unit)?)

    fun fetchAllCounters(callback: ((Boolean, List<CounterEntity>) -> Unit)?): List<CounterEntity>

    fun saveAllCounters(counters: List<CounterEntity>, callback: ((Boolean) -> Unit)?)

    fun hasCounter(counterEntity: CounterEntity): Boolean
}
package com.dp.cloudcounter.data.repository.datasource

import com.dp.cloudcounter.data.entity.CounterEntity
import com.dp.cloudcounter.data.storage.CCDatabase

class LocalCountersDataSource(private val ccDatabase: CCDatabase) : CountersDataSource {

    override fun addCounter(counterEntity: CounterEntity, callback: ((Boolean, CounterEntity) -> Unit)?) {
        try {
            ccDatabase.counterDao().addCounter(counterEntity)
        } catch (e: Exception) {
            callback?.invoke(false, counterEntity)
        }
        callback?.invoke(true, counterEntity)
    }

    override fun deleteCounter(counterEntity: CounterEntity, callback: ((Boolean) -> Unit)?) {
        try {
            ccDatabase.counterDao().deleteCounter(counterEntity)
        } catch (e: Exception) {
            callback?.invoke(false)
        }
        callback?.invoke(true)
    }

    override fun fetchAllCounters(callback: ((Boolean, List<CounterEntity>) -> Unit)?): List<CounterEntity> {
        return try {
            val counters = ccDatabase.counterDao().getAllCounters()
            callback?.invoke(true, counters)
            counters
        } catch (e: Exception) {
            callback?.invoke(false, emptyList())
            return emptyList()
        }
    }

    override fun saveAllCounters(counters: List<CounterEntity>, callback: ((Boolean) -> Unit)?) {
        try {
            ccDatabase.counterDao().addCounters(counters)
        } catch (e: Exception) {
            callback?.invoke(false)
        }
        callback?.invoke(true)
    }

    override fun hasCounter(counterEntity: CounterEntity): Boolean {
        return try {
            ccDatabase.counterDao().hasCounter(counterEntity.label) != null
        } catch (e: Exception) {
            false
        }
    }
}
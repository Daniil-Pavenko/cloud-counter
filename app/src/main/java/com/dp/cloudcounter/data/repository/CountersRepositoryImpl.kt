package com.dp.cloudcounter.data.repository

import android.util.Log
import com.dp.cloudcounter.data.entity.CounterEntity
import com.dp.cloudcounter.data.repository.datasource.CountersDataSource
import com.dp.cloudcounter.domain.NetworkHelper

private const val TAG = "CountersRepository"

class CountersRepositoryImpl(private val localCountersDataSource: CountersDataSource,
                             private val remoteCountersDataSource: CountersDataSource,
                             private val networkHelper: NetworkHelper) : CountersRepository {

    override fun addCounter(counterEntity: CounterEntity) {
        if (networkHelper.hasInternet()) {
            remoteCountersDataSource.addCounter(counterEntity, { success, entity ->
                if (success) {
                    localCountersDataSource.addCounter(entity, null)
                } else {
                    Log.e(TAG, "addCounter into remote storage FAIL")
                }
            })
        } else {
            localCountersDataSource.addCounter(counterEntity, null)
        }
    }

    override fun deleteCounter(counterEntity: CounterEntity) {
        if (networkHelper.hasInternet()) {
            remoteCountersDataSource.deleteCounter(counterEntity, {
                if (it) {
                    localCountersDataSource.deleteCounter(counterEntity, null)
                } else {
                    Log.e(TAG, "deleteCounter from remote storage FAIL")
                }
            })
        } else {
            localCountersDataSource.deleteCounter(counterEntity, null)
        }
    }

    override fun fetchAllCounters(callback: ((List<CounterEntity>) -> Unit)?) {
        if (networkHelper.hasInternet()) {
            remoteCountersDataSource.fetchAllCounters { success, counters ->
                if (success) {
                    localCountersDataSource.saveAllCounters(counters, null)
                    callback?.invoke(counters)
                }
            }
        } else {
            callback?.invoke(localCountersDataSource.fetchAllCounters(null))
        }
    }

    override fun updateCounter(counterEntity: CounterEntity, callback: ((Boolean) -> Unit)?) {
        if (networkHelper.hasInternet()) {
            remoteCountersDataSource.updateCounter(counterEntity, { success, _ ->
                callback?.invoke(success)
            })
        } else {
            localCountersDataSource.updateCounter(counterEntity, { success, _ ->
                callback?.invoke(success)
            })
        }
    }
}
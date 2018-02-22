package com.dp.cloudcounter.data.repository

import android.util.Log
import com.dp.cloudcounter.data.entity.CounterEntity
import com.dp.cloudcounter.data.repository.datasource.LocalCountersDataSource
import com.dp.cloudcounter.data.repository.datasource.RemoteCountersDataSource
import com.dp.cloudcounter.domain.NetworkHelper

private const val TAG = "CountersRepository"

class CountersRepositoryImpl(private val localCountersDataSource: LocalCountersDataSource,
                             private val remoteCountersDataSource: RemoteCountersDataSource,
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

    override fun fetchAllCounters(): List<CounterEntity> {
        return if (networkHelper.hasInternet()) {
            remoteCountersDataSource.fetchAllCounters { success, counters ->
                if (success) {
                    localCountersDataSource.saveAllCounters(counters, null)
                }
            }
        } else {
            localCountersDataSource.fetchAllCounters(null)
        }
    }
}
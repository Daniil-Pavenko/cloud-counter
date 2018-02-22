package com.dp.cloudcounter.data.repository.datasource

import com.dp.cloudcounter.data.entity.CounterEntity
import com.google.firebase.database.DatabaseReference

class RemoteCountersDataSource(firebaseDatabase: DatabaseReference) : CountersDataSource {

    override fun addCounter(counterEntity: CounterEntity, callback: ((Boolean, CounterEntity) -> Unit)?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteCounter(counterEntity: CounterEntity, callback: ((Boolean) -> Unit)?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun fetchAllCounters(callback: ((Boolean, List<CounterEntity>) -> Unit)?): List<CounterEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveAllCounters(counters: List<CounterEntity>, callback: ((Boolean) -> Unit)?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hasCounter(counterEntity: CounterEntity): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
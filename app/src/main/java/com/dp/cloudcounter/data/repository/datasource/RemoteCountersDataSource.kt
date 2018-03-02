package com.dp.cloudcounter.data.repository.datasource

import com.dp.cloudcounter.data.entity.CounterEntity
import com.dp.cloudcounter.data.repository.annotation.Remote
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

private const val FB_COUNTER_DB = "counters_db"

@Remote
class RemoteCountersDataSource(private val firebaseDatabase: DatabaseReference) : CountersDataSource {

    override fun addCounter(counterEntity: CounterEntity, callback: ((Boolean, CounterEntity) -> Unit)?) {
        firebaseDatabase.child(FB_COUNTER_DB)
                .child(counterEntity.label)
                .setValue(counterEntity)
                .addOnCompleteListener { callback?.invoke(true, counterEntity) }
                .addOnFailureListener { callback?.invoke(false, counterEntity) }
    }

    override fun deleteCounter(counterEntity: CounterEntity, callback: ((Boolean) -> Unit)?) {
        firebaseDatabase.child(FB_COUNTER_DB)
                .child(counterEntity.label)
                .removeValue()
                .addOnCompleteListener { callback?.invoke(true) }
                .addOnFailureListener { callback?.invoke(false) }
    }

    override fun updateCounter(counterEntity: CounterEntity, callback: ((Boolean, CounterEntity) -> Unit)?) {
        firebaseDatabase.child(FB_COUNTER_DB)
                .child(counterEntity.label)
                .setValue(counterEntity)
                .addOnCompleteListener { callback?.invoke(true, counterEntity) }
                .addOnFailureListener { callback?.invoke(false, counterEntity) }
    }

    override fun fetchAllCounters(callback: ((Boolean, List<CounterEntity>) -> Unit)?): List<CounterEntity> {
        firebaseDatabase.child(FB_COUNTER_DB).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                callback?.invoke(false, emptyList())
            }

            override fun onDataChange(data: DataSnapshot) {
                if (data.exists()) {
                    val result = mutableListOf<CounterEntity>()
                    data.children.forEach { result.add(it.getValue(CounterEntity::class.java)!!) }
                    callback?.invoke(true, result)
                } else {
                    callback?.invoke(true, emptyList())
                }
            }

        })
        return emptyList()
    }

    override fun saveAllCounters(counters: List<CounterEntity>, callback: ((Boolean) -> Unit)?) {
        try {
            counters.forEach { addCounter(it, null) }
        } catch (e: Exception) {
            callback?.invoke(false)
            return
        }
        callback?.invoke(true)
    }

    override fun hasCounter(counterEntity: CounterEntity): Boolean = firebaseDatabase.child(FB_COUNTER_DB).child(counterEntity.label) != null
}
package com.dp.cloudcounter.domain.usecases

import com.dp.cloudcounter.data.entity.CounterEntity
import com.dp.cloudcounter.data.repository.CountersRepository
import com.dp.cloudcounter.data.repository.datasource.mapper.Mapper
import com.dp.cloudcounter.domain.Background
import com.dp.cloudcounter.domain.model.Counter
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class CounterInteractor(private val countersRepository: CountersRepository,
                        private val counterEntityToCounterMapper: Mapper<CounterEntity, Counter>,
                        private val counterToCounterEntityMapper: Mapper<Counter, CounterEntity>) {

    fun fetchCounters(callback: (List<Counter>) -> Unit) {
        launch(Background) {
            countersRepository.fetchAllCounters({
                val countersList = counterEntityToCounterMapper.map(it)
                launch(UI) { callback.invoke(countersList) }
            })
        }
    }

    fun addCounter(counterName: String, format: String = "pt", callback: ((Counter) -> Unit)? = null) {
        launch(Background) {
            val counterEntity = CounterEntity(counterName, format = format)
            countersRepository.addCounter(counterEntity)
            val counter = counterEntityToCounterMapper.map(counterEntity)
            launch(UI) { callback?.invoke(counter) }
        }
    }

    fun updateCounter(counter: Counter) {
        launch(Background) { countersRepository.updateCounter(counterToCounterEntityMapper.map(counter)) }
    }

    fun deleteCounter(counter: Counter) {
        launch(Background) { countersRepository.deleteCounter(counterToCounterEntityMapper.map(counter)) }
    }
}
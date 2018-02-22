package com.dp.cloudcounter.domain.usecases

import com.dp.cloudcounter.data.repository.CountersRepository
import com.dp.cloudcounter.domain.Background
import com.dp.cloudcounter.domain.model.Counter
import com.dp.cloudcounter.domain.usecases.mapper.CounterEntityToCounterMapper
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class CounterInteractor(private val countersRepository: CountersRepository,
                        private val counterEntityToCounterMapper: CounterEntityToCounterMapper) {

    fun fetchCounters(callback: (List<Counter>) -> Unit) {
        launch(Background) {
            val countersList = counterEntityToCounterMapper.map(countersRepository.fetchAllCounters())
            launch(UI) { callback.invoke(countersList) }
        }
    }
}
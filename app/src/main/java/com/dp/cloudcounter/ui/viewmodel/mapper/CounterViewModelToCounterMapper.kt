package com.dp.cloudcounter.ui.viewmodel.mapper

import com.dp.cloudcounter.data.repository.datasource.mapper.Mapper
import com.dp.cloudcounter.domain.model.Counter
import com.dp.cloudcounter.ui.viewmodel.CounterViewModel

class CounterViewModelToCounterMapper : Mapper<CounterViewModel, Counter>() {

    override fun map(from: CounterViewModel): Counter = Counter(
            from.label,
            from.value.toIntOrNull() ?: 0,
            from.format
    )
}
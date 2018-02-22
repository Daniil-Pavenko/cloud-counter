package com.dp.cloudcounter.domain.usecases.mapper

import com.dp.cloudcounter.data.entity.CounterEntity
import com.dp.cloudcounter.data.repository.datasource.mapper.Mapper
import com.dp.cloudcounter.domain.model.Counter

class CounterEntityToCounterMapper : Mapper<CounterEntity, Counter>() {

    override fun map(from: CounterEntity): Counter = Counter(
            from.label,
            from.value,
            from.format
    )
}
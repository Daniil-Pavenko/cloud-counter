package com.dp.cloudcounter.domain.usecases.mapper

import com.dp.cloudcounter.data.entity.CounterEntity
import com.dp.cloudcounter.data.repository.datasource.mapper.Mapper
import com.dp.cloudcounter.domain.model.Counter

class CounterToCounterEntityMapper : Mapper<Counter, CounterEntity>() {

    override fun map(from: Counter): CounterEntity = CounterEntity(
            from.label,
            from.value,
            from.format
    )
}
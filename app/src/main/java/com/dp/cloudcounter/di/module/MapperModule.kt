package com.dp.cloudcounter.di.module

import com.dp.cloudcounter.data.entity.CounterEntity
import com.dp.cloudcounter.data.repository.datasource.mapper.Mapper
import com.dp.cloudcounter.domain.model.Counter
import com.dp.cloudcounter.domain.usecases.mapper.CounterEntityToCounterMapper
import dagger.Module
import dagger.Provides

@Module
class MapperModule {

    @Provides
    fun provideCounterEntityToCounterMapper(): Mapper<CounterEntity, Counter> = CounterEntityToCounterMapper()
}
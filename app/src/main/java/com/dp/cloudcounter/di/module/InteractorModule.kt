package com.dp.cloudcounter.di.module

import com.dp.cloudcounter.data.entity.CounterEntity
import com.dp.cloudcounter.data.repository.CountersRepository
import com.dp.cloudcounter.data.repository.datasource.mapper.Mapper
import com.dp.cloudcounter.di.annotation.PresenterScope
import com.dp.cloudcounter.domain.model.Counter
import com.dp.cloudcounter.domain.usecases.CounterInteractor
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    @PresenterScope
    fun provideCounterInteractor(countersRepository: CountersRepository,
                                 counterEntityToCounterMapper: Mapper<CounterEntity, Counter>,
                                 counterToCounterEntityMapper: Mapper<Counter, CounterEntity>)
            : CounterInteractor = CounterInteractor(countersRepository, counterEntityToCounterMapper, counterToCounterEntityMapper)
}
package com.dp.cloudcounter.di.module

import com.dp.cloudcounter.data.repository.CountersRepository
import com.dp.cloudcounter.domain.usecases.CounterInteractor
import com.dp.cloudcounter.domain.usecases.mapper.CounterEntityToCounterMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [(RepositoryModule::class), (MapperModule::class)])
class InteractorModule {

    @Provides
    @Singleton
    fun provideCounterInteractor(countersRepository: CountersRepository,
                                 counterEntityToCounterMapper: CounterEntityToCounterMapper)
            : CounterInteractor = CounterInteractor(countersRepository, counterEntityToCounterMapper)
}
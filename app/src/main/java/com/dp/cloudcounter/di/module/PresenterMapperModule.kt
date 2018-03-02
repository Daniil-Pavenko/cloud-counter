package com.dp.cloudcounter.di.module

import com.dp.cloudcounter.data.repository.datasource.mapper.Mapper
import com.dp.cloudcounter.di.annotation.PresenterScope
import com.dp.cloudcounter.domain.model.Counter
import com.dp.cloudcounter.ui.viewmodel.CounterViewModel
import com.dp.cloudcounter.ui.viewmodel.mapper.CounterToViewModelMapper
import com.dp.cloudcounter.ui.viewmodel.mapper.CounterViewModelToCounterMapper
import dagger.Module
import dagger.Provides

@Module
class PresenterMapperModule {

    @Provides
    @PresenterScope
    fun provideCounterToViewModelMapper(): Mapper<Counter, CounterViewModel> = CounterToViewModelMapper()

    @Provides
    @PresenterScope
    fun provideCounterViewModelToCounterMapper(): Mapper<CounterViewModel, Counter> = CounterViewModelToCounterMapper()
}
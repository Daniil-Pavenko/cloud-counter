package com.dp.cloudcounter.ui.screen.home

import com.dp.cloudcounter.data.repository.datasource.mapper.Mapper
import com.dp.cloudcounter.di.component.PresenterComponent
import com.dp.cloudcounter.domain.model.Counter
import com.dp.cloudcounter.domain.usecases.CounterInteractor
import com.dp.cloudcounter.ui.screen.core.BasePresenter
import com.dp.cloudcounter.ui.screen.core.BaseScreen
import com.dp.cloudcounter.ui.viewmodel.CounterViewModel
import javax.inject.Inject

class HomePresenter(ui: HomePresenter.Screen, component: PresenterComponent) : BasePresenter<HomePresenter.Screen>(ui) {

    init {
        component.inject(this)
    }

    @Inject
    lateinit var counterInteractor: CounterInteractor
    @Inject
    lateinit var viewModelMapper: Mapper<Counter, CounterViewModel>
    @Inject
    lateinit var counterViewModelToCounterMapper: Mapper<CounterViewModel, Counter>

    fun loadCounters() {
        counterInteractor.fetchCounters { screen.provideCounters(viewModelMapper.map(it)) }
    }

    fun addCounter(counterName: String) {
        counterInteractor.addCounter(counterName, callback = {
            loadCounters()
        })
    }

    fun updateValueCounter(counterViewModel: CounterViewModel) {
        counterInteractor.updateCounter(counterViewModelToCounterMapper.map(counterViewModel))
    }

    fun deleteCounter(counterViewModel: CounterViewModel) {
        counterInteractor.deleteCounter(counterViewModelToCounterMapper.map(counterViewModel))
    }

    interface Screen : BaseScreen {

        fun provideCounters(counters: List<CounterViewModel>)
    }
}
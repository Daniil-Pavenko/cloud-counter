package com.dp.cloudcounter.ui.viewmodel.mapper

import com.dp.cloudcounter.data.repository.datasource.mapper.Mapper
import com.dp.cloudcounter.domain.model.Counter
import com.dp.cloudcounter.ui.viewmodel.CounterViewModel

class CounterToViewModelMapper : Mapper<Counter, CounterViewModel>() {

    override fun map(from: Counter): CounterViewModel = CounterViewModel(
            from.label,
            from.value.toString(),
            from.format
    )
}
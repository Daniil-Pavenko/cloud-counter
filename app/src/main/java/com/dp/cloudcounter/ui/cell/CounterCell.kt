package com.dp.cloudcounter.ui.cell

import android.databinding.DataBindingUtil
import android.view.View
import com.dp.cloudcounter.R
import com.dp.cloudcounter.databinding.CellCounterItemBinding
import com.dp.cloudcounter.ui.viewmodel.CounterViewModel
import io.techery.celladapter.Cell
import io.techery.celladapter.Layout

@Layout(R.layout.cell_counter_item)
class CounterCell(view: View) : Cell<CounterViewModel, CounterCell.Listener>(view) {

    private val binding: CellCounterItemBinding = DataBindingUtil.bind(view)

    fun counter(): CounterViewModel = item

    override fun syncUiWithItem() {
        binding.tvTitle.text = item.label
        binding.setOnMinusListener {
            item.minusValue()
            countUpdated()
        }
        binding.setOnPlusListener {
            item.plusValue()
            countUpdated()
        }
        countUpdated()
    }

    private fun countUpdated() {
        binding.tvCount.text = item.value
        listener?.onCountUpdate(item.intValue(), item)
    }

    interface Listener: Cell.Listener<CounterViewModel> {

        fun onCountUpdate(count: Int, counter: CounterViewModel)
    }
}
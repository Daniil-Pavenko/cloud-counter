package com.dp.cloudcounter.ui.screen.home.helper

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.dp.cloudcounter.ui.cell.CounterCell
import com.dp.cloudcounter.ui.viewmodel.CounterViewModel
import com.loopeer.itemtouchhelperextension.ItemTouchHelperExtension

class CounterItemTouchHelperCallback(private val swipedCallback: ((CounterViewModel) -> Unit)?) : ItemTouchHelperExtension.Callback() {

    override fun getMovementFlags(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?): Int = makeMovementFlags(0, ItemTouchHelper.START);

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        swipedCallback?.invoke((viewHolder as CounterCell).counter())
    }
}
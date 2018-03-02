package com.dp.cloudcounter.ui.screen.home

import android.databinding.DataBindingUtil
import android.support.v7.widget.DividerItemDecoration
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.afollestad.materialdialogs.MaterialDialog
import com.dp.cloudcounter.App
import com.dp.cloudcounter.R
import com.dp.cloudcounter.databinding.ControllerHomeBinding
import com.dp.cloudcounter.ui.adapter.CountersAdapter
import com.dp.cloudcounter.ui.cell.CounterCell
import com.dp.cloudcounter.ui.screen.core.BaseController
import com.dp.cloudcounter.ui.screen.home.helper.CounterItemTouchHelperCallback
import com.dp.cloudcounter.ui.viewmodel.CounterViewModel
import com.loopeer.itemtouchhelperextension.ItemTouchHelperExtension

class HomeController : BaseController<HomePresenter.Screen, HomePresenter>(), HomePresenter.Screen {

    override fun createPresenter(): HomePresenter = HomePresenter(this, App.presenterComponent)

    private lateinit var binding: ControllerHomeBinding
    private val adapter by lazy { CountersAdapter(activity) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.controller_home, container, false)
        return binding.root
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        setupAdapter()
        setupListener()
        presenter.loadCounters()
    }

    private fun setupAdapter() {
        adapter.registerCell(CounterViewModel::class.java, CounterCell::class.java, counterItemListener)

        binding.rvCounters.adapter = adapter
        binding.rvCounters.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))

        ItemTouchHelperExtension(CounterItemTouchHelperCallback({
            adapter.remove(it)
            presenter.deleteCounter(it)
        })).attachToRecyclerView(binding.rvCounters)
    }

    private fun setupListener() {
        binding.fabAddCounter.setOnClickListener { showAddNewCounterDialog() }
    }

    private fun showAddNewCounterDialog() {
        MaterialDialog.Builder(activity!!)
                .inputType(InputType.TYPE_TEXT_FLAG_CAP_SENTENCES)
                .input(R.string.home_screen_counter_name_dialog_title, 0, false, { _, _ -> })
                .positiveText(R.string.home_screen_add_label)
                .negativeText(R.string.home_screen_cancel_label)
                .onPositive { dialog, _ ->
                    dialog.dismiss()
                    presenter.addCounter(dialog.inputEditText?.text.toString())
                }
                .onNegative { dialog, _ -> dialog.dismiss() }
                .build()
                .show()
    }

    override fun provideCounters(counters: List<CounterViewModel>) {
        adapter.clear()
        adapter.addItems(counters)
    }

    private val counterItemListener = object : CounterCell.Listener {

        override fun onCountUpdate(count: Int, counter: CounterViewModel) {
            presenter.updateValueCounter(counter)
        }

        override fun onCellClicked(p0: CounterViewModel?) {
        }

    }
}
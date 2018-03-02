package com.dp.cloudcounter.ui.viewmodel

data class CounterViewModel(val label: String, var value: String, val format: String) {

    var step: Int = 1

    fun intValue(): Int = value.toIntOrNull() ?: 0

    fun minusValue() {
        value = (intValue() - step).toString()
    }

    fun plusValue() {
        value = (intValue() + step).toString()
    }

}
package com.dp.cloudcounter.data.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * This is model for work with repos
 *
 * label - Name of counter, entered by user
 * value - it is number of counted item
 * format - for example km or cm or pts, atc.
 */
@Entity
data class CounterEntity(
        @PrimaryKey @ColumnInfo(name = "label") var label: String = "",
        @ColumnInfo(name = "value") var value: Float = 0f,
        @ColumnInfo(name = "format") var format: String = "")
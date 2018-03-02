package com.dp.cloudcounter.domain.model

/**
 * This is Business Logic Model
 *
 * label - Name of counter
 * value - it is number of counted item
 * format - for example km or cm or pts, atc.
 */
data class Counter(val label: String, val value: Int, val format: String)
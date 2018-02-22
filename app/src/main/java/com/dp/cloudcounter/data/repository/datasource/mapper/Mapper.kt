package com.dp.cloudcounter.data.repository.datasource.mapper

abstract class Mapper<SOURCE, RESULT> {

    abstract fun map(from: SOURCE): RESULT

    fun map(listFrom: List<SOURCE>): List<RESULT> {
        val resultList = mutableListOf<RESULT>()
        listFrom.forEach { resultList.add(map(it)) }
        return resultList
    }
}
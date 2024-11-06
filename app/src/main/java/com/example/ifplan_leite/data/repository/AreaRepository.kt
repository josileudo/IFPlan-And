package com.example.ifplan_leite.data.repository

import com.example.ifplan_leite.data.dao.AreaDao
import com.example.ifplan_leite.data.entities.Area
import javax.inject.Inject

class AreaRepository @Inject constructor(
    private val areaDao: AreaDao
) {
    fun getArea() = areaDao.getArea()

    suspend fun saveArea(
        area: Double,
        picketsNumber: Double,
    ) {
        val areaValue = Area(
            area = area,
            picketsNumber = picketsNumber,
        )

        areaDao.insertOrUpdate(areaValue)
    }

    suspend fun clearArea() = areaDao.deleteArea()
}

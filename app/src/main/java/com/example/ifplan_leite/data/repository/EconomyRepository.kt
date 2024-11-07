package com.example.ifplan_leite.data.repository

import com.example.ifplan_leite.data.dao.EconomyDao
import com.example.ifplan_leite.data.entities.Economy
import javax.inject.Inject

class EconomyRepository @Inject constructor(
    private val economyDao: EconomyDao
) {
    fun getEconomy() = economyDao.getEconomy()

    suspend fun saveEconomy(
        investmentsPerLiters: Double,
        familyIncome: Double,
        depreciationRate: Double,
    ) {
        val economyValue = Economy(
            investmentsPerLiters = investmentsPerLiters,
            familyIncome = familyIncome,
            depreciationRate = depreciationRate,
        )

        economyDao.insertOrUpdate(economyValue)
    }

    suspend fun clearEconomy() = economyDao.deleteEconomy()
}

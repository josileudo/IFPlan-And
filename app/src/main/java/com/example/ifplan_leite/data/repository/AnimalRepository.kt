package com.example.ifplan_leite.data.repository

import com.example.ifplan_leite.data.dao.AnimalDao
import com.example.ifplan_leite.data.entities.Animal
import javax.inject.Inject

class AnimalRepository @Inject constructor(
    private val animalDao: AnimalDao
) {
    fun getAnimal() = animalDao.getAnimal()

    suspend fun saveAnimal(
        pesoCorporal: Double,
        milkProduction: Double,
        milkFatContent: Double,
        pbFatMilk: Double,
        horizontalShift: Double,
        verticalShift: Double,
        lactatingCows: Double
    ) {
        val animal = Animal(
            pesoCorporal = pesoCorporal,
            milkProduction = milkProduction,
            milkFatContent = milkFatContent,
            pbFatMilk = pbFatMilk,
            horizontalShift = horizontalShift,
            verticalShift = verticalShift,
            lactatingCows = lactatingCows
        )
        animalDao.insertOrUpdate(animal)
    }

    suspend fun clearAnimal() = animalDao.deleteAnimal()
}

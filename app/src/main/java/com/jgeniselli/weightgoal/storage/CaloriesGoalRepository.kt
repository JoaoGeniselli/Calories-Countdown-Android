package com.jgeniselli.weightgoal.storage

class CaloriesGoalRepository {

    private var remainingKilograms = 65400
    private var basalMetabolicRate = 1947

    suspend fun getRemainingCalories(): Int {
        return remainingKilograms
    }

    suspend fun getBasalMetabolicRate(): Int {
        return basalMetabolicRate
    }

    suspend fun subtract(feedingCalories: Int, physicalActivitiesCalories: Int): Int {
        val totalToSubtract = calculateSubtraction(feedingCalories, physicalActivitiesCalories)
        remainingKilograms -= totalToSubtract
        return remainingKilograms
    }

    fun calculateSubtraction(feedingCalories: Int, physicalActivitiesCalories: Int): Int {
        return basalMetabolicRate - feedingCalories + physicalActivitiesCalories
    }


}
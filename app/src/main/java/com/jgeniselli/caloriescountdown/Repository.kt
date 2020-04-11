package com.jgeniselli.caloriescountdown

class Repository {

    private var remainingCalories = 65400
    private var basalMetabolicRate = 1947

    suspend fun getRemainingCalories(): Int {
        return remainingCalories
    }

    suspend fun getBasalMetabolicRate(): Int {
        return basalMetabolicRate
    }

    suspend fun subtract(feedingCalories: Int, physicalActivitiesCalories: Int): Int {
        val totalToSubtract = calculateSubtraction(feedingCalories, physicalActivitiesCalories)
        remainingCalories -= totalToSubtract
        return remainingCalories
    }

    fun calculateSubtraction(feedingCalories: Int, physicalActivitiesCalories: Int): Int {
        return basalMetabolicRate - feedingCalories + physicalActivitiesCalories
    }


}
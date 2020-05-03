package com.jgeniselli.weightgoal.storage

class Repository {
    private var remainingKilograms = 11.5

    suspend fun getRemainingKilograms(): Double {
        return remainingKilograms
    }
}
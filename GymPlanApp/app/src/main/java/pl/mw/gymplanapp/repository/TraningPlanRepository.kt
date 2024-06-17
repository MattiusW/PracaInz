package pl.mw.gymplanapp.repository

import android.content.Context
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pl.mw.gymplanapp.model.TrainingPlan
import pl.mw.gymplanapp.room.DatabaseInstance

class TraningPlanRepository(context: Context) {

    private val trainingPlanDao = DatabaseInstance.getInstance(context).TrainingPlanDao()

    // Dodanie planu treningowego do bazy danych
    suspend fun insertTrainingPlan(trainingPlan: TrainingPlan) {
        trainingPlanDao.insertTrainingPlan(trainingPlan)
    }

    // Edycja planu treningowego w bazie danych
    suspend fun updateTrainingPlan(trainingPlan: TrainingPlan) {
        trainingPlanDao.updateTrainingPlan(trainingPlan)
    }

    // Usuniecie planu badz planow treningowych z bazy danych
    suspend fun deleteTrainingPlan(trainingPlans: List<TrainingPlan>) {
        trainingPlanDao.deleteTrainingPlans(trainingPlans)
    }

    // Pokazanie wszystkich planow
    fun getAllTrainingPlans() = trainingPlanDao.getAllTrainingPlans()


}
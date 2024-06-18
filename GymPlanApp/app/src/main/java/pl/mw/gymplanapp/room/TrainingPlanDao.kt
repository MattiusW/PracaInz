package pl.mw.gymplanapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pl.mw.gymplanapp.model.Exercise
import pl.mw.gymplanapp.model.TrainingPlan

@Dao
interface TrainingPlanDao {

    // Dodanie planu treningowego do bazy danych
    @Insert
    suspend fun insertTrainingPlan(trainingPlan: TrainingPlan): Long

    // Edycja planu treningowego w bazie danych
    @Update
    suspend fun updateTrainingPlan(trainingPlan: TrainingPlan)

    // Usuniecie planu badz planow treningowych z bazy danych
    @Delete
    suspend fun deleteTrainingPlans(trainingPlans: List<TrainingPlan>)

    // Pokazanie wszystkich planow
    @Query("SELECT * FROM training_plans_table ORDER BY planId DESC")
    fun getAllTrainingPlans(): Flow<List<TrainingPlan>>

}
package pl.mw.gymplanapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pl.mw.gymplanapp.model.TrainingPlan

@Dao
interface TrainingPlanDao {

    // Dodanie planu treningowego do bazy danych
    @Insert
    fun insertTrainingPlan(trainingPlan: TrainingPlan)

    // Edycja planu treningowego w bazie danych
    @Update
    fun updateTrainingPlan(trainingPlan: TrainingPlan)

    // Usuniecie planu badz planow treningowych z bazy danych
    @Delete
    fun deleteTrainingPlan(trainingPlans: List<TrainingPlan>)

    // Pokazanie wszystkich planow
    @Query("SELECT * FROM training_plans_table ORDER BY id DESC")
    fun getAllTrainingPlans(): Flow<List<TrainingPlan>>
}
package pl.mw.gymplanapp.room

import android.adservices.adid.AdId
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pl.mw.gymplanapp.model.Exercise
import pl.mw.gymplanapp.model.ProgressResult

@Dao
interface ExcercisesDao {

    // Dodanie cwiczenia do bazy dancyh
    @Insert
    suspend fun insertExercises(exercise: Exercise)

    // Edycja cwiczenia w bazie danych
    @Update
    suspend fun updateExercises(exercise: Exercise)

    // Usuniecie cwiczen lub cwiczenia z bazy danych
    @Delete
    suspend fun deleteExercises(exercises: List<Exercise>)

    // Wyswietlenie listy wszystkich cwiczen
    @Query("SELECT * FROM exercises_table ORDER BY exerciseId DESC")
    fun getAllExercises(): Flow<List<Exercise>>

    // Najwieksza waga cwiczenia
    @Query("SELECT category_exercise, MAX(weight_exercise) AS progress FROM exercises_table GROUP BY category_exercise")
    fun getProgressOfExercisesGroupByCategory(): Flow<List<ProgressResult>>

    @Query("SELECT * FROM exercises_table INNER JOIN training_plans_table ON trainingPlanId == planId WHERE trainingPlanId = :trainingPlanId")
    fun getExercisesByTrainingPlan(trainingPlanId: Long): Flow<List<Exercise>>

}
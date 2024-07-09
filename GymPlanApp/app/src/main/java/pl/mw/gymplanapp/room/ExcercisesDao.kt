package pl.mw.gymplanapp.room

import android.adservices.adid.AdId
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pl.mw.gymplanapp.model.Exercise

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

    // Zliczenie progresu cwiczen
    // TODO naprawic blad, dodac kalse enum z poszczegolnymi kategoriami
//    @Query("SELECT category_exercise, SUM(weight_exercise) AS progress FROM exercises_table")
//    fun getProgressOfExercisesGroupByCategory(): Flow<List<Exercise>>

    // TODO sprawdzic czy dziala i czy wszystko sie dobrze ze soba laczy
    @Query("SELECT * FROM exercises_table INNER JOIN training_plans_table ON trainingPlanId == planId WHERE trainingPlanId = :trainingPlanId")
    fun getExercisesByTrainingPlan(trainingPlanId: Long): Flow<List<Exercise>>
}
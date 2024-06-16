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
    fun insertExercises(exercise: Exercise)

    // Edycja cwiczenia w bazie danych
    @Update
    fun updateExercises(exercise: Exercise)

    // Usuniecie cwiczen lub cwiczenia z bazy danych
    @Delete
    fun deleteExercises(exercises: List<Exercise>)

    // Wyswietlenie listy wszystkich cwiczen
    @Query("SELECT * FROM exercises_table ORDER BY id DESC")
    fun getAllExercises(): Flow<List<Exercise>>

    // Zliczenie progresu cwiczen
    @Query("SELECT category_exercise, SUM(amount_exercise * weight_exercise) as progress FROM exercises_table")
    fun getProgressOfExercisesGroupByCategory()

    // TODO sprawdzic czy dziala i czy wszystko sie dobrze ze soba laczy
    @Query("SELECT * FROM exercises_table WHERE trainingPlanId = :trainingPlanId ORDER By id DESC")
    fun getExercisesByTrainingPlan(trainingPlanId: Int): Flow<List<Exercise>>
}
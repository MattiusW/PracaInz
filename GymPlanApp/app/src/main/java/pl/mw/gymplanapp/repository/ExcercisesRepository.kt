package pl.mw.gymplanapp.repository

import android.content.Context
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import pl.mw.gymplanapp.model.Exercise
import pl.mw.gymplanapp.model.TrainingPlan
import pl.mw.gymplanapp.room.DatabaseInstance

class ExcercisesRepository(context: Context) {

    private val excercisesDao = DatabaseInstance.getInstance(context).ExcercisesDao()


    // Dodanie cwiczenia do bazy dancyh
    suspend fun insertExercises(exercise: Exercise) {
        excercisesDao.insertExercises(exercise)
    }

    // Edycja cwiczenia w bazie danych
    suspend fun updateExercises(exercise: Exercise) {
        excercisesDao.updateExercises(exercise)
    }

    // Usuniecie cwiczen lub cwiczenia z bazy danych
    suspend fun deleteExercises(exercises: List<Exercise>) {
        excercisesDao.deleteExercises(exercises)
    }

    // Wyswietlenie listy wszystkich cwiczen
    fun getAllExercises() = excercisesDao.getAllExercises()


    // TODO sprawdzic czy dziala i czy wszystko sie dobrze ze soba laczy
    fun getExercisesByTrainingPlan(trainingPlanId: Long) {
        excercisesDao.getExercisesByTrainingPlan(trainingPlanId)
    }

}
package pl.mw.gymplanapp

import android.adservices.adid.AdId
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import pl.mw.gymplanapp.model.Exercise
import pl.mw.gymplanapp.model.TrainingPlan
import pl.mw.gymplanapp.repository.ExcercisesRepository
import pl.mw.gymplanapp.repository.TraningPlanRepository

// View model niezbedny do przechowywania danych przed ich zniszczeniem
class MainViewModel(app: Application) : AndroidViewModel(app) {
    var isButtonVisible = true
    var isMenuVisible = true

    private var selectedTrainingPlan: TrainingPlan? = null
    private val trainingPlanRepo = TraningPlanRepository(app.applicationContext)
    private val excercisesRepo = ExcercisesRepository(app.applicationContext)

    private var selectedExercise: Exercise? = null

    // Training plan repository metody
    fun insertTrainingPlan(trainingPlan: TrainingPlan) =
        CoroutineScope(Dispatchers.IO).launch {
            trainingPlanRepo.insertTrainingPlan(trainingPlan)
        }

    fun updateTrainingPlan(trainingPlan: TrainingPlan) =
        CoroutineScope(Dispatchers.IO).launch {
            trainingPlanRepo.updateTrainingPlan(trainingPlan)
        }

    fun deleteTrainingPlan(trainingPlans: List<TrainingPlan>) =
        CoroutineScope(Dispatchers.IO).launch {
            trainingPlanRepo.deleteTrainingPlan(trainingPlans)
        }

    // Zamiana flow w live data oraz dodanie zamkniecia przeplywu danych kiedy uzytkownik wyjdzie z aplikacji
    fun getAllTrainingPlans() = trainingPlanRepo.getAllTrainingPlans().asLiveData(viewModelScope.coroutineContext)


    fun selectTrainingPlan(trainingPlan: TrainingPlan) {
        selectedTrainingPlan = trainingPlan
    }

    fun unselectTrainingPlan() {
        selectedTrainingPlan = null
    }

    fun getSelectedTrainingPlan() = selectedTrainingPlan

    // pobranie id planu
    fun getSelectedTrainingPlanId(): Long {
        return selectedTrainingPlan!!.planId
    }

    fun insertExercise(exercise: Exercise) =
        CoroutineScope(Dispatchers.IO).launch {
            excercisesRepo.insertExercises(exercise)
        }

    fun updateExercise(exercise: Exercise) =
        CoroutineScope(Dispatchers.IO).launch {
            excercisesRepo.updateExercises(exercise)
        }

    fun deleteExercise(exercises: List<Exercise>) =
        CoroutineScope(Dispatchers.IO).launch {
            excercisesRepo.deleteExercises(exercises)
        }

    // Zwracanie wszystkich cwiczen wedlug planu
    fun getAllExercisesByPlan(trainingPlanId: Long) =
        excercisesRepo.getExercisesByTrainingPlan(trainingPlanId).asLiveData(viewModelScope.coroutineContext)

    fun selectExercise(exercise: Exercise) {
        selectedExercise = exercise
    }

    fun unSelectExercise() {
        selectedExercise = null
    }

    fun getSelectedExercise() = selectedExercise

    // Pobranie ilosci ukonczonych cwiczen
//    fun getNumberOfDoneExercise(): Int{
//        return selectedExercise!!.done_exercise
//    }

    // Zwracanie progresu
    fun getProgressExercises() = excercisesRepo.getProgressOfExercises().asLiveData(viewModelScope.coroutineContext)

}
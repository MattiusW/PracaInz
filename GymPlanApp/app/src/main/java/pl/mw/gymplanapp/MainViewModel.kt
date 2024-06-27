package pl.mw.gymplanapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import pl.mw.gymplanapp.model.TrainingPlan
import pl.mw.gymplanapp.repository.ExcercisesRepository
import pl.mw.gymplanapp.repository.TraningPlanRepository

// View model niezbedny do przechowywania danych przed ich zniszczeniem
class MainViewModel(app: Application) : AndroidViewModel(app) {
    var isButtonVisible = true
    private var selectedTrainingPlan: TrainingPlan? = null
    private val trainingPlanRepo = TraningPlanRepository(app.applicationContext)
    private val excercisesRepo = ExcercisesRepository(app.applicationContext)

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

    // TODO stworzyc metody dla excercise repo



}
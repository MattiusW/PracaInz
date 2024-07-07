package pl.mw.gymplanapp

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import pl.mw.gymplanapp.model.Exercise
import pl.mw.gymplanapp.model.ExerciseCategory
import pl.mw.gymplanapp.model.TrainingPlan
import pl.mw.gymplanapp.room.ExcercisesDao
import pl.mw.gymplanapp.room.GymDatabase
import pl.mw.gymplanapp.room.TrainingPlanDao
import java.io.IOException
import kotlin.jvm.Throws

@RunWith(AndroidJUnit4::class)
class GymDatabaseUnitTest {

    private lateinit var db: GymDatabase
    private lateinit var trainingPlanDao: TrainingPlanDao
    private lateinit var excercisesDao: ExcercisesDao


    // Utworz instancje bazy danych w pamieci aby przetestowac czy dziala
    @Before
    fun createDataBaseInMemory() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, GymDatabase::class.java).build()
        trainingPlanDao = db.TrainingPlanDao()
        excercisesDao = db.ExcercisesDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDataBase() {
        db.close()
    }

    @Test
    @Throws(IOException::class)
    fun testInsertPlanToDataBase() = runBlocking {
        // Tworzenie planow
        val testPlan = TrainingPlan(name_training = "Testowy Plan", date = 1L)
        val testPlanTwo = TrainingPlan(name_training = "Testowy Plan Dwa", date = 1L)

        // Pobieranie ID planow
        val planIdjeden = trainingPlanDao.insertTrainingPlan(testPlan)
        val planIdDwa = trainingPlanDao.insertTrainingPlan(testPlanTwo)
        Log.d("planIdOne: ", planIdjeden.toString())
        Log.d("planIdTwo: ", planIdDwa.toString())


        val cwiczenie1doPlanuPierwszego = Exercise(
            name_exercise = "Wyciskanie sztangi na ławce płaskiej",
            amount_exercise = 3,
            weight_exercise = 60.0,
            category_exercise = ExerciseCategory.CHEST,
            trainingPlanId = planIdjeden
        )
        excercisesDao.insertExercises(cwiczenie1doPlanuPierwszego)

        val cwiczenie2doPlanuDrugiego = Exercise(
            name_exercise = "Przysiady ze sztangą",
            amount_exercise = 3,
            weight_exercise = 80.0,
            category_exercise = ExerciseCategory.LEGS,
            trainingPlanId = planIdDwa
        )
        excercisesDao.insertExercises(cwiczenie2doPlanuDrugiego)

        // Testowanie zwiekszenia id
        assert(planIdjeden == 1L)
        assert(planIdDwa == 2L)

    }
}
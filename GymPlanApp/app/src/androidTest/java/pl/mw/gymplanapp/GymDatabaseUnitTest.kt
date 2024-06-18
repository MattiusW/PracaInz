package pl.mw.gymplanapp

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import pl.mw.gymplanapp.model.Exercise
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

    // TODO naprawic testy, nie incrementuja ID oraz nie mozna dodac cwiczenia
    @Test
    @Throws(IOException::class)
    fun testInsertPlanToDataBase() = runBlocking {
        val testPlan = TrainingPlan(name_training = "Testowy Plan")
        val testPlanTwo = TrainingPlan(name_training = "Testowy Plan Dwa")
        trainingPlanDao.insertTrainingPlan(testPlan)
        trainingPlanDao.insertTrainingPlan(testPlanTwo)
        assert(testPlan.planId == 0L)
        assert(testPlanTwo.planId == 0L)

//        val cwiczenie1 = Exercise(
//        name_exercise = "Wyciskanie sztangi na ławce płaskiej",
//        amount_exercise = 3,
//        weight_exercise = 60.0,
//        category_exercise = "Klatka",
//        trainingPlanId = testPlan.planId
//    )
//
//        val cwiczenie2 = Exercise(
//            name_exercise = "Przysiady ze sztangą",
//            amount_exercise = 3,
//            weight_exercise = 80.0,
//            category_exercise = "Nogi",
//            trainingPlanId = testPlan.planId
//        )
//
//        excercisesDao.insertExercises(cwiczenie1)
//        excercisesDao.insertExercises(cwiczenie2)
    }
}
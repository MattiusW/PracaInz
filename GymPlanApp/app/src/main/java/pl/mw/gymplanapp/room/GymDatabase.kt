package pl.mw.gymplanapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.mw.gymplanapp.model.Exercise
import pl.mw.gymplanapp.model.TrainingPlan

@Database(entities = [TrainingPlan::class, Exercise::class], version = 1, exportSchema = false)
abstract class GymDatabase: RoomDatabase() {
    abstract fun TrainingPlanDao(): TrainingPlanDao
    abstract fun ExcercisesDao(): ExcercisesDao
}
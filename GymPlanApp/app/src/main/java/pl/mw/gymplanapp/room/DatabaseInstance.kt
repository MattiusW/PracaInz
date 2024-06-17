package pl.mw.gymplanapp.room

import android.content.Context
import androidx.room.Room

object DatabaseInstance {
    private var instance: GymDatabase? = null

    fun getInstance(context: Context): GymDatabase {
        if (instance == null) {
            synchronized(GymDatabase::class.java) {
                instance = roomBuild(context)
            }
        }

        return instance!!
    }

    private fun roomBuild(context: Context): GymDatabase =
        Room.databaseBuilder(context,
            GymDatabase::class.java,
            "app_gym_database")
            .fallbackToDestructiveMigration()
            .build()
}
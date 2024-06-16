package pl.mw.gymplanapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "training_plans_table")
data class TrainingPlan(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name_training: String
)

package pl.mw.gymplanapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "training_plans_table")
data class TrainingPlan(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("planId")
    var planId: Long = 0,
    val name_training: String,
    val date: Long
)

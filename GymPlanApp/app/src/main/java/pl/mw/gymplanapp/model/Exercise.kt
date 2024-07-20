package pl.mw.gymplanapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "exercises_table",
    foreignKeys = [ForeignKey(
        entity = TrainingPlan::class,
        parentColumns = ["planId"],
        childColumns = ["trainingPlanId"],
        onDelete = ForeignKey.CASCADE, // Jeżeli klucz w tabeli nadrzędnej zostanie usunięty to wszystkie powiązane rekordy z tabeli podrzędnej również
        onUpdate = ForeignKey.CASCADE
    )]
)

data class Exercise(
    @PrimaryKey(autoGenerate = true)
    var exerciseId: Long = 0,
    val name_exercise: String = "",
    val series_exercise: Int = 0,
    val amount_exercise: Int = 0,
    val weight_exercise: Double = 0.0,
    val category_exercise: ExerciseCategory,
    @ColumnInfo("trainingPlanId")
    var trainingPlanId: Long
)

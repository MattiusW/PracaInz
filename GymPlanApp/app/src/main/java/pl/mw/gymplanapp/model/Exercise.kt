package pl.mw.gymplanapp.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.math.BigDecimal


@Entity(tableName = "exercises_table",
    foreignKeys = [ForeignKey(
        entity = TrainingPlan::class,
        parentColumns = ["id"],
        childColumns = ["trainingPlanId"],
        onDelete = ForeignKey.CASCADE // Jeżeli klucz w tabeli nadrzędnej zostanie usunięty to wszystkie powiązane rekordy z tabeli podrzędnej również
    )]
    )
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name_exercise: String,
    val amount_exercise: Int,
    val weight_exercise: BigDecimal,
    val category_exercise: String,
    val trainingPlanId: Int
)

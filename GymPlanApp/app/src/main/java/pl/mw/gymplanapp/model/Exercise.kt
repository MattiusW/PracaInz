package pl.mw.gymplanapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal
@Entity(tableName = "exercises_table")
data class Exercise(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val name_exercise: String,
    val amount_exercise: Int,
    val weight_exercise: BigDecimal,
    val category_exercise: String
)

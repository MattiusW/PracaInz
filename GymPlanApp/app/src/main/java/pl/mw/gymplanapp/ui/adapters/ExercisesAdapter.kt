package pl.mw.gymplanapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import pl.mw.gymplanapp.buttons.OnMinusSeriesClickListener
import pl.mw.gymplanapp.buttons.OnPlusSeriesClickListener
import pl.mw.gymplanapp.databinding.ExerciseRowBinding
import pl.mw.gymplanapp.model.Exercise

class ExercisesAdapter(private val exercises: List<Exercise>,
                       private val onMinusSeriesClickListener: OnMinusSeriesClickListener,
                       private val onPlusSeriesClickListener: OnPlusSeriesClickListener,
                       private val onClick: (Exercise, Int) -> Unit): RecyclerView.Adapter<ExercisesAdapter.ExercisesViewHolder>() {

    inner class ExercisesViewHolder(binding: ExerciseRowBinding): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClick(exercises[adapterPosition], adapterPosition)
            }
        }

        val iconExercise = binding.exerciseIcon
        val nameExercise = binding.nameExercise
        val categoryExercise = binding.categoryExercise
        val amountExercise = binding.amountExercise
        val weightExercise = binding.weightExercise
        val amountButtonMinus: AppCompatButton = binding.amountButtonMinus
        val amountButtonPlus: AppCompatButton = binding.amountButtonPlus
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExercisesViewHolder {
        return ExercisesViewHolder(
            ExerciseRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return exercises.size
    }

    override fun onBindViewHolder(holder: ExercisesViewHolder, position: Int) {
        bindData(holder, position)
    }

    private fun bindData(holder: ExercisesViewHolder, position: Int) {

        //TODO("STWORZYC IKONKI, KTORE BEDA SIE ZMIENIALY W ZALEZNOSCI OD KATEGORI CWICZEN")

        holder.nameExercise.text = exercises[position].name_exercise.toString()
        holder.categoryExercise.text = exercises[position].category_exercise.name
        holder.amountExercise.text = exercises[position].amount_exercise.toString()
        holder.weightExercise.text = exercises[position].weight_exercise.toString()

        holder.amountButtonMinus.setOnClickListener {
            onMinusSeriesClickListener.onMinusSeriesClick(exercises[position])
        }

        holder.amountButtonPlus.setOnClickListener {
            onPlusSeriesClickListener.OnPlusSeriesClick(exercise = exercises[position])
        }

    }
}
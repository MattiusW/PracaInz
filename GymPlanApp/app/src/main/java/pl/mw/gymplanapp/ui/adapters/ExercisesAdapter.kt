package pl.mw.gymplanapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import pl.mw.gymplanapp.R
import pl.mw.gymplanapp.buttons.OnMinusDoneClickListener
import pl.mw.gymplanapp.buttons.OnMinusRepeatClickListener
import pl.mw.gymplanapp.buttons.OnMinusSeriesClickListener
import pl.mw.gymplanapp.buttons.OnMinusWeightClickListener
import pl.mw.gymplanapp.buttons.OnPlusDoneClickListener
import pl.mw.gymplanapp.buttons.OnPlusRepeatClickListener
import pl.mw.gymplanapp.buttons.OnPlusSeriesClickListener
import pl.mw.gymplanapp.buttons.OnPlusWeightClickListener
import pl.mw.gymplanapp.databinding.ExerciseRowBinding
import pl.mw.gymplanapp.model.Exercise
import pl.mw.gymplanapp.model.ExerciseCategory

class ExercisesAdapter(private val exercises: List<Exercise>,
                       private val onMinusSeriesClickListener: OnMinusSeriesClickListener,
                       private val onPlusSeriesClickListener: OnPlusSeriesClickListener,
                       private val onMinusRepeatClickListener: OnMinusRepeatClickListener,
                       private val onPlusRepeatClickListener: OnPlusRepeatClickListener,
                       private val onMinusWeightClickListener: OnMinusWeightClickListener,
                       private val onPlusWeightClickListener: OnPlusWeightClickListener,
                       private val onMinusDoneClickListener: OnMinusDoneClickListener,
                       private val onPlusDoneClickListener: OnPlusDoneClickListener,
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
        val seriesExercise = binding.seriesExercise
        val amountExercise = binding.amountExercise
        val weightExercise = binding.weightExercise
        val doneExercise = binding.doneExercise
        val seriesButtonMinus: AppCompatButton = binding.seriesButtonMinus
        val seriesButtonPlus: AppCompatButton = binding.seriesButtonPlus
        val amountButtonMinus: AppCompatButton = binding.amountButtonMinus
        val amountButtonPlus: AppCompatButton = binding.amountButtonPlus
        val weightButtonMinus: AppCompatButton = binding.weightButtonMinus
        val weightButtonPlus: AppCompatButton = binding.weightButtonPlus
        val doneButtonMinus: AppCompatButton = binding.doneButtonMinus
        val doneButtonPlus: AppCompatButton = binding.doneButtonPlus
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

        val typeIconResource = when(exercises[position].category_exercise) {
            ExerciseCategory.KLATKA -> R.drawable.klatka
            ExerciseCategory.RAMIONA -> R.drawable.icon_add
            ExerciseCategory.RECE -> R.drawable.reka
            ExerciseCategory.NOGI -> R.drawable.noga
            ExerciseCategory.PLECY -> R.drawable.icon_add
            ExerciseCategory.BRZUCH -> R.drawable.brzuch
            ExerciseCategory.INNE -> R.drawable.icon_add
        }

        holder.iconExercise.setImageResource(typeIconResource)
        holder.nameExercise.text = exercises[position].name_exercise.toString()
        holder.categoryExercise.text = exercises[position].category_exercise.name
        holder.seriesExercise.text = exercises[position].series_exercise.toString()
        holder.amountExercise.text = exercises[position].amount_exercise.toString()
        holder.weightExercise.text = exercises[position].weight_exercise.toString()
        holder.doneExercise.text = exercises[position].done_exercise.toString()

        holder.seriesButtonMinus.setOnClickListener {
            onMinusSeriesClickListener.onMinusSeriesClick(exercises[position])
        }

        holder.seriesButtonPlus.setOnClickListener {
            onPlusSeriesClickListener.onPlusSeriesClick(exercise = exercises[position])
        }

        holder.amountButtonMinus.setOnClickListener {
            onMinusRepeatClickListener.onMinusRepeatClick(exercises[position])
        }

        holder.amountButtonPlus.setOnClickListener {
            onPlusRepeatClickListener.onPlusRepeatClick(exercise = exercises[position])
        }

        // przeslanie cwiczenia do interfejsu
        holder.weightButtonMinus.setOnClickListener {
            onMinusWeightClickListener.onMinusWeightClick(exercise = exercises[position])
        }

        holder.weightButtonPlus.setOnClickListener {
            onPlusWeightClickListener.onPlusWeightClick(exercise = exercises[position])
        }

        holder.doneButtonMinus.setOnClickListener {
            onMinusDoneClickListener.onMinusDoneClick(exercise = exercises[position])
        }

        holder.doneButtonPlus.setOnClickListener {
            onPlusDoneClickListener.onPlusDoneClick(exercise = exercises[position])
        }
    }
}
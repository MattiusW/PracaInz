package pl.mw.gymplanapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.mw.gymplanapp.databinding.ExerciseRowBinding
import pl.mw.gymplanapp.model.Exercise

class ExercisesAdapter(private val exercises: List<Exercise>, private val onClick: (Exercise, Int) -> Unit): RecyclerView.Adapter<ExercisesAdapter.ExercisesViewHolder>() {
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
        TODO("Dokończyć implemenetacje ADAPTERA")
    }
}
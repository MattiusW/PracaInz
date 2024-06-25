package pl.mw.gymplanapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionScene.Transition.TransitionOnClick
import androidx.recyclerview.widget.RecyclerView
import pl.mw.gymplanapp.R
import pl.mw.gymplanapp.databinding.TreningPlanRowBinding
import pl.mw.gymplanapp.model.TrainingPlan
import java.text.SimpleDateFormat
import java.util.Date

class TrainingPlanAdapter(private val trainingPlans: List<TrainingPlan>, private val onClick: (TrainingPlan, Int) -> Unit): RecyclerView.Adapter<TrainingPlanAdapter.TrainingPlanViewHolder>() {

    inner class TrainingPlanViewHolder(binding: TreningPlanRowBinding): RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClick(trainingPlans[adapterPosition], adapterPosition)
            }
        }

        val iconPlan = binding.iconPlan
        val namePlan = binding.namePlan
        val datePlan = binding.datePlan
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingPlanViewHolder {
        return TrainingPlanViewHolder(
            TreningPlanRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return trainingPlans.size
    }

    override fun onBindViewHolder(holder: TrainingPlanViewHolder, position: Int) {
        bindData(holder, position)
    }

    private fun bindData(holder: TrainingPlanViewHolder, position: Int) {
        val sdf = SimpleDateFormat("dd-MM-yyyy")
        val date = Date(trainingPlans[position].date)
        val datePlaceholder = sdf.format(date)

        holder.namePlan.text = trainingPlans[position].name_training
        holder.datePlan.text = datePlaceholder
        holder.iconPlan.setImageResource(R.drawable.ic_plan)
    }

}
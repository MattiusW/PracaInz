package pl.mw.gymplanapp.room

import pl.mw.gymplanapp.model.TrainingPlan

interface OnEditPlanClickListener {
    fun onEditPlanClick(plan: TrainingPlan)
}
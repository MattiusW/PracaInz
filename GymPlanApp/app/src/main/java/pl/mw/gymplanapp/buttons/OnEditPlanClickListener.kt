package pl.mw.gymplanapp.buttons

import pl.mw.gymplanapp.model.TrainingPlan

interface OnEditPlanClickListener {
    fun onEditPlanClick(plan: TrainingPlan)
}
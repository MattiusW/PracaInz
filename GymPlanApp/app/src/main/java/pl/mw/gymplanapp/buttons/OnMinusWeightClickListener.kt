package pl.mw.gymplanapp.buttons

import pl.mw.gymplanapp.model.Exercise

interface OnMinusWeightClickListener {
    fun onMinusWeightClick(exercise: Exercise)
}
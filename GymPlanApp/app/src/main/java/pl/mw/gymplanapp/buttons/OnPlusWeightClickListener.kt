package pl.mw.gymplanapp.buttons

import pl.mw.gymplanapp.model.Exercise

interface OnPlusWeightClickListener {
    fun onPlusWeightClick(exercise: Exercise)
}
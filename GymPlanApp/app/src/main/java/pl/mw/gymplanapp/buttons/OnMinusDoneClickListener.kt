package pl.mw.gymplanapp.buttons

import pl.mw.gymplanapp.model.Exercise

interface OnMinusDoneClickListener {
    fun onMinusDoneClick(exercise: Exercise)
}
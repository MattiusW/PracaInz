package pl.mw.gymplanapp.buttons

import pl.mw.gymplanapp.model.Exercise

interface OnPlusDoneClickListener {
    fun onPlusDoneClick(exercise: Exercise)
}
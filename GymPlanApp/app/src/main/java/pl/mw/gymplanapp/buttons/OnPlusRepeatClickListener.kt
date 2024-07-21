package pl.mw.gymplanapp.buttons

import pl.mw.gymplanapp.model.Exercise

interface OnPlusRepeatClickListener {
    fun onPlusRepeatClick(exercise: Exercise)
}
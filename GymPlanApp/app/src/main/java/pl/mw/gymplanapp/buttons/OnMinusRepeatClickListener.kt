package pl.mw.gymplanapp.buttons

import pl.mw.gymplanapp.model.Exercise

interface OnMinusRepeatClickListener {
    fun onMinusRepeatClick(exercise: Exercise)
}
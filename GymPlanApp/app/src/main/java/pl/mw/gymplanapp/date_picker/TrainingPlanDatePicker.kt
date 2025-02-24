package pl.mw.gymplanapp.date_picker

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar

//class TrainingPlanDatePicker(private val onDateSetListener: (Int, Int, Int) -> Unit): DialogFragment(),
//    DatePickerDialog.OnDateSetListener {
//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val c = Calendar.getInstance()
//        val year = c.get(Calendar.YEAR)
//        val month = c.get(Calendar.MONTH)
//        val day = c.get(Calendar.DAY_OF_MONTH)
//
//        return  DatePickerDialog(requireContext(), this, year, month, day)
//    }
//    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
//        onDateSetListener(dayOfMonth, month, year)
//    }
//}

class TrainingPlanDatePicker(
    private var onDateSetListener: ((Int, Int, Int) -> Unit)? = null
) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireContext(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        onDateSetListener?.invoke(dayOfMonth, month, year)
    }

    companion object {
        fun newInstance(listener: (Int, Int, Int) -> Unit): TrainingPlanDatePicker {
            return TrainingPlanDatePicker(listener)
        }
    }
}
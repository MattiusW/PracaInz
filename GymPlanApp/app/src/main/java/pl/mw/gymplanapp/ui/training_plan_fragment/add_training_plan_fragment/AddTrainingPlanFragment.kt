package pl.mw.gymplanapp.ui.training_plan_fragment.add_training_plan_fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import pl.mw.gymplanapp.MainViewModel
import pl.mw.gymplanapp.R
import pl.mw.gymplanapp.databinding.FragmentAddTrainingPlanBinding
import pl.mw.gymplanapp.date_picker.TrainingPlanDatePicker
import pl.mw.gymplanapp.model.TrainingPlan
import java.util.Calendar

class AddTrainingPlanFragment : Fragment() {

    private val viewModel by viewModels<AddTrainingPlanViewModel>()
    private val mainVm by activityViewModels<MainViewModel>()
    private var _binding: FragmentAddTrainingPlanBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTrainingPlanBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calendarImage.setOnClickListener {
            showDatePickerDialog()
        }

        binding.savePlanBtn.setOnClickListener {
            val training_plan = createTrainingPlan()
            mainVm.insertTrainingPlan(training_plan)
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

    }

    private fun showDatePickerDialog() {
        val newDatePicker = TrainingPlanDatePicker { day, month, year ->
            binding.dayTv.text = day.toString()
            binding.monthTv.text = month.toString()
            binding.yearTv.text = year.toString()

            val date = Calendar.getInstance()
            date.set(day, month, year)
            viewModel.date = date.timeInMillis
        }

        newDatePicker.show(parentFragmentManager, "DatePicker")
    }

    private fun createTrainingPlan(): TrainingPlan {
        val namePlan = binding.enterPlanName.toString()

        return TrainingPlan(0, namePlan, viewModel.date)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
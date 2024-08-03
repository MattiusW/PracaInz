package pl.mw.gymplanapp.ui.training_plan_fragment.edit_training_plan_fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import pl.mw.gymplanapp.MainActivity
import pl.mw.gymplanapp.MainViewModel
import pl.mw.gymplanapp.R
import pl.mw.gymplanapp.databinding.FragmentEditTrainingPlanBinding
import pl.mw.gymplanapp.date_picker.TrainingPlanDatePicker
import pl.mw.gymplanapp.model.TrainingPlan
import java.text.SimpleDateFormat
import java.util.Calendar

class EditTrainingPlanFragment : Fragment() {

    private val viewModel by viewModels<EditTrainingPlanViewModel>()
    private val mainVm by activityViewModels<MainViewModel>()
    private var _binding: FragmentEditTrainingPlanBinding? = null
    private val binding get() = _binding!!

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            (requireActivity() as MainActivity).setButtonVisibility(buttonVisible = true, menuVisible = true)
            isEnabled = false
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditTrainingPlanBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleOnBackPressed()
        setTrainingPlanData(mainVm.getSelectedTrainingPlan()!!)
        setupOnClicks()
    }

    private fun handleOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
    }

    private fun setupOnClicks() {
        binding.calendarImage.setOnClickListener {
            showDatePickerDialog()
        }
        binding.deletePlanBtn.setOnClickListener {
            mainVm.getSelectedTrainingPlan()?.let { plans ->
                mainVm.deleteTrainingPlan(listOf(plans))
                mainVm.unselectTrainingPlan()
            }
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        binding.savePlanBtn.setOnClickListener {
            updateTrainingPlan()
        }
    }

    private fun updateTrainingPlan() {
        val updateTraining = createTrainingPlan()
        if (updateTraining != null) {
            mainVm.updateTrainingPlan(updateTraining)
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun createTrainingPlan(): TrainingPlan? {
        val namePlan = binding.enterPlanName.text.toString()

        if (namePlan.isEmpty() || namePlan.length > 100) {
            binding.enterPlanName.error = "Wprowadź od 1 do 100 znaków"
            Toast.makeText(context, "Podaj poprawną nazwę planu treningowego", Toast.LENGTH_SHORT).show()
            return null
        }

        return TrainingPlan(mainVm.getSelectedTrainingPlan()!!.planId, namePlan, viewModel.date)
    }


    private fun setTrainingPlanData(plans: TrainingPlan) {
        setCurrentPlanName(plans.name_training)
        setCurrentDate(plans.date)
    }

    private fun showDatePickerDialog() {
        val newDatePicker = TrainingPlanDatePicker { day, month, year ->
            val dayPlaceholder = if (day < 10) "0$day" else "$day"
            binding.dayTv.text = dayPlaceholder
            val monthPlaceHolder = if (month + 1 < 10) "0${month+1}" else "${month+1}"
            binding.monthTv.text = monthPlaceHolder
            binding.yearTv.text = year.toString()

            val date = Calendar.getInstance()
            date.set(year, month, day)
            viewModel.date = date.timeInMillis
        }

        newDatePicker.show(parentFragmentManager, "DatePicker")
    }

    private fun setCurrentDate(date: Long) {
        viewModel.date = date
        val sdf = SimpleDateFormat("dd-MM-yyyy")
        val datePlaceholder = sdf.format(date)
        val list = datePlaceholder.split("-")

        binding.dayTv.text = list[0]
        binding.monthTv.text = list[1]
        binding.yearTv.text = list[2]

    }

    private fun setCurrentPlanName(nameTraining: String) {
        binding.enterPlanName.setText(nameTraining.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
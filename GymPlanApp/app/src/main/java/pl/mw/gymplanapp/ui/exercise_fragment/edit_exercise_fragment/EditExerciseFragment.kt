package pl.mw.gymplanapp.ui.exercise_fragment.edit_exercise_fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.activityViewModels
import pl.mw.gymplanapp.MainViewModel
import pl.mw.gymplanapp.R
import pl.mw.gymplanapp.databinding.FragmentEditExerciseBinding
import pl.mw.gymplanapp.model.Exercise
import pl.mw.gymplanapp.model.ExerciseCategory
import java.math.BigDecimal
import java.math.RoundingMode

class EditExerciseFragment : Fragment() {

    private val viewModel by viewModels<EditExerciseViewModel>()
    private val mainVm by activityViewModels<MainViewModel>()
    private var _binding: FragmentEditExerciseBinding? = null
    private val binding get() = _binding!!
    private lateinit var categoryExerciseSpinner: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditExerciseBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryExerciseSpinner = view.findViewById(R.id.categoryExerciseSpinner)
        setExerciseData(mainVm.getSelectedExercise()!!)
        setupOnClicks()
    }

    private fun setExerciseData(exercise: Exercise) {
        setCurrentName(exercise.name_exercise)
        setCurrentAmount(exercise.amount_exercise)
        setCurrentWeight(exercise.weight_exercise)
        setCurrentCategory(exercise.category_exercise)
        setupOnClicks()
    }

    private fun setupOnClicks() {
        binding.saveExerciseBtn.setOnClickListener {
            updateExercise()
        }
        binding.deleteExerciseBtn.setOnClickListener {
            mainVm.getSelectedExercise()?.let { exercise ->
                mainVm.deleteExercise(listOf(exercise))
                mainVm.unSelectExercise()
            }
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun updateExercise() {
        val updateExe = createExercise()
        mainVm.updateExercise(updateExe)
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    private fun setCurrentName(nameExercise: String) {
        binding.enterExerciseName.setText(nameExercise)
    }

    private fun setCurrentCategory(categoryExercise: ExerciseCategory) {
        val categories = resources.getStringArray(R.array.category_exercise)
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            categories
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categoryExerciseSpinner.adapter = adapter

        val position = adapter.getPosition(categoryExercise.name)
        binding.categoryExerciseSpinner.setSelection(position)

    }

    private fun setCurrentWeight(weightExercise: Double) {
        binding.enterWeight.setText(weightExercise.toString())
    }

    private fun setCurrentAmount(amountExercise: Int) {
        binding.enterAmountSeries.setText(amountExercise.toString())
    }

    private fun createExercise(): Exercise {
        //  Stworzenie logiki wybierania kategorii (CHEST, SHOULDERS, ARMS, LEGS, BACK, ABS, OTHERS)
        val type = when(binding.categoryExerciseSpinner.selectedItem.toString()){
            "KLATKA" -> ExerciseCategory.KLATKA
            "RAMIONA" -> ExerciseCategory.RAMIONA
            "RECE" -> ExerciseCategory.RECE
            "NOGI" -> ExerciseCategory.NOGI
            "PLECY" -> ExerciseCategory.PLECY
            "BRZUCH" -> ExerciseCategory.BRZUCH
            else -> ExerciseCategory.INNE
        }

        val exerciseName = binding.enterExerciseName.text.toString()
        val series = binding.enterAmountSeries.text.toString()
        val weight = binding.enterWeight.text.toString()
        val planId = mainVm.getSelectedTrainingPlanId()

        val weightBD = BigDecimal.valueOf(weight.toDouble()).setScale(2, RoundingMode.HALF_UP)

        return Exercise(mainVm.getSelectedExercise()!!.exerciseId, exerciseName, series.toInt(), weightBD.toDouble(), type, planId)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
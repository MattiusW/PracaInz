package pl.mw.gymplanapp.ui.exercise_fragment.add_exercise_fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import pl.mw.gymplanapp.MainViewModel
import pl.mw.gymplanapp.databinding.FragmentAddExerciseBinding
import pl.mw.gymplanapp.model.Exercise
import pl.mw.gymplanapp.model.ExerciseCategory
import java.math.BigDecimal
import java.math.RoundingMode

class AddExerciseFragment : Fragment() {

    private val viewModel by viewModels<AddExerciseViewModel>()
    private val mainVm by activityViewModels<MainViewModel>()
    private var _binding: FragmentAddExerciseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddExerciseBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveExerciseBtn.setOnClickListener {
            val exercise_add = createExercise()
            if(exercise_add != null) {
                mainVm.insertExercise(exercise_add)
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }

    }

    private fun createExercise(): Exercise? {
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
        var series = binding.enterAmountSeries.text.toString()
        val weight = binding.enterWeight.text.toString()
        val planId = mainVm.getSelectedTrainingPlanId()
        var weightBD: BigDecimal = BigDecimal.ZERO;
        val weightScale: BigDecimal = BigDecimal(999.00)

        // Walidacja danych nazwy planu
        if (exerciseName.isEmpty() || exerciseName.length > 70) {
            binding.enterExerciseName.error = "Wprowadź od 1 do 70 znaków"
            Toast.makeText(context, "Podaj poprawną nazwę ćwiczenia", Toast.LENGTH_SHORT).show()
            return null
        }

        // Walidacja maksymalnej liczby serii
        if (series.isEmpty() || series.toInt() > 99) {
            binding.enterAmountSeries.error = "Wprowadź od 0 do 99 serii"
            Toast.makeText(context, "Podaj prawidłową liczbę serii", Toast.LENGTH_LONG).show()
            return null
        }

        if(weight.isEmpty() || weight.length > 6) {
            binding.enterWeight.error = "Podaj liczbę do 6 znaków"
            Toast.makeText(context, "Podaj prawidłowe obciążenie \nPrzykład 120.50", Toast.LENGTH_LONG).show()
            return null
        }

        // Chwilowa konwercja aby ustawic dwa miejsca po przecinku zabezpieczenie przed input for string ""
        if (!TextUtils.isEmpty(weight)) {
            weightBD = BigDecimal.valueOf(weight.toDouble()).setScale(2, RoundingMode.HALF_UP)
            if(weightBD > weightScale) {
                binding.enterWeight.error = "Maksymalne obciażenie to 999.0"
                return null
            }
        }

        return Exercise(0, exerciseName, series.toInt(), weightBD.toDouble() , type, planId)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
package pl.mw.gymplanapp.ui.exercise_fragment.add_exercise_fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import pl.mw.gymplanapp.MainViewModel
import pl.mw.gymplanapp.R
import pl.mw.gymplanapp.databinding.FragmentAddExerciseBinding
import pl.mw.gymplanapp.databinding.FragmentExercisesBinding
import pl.mw.gymplanapp.model.Exercise
import pl.mw.gymplanapp.model.ExerciseCategory

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
            createExercise()
        }
    }


    private fun createExercise(): Exercise {
        //  Stworzenie logiki wybierania kategorii (CHEST, SHOULDERS, ARMS, LEGS, BACK, ABS, OTHERS)
        val type = when(binding.categoryExerciseRB.checkedRadioButtonId){
            binding.chestRB.id -> ExerciseCategory.CHEST
            binding.shouldersRB.id -> ExerciseCategory.SHOULDERS
            binding.armsRB.id -> ExerciseCategory.ARMS
            binding.legsRB.id -> ExerciseCategory.LEGS
            binding.backRB.id -> ExerciseCategory.BACK
            binding.absRB.id -> ExerciseCategory.ABS
            else -> ExerciseCategory.OTHERS
        }

        val exerciseName = binding.enterExerciseName.text.toString()
        val series = binding.enterAmountSeries.text.toString()
        val weight = binding.enterWeight.text.toString()
        val planId = mainVm.getSelectedTrainingPlanId()

        return Exercise(0, exerciseName, series.toInt(), weight.toDouble(), type, planId)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
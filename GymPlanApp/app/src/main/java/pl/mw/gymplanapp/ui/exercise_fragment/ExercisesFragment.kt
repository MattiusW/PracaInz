package pl.mw.gymplanapp.ui.exercise_fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import pl.mw.gymplanapp.MainActivity
import pl.mw.gymplanapp.MainViewModel
import pl.mw.gymplanapp.R
import pl.mw.gymplanapp.buttons.OnMinusSeriesClickListener
import pl.mw.gymplanapp.buttons.OnPlusSeriesClickListener
import pl.mw.gymplanapp.databinding.FragmentExercisesBinding
import pl.mw.gymplanapp.model.Exercise
import pl.mw.gymplanapp.ui.adapters.ExercisesAdapter

class ExercisesFragment : Fragment(), OnMinusSeriesClickListener, OnPlusSeriesClickListener {

    private val exercisesViewModel by viewModels<ExercisesViewModel>()
    private val mainVm by activityViewModels<MainViewModel>()
    private var _binding: FragmentExercisesBinding? = null
    private val binding get() = _binding!!

    // Logika odpowiadajaca za ponowne pojawienie sie przycisku
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
        _binding = FragmentExercisesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleOnBackPressed()

        binding.exerciseRecylerView.layoutManager = LinearLayoutManager(requireContext())

        mainVm.getSelectedTrainingPlan()?.let { trainingPlan ->
            mainVm.getAllExercisesByPlan(trainingPlan.planId).observe(viewLifecycleOwner) {
                exercises ->
                binding.exerciseRecylerView.adapter = ExercisesAdapter(exercises,this, this) {
                    exercises, position ->
                    mainVm.selectExercise(exercises)
                    findNavController().navigate(R.id.editExerciseFragment)
                }
            }
        }

        binding.addExercise.setOnClickListener {
            findNavController().navigate(R.id.addExerciseFragment)
        }

    }

    private fun handleOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
    }

    // Zrobienie kopii encji cwiczenie oraz update zmniejszenie pola o 1
    override fun onMinusSeriesClick(exercise: Exercise) {
        val updateMinusSeries = exercise.copy(amount_exercise = (exercise.amount_exercise - 1).coerceAtLeast(0))
        mainVm.updateExercise(updateMinusSeries)
    }

    override fun OnPlusSeriesClick(exercise: Exercise) {
        val updatePlusSeries = exercise.copy(amount_exercise = (exercise.amount_exercise + 1).coerceAtMost(99))
        mainVm.updateExercise(updatePlusSeries)
    }

}
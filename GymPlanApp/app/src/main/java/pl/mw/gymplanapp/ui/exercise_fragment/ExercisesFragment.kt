package pl.mw.gymplanapp.ui.exercise_fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import pl.mw.gymplanapp.MainViewModel
import pl.mw.gymplanapp.R
import pl.mw.gymplanapp.databinding.FragmentExercisesBinding
import pl.mw.gymplanapp.ui.adapters.ExercisesAdapter

class ExercisesFragment : Fragment() {

    private val exercisesViewModel by viewModels<ExercisesViewModel>()
    private val mainVm by activityViewModels<MainViewModel>()
    private var _binding: FragmentExercisesBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentExercisesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.exerciseRecylerView.layoutManager = LinearLayoutManager(requireContext())

        mainVm.getSelectedTrainingPlan()?.let { trainingPlan ->
            mainVm.getAllExercisesByPlan(trainingPlan.planId).observe(viewLifecycleOwner) {
                exercises ->
                binding.exerciseRecylerView.adapter = ExercisesAdapter(exercises) {
                    exercises, position ->
                    Log.d("TEST CWICZEN", "CWICZENIA ${exercises.toString()}")
            }
            }
        }

    }
}
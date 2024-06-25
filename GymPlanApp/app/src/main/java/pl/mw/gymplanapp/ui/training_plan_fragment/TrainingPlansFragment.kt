package pl.mw.gymplanapp.ui.training_plan_fragment

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
import pl.mw.gymplanapp.databinding.FragmentTrainingPlansBinding
import pl.mw.gymplanapp.ui.adapters.TrainingPlanAdapter
import pl.mw.gymplanapp.ui.exercise_fragment.ExercisesViewModel

class TrainingPlansFragment : Fragment() {

    private val trainingViewModel by viewModels<TrainingPlansViewModel>()
    private val mainVm by activityViewModels<MainViewModel>()
    private var _binding: FragmentTrainingPlansBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTrainingPlansBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.listaTreningowRV.layoutManager = LinearLayoutManager(requireContext())
        mainVm.getAllTrainingPlans().observe(viewLifecycleOwner) {plans ->
            binding.listaTreningowRV.adapter = TrainingPlanAdapter(
                plans
            ) { plans, position ->
                Log.d("TEST PLAN", "PLANS: ${plans.toString()}")
            }
        }
    }
}
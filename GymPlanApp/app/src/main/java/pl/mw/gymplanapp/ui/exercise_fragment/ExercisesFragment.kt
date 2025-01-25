package pl.mw.gymplanapp.ui.exercise_fragment

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.os.Parcelable
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
import pl.mw.gymplanapp.buttons.OnMinusDoneClickListener
import pl.mw.gymplanapp.buttons.OnMinusRepeatClickListener
import pl.mw.gymplanapp.buttons.OnMinusSeriesClickListener
import pl.mw.gymplanapp.buttons.OnMinusWeightClickListener
import pl.mw.gymplanapp.buttons.OnPlusDoneClickListener
import pl.mw.gymplanapp.buttons.OnPlusRepeatClickListener
import pl.mw.gymplanapp.buttons.OnPlusSeriesClickListener
import pl.mw.gymplanapp.buttons.OnPlusWeightClickListener
import pl.mw.gymplanapp.databinding.FragmentExercisesBinding
import pl.mw.gymplanapp.model.Exercise
import pl.mw.gymplanapp.ui.adapters.ExercisesAdapter

class ExercisesFragment : Fragment(),
    OnMinusSeriesClickListener,
    OnPlusSeriesClickListener,
    OnMinusRepeatClickListener,
    OnPlusRepeatClickListener,
    OnMinusWeightClickListener,
    OnPlusWeightClickListener,
    OnMinusDoneClickListener,
    OnPlusDoneClickListener {

    private val exercisesViewModel by viewModels<ExercisesViewModel>()
    private val mainVm by activityViewModels<MainViewModel>()
    private var _binding: FragmentExercisesBinding? = null
    private val binding get() = _binding!!

    // Zmienna potrzebna do zarzadzania recylerView
    private var recylerViewState: Parcelable? = null

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
            mainVm.getAllExercisesByPlan(trainingPlan.planId)
                .observe(viewLifecycleOwner) { exercises ->
                    val adapter = ExercisesAdapter(
                        exercises,
                        this,
                        this,
                        this,
                        this,
                        this,
                        this,
                        this,
                        this
                    )
                    { exercises, position ->
                        mainVm.selectExercise(exercises)
                        findNavController().navigate(R.id.editExerciseFragment)
                    }
                    binding.exerciseRecylerView.adapter = adapter
                    restoreRecylerViewState()
                }
        }

        binding.addExercise.setOnClickListener {
            findNavController().navigate(R.id.addExerciseFragment)
        }
    }

    // Funkcja zapisujaca aktualny stan listy
    private fun saveRecylerViewState() {
        recylerViewState = binding.exerciseRecylerView.layoutManager?.onSaveInstanceState()
    }

    private fun restoreRecylerViewState() {
        recylerViewState?.let {
            binding.exerciseRecylerView.layoutManager?.onRestoreInstanceState(it)
        }
    }

    private fun handleOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
    }

    override fun onMinusSeriesClick(exercise: Exercise) {
        saveRecylerViewState()
        val updateMinusSeries = exercise.copy(series_exercise = (exercise.series_exercise - 1).coerceAtLeast(0))
        mainVm.updateExercise(updateMinusSeries)
        restoreRecylerViewState()
    }

    override fun onPlusSeriesClick(exercise: Exercise) {
        saveRecylerViewState()
        val updatePlusSeries = exercise.copy(series_exercise = (exercise.series_exercise + 1).coerceAtMost(99))
        mainVm.updateExercise(updatePlusSeries)
        restoreRecylerViewState()
    }

    // Zrobienie kopii encji cwiczenie oraz update zmniejszenie pola o 1
    override fun onMinusRepeatClick(exercise: Exercise) {
        saveRecylerViewState() // zapisanie aktualnego stanu RV
        val updateMinusRepeat = exercise.copy(amount_exercise = (exercise.amount_exercise - 1).coerceAtLeast(0))
        mainVm.updateExercise(updateMinusRepeat)
        restoreRecylerViewState() // przywrocenie w tym samym miejscu RV
    }

    override fun onPlusRepeatClick(exercise: Exercise) {
        saveRecylerViewState()
        val updatePlusRepeat = exercise.copy(amount_exercise = (exercise.amount_exercise + 1).coerceAtMost(99))
        mainVm.updateExercise(updatePlusRepeat)
        restoreRecylerViewState()
    }

    override fun onMinusWeightClick(exercise: Exercise) {
        saveRecylerViewState()
        val updateMinusWeight = exercise.copy(weight_exercise = (exercise.weight_exercise - 0.50).coerceAtLeast(0.0))
        mainVm.updateExercise(updateMinusWeight)
        restoreRecylerViewState()
    }

    override fun onPlusWeightClick(exercise: Exercise) {
        saveRecylerViewState()
        val updatePlusWeight = exercise.copy(weight_exercise = (exercise.weight_exercise + 0.50).coerceAtMost(999.00))
        mainVm.updateExercise(updatePlusWeight)
        restoreRecylerViewState()
    }

    override fun onMinusDoneClick(exercise: Exercise) {
        saveRecylerViewState()
        val updateMinusDone = exercise.copy(done_exercise = (exercise.done_exercise - exercise.done_exercise).coerceAtLeast(0))
        mainVm.updateExercise(updateMinusDone)
        restoreRecylerViewState()
    }

    override fun onPlusDoneClick(exercise: Exercise) {
        saveRecylerViewState()
        val updatePlusDone = exercise.copy(done_exercise = (exercise.done_exercise + 1).coerceAtMost(10))
        mainVm.updateExercise(updatePlusDone)
        restoreRecylerViewState()
    }

}
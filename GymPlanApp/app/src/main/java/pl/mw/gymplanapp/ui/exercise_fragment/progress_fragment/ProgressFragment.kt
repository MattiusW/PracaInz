package pl.mw.gymplanapp.ui.exercise_fragment.progress_fragment

import android.graphics.Color
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import pl.mw.gymplanapp.MainViewModel
import pl.mw.gymplanapp.R
import pl.mw.gymplanapp.databinding.FragmentProgressBinding

class ProgressFragment : Fragment() {

    private val viewModel by viewModels<ProgressViewModel>()
    private val mainVm by activityViewModels<MainViewModel>()
    private var _binding: FragmentProgressBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProgressBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val entries = ArrayList<BarEntry>()
        val labels = arrayListOf("KLATKA", "RAMIONA", "RECE", "NOGI", "PLECY", "BRZUCH", "INNE")

        val barChart = view.findViewById<BarChart>(R.id.progressBarChart)

        // Ustawienie danych i kolorów wstępnych
        var barDataSet = BarDataSet(entries, "Osiągnięcia wagowe")
        barDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
        var barData = BarData(barDataSet)

        barChart.data = barData

        // Obserwacja danych z ViewModel
        mainVm.getProgressExercises().observe(viewLifecycleOwner) { progressList ->
            entries.clear()
            labels.clear()

            if (progressList.isEmpty()) {
                entries.add(BarEntry(0f,0f))
                labels.add("AKTUALNIE BRAK DANYCH")
            } else {
                for ((index, progress) in progressList.withIndex()) {
                    entries.add(BarEntry(index.toFloat(), progress.progress.toFloat()))
                    labels.add(progress.category_exercise.uppercase())
                }
            }
            // Aktualizacja danych
            barDataSet = BarDataSet(entries, "Osiągnięcia wagowe")
            barDataSet.setColors(*ColorTemplate.COLORFUL_COLORS)
            barData = BarData(barDataSet)
            barChart.data = barData
            // Aktualizacja etykiet
            barChart.xAxis.valueFormatter = IndexAxisValueFormatter(labels)
            barChart.xAxis.granularity = 1f
            barChart.xAxis.labelCount = labels.size
            barChart.legend.isEnabled = false
            barChart.description.isEnabled = false
            barChart.invalidate()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
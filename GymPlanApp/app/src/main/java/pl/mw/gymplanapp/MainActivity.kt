package pl.mw.gymplanapp

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isInvisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import pl.mw.gymplanapp.databinding.ActivityMainBinding
import pl.mw.gymplanapp.model.TrainingPlan

class MainActivity : AppCompatActivity() {

    private val mainVm by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setButtonVisibility(mainVm.isButtonVisible)

        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

        binding.addNewPlan.setOnClickListener {
            setButtonVisibility(false)
            navController.navigate(R.id.addTrainingPlanFragment)
        }
    }

    fun setButtonVisibility(bool: Boolean) {
        mainVm.isButtonVisible = bool

        val isVisibile = when(bool) {
            true -> View.VISIBLE
            false -> View.INVISIBLE
        }

        binding.addNewPlan.visibility = isVisibile
        binding.bottomNavigationView.visibility = isVisibile
    }
}
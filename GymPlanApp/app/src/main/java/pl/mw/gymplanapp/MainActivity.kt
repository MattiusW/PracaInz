package pl.mw.gymplanapp

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import pl.mw.gymplanapp.databinding.ActivityMainBinding
import pl.mw.gymplanapp.model.Exercise
import pl.mw.gymplanapp.model.ExerciseCategory

class MainActivity : AppCompatActivity() {

    private val mainVm by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setButtonVisibility(mainVm.isButtonVisible, mainVm.isMenuVisible)

        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navController)

        binding.addNewPlan.setOnClickListener {
            setButtonVisibility(buttonVisible = false, menuVisible = false)
            navController.navigate(R.id.addTrainingPlanFragment)
        }

    }

    fun setButtonVisibility(buttonVisible: Boolean, menuVisible: Boolean) {
        mainVm.isButtonVisible = buttonVisible
        mainVm.isMenuVisible = menuVisible

        val isVisibleBtn = when(buttonVisible) {
            true -> View.VISIBLE
            false -> View.INVISIBLE
        }
        val isVisibleMenu = when(menuVisible) {
            true -> View.VISIBLE
            false -> View.INVISIBLE
        }

        binding.addNewPlan.visibility = isVisibleBtn
        binding.bottomNavigationView.visibility = isVisibleMenu
    }

}
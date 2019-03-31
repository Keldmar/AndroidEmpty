package keldmar.example.com.presentation.navigation.navigator

import android.content.Intent
import keldmar.example.com.presentation.navigation.BaseNavigator
import keldmar.example.com.presentation.ui.activity.main.MainActivity
import keldmar.example.com.presentation.ui.activity.second.SecondActivity
import keldmar.example.com.presentation.navigation.screens.ActivityScreen
import keldmar.example.com.presentation.navigation.screens.SecondActivityScreen

class MainNavigator(
        activity: MainActivity
): BaseNavigator<MainActivity>(activity) {

    override fun showActivityScreen(screen: ActivityScreen) {
        when (screen) {
            is SecondActivityScreen -> {
                val intent = Intent(activity, SecondActivity::class.java)
                activity.startActivity(intent)
            }
            else -> super.showActivityScreen(screen)
        }
    }
}
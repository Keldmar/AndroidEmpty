package keldmar.example.com.presentation.navigation.strategy.impl

import android.content.Intent
import keldmar.example.com.presentation.navigation.strategy.IActivityNavigationStrategy
import keldmar.example.com.presentation.ui.activity.second.SecondActivity
import keldmar.example.com.presentation.ui.base.NavigableActivity
import keldmar.example.com.presentation.navigation.screens.ActivityScreen
import keldmar.example.com.presentation.navigation.screens.SecondActivityScreen

open class DefaultActivityNavigationStrategy(
        val navigableActivity: NavigableActivity
) : IActivityNavigationStrategy {

    override fun showActivityScreen(screen: ActivityScreen) {
        when (screen) {
            is SecondActivityScreen -> {
                val intent = Intent(navigableActivity, SecondActivity::class.java)
                navigableActivity.startActivity(intent)
            }

        }
    }
}
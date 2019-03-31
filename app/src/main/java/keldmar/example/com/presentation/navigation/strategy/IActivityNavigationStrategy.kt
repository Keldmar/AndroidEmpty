package keldmar.example.com.presentation.navigation.strategy

import keldmar.example.com.presentation.navigation.screens.ActivityScreen

interface IActivityNavigationStrategy {
    fun showActivityScreen(screen: ActivityScreen)
}
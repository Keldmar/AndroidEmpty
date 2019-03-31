package keldmar.example.com.presentation.navigation.strategy.impl

import androidx.fragment.app.Fragment
import keldmar.example.com.presentation.navigation.strategy.IFragmentNavigationStrategy
import keldmar.example.com.presentation.navigation.screens.FirstFragmentScreen
import keldmar.example.com.presentation.navigation.screens.FragmentScreen
import keldmar.example.com.presentation.navigation.screens.SecondFragmentScreen
import keldmar.example.com.presentation.ui.fragment.first.FirstFragment
import keldmar.example.com.presentation.ui.fragment.second.SecondFragment

class DefaultFragmentNavigationStrategy : IFragmentNavigationStrategy {
    override fun createFragment(screen: FragmentScreen): Fragment? = when (screen) {
        is FirstFragmentScreen -> FirstFragment()
        is SecondFragmentScreen -> SecondFragment()
        else -> null
    }
}
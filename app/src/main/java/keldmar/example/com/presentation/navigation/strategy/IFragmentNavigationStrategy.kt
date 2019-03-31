package keldmar.example.com.presentation.navigation.strategy

import androidx.fragment.app.Fragment
import keldmar.example.com.presentation.navigation.screens.FragmentScreen

interface IFragmentNavigationStrategy {
    fun createFragment(screen: FragmentScreen): Fragment?
}
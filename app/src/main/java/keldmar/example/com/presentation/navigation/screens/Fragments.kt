package keldmar.example.com.presentation.navigation.screens

import keldmar.example.com.presentation.ui.fragment.first.FirstFragment
import keldmar.example.com.presentation.ui.fragment.second.SecondFragment

class FirstFragmentScreen : FragmentScreen {
    override val screenKey: String = FirstFragment.SCREEN_NAME
}

class SecondFragmentScreen : FragmentScreen {
    override val screenKey: String = SecondFragment.SCREEN_NAME
}


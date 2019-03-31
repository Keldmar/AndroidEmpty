package keldmar.example.com.presentation.navigation.screens

interface Screen {
    val screenKey: String
}

interface FragmentScreen: Screen

interface FragmentDialogScreen: Screen

interface ActivityScreen: Screen
package keldmar.example.com.presentation.navigation.base.commands

import keldmar.example.com.presentation.navigation.screens.Screen

/**
 * Navigation command describes screens transition.
 * that can be processed by [Navigator].
 */
sealed class Command

class Back : Command()

class BackTo(val screenKey: String? = null) : Command()

class SystemMessage(val message: String) : Command()

class Forward(val screen: Screen) : Command()

class Replace(val screen: Screen) : Command()

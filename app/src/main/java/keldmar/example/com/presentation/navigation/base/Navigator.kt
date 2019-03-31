package keldmar.example.com.presentation.navigation.base

import androidx.fragment.app.FragmentManager
import keldmar.example.com.presentation.navigation.base.commands.Command

/**
 * The low-level navigation interface.
 * Navigator is the one who actually performs any transition.
 */

interface Navigator {
    /**
     * Performs transition described by the navigation command
     *
     * @param commands the navigation command array to apply per single transaction
     */
    fun applyCommands(commands: Array<Command>)

    val fragmentManager: FragmentManager?

    val containerId: Int?
}
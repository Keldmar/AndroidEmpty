package keldmar.example.com.presentation.navigation

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import keldmar.example.com.presentation.navigation.base.Navigator
import keldmar.example.com.presentation.navigation.base.commands.*
import keldmar.example.com.presentation.navigation.screens.ActivityScreen
import keldmar.example.com.presentation.navigation.screens.FragmentDialogScreen
import keldmar.example.com.presentation.navigation.screens.FragmentScreen
import keldmar.example.com.presentation.navigation.screens.Screen
import keldmar.example.com.presentation.navigation.strategy.IActivityNavigationStrategy
import keldmar.example.com.presentation.navigation.strategy.IFragmentDialogNavigationStrategy
import keldmar.example.com.presentation.navigation.strategy.IFragmentNavigationStrategy
import keldmar.example.com.presentation.navigation.strategy.impl.DefaultActivityNavigationStrategy
import keldmar.example.com.presentation.navigation.strategy.impl.DefaultFragmentNavigationStrategy
import keldmar.example.com.presentation.ui.base.NavigableActivity
import java.util.*

/**
 * Jelvix Inventory
 * Copyright © 2018 Jelvix. All rights reserved.
 *
 * [Navigator] implementation based on the support fragments.
 *
 *
 * [BackTo] navigation command will return to the root if
 * needed screen isn't found in the screens chain.
 * To change this behavior override [.backToUnexisting] method.
 *
 *
 *
 * [Back] command will call [.exit] method if current screen is the root.
 *
 */
open class BaseNavigator<out T : NavigableActivity>(
        protected val activity: T,
        override val containerId: Int? = null,
        fm: FragmentManager? = null
) : Navigator {

    override val fragmentManager: FragmentManager = fm ?: activity.supportFragmentManager

    protected open var activityNavigationStrategy: IActivityNavigationStrategy = DefaultActivityNavigationStrategy(activity)
    protected open var fragmentNavigationStrategy: IFragmentNavigationStrategy = DefaultFragmentNavigationStrategy()
//    protected open var fragmentDialogNavigationStrategy: IFragmentDialogNavigationStrategy = DefaultFragmentDialogNavigationStrategy(activity)
    open var localStackCopy: LinkedList<String> = LinkedList()
    /**
     * Override this method to setup custom fragment transaction animation.
     *
     * @param command             current navigation command. Will be only [Forward] or [Replace]
     * @param currentFragment     current fragment in container
     * (for [Replace] command it will be screen previous in new chain, NOT replaced screen)
     * @param nextFragment        next screen fragment
     * @param fragmentTransaction fragment transaction
     */
    protected open fun setupFragmentTransactionAnimation(screen: Screen,
                                                         currentFragment: Fragment?,
                                                         nextFragment: Fragment,
                                                         fragmentTransaction: FragmentTransaction) {
    }

    override fun applyCommands(commands: Array<Command>) {
        fragmentManager.executePendingTransactions()

        //copy stack before apply commands
        copyStackToLocal()

        for (command in commands) {
            applyCommand(command)
        }
    }

    private fun copyStackToLocal() {
        localStackCopy = LinkedList()

        val stackSize = fragmentManager.backStackEntryCount
        for (i in 0 until stackSize) {
            fragmentManager.getBackStackEntryAt(i).name?.let {
                localStackCopy.add(it)
            }
        }
    }

    /**
     * Perform transition described by the navigation command
     *
     * @param command the navigation command to apply
     */
    protected fun applyCommand(command: Command) = when (command) {
        is Back -> back()
        is BackTo -> backTo(command)
        is SystemMessage -> showSystemMessage(command.message)
        is Forward -> forward(command)
        is Replace -> replace(command)
    }

    /**
     * Performs [Forward] command transition
     */
    protected open fun forward(command: Forward) {
        val screen = command.screen
        when (screen) {
            is FragmentScreen -> forwardFragment(screen)
            is ActivityScreen -> forwardActivity(screen)
            is FragmentDialogScreen -> forwardFragmentDialog(screen)
        }
    }

    protected open fun forwardFragment(screen: FragmentScreen) {
        val fragment = createFragment(screen)
        if (fragment == null || containerId == null) {
            unknownScreen(screen)
            return
        }
        val fragmentTransaction = fragmentManager.beginTransaction()

        setupFragmentTransactionAnimation(screen, fragmentManager.findFragmentById(containerId!!), fragment, fragmentTransaction)

        fragmentTransaction
                .replace(containerId!!, fragment, screen.screenKey)
                .addToBackStack(screen.screenKey)
                .commit()
        localStackCopy.add(screen.screenKey)
    }

    protected fun forwardActivity(screen: ActivityScreen) {
        showActivityScreen(screen)
    }

    protected fun forwardFragmentDialog(screen: FragmentDialogScreen) {
//        showFragmentDialogScreen(screen)
    }

    /**
     * Performs [Replace] command transition
     */
    protected open fun replace(command: Replace) {
        val screen = command.screen
        when (screen) {
            is FragmentScreen -> replaceFragment(screen)
            is ActivityScreen -> replaceActivity(screen)
            is FragmentDialogScreen -> replaceFragmentDialog(screen)
        }
    }

    protected open fun backLocalToRoot(screenKey: String?) {}

    protected open fun replaceFragment(screen: FragmentScreen) {
        val fragment = createFragment(screen)

        if (fragment == null || containerId == null) {
            unknownScreen(screen)
            return
        }

        if (localStackCopy.size > 0) {
            fragmentManager.popBackStack()
            localStackCopy.pop()

            val fragmentTransaction = fragmentManager.beginTransaction()

            setupFragmentTransactionAnimation(screen, fragmentManager.findFragmentById(containerId!!), fragment, fragmentTransaction)

            fragmentTransaction
                    .replace(containerId!!, fragment, screen.screenKey)
                    .addToBackStack(screen.screenKey)
                    .commit()
            localStackCopy.add(screen.screenKey)
        } else {
            val fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction
                    .replace(containerId!!, fragment, screen.screenKey)
                    .commit()
        }
    }

    protected fun replaceActivity(screen: ActivityScreen) {
        showActivityScreen(screen)
    }

    protected fun replaceFragmentDialog(screen: FragmentDialogScreen) {
//        showFragmentDialogScreen(screen)
    }

    /**
     * Performs [Back] command transition
     */
    protected fun back() {
        if (localStackCopy.size > 0) {
            fragmentManager.popBackStack()
            localStackCopy.pop()
        } else {
            exit()
        }
    }

    /**
     * Performs [BackTo] command transition
     */
    protected fun backTo(command: BackTo) {
        val key = command.screenKey
        if (key == null) {
            backToRoot()
        } else {
            val index = localStackCopy.indexOf(key)
            val size = localStackCopy.size

            if (index != -1) {
                for (i in 1 until size - index) {
                    localStackCopy.pop()
                }
                fragmentManager.popBackStack(key, 0)
            } else {
                backToUnexisting(command.screenKey)
            }
        }
    }

    protected fun backToRoot() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        localStackCopy.clear()
    }

    /**
     * Creates Fragment matching `screenKey`.
     *
     * @param screenKey screen key
     * @param data      initialization data
     * @return instantiated fragment for the passed screen key
     */
    open fun createFragment(screen: FragmentScreen): Fragment? = fragmentNavigationStrategy.createFragment(screen)

    protected open fun showActivityScreen(screen: ActivityScreen) = activityNavigationStrategy.showActivityScreen(screen)

//    protected open fun showFragmentDialogScreen(screen: FragmentDialogScreen) = fragmentDialogNavigationStrategy.showFragmentDialogScreen(screen)

    /**
     * Shows system message.
     *
     * @param message message to show
     */
    protected fun showSystemMessage(message: String) {
//        activity.showAlertDialog(message)
    }

    /**
     * Called when we try to back from the root.
     */
    protected open fun exit() {
        activity.finish()
    }

    /**
     * Called when we tried to back to some specific screen (via [BackTo] command),
     * but didn't found it.
     * @param screenKey screen key
     */
    protected fun backToUnexisting(screenKey: String) {
        backToRoot()
    }

    /**
     * Called if we can't create a screen.
     */
    protected fun unknownScreen(screen: Screen) {
        Toast.makeText(activity, "Can't create a screen for passed screenKey.", Toast.LENGTH_SHORT).show()
    }
}
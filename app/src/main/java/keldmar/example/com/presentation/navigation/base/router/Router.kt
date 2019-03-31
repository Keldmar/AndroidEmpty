package keldmar.example.com.presentation.navigation.base.router

import keldmar.example.com.presentation.navigation.screens.Screen
import keldmar.example.com.presentation.navigation.base.commands.*

class Router : BaseRouter() {

    private val resultListeners = HashMap<Int, (Any?) -> Unit>()

    /**
     * Subscribe to the screen result.<br></br>
     * **Note:** only one listener can subscribe to a unique resultCode!<br></br>
     * You must call a **removeResultListener()** to avoid a memory leak.
     *
     * @param resultCode key for filter results
     * @param listener   result listener
     */
    fun setResultListener(resultCode: Int, listener: (Any?) -> Unit) {
        resultListeners.put(resultCode, listener)
    }

    /**
     * Unsubscribe from the screen result.
     *
     * @param resultCode key for filter results
     */
    fun removeResultListener(resultCode: Int?) {
        resultListeners.remove(resultCode)
    }

    /**
     * Send result data to subscriber.
     *
     * @param resultCode result data key
     * @param result     result data
     * @return TRUE if listener was notified and FALSE otherwise
     */
    fun sendResult(resultCode: Int?, result: Any): Boolean {
        val resultListener = resultListeners[resultCode]
        resultListener?.let {
            it(result)
            return true
        }
        return false
    }

    /**
     * Open new screen and add it to screens chain.
     *
     * @param screenKey screen key
     * @param data      initialisation parameters for the new screen
     */
    fun navigateTo(screen: Screen) {
        executeCommands(arrayOf(Forward(screen)))
    }

    /**
     * Clear the current screens chain and start new one
     * by opening a new screen right after the root.
     *
     * @param screenKey screen key
     * @param data      initialisation parameters for the new screen
     */
    fun newScreenChain(screen: Screen) {
        executeCommands(arrayOf(
                BackTo(),
                Forward(screen)
        ))
    }

    /**
     * Clear all screens and open new one as root.
     *
     * @param screenKey screen key
     * @param data      initialisation parameters for the root
     */
    fun newRootScreen(screen: Screen) {
        executeCommands(arrayOf(
                BackTo(),
                Replace(screen)
        ))
    }

    /**
     * Replace current screen.
     * By replacing the screen, you alters the backstack,
     * so by going back you will return to the previous screen
     * and not to the replaced one.
     *
     * @param screenKey screen key
     * @param data      initialisation parameters for the new screen
     */
    fun replaceScreen(screen: Screen) {
        executeCommands(arrayOf(Replace(screen)))
    }

    /**
     * Return back to the needed screen from the chain.
     * Behavior in the case when no needed screens found depends on
     * the processing of the [BackTo] command in a [Navigator] implementation.
     *
     * @param screenKey screen key
     */
    fun backTo(screenKey: String?) {
        executeCommands(arrayOf(BackTo(screenKey)))
    }

    /**
     * Remove all screens from the chain and exit.
     * It's mostly used to finish the application or close a supplementary navigation chain.
     */
    fun finishChain() {
        executeCommands(arrayOf(
                BackTo(),
                Back()
        ))
    }

    /**
     * Return to the previous screen in the chain.
     * Behavior in the case when the current screen is the root depends on
     * the processing of the [Back] command in a [Navigator] implementation.
     */
    fun exit() {
        executeCommands(arrayOf(Back()))
    }

    /**
     * Return to the previous screen in the chain and send result data.
     *
     * @param resultCode result data key
     * @param result     result data
     */
    fun exitWithResult(resultCode: Int?, result: Any) {
        exit()
        sendResult(resultCode, result)
    }

    /**
     * Return to the previous screen in the chain and show system message.
     *
     * @param message message to show
     */
    fun exitWithMessage(message: String) {
        executeCommands(arrayOf(
                Back(),
                SystemMessage(message)
        ))
    }

    /**
     * Show system message.
     *
     * @param message message to show
     */
    fun showSystemMessage(message: String) {
        executeCommands(arrayOf(SystemMessage(message)))
    }
}
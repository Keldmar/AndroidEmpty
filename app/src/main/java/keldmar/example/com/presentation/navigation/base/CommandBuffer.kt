package keldmar.example.com.presentation.navigation.base

import keldmar.example.com.presentation.navigation.base.commands.Command
import java.util.*

internal class CommandBuffer : NavigatorHolder {

    private var navigator: Navigator? = null
    private val pendingCommands: Queue<Array<Command>> = LinkedList()

    override fun setNavigator(navigator: Navigator) {
        this.navigator = navigator

        while (!pendingCommands.isEmpty()) {
            if (this.navigator != null) {
                executeCommands(pendingCommands.poll())
            } else break
        }
    }

    override fun removeNavigator() {
        this.navigator = null
    }

    /**
     * Passes `commands` to the [Navigator] if it available.
     * Else puts it to the pending commands queue to pass it later.
     * @param commands navigation command array
     */
    fun executeCommands(commands: Array<Command>) {
        if (navigator != null) {
            navigator?.applyCommands(commands)
        } else {
            pendingCommands.add(commands)
        }
    }
}
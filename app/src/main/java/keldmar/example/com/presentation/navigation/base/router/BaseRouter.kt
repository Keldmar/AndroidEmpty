package keldmar.example.com.presentation.navigation.base.router

import keldmar.example.com.presentation.navigation.base.CommandBuffer
import keldmar.example.com.presentation.navigation.base.commands.Command

/**
 * BaseRouter is an abstract class to implement high-level navigation.
 * Extend it to add needed transition methods.
 */

abstract class BaseRouter {

    internal val commandBuffer: CommandBuffer = CommandBuffer()

    /**
     * Sends navigation command array to [CommandBuffer].
     *
     * @param commands navigation command array to execute
     */
    protected fun executeCommands(commands: Array<Command>) {
        commandBuffer.executeCommands(commands)
    }
}
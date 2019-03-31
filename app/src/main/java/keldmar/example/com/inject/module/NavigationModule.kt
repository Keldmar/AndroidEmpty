package keldmar.example.com.inject.module

import dagger.Module
import dagger.Provides
import keldmar.example.com.presentation.navigation.base.Cicerone
import keldmar.example.com.presentation.navigation.base.NavigatorHolder
import keldmar.example.com.presentation.navigation.base.router.Router
import javax.inject.Singleton

/**
 * Jelvix Inventory
 * Copyright © 2018 Jelvix. All rights reserved.
 */

@Module
class NavigationModule {

    private val cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    fun provideRouter(): Router = cicerone.router

    @Provides
    @Singleton
    fun provideNavigatorHolder(): NavigatorHolder = cicerone.navigatorHolder
}
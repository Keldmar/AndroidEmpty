package keldmar.example.com.presentation.navigation

import keldmar.example.com.presentation.navigation.base.Navigator
import keldmar.example.com.presentation.navigation.base.NavigatorHolder
import keldmar.example.com.presentation.navigation.base.router.Router

interface IBaseNavigatorView {

    var navigatorHolder: NavigatorHolder
    var router: Router
    val activityNavigator: Navigator
}
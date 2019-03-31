package keldmar.example.com.presentation.ui.base

import android.content.Intent
import androidx.fragment.app.Fragment
import keldmar.example.com.presentation.navigation.IBaseNavigatorView
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.HasSupportFragmentInjector
import keldmar.example.com.presentation.navigation.base.Navigator
import keldmar.example.com.presentation.navigation.base.NavigatorHolder
import keldmar.example.com.presentation.navigation.base.router.Router
import javax.inject.Inject

abstract class NavigableActivity : BaseActivity(), IBaseNavigatorView, HasFragmentInjector, HasSupportFragmentInjector {

    @Inject
    override lateinit var navigatorHolder: NavigatorHolder
    @Inject
    override lateinit var router: Router
    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<android.app.Fragment>

    override val activityNavigator by lazy { getNavigator() }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = supportFragmentInjector
    override fun fragmentInjector(): AndroidInjector<android.app.Fragment>? = fragmentInjector


    /* lifecycle */

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(activityNavigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        router.exit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        activityNavigator.containerId?.let {
            activityNavigator.fragmentManager?.findFragmentById(it)?.onActivityResult(requestCode, resultCode, data)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    /* navigation */

    abstract fun getNavigator(): Navigator
}
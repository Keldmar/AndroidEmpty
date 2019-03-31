package keldmar.example.com.presentation.ui.activity.main

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.Lazy
import keldmar.example.com.R
import keldmar.example.com.presentation.navigation.BaseNavigator
import keldmar.example.com.presentation.navigation.base.Navigator
import keldmar.example.com.presentation.ui.base.NavigableActivity
import javax.inject.Inject

class MainActivity : NavigableActivity(), IMainViewI {

    @Inject
    lateinit var daggerPresenter: Lazy<MainPresenter>

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun providePresenter() = daggerPresenter.get()

    override fun getNavigator(): Navigator = BaseNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

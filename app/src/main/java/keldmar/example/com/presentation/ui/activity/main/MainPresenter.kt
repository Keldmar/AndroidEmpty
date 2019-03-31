package keldmar.example.com.presentation.ui.activity.main

import com.arellomobile.mvp.InjectViewState
import keldmar.example.com.presentation.navigation.base.router.Router
import keldmar.example.com.presentation.navigation.screens.FirstFragmentScreen
import keldmar.example.com.presentation.ui.base.BasePresenter
import javax.inject.Inject

@InjectViewState
class MainPresenter @Inject constructor(
    private val router: Router
): BasePresenter<IMainViewI>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.navigateTo(FirstFragmentScreen())
    }
}
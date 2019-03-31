package keldmar.example.com.presentation.ui.fragment.second

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.Lazy
import keldmar.example.com.R
import keldmar.example.com.presentation.navigation.IBaseNavigatorView
import keldmar.example.com.presentation.ui.base.BaseComponentFragment
import keldmar.example.com.presentation.ui.base.BasePresenter
import javax.inject.Inject

@InjectViewState
class SecondFragmentPresenter @Inject constructor(): BasePresenter<ISecondFragmentView>() {
}

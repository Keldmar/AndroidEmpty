package keldmar.example.com.presentation.ui.fragment.second

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.Lazy
import keldmar.example.com.R
import keldmar.example.com.presentation.ui.base.BaseComponentFragment
import keldmar.example.com.presentation.navigation.IBaseNavigatorView
import javax.inject.Inject

class SecondFragment : BaseComponentFragment<IBaseNavigatorView>(), ISecondFragmentView {

    companion object {

        const val SCREEN_NAME = "SecondFragmentScreen"
    }

    override val layoutResId: Int
        get() = R.layout.fragment_second



    @Inject
    lateinit var daggerPresenter: Lazy<SecondFragmentPresenter>
    @InjectPresenter
    lateinit var presenter: SecondFragmentPresenter

    @ProvidePresenter
    protected fun providePresenter(): SecondFragmentPresenter = daggerPresenter.get()
}
package keldmar.example.com.presentation.ui.fragment.first

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import dagger.Lazy
import keldmar.example.com.R
import keldmar.example.com.presentation.ui.base.BaseComponentFragment
import keldmar.example.com.presentation.navigation.IBaseNavigatorView
import keldmar.example.com.presentation.navigation.screens.SecondActivityScreen
import keldmar.example.com.presentation.navigation.screens.SecondFragmentScreen
import kotlinx.android.synthetic.main.fragment_first.*
import javax.inject.Inject

class FirstFragment : BaseComponentFragment<IBaseNavigatorView>(), IFirstFragmentView {

    companion object {

        const val SCREEN_NAME = "FirstFragmentScreen"
    }

    override val layoutResId: Int
        get() = R.layout.fragment_first

    @Inject
    lateinit var daggerPresenter: Lazy<FirstFragmentPresenter>
    @InjectPresenter
    lateinit var presenter: FirstFragmentPresenter

    @ProvidePresenter
    protected fun providePresenter(): FirstFragmentPresenter = daggerPresenter.get()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startActivityButton.setOnClickListener {
            navigableActivity.router.navigateTo(SecondActivityScreen())
        }

        navigateFragmentButton.setOnClickListener {
            navigableActivity.router.navigateTo(SecondFragmentScreen())
        }
    }
}

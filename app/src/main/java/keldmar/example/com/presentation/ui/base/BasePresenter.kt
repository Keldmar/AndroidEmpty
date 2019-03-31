package keldmar.example.com.presentation.ui.base

import com.arellomobile.mvp.MvpPresenter

abstract class BasePresenter<TPresenterView : IBaseView> : MvpPresenter<TPresenterView>()
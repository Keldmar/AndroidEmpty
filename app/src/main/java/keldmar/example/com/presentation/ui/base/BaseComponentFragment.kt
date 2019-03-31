package keldmar.example.com.presentation.ui.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import keldmar.example.com.presentation.ui.base.mvp.MvpAppCompatFragment
import keldmar.example.com.presentation.navigation.IBaseNavigatorView

abstract class BaseComponentFragment<T : IBaseNavigatorView> : MvpAppCompatFragment(), IBaseView{

    lateinit var navigableActivity: T
        private set
    abstract val layoutResId: Int

    private var lifecycleListeners: MutableSet<MvpLifecycleListener>? = HashSet()


    /* lifecycle */

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResId, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (activity is IBaseNavigatorView) {
            @Suppress("UNCHECKED_CAST")
            navigableActivity = activity as T
        } else {
            throw RuntimeException("Navigable activity must implement IBaseNavigatorView")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleListeners?.clear()
        lifecycleListeners = null
    }

    override fun onDestroyView() {
      //  activity?.hideKeyboard()
        super.onDestroyView()
    }

//    override fun setLifecycleListener(lifecycleListener: MvpLifecycleListener) {
//        this.lifecycleListeners?.add(lifecycleListener)
//    }
//
//    override fun removeLifecycleListener(lifecycleListener: MvpLifecycleListener) {
//        this.lifecycleListeners?.remove(lifecycleListener)
//    }

    override fun onStart() {
        super.onStart()
        lifecycleListeners?.forEach { it.onStart() }
    }

    override fun onResume() {
        super.onResume()
        lifecycleListeners?.forEach { it.onResume() }
    }

    override fun onPause() {
        super.onPause()
        lifecycleListeners?.forEach { it.onPause() }
    }

    override fun onStop() {
        super.onStop()
        lifecycleListeners?.forEach { it.onStop() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        lifecycleListeners?.forEach { it.onActivityResult(requestCode, resultCode, data) }
    }

//    override fun hideInputKeyboard() {
//        getBaseComponentActivity().hideKeyboard()
//    }


    /* private functions */

    private fun getBaseComponentActivity(): BaseActivity {
        if (activity is BaseActivity) {
            return activity as BaseActivity
        } else {
            throw RuntimeException("BaseComponentFragment must be used in BaseComponentActivity")
        }
    }

}
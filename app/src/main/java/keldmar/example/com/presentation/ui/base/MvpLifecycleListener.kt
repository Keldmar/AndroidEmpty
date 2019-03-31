package keldmar.example.com.presentation.ui.base

import android.content.Intent

interface MvpLifecycleListener {
    fun onStart()
    fun onStop()
    fun onPause()
    fun onResume()
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
}
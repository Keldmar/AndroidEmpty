package keldmar.example.com.presentation.ui.activity.second

import android.os.Bundle
import keldmar.example.com.R
import keldmar.example.com.presentation.navigation.BaseNavigator
import keldmar.example.com.presentation.navigation.base.Navigator
import keldmar.example.com.presentation.ui.base.NavigableActivity

class SecondActivity: NavigableActivity(), ISecondView {

    companion object {
        const val SCREEN_NAME = "SecondActivityScreen"
    }

    override fun getNavigator(): Navigator = BaseNavigator(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }
}
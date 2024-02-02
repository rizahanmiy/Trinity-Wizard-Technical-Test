package com.rizahanmiy.trinitywizardtest.data.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


abstract class BaseActivity : AppCompatActivity(){

    //    abstract val layout:View
    var instanceState:Bundle? = null

    abstract fun onPreparation()
    abstract fun onIntent()
    abstract fun onUi()
    abstract fun onAction()
    abstract fun onObserver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(layout)
        instanceState = savedInstanceState

        onPreparation()
        onIntent()
        onUi()
        onAction()
        onObserver()
    }

}
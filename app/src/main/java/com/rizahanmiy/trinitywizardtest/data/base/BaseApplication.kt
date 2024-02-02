package com.rizahanmiy.trinitywizardtest.data.base

import android.app.Application
import com.rizahanmiy.trinitywizardtest.presentation.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class BaseApplication : Application(), HasAndroidInjector {


    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
//        val config = Configuration.Builder()
//            .setWorkerFactory(myWorkerFactory)
//            .build()
//        WorkManager.initialize(this, config)
    }


//    override fun attachBaseContext(base: Context?) {
//        super.attachBaseContext(base)
//        MultiDex.install(this)
//    }

/*    override fun getWorkManagerConfiguration(): Configuration {
        val config = Configuration.Builder()
            .setWorkerFactory(myWorkerFactory)
            .build()
        WorkManager.initialize(this, config)
        return config
    }*/
}
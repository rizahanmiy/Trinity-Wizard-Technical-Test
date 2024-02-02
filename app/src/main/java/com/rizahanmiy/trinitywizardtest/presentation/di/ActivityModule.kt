package com.rizahanmiy.trinitywizardtest.presentation.di

import com.rizahanmiy.trinitywizardtest.presentation.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [FragmentModule::class])
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}
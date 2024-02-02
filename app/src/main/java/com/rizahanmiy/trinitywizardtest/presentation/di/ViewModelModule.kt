package com.rizahanmiy.trinitywizardtest.presentation.di


import androidx.lifecycle.ViewModelProvider
import com.rizahanmiy.trinitywizardtest.presentation.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module


@Module
abstract class ViewModelModule{

/*
    @Binds
    @IntoMap
    @ViewModelKey(CallViewModel::class)
    abstract fun bindCallViewModel(viewModel: CallViewModel) : BaseViewModel
*/


    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelProvider.Factory

}
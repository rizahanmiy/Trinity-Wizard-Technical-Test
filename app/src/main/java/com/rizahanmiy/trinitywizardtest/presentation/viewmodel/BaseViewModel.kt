package com.rizahanmiy.trinitywizardtest.presentation.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.toLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.reactivestreams.Publisher

open class BaseViewModel : ViewModel() {

    private  val compositeDisposable: CompositeDisposable = CompositeDisposable()

    protected fun  addDisposable(disposable: Disposable){
        compositeDisposable.add(disposable)
    }
    private fun clearDisposable(){
        compositeDisposable.clear()
    }

    override fun onCleared() {
        clearDisposable()
    }
    protected fun <T> MediatorLiveData<T>.add(publisher: Publisher<T>) {
        addSource(publisher.toLiveData()) {
            postValue(it)
        }
    }
}
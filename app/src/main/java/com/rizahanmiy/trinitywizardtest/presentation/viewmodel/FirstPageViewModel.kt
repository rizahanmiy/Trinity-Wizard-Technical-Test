package com.rizahanmiy.trinitywizardtest.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rizahanmiy.trinitywizardtest.data.entities.Contacts
import com.rizahanmiy.trinitywizardtest.util.common.JsonUtil.getAsset

class FirstPageViewModel: ViewModel() {

    private val _contactList = MutableLiveData<List<Contacts>>()
    val contactList : LiveData<List<Contacts>> = _contactList

    fun handleGetData(context: Context?) {
        val test = getAsset(context)

        _contactList.value = test
    }
}
package com.rizahanmiy.trinitywizard.util.common

import android.content.Context
import com.rizahanmiy.trinitywizard.data.entities.Contacts
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object JsonUtil {

    fun getAsset(context: Context?): List<Contacts> {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val listType = Types.newParameterizedType(List::class.java, Contacts::class.java)
        val adapter: JsonAdapter<List<Contacts>> = moshi.adapter(listType)

        val file = "data.json"

        val myjson = context?.assets?.open(file)?.bufferedReader().use{ it?.readText()}

        return myjson?.let { adapter.fromJson(it) }!!
    }

}
package com.rizahanmiy.trinitywizard.data.entities

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Contacts(
    val id: String?,
    var firstName: String?,
    var lastName: String?,
    val email: String?,
    val dob: String?
)
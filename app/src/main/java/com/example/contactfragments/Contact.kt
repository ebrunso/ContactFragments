package com.example.contactfragments

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(
    var name : String,
    var email : String,
    var phone : String) : Parcelable
package com.karsatech.overlordcharacter.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OverlordModel(
    val name: String,
    val img: String,
    val desc: String
):Parcelable
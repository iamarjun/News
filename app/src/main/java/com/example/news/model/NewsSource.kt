package com.example.news.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsSource(
    @SerializedName("sources")
    var sources: List<Source?>?,
    @SerializedName("status")
    var status: String?
) : Parcelable
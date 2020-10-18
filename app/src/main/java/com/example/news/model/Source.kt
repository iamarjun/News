package com.example.news.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Source(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("category")
    val category: String = "",
    @SerializedName("country")
    val country: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("language")
    val language: String = "",
    var isSelected: Boolean = false
) : Parcelable
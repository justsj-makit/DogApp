package dev.mitigate.dogapp.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class DogModel(
    val name: String,
    @Json(name = "image_link")
    val imageLink: String,
    @Json(name = "good_with_children")
    val goodWithChildren: Int,
    val drooling: Int,
    val playfulness: Int,
    val protectiveness: Int,
    val barking: Int
): Parcelable
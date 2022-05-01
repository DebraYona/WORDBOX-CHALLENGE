package com.simios.workboxchallenge.data.model

import com.google.gson.annotations.SerializedName

data class RandomUserModel(
    @SerializedName("gender") val gender: String,
    @SerializedName("name") val name: UserName,
    @SerializedName("picture") val picture: Picture,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("location") val location: UserLocation,
    @SerializedName("dob") val dob: UserAge,
)

data class  ResponseRandomUser(
    @SerializedName("results") val results: List<RandomUserModel>
)

data class  UserName(
    @SerializedName("title") val title: String,
    @SerializedName("first") val first: String,
    @SerializedName("last") val last: String,
    )

data class Picture(
    @SerializedName("large") val large: String,
    @SerializedName("medium") val medium: String,
    @SerializedName("thumbnail") val thumbnail: String,
    )

data class UserAge(
    @SerializedName("date") val date: String,
    @SerializedName("age") val age: Int,
    )

data class UserLocation(
    @SerializedName("city") val city: String,
    )

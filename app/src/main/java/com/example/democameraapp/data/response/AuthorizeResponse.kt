package com.example.democameraapp.data.response

import com.google.gson.annotations.SerializedName

data class AuthorizeResponse(
    @SerializedName("last_name") val lastName: String
    // Other user fields
)
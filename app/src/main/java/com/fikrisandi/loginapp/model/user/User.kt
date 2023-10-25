package com.fikrisandi.loginapp.model.user


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class User(
    @SerializedName("avatar")
    @Expose
    var avatar: String = "",
    @SerializedName("email")
    @Expose
    var email: String = "",
    @SerializedName("first_name")
    @Expose
    var firstName: String = "",
    @SerializedName("id")
    @Expose
    var id: Int = 0,
    @SerializedName("last_name")
    @Expose
    var lastName: String = ""
)

data class PagingUser(
    @SerializedName("page")
    @Expose
    var page: Int? = null,
    @SerializedName("per_page")
    @Expose
    var perPage: Int? = null,
    @SerializedName("total")
    @Expose
    var total: Int? = null,
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null,
    @SerializedName("data")
    @Expose
    var data: List<User>? = null,
)
package com.example.aula_retrofit

import com.google.gson.annotations.SerializedName

class PostEntity {

    //https://jsonplaceholder.typicode.com/posts

    @SerializedName("userId")
    var userId: Int = 0

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("title")
    var title: String = ""

    @SerializedName("body")
    var body: String = ""
}
package com.example.armen.dandur.http

import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface APIService {


    @Headers("accesstoken: 3e265EER-wW85-8Po8-4w88-wWfhlOS61548")
    @GET("posts")
    fun getPostsInCategory(@Query("categories") categories:String):Call<Any>
}
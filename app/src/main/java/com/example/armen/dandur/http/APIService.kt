package com.example.armen.dandur.http

import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface APIService {


    @Headers("accesstoken: 3e265EER-wW85-8Po8-4w88-wWfhlOS61548")
    @GET("posts?_embed")
    fun getPostsInCategory(@Query("categories", encoded = true) categories:String):Call<Any>

    @Headers("accesstoken: 3e265EER-wW85-8Po8-4w88-wWfhlOS61548")
    @GET("posts?_embed")
    fun getTypofResult(@Query("typeofresult",encoded = true) categories:String):Call<Any>

    @POST("points")
    @FormUrlEncoded
    fun likeRequest(@Field("post_id ") post_id:Double):Call<Any>

}
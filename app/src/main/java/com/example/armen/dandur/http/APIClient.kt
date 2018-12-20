package com.example.armen.dandur.http

import retrofit2.Retrofit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import com.google.gson.GsonBuilder
import retrofit2.converter.gson.GsonConverterFactory

class APIClient {
    companion object {
        var retrofit:Retrofit? = null

        fun getClient(baseUrl:String):Retrofit?{

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
            httpClient.addInterceptor(logging)


            val gson = GsonBuilder()
                    .setLenient()
                    .create()


            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(httpClient.build())
                        .build()
            }

            return retrofit
        }
    }
}
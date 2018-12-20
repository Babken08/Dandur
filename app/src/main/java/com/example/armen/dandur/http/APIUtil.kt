package com.example.armen.dandur.http

class APIUtil {
    companion object {
        val BASE_URL = "https://www.dandur.com/wp-json/wp/v2/"

        fun getAPIService(): APIService? {
            return APIClient.getClient(BASE_URL)?.create(APIService::class.java)
        }
    }
}
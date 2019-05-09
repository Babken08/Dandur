package com.example.armen.dandur.models

import com.google.gson.internal.LinkedTreeMap

class UserEntity {

    constructor(map:LinkedTreeMap<*, *>){
        id  = map["id"].toString()
        name = map["name"].toString()
        val urls = map["avatar_urls"] as LinkedTreeMap<String, String>?
        url = urls!!["24"] as String
    }

    var id:String? = null
    var name:String? = null
    var url:String? = null
}
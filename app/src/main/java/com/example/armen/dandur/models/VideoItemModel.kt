package com.example.armen.dandur.models

import android.util.Log
import com.example.armen.dandur.util.DandurConstants
import com.google.gson.internal.LinkedTreeMap
import java.lang.NumberFormatException
import java.util.*
import kotlin.collections.ArrayList

class VideoItemModel(val response:LinkedTreeMap<String, Any>?) {
    var id:Double? = null
    var video_thmb:String? = null
    var videoerlMp4:String? = null
    var videoerlWebm:String? = null
    var title :String?= null
    var link:String?= null
    var likeCount:Double? = null
    var gif_url:String? =null
    var renderedTitle:Map<String, String>? = null
    var height:Int= 0
    var width:Float = 0.0f
    var userEntity:UserEntity? = null


    init {
        parseResponse(response)
    }

    private fun parseResponse(response:LinkedTreeMap<String, Any>?){
        if(response == null) {
            return
        }
            id = response["id"].toString().toDouble()
            video_thmb = response["video_thumb_src"].toString()

            val v1 = response["mp4_url"]
            if(v1 != null){
                videoerlMp4 = v1.toString()
            }
            val v2 = response["webm_vp8_url"]
            if(v2 != null) {
                videoerlWebm = v2.toString()
            }

            link =  response["link"].toString()

            renderedTitle = response["title"] as Map<String, String>?
            if(renderedTitle != null) {
                title = renderedTitle!!["rendered"]
            }
            val likes  = response["points"].toString()
            try {
                likeCount = likes.toDouble()
            }catch (e:NumberFormatException) {
                Log.i(" ---------------- ", e.message)
            }

            val g = response["gif_url"]
            if(g != null) {
                gif_url = g.toString()
            }

        val embedded: Map<*, *>? = if(response["_embedded"] != null) response["_embedded"] as Map<*, *> else null
        if(embedded != null){
         val futreMedian: ArrayList<*>? =if( embedded["wp:featuredmedia"] != null) embedded["wp:featuredmedia"] as ArrayList<*> else null
            if(futreMedian != null && futreMedian.size > 0) {
                val mediaType:Map<*,*>  = futreMedian.get(0) as Map<*, *>
                val mediaDetalis:Map<*,*>?  = if(mediaType["media_details"] != null)  mediaType["media_details"] as Map<*, *> else null
                if(mediaDetalis != null) {
                    val height:Double? = mediaDetalis["height"] as Double
                    val width:Double? = mediaDetalis["width"] as Double

                    if(width != null && width != 0.0) {
                        val result  = DandurConstants.deviceWith / width

                        if(result > 0) {
                            if (height != null) {
                                this.height = (height*result).toInt()
                            }
                        } else {
                            if(height != null) {
                                this.height = (height/result).toInt()
                            }
                        }
                    }



//                    if(height !=  null) {
//                        if (width != null) {
//                            if((width - DandurConstants.deviceWith) > 0) {
//                                this.height = (height - (width - DandurConstants.deviceWith)).toInt()
//                            } else if((width - DandurConstants.deviceWith) < 0) {
//                                this.height = (height + (DandurConstants.deviceWith)-width).toInt()
//                            } else {
//                                this.height = height.toInt()
//                            }
//                        }
//                    }
                    if(width != null) {
                        this.width = width.toFloat()
                    }
                }
            }
            val author:ArrayList<*>?  = embedded["author"] as ArrayList<*>
//
            if(author != null && author.size > 0) {
                val userEntityMap:LinkedTreeMap<*, *>  = author[0] as LinkedTreeMap<*, *>
                this.userEntity  = UserEntity(userEntityMap)
            }

            Log.i("ssssssssss", "heigth  ==== $height")
        }
    }
}
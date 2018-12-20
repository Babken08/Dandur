package com.example.armen.dandur.adapters

import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.allattentionhere.autoplayvideos.AAH_VideosAdapter
import android.widget.TextView
import com.allattentionhere.autoplayvideos.AAH_CustomViewHolder
import com.example.armen.dandur.R
import android.view.LayoutInflater
import android.widget.ProgressBar
import com.example.armen.dandur.util.MyApplication
import com.google.gson.internal.LinkedTreeMap
import com.squareup.picasso.Picasso
import java.util.ArrayList


class MyVideosAdapter : AAH_VideosAdapter() {

    var picasso:Picasso = Picasso.get()
    var list: ArrayList<LinkedTreeMap<String, Any>>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AAH_CustomViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.video_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AAH_CustomViewHolder, position: Int, payloads: MutableList<Any>) {
        val ob  = list?.get(position)

        val video_thmb:String? = ob?.get("video_thumb_src").toString()
        if(video_thmb != null && !video_thmb.isEmpty()){
            picasso.load(video_thmb).config(Bitmap.Config.RGB_565).into(holder.getAAH_ImageView());
        }




        val mp4_url:Any? = ob?.get("mp4_url")
        val videoerl:String? = mp4_url.toString()
        if(videoerl != null){
            holder.setVideoUrl(MyApplication.getProxy().getProxyUrl(videoerl+"")); // url should not be null
        } else {
            holder.setVideoUrl(""); // url should not be null
        }

        (holder as MyViewHolder).getAah_vi().setOnClickListener {
            if (holder.isMuted) {
                holder.unmuteVideo()
                holder.img_vol!!.setImageResource(R.drawable.ic_volume_mute_black_24dp)
            } else {
                holder.muteVideo()
                holder.img_vol!!.setImageResource(R.drawable.ic_volume_off_black_24dp)
            }
            holder.isMuted = !holder.isMuted
        }
    }


    override fun getItemCount(): Int {
        return if(list == null) 0 else list!!.size
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    inner class MyViewHolder(x: View) : AAH_CustomViewHolder(x) {

        var videoProgress:ProgressBar? = null
        var img_vol: ImageView ? = null
        var isMuted: Boolean = false //to mute/un-mute video (optional)

        init {
            img_vol = x.findViewById(R.id.img_vol)
            videoProgress = x.findViewById(R.id.video_progress)
        }

        override fun videoStarted() {
            super.videoStarted()
        }

        override fun pauseVideo() {
            super.pauseVideo()
        }
    }
}
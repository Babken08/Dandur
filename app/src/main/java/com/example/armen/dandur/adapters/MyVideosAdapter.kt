package com.example.armen.dandur.adapters

import android.app.Notification
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.allattentionhere.autoplayvideos.AAH_VideosAdapter
import com.allattentionhere.autoplayvideos.AAH_CustomViewHolder
import com.example.armen.dandur.R
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.example.armen.dandur.models.VideoItemModel
import com.example.armen.dandur.screen.VideoItemView
import com.example.armen.dandur.util.DandurConstants
import com.example.armen.dandur.util.MyApplication
import com.squareup.picasso.Picasso
import java.util.ArrayList
import kotlin.math.roundToInt


class MyVideosAdapter(val context: Context) : AAH_VideosAdapter() {

    interface MyVideosAdapterDelegate{
        fun shareFunctional(linkVideo:String)
        fun likeFunctional(id:Double)
    }
    var delegate:MyVideosAdapterDelegate?= null
    var picasso:Picasso = Picasso.get()
    var allVideoMuted = true
//    var list: ArrayList<LinkedTreeMap<String, Any>>? = null
//        set(value) {
//            field = value
//            notifyDataSetChanged()
//        }
    var list: ArrayList<VideoItemModel>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun changeMode() {
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AAH_CustomViewHolder {
//        val itemView = LayoutInflater.from(parent.context)
//                .inflate(R.layout.video_item, parent, false)

        val itemView:VideoItemView = VideoItemView(context)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AAH_CustomViewHolder, position: Int, payloads: MutableList<Any>) {
        val item  = holder.itemView as VideoItemView
        val holderView = (holder as MyViewHolder)

        val videoitemModel = list?.get(position)
        val gif_url  = videoitemModel?.gif_url
        val id  = videoitemModel?.id
        val title  = videoitemModel?.title
        val likeCount :Double? = videoitemModel?.likeCount
        val count  = likeCount?.roundToInt()
        val video_thmb:String? = videoitemModel?.video_thmb
        val videoUrlMp4:String? = videoitemModel?.videoerlMp4
        val videoUrlWebm:String? = videoitemModel?.videoerlWebm
        val link  = videoitemModel?.link
        var height = videoitemModel?.height
        val with = videoitemModel?.width
        val userEntity = videoitemModel?.userEntity


        item.configeItem(title, count, height, with)

        if(gif_url != null && !gif_url.isEmpty()){
            item.gifImage.visibility = View.VISIBLE
            item.videoLayout.visibility = View.GONE

            Glide.with(context).asGif().load(gif_url).into(item.gifImage)
        } else if (videoUrlMp4 != null && !videoUrlMp4.isEmpty()) {
            item.gifImage.visibility = View.GONE
            item.videoLayout.visibility = View.VISIBLE
            holderView.img_vol?.visibility = View.VISIBLE

            holder.imageUrl = video_thmb
            if(video_thmb != null && !video_thmb.isEmpty()){
                picasso.load(video_thmb).config(Bitmap.Config.RGB_565).into(holder.getAAH_ImageView());
            }
            holder.setVideoUrl(MyApplication.getProxy().getProxyUrl(videoUrlMp4)); // url should not be null
        } else {
            item.videoLayout.visibility = View.GONE
            item.gifImage.visibility = View.VISIBLE
            picasso.load(video_thmb).config(Bitmap.Config.RGB_565).into(item.gifImage)
            holderView.img_vol?.visibility = View.GONE
            //to do
        }

        if(userEntity != null) {
            if(userEntity.url != null) {
                picasso.load(userEntity.url).config(Bitmap.Config.RGB_565).into(item.authorImg)
            }
            if(userEntity.name != null) {
                item.authorText.text = "by " + userEntity.name
            }
        }
        holderView.getAah_vi().setOnClickListener {
//            if (holder.isMuted) {
//                holder.unmuteVideo()
//                holder.img_vol!!.setImageResource(R.drawable.ic_volume_mute_black_24dp)
//            } else {
//                holder.muteVideo()
//                holder.img_vol!!.setImageResource(R.drawable.ic_volume_off_black_24dp)
//            }
//            holder.isMuted = !holder.isMuted


            if(DandurConstants.isMutedVideo) {
                holder.unmuteVideo()
                holder.img_vol!!.setImageResource(R.drawable.ic_volume_mute_black_24dp)
                DandurConstants.isMutedVideo = false
            } else {
                holder.muteVideo()
                holder.img_vol!!.setImageResource(R.drawable.ic_volume_off_black_24dp)
                DandurConstants.isMutedVideo = true
            }
        }


        holder.share.setOnClickListener {
            delegate?.shareFunctional(link+"")
        }
        holder.like.setOnClickListener {
            if(id != null) {
                delegate?.likeFunctional(id)
            }
        }
    }


    override fun getItemCount(): Int {
        return if(list == null) 0 else list!!.size
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }
    inner class MyViewHolder(x: VideoItemView) : AAH_CustomViewHolder(x){

        var gifImageView: ImageView
        var like:FrameLayout
        var share :FrameLayout
        var handler :android.os.Handler?= null
        var videoProgress:ProgressBar? = null
        var img_vol: ImageView ? = null
        var isMuted: Boolean = false //to mute/un-mute video (optional)

        init {
            gifImageView = x.gifImage
            like = x.likeLayout
            share = x.shareLayout
            handler = android.os.Handler()
            videoProgress = x.videoPreogress
            img_vol = x.volumeImg
//            img_vol = x.findViewById(R.id.img_vol)
//            videoProgress = x.findViewById(R.id.video_progress)
        }

        override fun videoStarted() {
            if(DandurConstants.isMutedVideo) {
                muteVideo()
            } else {
                unmuteVideo()
            }
            handler?.post {

                videoProgress?.visibility = View.GONE
            }
            super.videoStarted()

        }
//
    override fun playVideo() {
         handler?.post {
             if(DandurConstants.isMutedVideo) {
                 img_vol!!.setImageResource(R.drawable.ic_volume_off_black_24dp)
             } else {
                 img_vol!!.setImageResource(R.drawable.ic_volume_mute_black_24dp)
             }
          videoProgress?.visibility = View.VISIBLE
        }
        super.playVideo()

}
//
//        override fun pauseVideo() {
//            super.pauseVideo()
//        }
    }


}
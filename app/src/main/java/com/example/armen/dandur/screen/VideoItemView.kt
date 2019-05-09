package com.example.armen.dandur.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.allattentionhere.autoplayvideos.AAH_VideoImage
import com.example.armen.dandur.R
import com.example.armen.dandur.util.DandurConstants
import com.google.gson.internal.LinkedTreeMap
import de.hdodenhof.circleimageview.CircleImageView

class VideoItemView(context:Context) : LinearLayout(context) {

    lateinit var title:TextView
    lateinit var videoLayout:FrameLayout
             lateinit var gifImage:ImageView
             lateinit var videoView: AAH_VideoImage
             lateinit var volumeImg:ImageView
             lateinit var videoPreogress:ProgressBar
//    lateinit var layoutType:LinearLayout
//             lateinit var typeText:TextView
//             lateinit var img:ImageView
//             lateinit var txtIMG:TextView
    lateinit var authorLayout: FrameLayout
             lateinit var authorImg:CircleImageView
             lateinit var authorText:TextView
    lateinit var layoutOther:LinearLayout
             lateinit var likeLayout:FrameLayout
                      lateinit var likeText:TextView
             lateinit var commentLayout:FrameLayout
                      lateinit var commentText:TextView
             lateinit var shareLayout:FrameLayout
                      lateinit var shareText:TextView
    lateinit var divider:View

    init {
        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
        this.layoutParams = params
        this.orientation = LinearLayout.VERTICAL

        createTitle()
        createVideoLayout()
        createGifImage()
        createAuthorLayout()
        createOtherLayout()
        createDivider()
    }

    @SuppressLint("ResourceAsColor")
    private fun createTitle() {
        title = TextView(context)
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(10.dp, 10.dp, 10.dp, 10.dp)
        title.layoutParams = params
        title.textSize = 20f
        title.text = "Post"
        if(DandurConstants.isDarkBackground) {
            title.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
        } else {
            title.setTextColor(ContextCompat.getColor(context, R.color.colorBlack))
        }
        this.addView(title)
    }


    ////video items
    private fun createVideoLayout() {
        videoLayout = FrameLayout(context)
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(10.dp, 10.dp, 10.dp, 10.dp)
        videoLayout.layoutParams = params

        createVideoView()
        createVolumeImage()
        createVideoProgress()
        this.addView(videoLayout)
    }

    fun createGifImage() {
        gifImage = ImageView(context)
        gifImage.visibility = View.GONE
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        gifImage.layoutParams = params
        params.setMargins(10.dp, 10.dp, 10.dp, 10.dp)
        this.addView(gifImage)
    }

    private fun createVideoView() {
        videoView = AAH_VideoImage(context)
//        videoView.minimumHeight = 200.dp
        val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(10.dp, 10.dp, 10.dp, 10.dp)
        videoLayout.addView(videoView)
    }
    private fun createVolumeImage() {
        volumeImg = ImageView(context)
        val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        params.leftMargin = 8.dp
        params.gravity = (Gravity.END or Gravity.BOTTOM)
        volumeImg.layoutParams = params
        volumeImg.setImageResource(R.drawable.ic_volume_mute_black_24dp)
        videoLayout.addView(volumeImg)
    }
    private fun createVideoProgress() {
        videoPreogress = ProgressBar(context)
        val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        params.gravity = Gravity.CENTER
        videoPreogress.layoutParams = params
        videoPreogress.visibility = View.GONE
        videoLayout.addView(videoPreogress)
    }
    /////video items
    //**********************************************
    /////// type items
//    private fun createTypeLayout() {
//        layoutType = LinearLayout(context)
//        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
//        params.setMargins(10.dp, 0.dp, 10.dp, 0.dp)
//        layoutType.layoutParams = params
//        layoutType.orientation = LinearLayout.HORIZONTAL
//
//        createTypeText()
//        createImg()
//        createTxtImg()
//        this.addView(layoutType)
//    }
//    @SuppressLint("ResourceAsColor")
//    private fun createTypeText() {
//        typeText = TextView(context)
//        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
//        params.rightMargin = 10.dp
//        typeText.layoutParams = params
//        typeText.textSize = 16f
//        typeText.setTextColor(R.color.colorBlack)
//        typeText.text = "Animal And Pets"
//        layoutType.addView(typeText)
//    }
//    private fun createImg() {
//        img = ImageView(context)
//        val params = LinearLayout.LayoutParams(24.dp, 24.dp)
//        params.rightMargin = 6.dp
//        img.layoutParams = params
//        img.setImageResource(R.drawable.icons_topic)
//        layoutType.addView(img)
//    }
//    @SuppressLint("ResourceAsColor")
//    private fun createTxtImg() {
//        txtIMG = TextView(context)
//        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
//        params.gravity = Gravity.CENTER_VERTICAL
//        typeText.layoutParams = params
//        txtIMG.text = "1111111111111111111111111"
//        txtIMG.textSize = 16f
//        txtIMG.setTextColor(R.color.colorBlack)
//        layoutType.addView(txtIMG)
//    }
    ////// Type items
    //******************************
    ///// author items
    private fun createAuthorLayout() {
        authorLayout = FrameLayout(context)
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(10.dp, 10.dp, 10.dp, 0)
        authorLayout.layoutParams = params

        createAuthoeImg()
        createAuthorText()
        this.addView(authorLayout)
    }
    private fun createAuthoeImg() {
        authorImg = CircleImageView(context)
        val params = FrameLayout.LayoutParams(44.dp, 44.dp)
        params.rightMargin = 5.dp
        authorImg.layoutParams = params
        authorImg.scaleType = ImageView.ScaleType.CENTER_CROP
        if(DandurConstants.isDarkBackground) {
            authorImg.setImageResource(R.drawable.ic_account_circle_white_24dp)
        } else {
            authorImg.setImageResource(R.drawable.ic_account_circle_black_24dp)
        }
        authorLayout.addView(authorImg)
    }
    @SuppressLint("ResourceAsColor")
    private fun createAuthorText() {
        authorText = TextView(context)
        val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        params.leftMargin = 54.dp
        params.gravity = Gravity.CENTER_VERTICAL
        authorText.layoutParams = params
        if(DandurConstants.isDarkBackground){
            authorText.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
        }else{
            authorText.setTextColor(ContextCompat.getColor(context, R.color.colorBlack))
        }
        authorText.text = "create by my"
        authorLayout.addView(authorText)

    }
    ///////author item
    /////*****************************
    //// like, comment, share
    private fun createOtherLayout() {
        layoutOther = LinearLayout(context)
        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.setMargins(10.dp, 10.dp, 10.dp, 10.dp)
        layoutOther.layoutParams = params
        layoutOther.orientation = LinearLayout.HORIZONTAL

        createLikeLayout()
//        createCommentLayout()
        createSharelayout()
        this.addView(layoutOther)
    }
    private fun createLikeLayout() {
        likeLayout = FrameLayout(context)
        val params = LinearLayout.LayoutParams(0.dp, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.weight = 1F
        likeLayout.layoutParams = params
        createLikeTaxt()
        layoutOther.addView(likeLayout)
    }
    @SuppressLint("ResourceAsColor")
    private fun createLikeTaxt() {
        likeText = TextView(context)
        val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
//        params.gravity = Gravity.CENTER
        likeText.layoutParams = params
        likeText.text = context.getString(R.string.like)
        likeText.gravity = Gravity.CENTER_VERTICAL
        likeText.compoundDrawablePadding = 10.dp
        likeText.setBackgroundResource(R.drawable.share_click_effect)

        if(DandurConstants.isDarkBackground){
            likeText.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
            likeText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.like_white, 0,0, 0)
        }else{
            likeText.setTextColor(ContextCompat.getColor(context, R.color.colorBlack))
            likeText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_thumb_up_black_24dp, 0,0, 0)
        }
        likeLayout.addView(likeText)
    }

    private fun createCommentLayout() {
        commentLayout = FrameLayout(context)
        val params = LinearLayout.LayoutParams(0.dp, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.weight = 1F
        commentLayout.layoutParams = params
        createCommentText()
        layoutOther.addView(commentLayout)
    }
    @SuppressLint("ResourceAsColor")
    private fun createCommentText() {
        commentText = TextView(context)
        val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        params.gravity = Gravity.CENTER
        commentText.layoutParams = params
        commentText.compoundDrawablePadding = 10.dp
        commentText.gravity = Gravity.CENTER_VERTICAL
        commentText.text = context.getString(R.string.comment)

        if(DandurConstants.isDarkBackground){
            commentText.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
            commentText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.comment_white, 0, 0, 0)
        } else {
            commentText.setTextColor(ContextCompat.getColor(context, R.color.colorBlack))
            commentText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_comment_black_24dp, 0, 0, 0)

        }
        commentLayout.addView(commentText)
    }
    private fun createSharelayout() {
        shareLayout = FrameLayout(context)
        val params = LinearLayout.LayoutParams(0.dp, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.weight = 1F
        shareLayout.layoutParams = params
        createSharreText()
        layoutOther.addView(shareLayout)
    }
    @SuppressLint("ResourceAsColor")
    private fun createSharreText() {
        shareText = TextView(context)
        val params = FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        params.gravity = Gravity.RIGHT
        shareText.layoutParams = params
        shareText.compoundDrawablePadding = 10.dp
        shareText.gravity = Gravity.CENTER_VERTICAL
        shareText.text = context.getString(R.string.share)

        shareText.setBackgroundResource(R.drawable.share_click_effect)

        if(DandurConstants.isDarkBackground){
            shareText.setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
            shareText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.share_white, 0, 0, 0)
        } else {
            shareText.setTextColor(ContextCompat.getColor(context, R.color.colorBlack))
            shareText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_share_black_24dp, 0, 0, 0)
        }

        shareLayout.addView(shareText)

    }
    //// like, comment, share

    private fun createDivider() {
        divider = View(context)
        val params  = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1)
        params.setMargins(10.dp, 10.dp, 10.dp, 10.dp)
        divider.layoutParams = params
        if(DandurConstants.isDarkBackground) {
            divider.setBackgroundResource(R.color.colorWhite)
        } else {
            divider.setBackgroundResource(R.color.colorBlack)
        }
        this.addView(divider)
    }

    fun configeItem(titleText:String?, likecount:Int?, height:Int?, with:Float?) {
       if(titleText != null) {
           title.text = titleText
       }
        if(likecount != null) {
           likeText.text = context.resources.getString(R.string.like, likecount)
        }
        if(DandurConstants.isMutedVideo){
            volumeImg.setImageResource(R.drawable.ic_volume_off_black_24dp)
        } else {
            volumeImg.setImageResource(R.drawable.ic_volume_mute_black_24dp)
        }


        val params:LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height!!)
        params.setMargins(10.dp, 10.dp, 10.dp, 10.dp)
        videoLayout.layoutParams = params
//        if(with != null && height != null) {
//            val params:LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height)
//            params.setMargins(10.dp, 10.dp, 10.dp, 10.dp)
//            videoLayout.layoutParams = params
////            val param = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, height)
////            param.setMargins(10.dp, 10.dp, 10.dp, 10.dp)
////            videoView.layoutParams = param
//        } else {
//            val params:LayoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
//            params.setMargins(10.dp, 10.dp, 10.dp, 10.dp)
//            videoLayout.layoutParams = params
//        }
//        val commentCount =  item?.get("comments_count").toString()
//        commentText.text = context.resources.getString(R.string.comment_text, commentCount)
    }

}

private val Int.dp: Int
    get()=(this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
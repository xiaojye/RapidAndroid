package com.jye.rapidandroid.demo

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.jye.rapidandroid.ui.image.preview.RapidImagePreview

/**
 * 描述：[类描述]
 * 创建人：jye-ixiaojye@163.com
 */
class RapidImagePreviewLoader : RapidImagePreview.ILoader {

    object Holder {
        val INSTANCE = RapidImagePreviewLoader()
    }

    companion object {
        fun getInstance() = Holder.INSTANCE
    }

    override fun loadImage(context: Context, uri: Uri, imageView: ImageView, progressView: View) {
        progressView.visibility = View.VISIBLE
        Glide.with(context)
            .load(uri)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    progressView.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    progressView.visibility = View.GONE
                    return false
                }
            })
            .into(imageView)
    }

    override fun loadGifImage(
        context: Context,
        uri: Uri,
        imageView: ImageView,
        progressView: View
    ) {
        progressView.visibility = View.VISIBLE
        Glide.with(context)
            .load(uri)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    progressView.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    progressView.visibility = View.GONE
                    return false
                }
            })
            .into(imageView)
    }
}
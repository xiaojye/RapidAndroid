package com.jye.rapidandroid.demo

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.jye.rapidandroid.ui.image.picker.RapidImagePicker

/**
 * 描述：[类描述]
 * 创建人：jye-ixiaojye@163.com
 */
class RapidImagePickerLoader : RapidImagePicker.ILoader {

    object Holder {
        val INSTANCE = RapidImagePickerLoader()
    }

    companion object {
        fun getInstance() = Holder.INSTANCE
    }

    override fun loadThumbnail(
        context: Context?,
        resize: Int,
        placeholder: Drawable?,
        imageView: ImageView?,
        uri: Uri?
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadAnimatedGifThumbnail(
        context: Context?,
        resize: Int,
        placeholder: Drawable?,
        imageView: ImageView?,
        uri: Uri?
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadImage(
        context: Context?,
        resizeX: Int,
        resizeY: Int,
        imageView: ImageView?,
        uri: Uri?
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadAnimatedGifImage(
        context: Context?,
        resizeX: Int,
        resizeY: Int,
        imageView: ImageView?,
        uri: Uri?
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun supportAnimatedGif(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
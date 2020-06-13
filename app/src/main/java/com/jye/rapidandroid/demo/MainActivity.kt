package com.jye.rapidandroid.demo

import android.net.Uri
import androidx.appcompat.app.AlertDialog
import androidx.viewpager.widget.ViewPager
import com.jye.rapidandroid.log.RapidLogger
import com.jye.rapidandroid.payment.RapidPayment
import com.jye.rapidandroid.payment.PayCallback
import com.jye.rapidandroid.ui.base.ToolbarBuilder
import com.jye.rapidandroid.ui.image.picker.RapidImagePicker
import com.jye.rapidandroid.ui.image.picker.PickerType
import com.jye.rapidandroid.ui.image.preview.RapidImagePreview
import com.jye.rapidandroid.ui.image.preview.bean.ImagePreviewInfo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun buildToolbar(): ToolbarBuilder? {
        return ToolbarBuilder()
            .setTitle("主页")
    }

    override fun getContentLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initEvent() {
        super.initEvent()
        main_btn_toast.setOnClickListener {
            showLongToast("Hello, Word!")
        }
        main_btn_image_preview.setOnClickListener {
            val imageInfoList = ArrayList<ImagePreviewInfo>()
            imageInfoList.add(ImagePreviewInfo("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2534506313,1688529724&fm=26&gp=0.jpg"))
            imageInfoList.add(ImagePreviewInfo("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587204478588&di=11c27f8e0016adfe7093c3b5dadbaf8a&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2Fd009b3de9c82d1587e249850820a19d8bd3e42a9.jpg"))
            imageInfoList.add(ImagePreviewInfo("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587204478588&di=6197636b29ac839f0cd054cbb95d0377&imgtype=0&src=http%3A%2F%2Fa4.att.hudong.com%2F21%2F09%2F01200000026352136359091694357.jpg"))
            imageInfoList.add(ImagePreviewInfo("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587204478588&di=aa167f97a1fbdcbeed5ad95f139f887d&imgtype=0&src=http%3A%2F%2Fa0.att.hudong.com%2F64%2F76%2F20300001349415131407760417677.jpg"))
            imageInfoList.add(ImagePreviewInfo("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1587205984538&di=3ef586c63e2f9eff7db9062e88eb005d&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171208%2F4afc910150064dde9c744f68c019bb23.gif"))
            RapidImagePreview.from(getContext())
                .imageList(imageInfoList)
                .currentIndex(0)
                .onPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        showShortToast("${position + 1}/${imageInfoList.size}")
                    }
                })
                .start(RapidImagePreviewLoader.getInstance())
        }
        main_btn_image_select.setOnClickListener {
            RapidImagePicker.from(this)
                .setType(PickerType.ALL)
                .setCameraEnable(true)
                .setMaxCount(10)
                .setResultCallback(object : RapidImagePicker.IResultCallback {
                    override fun onCancel() {
                        RapidLogger.e("onCancel")
                    }

                    override fun onError(e: Exception) {
                        RapidLogger.e("onError: ${e.message}")
                    }

                    override fun onResult(uriList: MutableList<Uri>) {
                        RapidLogger.e("onResult: ${uriList.map { it.toString() }}")
                    }
                })
                .start(RapidImagePickerLoader.getInstance())
        }
        main_btn_pay.setOnClickListener {
            AlertDialog.Builder(getContext())
                .setItems(arrayOf("微信支付", "支付宝支付")) { dialog, which ->
                    val payCallback = object : PayCallback {
                        override fun onSuccess() {
                            showShortToast("支付成功")
                        }

                        override fun onError(errorCode: Int, errorDesc: String) {
                            showShortToast("$errorDesc($errorCode)")
                        }

                        override fun onCancel() {
                            showShortToast("取消支付")
                        }
                    }
                    if (which == 0) {
                        RapidPayment.WxPayBuilder(getContext())
                            .setPayParams("123")
                            .doPay(payCallback)
                    } else {
                        RapidPayment.AliPayBuilder(getContext())
                            .setOrderInfo("123")
                            .doPay(payCallback)
                    }
                }
                .show()
        }
        main_btn_exit.setOnClickListener {
            DemoApplication.instance.exit()
        }
    }

}

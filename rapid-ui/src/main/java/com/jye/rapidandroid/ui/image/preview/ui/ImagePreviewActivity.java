package com.jye.rapidandroid.ui.image.preview.ui;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import com.jye.rapidandroid.ui.util.RapidScreenUtils;
import com.jye.rapidandroid.ui.widget.photoview.PhotoView;
import com.jye.rapidandroid.ui.R;
import com.jye.rapidandroid.ui.image.preview.PreviewOptions;
import com.jye.rapidandroid.ui.image.preview.bean.ImagePreviewInfo;
import com.jye.rapidandroid.ui.image.preview.widget.PreviewGestureView;

import java.util.List;

/**
 * 描述：[功能描述]
 * </p>
 * 作者：jye-ixiaojye@163.com
 * 日期：2018/12/14
 */
public class ImagePreviewActivity extends FragmentActivity {

    private View mRootView;
    private PreviewGestureView mPreviewGestureView;
    private ViewPager mViewPager;
    private TextView mPagerIndexTv;
    private ViewGroup mCustomViewLayout;

    private ImagePreviewPagerAdapter mPagerAdapter;

    private PreviewOptions<ImagePreviewInfo> mPreviewOptions;
    private int mPagerIndex;
    private List<ImagePreviewInfo> mImagePreviewInfoList;
    private ViewPager.OnPageChangeListener mOnPageChangeListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.rapid_image_preview_activity);
        RapidScreenUtils.setFullScreen(this);

        mPreviewOptions = PreviewOptions.getInstance();
        mPagerIndex = mPreviewOptions.currentIndex;
        mImagePreviewInfoList = mPreviewOptions.previewInfoList;
        mOnPageChangeListener = mPreviewOptions.onPageChangeListener;

        initView();

        initViewPager();
    }

    private void initView() {
        mRootView = findViewById(R.id.rapid_image_preview_rootView);
        mPreviewGestureView = findViewById(R.id.rapid_image_preview_gesture_view);
        mViewPager = findViewById(R.id.rapid_image_preview_vp);
        mPagerIndexTv = findViewById(R.id.rapid_image_preview_index_tv);
        mCustomViewLayout = findViewById(R.id.rapid_image_preview_custom_view_layout);

        mRootView.getBackground().setAlpha(255);

        if (mPreviewOptions.customShadeView != null) {
            mCustomViewLayout.removeAllViews();
            mCustomViewLayout.addView(mPreviewOptions.customShadeView);
            //当设置了自定义遮盖层时隐藏索引指示文本
            mPagerIndexTv.setVisibility(View.GONE);
        }

        mPreviewGestureView.setOnGestureListener(new PreviewGestureView.OnCanSwipeListener() {
            @Override
            public boolean canSwipe() {
                PhotoView imageView = mPagerAdapter.getPrimaryImageView();
                return !(imageView.getScale() != 1.0);
            }
        });

        mPreviewGestureView.setOnSwipeListener(new PreviewGestureView.OnSwipeListener() {
            @Override
            public void downSwipe() {
                ImagePreviewActivity.super.finish();
                ImagePreviewActivity.this.overridePendingTransition(0, 0);
            }

            @Override
            public void onSwiping(float deltaY) {
                mPagerIndexTv.setVisibility(View.GONE);
                mCustomViewLayout.setVisibility(View.GONE);

                // 滑动的最小距离（自行定义，you happy jiu ok）
                int minHeight = 0;
                // 滑动的最大距离（自行定义，you happy jiu ok）
                int maxHeight = mPreviewGestureView.getHeight();

                int alpha;
                // 滑动距离小于定义得最小距离
                if (deltaY <= minHeight) {
                    alpha = minHeight;
                } // 滑动距离大于定义得最大距离
                else if (deltaY > maxHeight) {
                    alpha = 255;
                } else {
                    // （滑动距离 - 开始变化距离）：最大限制距离 = mAlpha ：255
                    alpha = (int) (255 - ((deltaY - minHeight) * 255 / (maxHeight - minHeight)));
                }
                mRootView.getBackground().setAlpha(alpha);
            }

            @Override
            public void overSwipe() {
                if (mPreviewOptions.customShadeView == null) {
                    mPagerIndexTv.setVisibility(View.VISIBLE);
                }
                mCustomViewLayout.setVisibility(View.VISIBLE);

                mRootView.getBackground().setAlpha(255);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void initViewPager() {
        mPagerAdapter = new ImagePreviewPagerAdapter(this, mPreviewOptions);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setCurrentItem(mPagerIndex);
        mViewPager.setOffscreenPageLimit(mImagePreviewInfoList.size());
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
            }

            @Override
            public void onPageSelected(int position) {
                mPagerIndex = position;
                mPagerIndexTv.setText(position + 1 + "/" + mImagePreviewInfoList.size());
                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (mOnPageChangeListener != null) {
                    mOnPageChangeListener.onPageScrollStateChanged(state);
                }
            }
        });
        mPagerIndexTv.setText(mPagerIndex + 1 + "/" + mImagePreviewInfoList.size());
    }

    @Override
    public void finish() {
        mPagerIndexTv.setVisibility(View.GONE);
        mCustomViewLayout.setVisibility(View.GONE);
        super.finish();
        this.overridePendingTransition(0, R.anim.rapid_image_preview_exit_anim);
    }

}

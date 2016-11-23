package com.yuanchuangli.mreader.ui.myview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

/**
 * 通过滤镜和监听onTouchEvent事件来达到变暗效果的方法
 * 通过监听Down事件设置滤镜，监听Up和Cancel事件去掉滤镜，以此达到图片变暗效果。
 * 但是这个方法也有个很麻烦的缺点：由于拦截了Touch事件，会导致onClick和onLongClick事件还有其他很多事件都无法正常触发
 * 当该View是作为ListView的内部View时，情况将更加麻烦，因此要慎用
 * Created by Blank on 2016/10/27 11:53
 */

public class ThumbnailView extends ImageView {
    public ThumbnailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                //在按下事件中设置滤镜
                setFilter();
                break;
            case MotionEvent.ACTION_UP:
                //由于捕获了Touch事件，需要手动触发Click事件
                performClick();
            case MotionEvent.ACTION_CANCEL:
                //在CANCEL和UP事件中清除滤镜
                removeFilter();
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * 设置滤镜
     */
    private void setFilter() {
        //先获取设置的src图片
        Drawable drawable = getDrawable();
        //当src图片为Null，获取背景图片
        if (drawable == null) {
            drawable = getBackground();
        }
        if (drawable != null) {
            //设置滤镜
            drawable.setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
            ;
        }
    }

    /**
     * 清除滤镜
     */
    private void removeFilter() {
        //先获取设置的src图片
        Drawable drawable = getDrawable();
        //当src图片为Null，获取背景图片
        if (drawable == null) {
            drawable = getBackground();
        }
        if (drawable != null) {
            //清除滤镜
            drawable.clearColorFilter();
        }
    }


}

package com.aveteam.avewallpaper.Model;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Lorenzo on 23/08/2016.
 */
public class SquardeImage extends ImageView {
        public SquardeImage(Context context) {
            super(context);
        }

        public SquardeImage(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public SquardeImage(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
        }
    }

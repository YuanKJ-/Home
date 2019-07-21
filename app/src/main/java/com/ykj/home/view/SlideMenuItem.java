package com.ykj.home.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;

import com.ykj.home.R;

public class SlideMenuItem extends ConstraintLayout {

    public SlideMenuItem(Context context) {
        this(context, null);
    }

    public SlideMenuItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideMenuItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.item_slide_menu, this);
    }


}

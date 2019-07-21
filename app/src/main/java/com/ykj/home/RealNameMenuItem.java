package com.ykj.home;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;

public class RealNameMenuItem extends ConstraintLayout {

    public RealNameMenuItem(Context context) {
        this(context, null);
    }

    public RealNameMenuItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RealNameMenuItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.real_name_slide_menu, this);
    }


}

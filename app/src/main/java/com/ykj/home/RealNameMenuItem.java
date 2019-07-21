package com.ykj.home;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;

public class RealNameMenuItem extends ConstraintLayout {

    private View realNameSuccess;
    private View realNameFail;

    public RealNameMenuItem(Context context) {
        this(context, null);
    }

    public RealNameMenuItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RealNameMenuItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.real_name_slide_menu, this);
        realNameSuccess = findViewById(R.id.real_name_success);
        realNameFail = findViewById(R.id.real_name_fail);
        setRealName(true);
    }

    public void setRealName(boolean success) {
        if (success) {
            realNameSuccess.setVisibility(VISIBLE);
            realNameFail.setVisibility(GONE);
        } else {
            realNameSuccess.setVisibility(GONE);
            realNameFail.setVisibility(VISIBLE);
        }
    }

}

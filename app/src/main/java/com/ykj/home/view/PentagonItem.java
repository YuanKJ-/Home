package com.ykj.home.view;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ykj.home.R;

public class PentagonItem extends LinearLayout {

    private TextView textView;

    public PentagonItem(Context context) {
        this(context, null);
    }

    public PentagonItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PentagonItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.item_pentagon, this);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        textView = findViewById(R.id.text);
    }

    public void setText(String text) {
        textView.setText(text);
    }
}

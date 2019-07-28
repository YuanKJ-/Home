package com.ykj.home;

import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ykj.home.utils.DensityUtil;
import com.ykj.home.utils.StatusBarUtil;
import com.ykj.home.view.PentagonItem;

public class MainActivity extends AppCompatActivity {

    private int COUNT = 14;
    private int oldCount = COUNT;

    private String[] titles = new String[]{
            "线上直销占比",
            "礼包销售量",
            "会员发展数量",
            "信息完整度",
            "出租率",
            "用户评分",
            "差评回复率",
            "线上直销占比",
            "礼包销售量",
            "会员发展数量",
            "信息完整度",
            "出租率",
            "用户评分",
            "差评回复率",
            "线上直销占比",
            "礼包销售量",
            "会员发展数量",
            "信息完整度",
            "出租率",
            "用户评分",
            "差评回复率"
    };

    private PentagonView pentagonView;
    private ConstraintLayout pentagonLayout;

    private TextView count;
    private Button add;
    private Button sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarUtil.setTranslucentStatus(this);
        StatusBarUtil.setStatusBarDarkTheme(this, true);
        pentagonLayout = findViewById(R.id.pentagon_layout);
        pentagonView = findViewById(R.id.pentagon);
        count = findViewById(R.id.count);
        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);
        count.setText(String.valueOf(COUNT));
        pentagonView.setCount(COUNT);
        addView();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldCount = COUNT;
                COUNT++;
                refresh();
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldCount = COUNT;
                COUNT--;
                refresh();
            }
        });
    }

    private void addView() {
        for (int i = 0; i < COUNT; i++) {
            PentagonItem textView = new PentagonItem(this);
            textView.setText(titles[i]);
            int id = i;
            if (android.os.Build.VERSION.SDK_INT >= 17) {
                id = View.generateViewId();
            }
            textView.setId(id);
            pentagonLayout.addView(textView);
            ConstraintSet set = new ConstraintSet();
            set.clone(pentagonLayout);
            set.constrainCircle(textView.getId(), R.id.pentagon,
                    DensityUtil.dip2px(120f), 360f / COUNT * i);
            set.applyTo(pentagonLayout);
        }
    }

    private void refresh() {
        count.setText(String.valueOf(COUNT));
        pentagonView.setCount(COUNT);
        pentagonLayout.removeViews(4, oldCount);
        addView();
    }
}

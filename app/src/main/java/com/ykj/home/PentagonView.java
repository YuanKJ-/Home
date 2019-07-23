package com.ykj.home;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.ykj.home.utils.DensityUtil;


public class PentagonView extends View {

    private static final int DATA_COUNT = 6; //多边形维度，这里是六边形
    private static final float RADIAN = (float) (Math.PI * 2 / DATA_COUNT); //每个维度的弧度
    private static final float RADIUS = DensityUtil.dip2px(80f); //一条星射线的长度,发散的六条线,80dp
    private static final float CIRCLE_STROKE_WIDTH = DensityUtil.dip2px(1f); //圆环描边1dp
    private static final float MAX_VALUE = 10; //每个维度的最大值

    private int centerX; //中心坐标 X
    private int centerY; //中心坐标 Y
    private int[] data = {5, 9, 4, 2, 3, 5}; //五个维度的数据值
    private int sumVal = 85; //总数值

    private Path basicPentagonPath; //记录基础六边形的路径
    private Paint basicPentagonPaint; //绘制基础六边形的画笔
    private Paint basicCircleFillPaint; //绘制基础填充圆的画笔
    private Paint basicCirclePaint; //绘制基础圆环的画笔
    private Paint basicDashCirclePaint; //绘制基础虚线圆环的画笔

    private Path valuePentagonPath; //能力值六边形的路径
    private Paint valuePentagonPaint; //绘制能力值六边形画笔
    private Paint valueStrokePaint; //能力值描边画笔

    private Paint valueTextPaint;//绘制文字的画笔

    public PentagonView(Context context) {
        this(context, null);
    }

    public PentagonView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PentagonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //初始化白色六边形画笔
        basicPentagonPaint = new Paint();
        basicPentagonPaint.setAntiAlias(true);
        basicPentagonPaint.setColor(Color.WHITE);
        basicPentagonPaint.setStyle(Paint.Style.FILL);
        //初始化基础填充圆画笔
        basicCircleFillPaint = new Paint();
        basicCircleFillPaint.setAntiAlias(true);
        basicCircleFillPaint.setStyle(Paint.Style.FILL);
        basicCircleFillPaint.setColor(Color.parseColor("#FBFBFB"));
        //初始化基础圆环画笔
        basicCirclePaint = new Paint();
        basicCirclePaint.setAntiAlias(true);
        basicCirclePaint.setStyle(Paint.Style.STROKE);
        basicCirclePaint.setStrokeWidth(CIRCLE_STROKE_WIDTH); //1dp
        basicCirclePaint.setColor(Color.parseColor("#DEDEDE"));
        //初始化基础虚线圆环画笔
        basicDashCirclePaint = new Paint(basicCirclePaint);
        basicDashCirclePaint.setPathEffect(new DashPathEffect(new float[]{25, 15}, 0));

        //初始化能力值六边形画笔
        valuePentagonPaint = new Paint();
        valuePentagonPaint.setAntiAlias(true);
        valuePentagonPaint.setColor(getResources().getColor(R.color.M1_20));
        valuePentagonPaint.setStyle(Paint.Style.FILL);
        //初始化能力值描边画笔
        valueStrokePaint = new Paint(valuePentagonPaint);
        valueStrokePaint.setStrokeWidth(CIRCLE_STROKE_WIDTH); //1dp
        valueStrokePaint.setColor(getResources().getColor(R.color.M1));
        valueStrokePaint.setStyle(Paint.Style.STROKE);

        //初始化能力值Text画笔
        valueTextPaint = new TextPaint();
        valueTextPaint.setAntiAlias(true);
        valueTextPaint.setTextAlign(Paint.Align.CENTER);
        valueTextPaint.setTextSize(DensityUtil.dip2px(24f));
        valueTextPaint.setColor(getResources().getColor(R.color.M1));

        //初始化白色五边形路径
        basicPentagonPath = new Path();
        //初始化红色五边形路径
        valuePentagonPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int defaultSize = (int) Math.ceil(RADIUS * 2 + CIRCLE_STROKE_WIDTH);
        int width = measureDimension(defaultSize, widthMeasureSpec);
        int height = measureDimension(defaultSize, heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        drawBasicPentagon(canvas);//绘制白色六边形和圆环
        drawRay(canvas);//绘制六条星射线
        drawValuePentagon(canvas); //绘制数值六边形
        drawValueText(canvas);
    }

    /**
     * 绘制背景
     *
     * @param canvas canvas
     */
    private void drawBasicPentagon(Canvas canvas) {
        //绘制填充圆
        canvas.drawCircle(centerX, centerY, RADIUS, basicCircleFillPaint);
        //绘制底图六边形
        basicPentagonPath.reset();
        float tmpRadius = RADIUS - DensityUtil.dip2px(20f);
        for (int i = 0; i < DATA_COUNT; i++) {//绘制一层
            if (i == 0) {
                basicPentagonPath.moveTo(getPoint(i, tmpRadius).x, getPoint(i, tmpRadius).y);
            } else {
                basicPentagonPath.lineTo(getPoint(i, tmpRadius).x, getPoint(i, tmpRadius).y);
            }
        }
        basicPentagonPath.close();
        canvas.drawPath(basicPentagonPath, basicPentagonPaint);
        //绘制最外层圆环
        canvas.drawCircle(centerX, centerY, RADIUS, basicCirclePaint);
        //绘制内层虚线圆环
        float dashTmpRadius = RADIUS;
        for (int i = 0; i < 2; i++) {
            dashTmpRadius -= DensityUtil.dip2px(20f);
            canvas.drawCircle(centerX, centerY, dashTmpRadius, basicDashCirclePaint);
        }
    }

    /**
     * 绘制星射线
     *
     * @param canvas canvas
     */
    private void drawRay(Canvas canvas) {
        //绘制星射线
        for (int i = 0; i < DATA_COUNT; i++) {
            Point point = getPoint(i, RADIUS);
            canvas.drawLine(centerX, centerY, point.x, point.y, basicDashCirclePaint);
        }
    }

    /**
     * 绘制数值六边形
     *
     * @param canvas canvas
     */
    private void drawValuePentagon(Canvas canvas) {
        valuePentagonPath.reset();
        for (int i = 0; i < DATA_COUNT; i++) {
            float percent = data[i] / MAX_VALUE;//数据值与最大值的百分比
            Point point = getPoint(i, RADIUS, 0, percent);
            if (i == 0) {
                valuePentagonPath.moveTo(point.x, point.y);
            } else {
                valuePentagonPath.lineTo(point.x, point.y);
            }
        }
        valuePentagonPath.close();
        //能力值填充颜色
        canvas.drawPath(valuePentagonPath, valuePentagonPaint);
        //能力值描边
        canvas.drawPath(valuePentagonPath, valueStrokePaint);
    }

    /**
     * 绘制得分总值
     *
     * @param canvas
     */
    private void drawValueText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = valueTextPaint.getFontMetrics();
        float y = centerY + (Math.abs(fontMetrics.ascent) - fontMetrics.descent) / 2;
        canvas.drawText(String.valueOf(sumVal), centerX, y, valueTextPaint);
    }

    public Point getPoint(int position, float radius) {
        return getPoint(position, radius, 0, 1);
    }

    /**
     * 正上方的顶点为第一个点，顺时针计算，position 依次是 0, 1, 2, 3, 4, 5
     *
     * @param position    顶点的位置
     * @param radius      半径
     * @param radarMargin 边距
     * @param percent     星射线长度的百分比,用于计算六边形的顶点
     * @return
     */
    public Point getPoint(int position, float radius, float radarMargin, float percent) {
        int x = 0;
        int y = 0;
        switch (position) {
            case 0://第一象限,正上方坐标
                x = centerX;
                y = (int) (centerY - (radius + radarMargin) * percent);
                break;
            case 1://第二象限,右上角顶点的坐标
                x = (int) (centerX + (radius + radarMargin) * Math.cos(RADIAN / 2) * percent);
                y = (int) (centerY - (radius + radarMargin) * Math.sin(RADIAN / 2) * percent);
                break;
            case 2://第三象限,右下角顶点的坐标
                x = (int) (centerX + (radius + radarMargin) * Math.cos(RADIAN / 2) * percent);
                y = (int) (centerY + (radius + radarMargin) * Math.sin(RADIAN / 2) * percent);
                break;
            case 3://第四象限,正下方的坐标
                x = centerX;
                y = (int) (centerY + (radius + radarMargin) * percent);
                break;
            case 4:// 第五象限,左下角顶点的坐标
                x = (int) (centerX - (radius + radarMargin) * Math.cos(RADIAN / 2) * percent);
                y = (int) (centerY + (radius + radarMargin) * Math.sin(RADIAN / 2) * percent);
                break;
            case 5://第六象限,左上角顶点的坐标
                x = (int) (centerX - (radius + radarMargin) * Math.cos(RADIAN / 2) * percent);
                y = (int) (centerY - (radius + radarMargin) * Math.sin(RADIAN / 2) * percent);
                break;
        }
        return new Point(x, y);
    }

    //计算wrap_content下准确宽高
    protected int measureDimension(int defaultSize, int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        //1. layout给出了确定的值，比如：100dp
        //2. layout使用的是match_parent，但父控件的size已经可以确定了，比如设置的是具体的值或者match_parent
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize; //建议：result直接使用确定值
        }
        //1. layout使用的是wrap_content
        //2. layout使用的是match_parent,但父控件使用的是确定的值或者wrap_content
        else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(defaultSize, specSize); //建议：result不能大于specSize
        }
        //UNSPECIFIED,没有任何限制，所以可以设置任何大小
        //多半出现在自定义的父控件的情况下，期望由自控件自行决定大小
        else {
            result = defaultSize;
        }
        return result;
    }
}
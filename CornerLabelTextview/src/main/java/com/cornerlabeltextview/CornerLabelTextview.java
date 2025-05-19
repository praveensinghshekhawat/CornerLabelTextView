package com.cornerlabeltextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import io.github.praveensinghshekhawat.cornerlabeltextview.R;

public class CornerLabelTextview extends View {

    public static final int MODE_LEFT = 0;
    public static final int MODE_RIGHT = 1;
    public static final int MODE_LEFT_BOTTOM = 2;
    public static final int MODE_RIGHT_BOTTOM = 3;
    public static final int MODE_LEFT_TRIANGLE = 4;
    public static final int MODE_RIGHT_TRIANGLE = 5;
    public static final int MODE_LEFT_BOTTOM_TRIANGLE = 6;
    public static final int MODE_RIGHT_BOTTOM_TRIANGLE = 7;

    public static final int ROTATE_ANGLE = 45;
    private Paint mPaint;
    private TextPaint mTextPaint;
    private float mCornerLabelLength = 40;
    private float mTextSize = 16;
    private int mCornerLabelBackgroundColor = Color.TRANSPARENT;
    private int mTextColor = Color.WHITE;
    private String mCornerLabelText = "";
    private int mMode = MODE_LEFT;

    public CornerLabelTextview(Context context) {
        this(context, null);
    }

    public CornerLabelTextview(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CornerLabelTextview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public CornerLabelTextview(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public void init(AttributeSet attrs) {
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.CornerLabelTextview);

        mTextSize = array.getDimension(R.styleable.CornerLabelTextview_cornerLabelTextSize, mTextSize);
        mTextColor = array.getColor(R.styleable.CornerLabelTextview_cornerLabelTextColor, mTextColor);
        mCornerLabelLength = array.getDimension(R.styleable.CornerLabelTextview_cornerLabelLength, mCornerLabelLength);
        mCornerLabelBackgroundColor = array.getColor(R.styleable.CornerLabelTextview_cornerLabelBackgroundColor, mCornerLabelBackgroundColor);

        if (array.hasValue(R.styleable.CornerLabelTextview_cornerLabelText)) {
            mCornerLabelText = array.getString(R.styleable.CornerLabelTextview_cornerLabelText);
        }

        if (array.hasValue(R.styleable.CornerLabelTextview_cornerLabelMode)) {
            mMode = array.getInt(R.styleable.CornerLabelTextview_cornerLabelMode, 0);
        }
        array.recycle();

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        mPaint.setAntiAlias(true);
        mPaint.setColor(mCornerLabelBackgroundColor);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBackground(canvas);
        drawText(canvas);
    }

    private void drawBackground(Canvas canvas) {
        Path path = new Path();
        int w = getWidth();
        int h = getHeight();

        if (w != h) throw new IllegalStateException("CornerLabelTextview's width must equal to height");

        switch (mMode) {
            case MODE_LEFT:
                path = getModeLeftPath(path, w, h);
                break;
            case MODE_RIGHT:
                path = getModeRightPath(path, w, h);
                break;
            case MODE_LEFT_BOTTOM:
                path = getModeLeftBottomPath(path, w, h);
                break;
            case MODE_RIGHT_BOTTOM:
                path = getModeRightBottomPath(path, w, h);
                break;
            case MODE_LEFT_TRIANGLE:
                path = getModeLeftTrianglePath(path, w, h);
                break;
            case MODE_RIGHT_TRIANGLE:
                path = getModeRightTrianglePath(path, w, h);
                break;
            case MODE_LEFT_BOTTOM_TRIANGLE:
                path = getModeLeftBottomTrianglePath(path, w, h);
                break;
            case MODE_RIGHT_BOTTOM_TRIANGLE:
                path = getModeRightBottomTrianglePath(path, w, h);
                break;
        }
        path.close();
        canvas.drawPath(path, mPaint);
        canvas.save();
    }

    private Path getModeLeftPath(Path path, int w, int h) {
        path.moveTo(w, 0);
        path.lineTo(0, h);
        path.lineTo(0, h - mCornerLabelLength);
        path.lineTo(w - mCornerLabelLength, 0);
        return path;
    }

    private Path getModeRightPath(Path path, int w, int h) {
        path.lineTo(w, h);
        path.lineTo(w, h - mCornerLabelLength);
        path.lineTo(mCornerLabelLength, 0);
        return path;
    }

    private Path getModeLeftBottomPath(Path path, int w, int h) {
        path.lineTo(w, h);
        path.lineTo(w - mCornerLabelLength, h);
        path.lineTo(0, mCornerLabelLength);
        return path;
    }

    private Path getModeRightBottomPath(Path path, int w, int h) {
        path.moveTo(0, h);
        path.lineTo(mCornerLabelLength, h);
        path.lineTo(w, mCornerLabelLength);
        path.lineTo(w, 0);
        return path;
    }

    private Path getModeLeftTrianglePath(Path path, int w, int h) {
        path.lineTo(0,h);
        path.lineTo(w,0);
        return path;
    }

    private Path getModeRightTrianglePath(Path path, int w, int h) {
        path.lineTo(w,0);
        path.lineTo(w,h);
        return path;
    }

    private Path getModeLeftBottomTrianglePath(Path path, int w, int h) {
        path.lineTo(w,h);
        path.lineTo(0,h);
        return path;
    }

    private Path getModeRightBottomTrianglePath(Path path, int w, int h) {
        path.moveTo(0,h);
        path.lineTo(w,h);
        path.lineTo(w,0);
        return path;
    }

    private void drawText(Canvas canvas) {
        int w = (int) (canvas.getWidth() - mCornerLabelLength / 2);
        int h = (int) (canvas.getHeight() - mCornerLabelLength / 2);
        float[] xy = calculateXY(canvas,w, h);
        float toX = xy[0];
        float toY = xy[1];
        float centerX = xy[2];
        float centerY = xy[3];
        float angle = xy[4];

        canvas.rotate(angle, centerX , centerY );

        canvas.drawText(mCornerLabelText, toX, toY, mTextPaint);
    }

    private float[] calculateXY(Canvas canvas,int w, int h) {
        float[] xy = new float[5];
        Rect rect = null;
        RectF rectF = null;
        int offset = (int) (mCornerLabelLength / 2);
        switch (mMode) {
            case MODE_LEFT_TRIANGLE:
            case MODE_LEFT:
                rect = new Rect(0, 0, w, h);
                rectF = new RectF(rect);
                rectF.right = mTextPaint.measureText(mCornerLabelText, 0, mCornerLabelText.length());
                rectF.bottom = mTextPaint.descent() - mTextPaint.ascent();
                rectF.left += (rect.width() - rectF.right) / 2.0f;
                rectF.top += (rect.height() - rectF.bottom) / 2.0f;
                xy[0] = rectF.left;
                xy[1] = rectF.top - mTextPaint.ascent();
                xy[2] = w / 2;
                xy[3] = h / 2;
                xy[4] = -ROTATE_ANGLE;
                break;
            case MODE_RIGHT_TRIANGLE:
            case MODE_RIGHT:
                rect = new Rect(offset, 0, w + offset, h);
                rectF = new RectF(rect);
                rectF.right = mTextPaint.measureText(mCornerLabelText, 0, mCornerLabelText.length());
                rectF.bottom = mTextPaint.descent() - mTextPaint.ascent();
                rectF.left += (rect.width() - rectF.right) / 2.0f;
                rectF.top += (rect.height() - rectF.bottom) / 2.0f;
                xy[0] = rectF.left;
                xy[1] = rectF.top - mTextPaint.ascent();
                xy[2] = w / 2 + offset;
                xy[3] = h / 2;
                xy[4] = ROTATE_ANGLE;
                break;
            case MODE_LEFT_BOTTOM_TRIANGLE:
            case MODE_LEFT_BOTTOM:
                rect = new Rect(0, offset, w, h+offset);
                rectF = new RectF(rect);
                rectF.right = mTextPaint.measureText(mCornerLabelText, 0, mCornerLabelText.length());
                rectF.bottom = mTextPaint.descent() - mTextPaint.ascent();
                rectF.left += (rect.width() - rectF.right) / 2.0f;
                rectF.top += (rect.height() - rectF.bottom) / 2.0f;

                xy[0] = rectF.left;
                xy[1] = rectF.top - mTextPaint.ascent();
                xy[2] = w / 2;
                xy[3] = h / 2 + offset;
                xy[4] = ROTATE_ANGLE;
                break;
            case MODE_RIGHT_BOTTOM_TRIANGLE:
            case MODE_RIGHT_BOTTOM:
                rect = new Rect(offset, offset, w+offset, h+offset);
                rectF = new RectF(rect);
                rectF.right = mTextPaint.measureText(mCornerLabelText, 0, mCornerLabelText.length());
                rectF.bottom = mTextPaint.descent() - mTextPaint.ascent();
                rectF.left += (rect.width() - rectF.right) / 2.0f;
                rectF.top += (rect.height() - rectF.bottom) / 2.0f;
                xy[0] = rectF.left;
                xy[1] = rectF.top - mTextPaint.ascent();
                xy[2] = w / 2 + offset;
                xy[3] = h / 2 + offset;
                xy[4] = -ROTATE_ANGLE;
                break;
        }
        return xy;
    }

    public CornerLabelTextview setText(String str) {
        mCornerLabelText = str;
        postInvalidate();
        return this;
    }

    public CornerLabelTextview setText(int res) {
        String str = getResources().getString(res);
        if (!TextUtils.isEmpty(str)) {
            setText(str);
        }
        return this;
    }

    public String getText() {
        return mCornerLabelText;
    }

    public CornerLabelTextview setCornerLabelBackgroundColor(int color) {
        mCornerLabelBackgroundColor = color;
        mPaint.setColor(mCornerLabelBackgroundColor);
        postInvalidate();
        return this;
    }

    public CornerLabelTextview setTextColor(int color) {
        mTextColor = color;
        mTextPaint.setColor(mTextColor);
        postInvalidate();
        return this;
    }

    /**
     * @param mode :
     *             CornerLabelTextview.MODE_LEFT : top left
     *             CornerLabelTextview.MODE_RIGHT :top right
     * @return this
     */
    public CornerLabelTextview setMode(int mode) {
        if (mMode > MODE_RIGHT_BOTTOM_TRIANGLE || mMode < 0)
            throw new IllegalArgumentException(mode + "is illegal argument ,please use right value");
        this.mMode = mode;
        postInvalidate();
        return this;
    }

    public int getMode() {
        return mMode;
    }

    public CornerLabelTextview setTextSize(int size) {
        this.mTextSize = size;
        mTextPaint.setTextSize(mTextSize);
        postInvalidate();
        return this;
    }

    /**
     * set cornerLabel space length
     *
     * @param length
     * @return this
     */
    public CornerLabelTextview setCornerLabelLength(int length) {
        mCornerLabelLength = length;
        postInvalidate();
        return this;
    }

}
package io.github.praveensinghshekhawat;

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

    public static final int ROTATE_ANGLE = 45;
    private Paint mPaint;
    private TextPaint mTextPaint;
    private float mCornerLabelLength = 40;
    private float mTextSize = 16;
    private int mCornerLabelBackgroundColor = Color.TRANSPARENT;
    private int mTextColor = Color.WHITE;
    private String mCornerLabelText = "";
    private LabelMode mMode = LabelMode.MODE_LEFT; // Default mode, change as needed

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
            mMode = LabelMode.fromInt(array.getInt(R.styleable.CornerLabelTextview_cornerLabelMode, 0));
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
        //   Path path = new Path();
        int w = getWidth();
        int h = getHeight();

        if (w != h)
            throw new IllegalStateException("CornerLabelTextview's width must equal to height");

        Path path = new Path();
        path = mMode.getPath(this, path, w, h); // Cleaner, no switch-case

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
        path.lineTo(0, h);
        path.lineTo(w, 0);
        return path;
    }

    private Path getModeRightTrianglePath(Path path, int w, int h) {
        path.lineTo(w, 0);
        path.lineTo(w, h);
        return path;
    }

    private Path getModeLeftBottomTrianglePath(Path path, int w, int h) {
        path.lineTo(w, h);
        path.lineTo(0, h);
        return path;
    }

    private Path getModeRightBottomTrianglePath(Path path, int w, int h) {
        path.moveTo(0, h);
        path.lineTo(w, h);
        path.lineTo(w, 0);
        return path;
    }

    private void drawText(Canvas canvas) {
        int w = (int) (canvas.getWidth() - mCornerLabelLength / 2);
        int h = (int) (canvas.getHeight() - mCornerLabelLength / 2);
        int offset = (int) (mCornerLabelLength / 2);
        float[] xy = mMode.calculateXY(this, w, h, offset);
        float toX = xy[0];
        float toY = xy[1];
        float centerX = xy[2];
        float centerY = xy[3];
        float angle = xy[4];

        canvas.rotate(angle, centerX, centerY);
        canvas.drawText(mCornerLabelText, toX, toY, mTextPaint);
    }

    private float[] buildXY(Rect rect, float centerX, float centerY, float rotation_angle) {
        float[] xy = new float[5];
        RectF rectF = new RectF(rect);
        rectF.right = mTextPaint.measureText(mCornerLabelText, 0, mCornerLabelText.length());
        rectF.bottom = mTextPaint.descent() - mTextPaint.ascent();
        rectF.left += (rect.width() - rectF.right) / 2.0f;
        rectF.top += (rect.height() - rectF.bottom) / 2.0f;
        xy[0] = rectF.left;
        xy[1] = rectF.top - mTextPaint.ascent();
        xy[2] = centerX;
        xy[3] = centerY;
        xy[4] = rotation_angle;
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
     *             CornerLabelTextview.LabelMode.MODE_LEFT : top left
     *             CornerLabelTextview.LabelMode.MODE_RIGHT :top right
     * @return this
     */
    public CornerLabelTextview setMode(LabelMode mode) {
        if (mode == null) {
            throw new IllegalArgumentException("Mode cannot be null");
        }
        this.mMode = mode;
        postInvalidate();
        return this;
    }

    public LabelMode getMode() {
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

    public enum LabelMode {
        MODE_LEFT {
            @Override
            Path getPath(CornerLabelTextview view, Path path, int w, int h) {
                return view.getModeLeftPath(path, w, h);
            }

            @Override
            float[] calculateXY(CornerLabelTextview view, int w, int h, int offset) {
                Rect rect = new Rect(0, 0, w, h);
                return view.buildXY(rect, w / 2, h / 2, -ROTATE_ANGLE);
            }
        },
        MODE_RIGHT {
            @Override
            Path getPath(CornerLabelTextview view, Path path, int w, int h) {
                return view.getModeRightPath(path, w, h);
            }

            @Override
            float[] calculateXY(CornerLabelTextview view, int w, int h, int offset) {
                Rect rect = new Rect(offset, 0, w + offset, h);
                return view.buildXY(rect, w / 2 + offset, h / 2, ROTATE_ANGLE);
            }
        },
        MODE_LEFT_BOTTOM {
            @Override
            Path getPath(CornerLabelTextview view, Path path, int w, int h) {
                return view.getModeLeftBottomPath(path, w, h);
            }

            @Override
            float[] calculateXY(CornerLabelTextview view, int w, int h, int offset) {
                Rect rect = new Rect(0, offset, w, h + offset);
                return view.buildXY(rect, w / 2, h / 2 + offset, ROTATE_ANGLE);
            }
        },
        MODE_RIGHT_BOTTOM {
            @Override
            Path getPath(CornerLabelTextview view, Path path, int w, int h) {
                return view.getModeRightBottomPath(path, w, h);
            }

            @Override
            float[] calculateXY(CornerLabelTextview view, int w, int h, int offset) {
                Rect rect = new Rect(offset, offset, w + offset, h + offset);
                return view.buildXY(rect, w / 2 + offset, h / 2 + offset, -ROTATE_ANGLE);
            }
        },
        MODE_LEFT_TRIANGLE {
            @Override
            Path getPath(CornerLabelTextview view, Path path, int w, int h) {
                return view.getModeLeftTrianglePath(path, w, h);
            }

            @Override
            float[] calculateXY(CornerLabelTextview view, int w, int h, int offset) {
                Rect rect = new Rect(0, 0, w, h);
                return view.buildXY(rect, w / 2, h / 2, -ROTATE_ANGLE);
            }
        },
        MODE_RIGHT_TRIANGLE {
            @Override
            Path getPath(CornerLabelTextview view, Path path, int w, int h) {
                return view.getModeRightTrianglePath(path, w, h);
            }

            @Override
            float[] calculateXY(CornerLabelTextview view, int w, int h, int offset) {
                Rect rect = new Rect(offset, 0, w + offset, h);
                return view.buildXY(rect, w / 2 + offset, h / 2, ROTATE_ANGLE);
            }
        },
        MODE_LEFT_BOTTOM_TRIANGLE {
            @Override
            Path getPath(CornerLabelTextview view, Path path, int w, int h) {
                return view.getModeLeftBottomTrianglePath(path, w, h);
            }

            @Override
            float[] calculateXY(CornerLabelTextview view, int w, int h, int offset) {
                Rect rect = new Rect(0, offset, w, h + offset);
                return view.buildXY(rect, w / 2, h / 2 + offset, ROTATE_ANGLE);
            }
        },
        MODE_RIGHT_BOTTOM_TRIANGLE {
            @Override
            Path getPath(CornerLabelTextview view, Path path, int w, int h) {
                return view.getModeRightBottomTrianglePath(path, w, h);
            }

            @Override
            float[] calculateXY(CornerLabelTextview view, int w, int h, int offset) {
                Rect rect = new Rect(offset, offset, w + offset, h + offset);
                return view.buildXY(rect, w / 2 + offset, h / 2 + offset, -ROTATE_ANGLE);
            }
        };

        abstract Path getPath(CornerLabelTextview view, Path path, int w, int h);

        abstract float[] calculateXY(CornerLabelTextview view, int w, int h, int offset);

        public static LabelMode fromInt(int index) {
            LabelMode[] values = LabelMode.values();
            return (index >= 0 && index < values.length) ? values[index] : MODE_LEFT;
        }
    }

}
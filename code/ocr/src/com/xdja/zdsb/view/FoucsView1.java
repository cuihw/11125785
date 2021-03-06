package com.xdja.zdsb.view;

import com.xdja.zdsb.utils.Zzlog;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

public final class FoucsView1 extends View {

    private static final String TAG = "ViewfinderView";

    private final Paint paint;

    private final Paint paintLine;

    public Rect frame;

    int foucsW, foucsH;

    public int t, b, l, r;

    public boolean transparency = false;

    public FoucsView1(Context context, int foucsW, int foucsH) {
        super(context);
        this.foucsW = foucsW;
        this.foucsH = foucsH;

        Log.i(TAG, "IdCardFoucsView, foucsW = " + foucsW + ",  foucsH = " + foucsH);

        paint = new Paint();
        paintLine = new Paint();
    }

    @Override
    public void onDraw(Canvas canvas) {

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        Log.i(TAG, "onDraw( ) width = " + width + ", height = " + height);

        l = (width - foucsW) / 2;
        r = (width + foucsW) / 2;
        t = (height - foucsH) / 2;
        b = (height + foucsH) / 2;
        frame = new Rect(l, t, r, b);

        Zzlog.out(TAG, "Rect(l, t, r, b) = " + l + ", " + t + ", " + r + ", " + b);

        if (!transparency) {
            // draw background...
            paint.setColor(Color.argb(50, 0, 0, 0));
            canvas.drawRect(0, 0, width, frame.top, paint);
            canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
            canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1, paint);
            canvas.drawRect(0, frame.bottom + 1, width, height, paint);
        }

        paintLine.setColor(Color.rgb(0, 255, 0));
        paintLine.setStrokeWidth(10);
        paintLine.setAntiAlias(true);
        canvas.drawLine(l, t, l + 50, t, paintLine);
        canvas.drawLine(l, t, l, t + 50, paintLine);
        canvas.drawLine(r, t, r - 50, t, paintLine);
        canvas.drawLine(r, t, r, t + 50, paintLine);
        canvas.drawLine(l, b, l + 50, b, paintLine);
        canvas.drawLine(l, b, l, b - 50, paintLine);
        canvas.drawLine(r, b, r - 50, b, paintLine);
        canvas.drawLine(r, b, r, b - 50, paintLine);
        // }

        paintLine.setColor(Color.rgb(250, 0, 0));
        paintLine.setStrokeWidth(4);

        int x = width / 2;
        int y = height / 2;
        canvas.drawLine(x, y - 25, x, y + 25, paintLine);
        canvas.drawLine(x - 25, y, x + 25, y, paintLine);
    }

    public boolean isTransparency() {
        return transparency;
    }

    public void setTransparency(boolean transparency) {
        this.transparency = transparency;
    }

}

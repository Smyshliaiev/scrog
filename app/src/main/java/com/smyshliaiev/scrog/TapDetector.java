/*
 * Copyright (C) 2012 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.smyshliaiev.scrog;

import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.util.Pair;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Anton Smyshliaiev (anton.emale@gmail.com)
 */
class TapDetector extends GestureDetector.SimpleOnGestureListener {

    private static final int ALPHA = 150;
    private Paint mPaint;
    private HUDView mView;
    private List<Colors> colors = new ArrayList<Colors>();
    private Iterator<Colors> it = colors.iterator();
    private Colors color;

    TapDetector(Paint mPaint, HUDView mView) {
        this.mPaint = mPaint;
        this.mView = mView;
        addColors();
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {

        if(!it.hasNext()) {it = colors.iterator();}
        color = it.next();

        mView.setBackgroundColor(color.bg);
        mPaint.setColor(color.fg);
        mView.getBackground().setAlpha(ALPHA);
        return true;
    }

    private class Colors{
        int bg;
        int fg;

        private Colors(int bg, int fg) {
            this.bg = bg;
            this.fg = fg;
        }
    }

    private void addColors(){
        colors.add(new Colors(Color.BLACK, Color.LTGRAY));
        colors.add(new Colors(Color.GRAY, Color.WHITE));
        colors.add(new Colors(Color.WHITE, Color.BLACK));
        colors.add(new Colors(Color.BLACK, Color.GREEN));
    }
}

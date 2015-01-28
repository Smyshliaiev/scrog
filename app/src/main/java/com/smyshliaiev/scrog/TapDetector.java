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
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Anton Smyshliaiev (anton.emale@gmail.com)
 */
class TapDetector extends GestureDetector.SimpleOnGestureListener {


    private Paint mPaint;
    private HUDView mView;
    private ColorManager colMan;

    TapDetector(Paint mPaint, HUDView mView, ColorManager colMan) {
        this.mPaint = mPaint;
        this.mView = mView;
        this.colMan = colMan;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        colMan.nextColor();
        mView.setBackgroundColor(colMan.getColor().bg);
        mPaint.setColor(colMan.getColor().fg);
        mView.getBackground().setAlpha(ColorManager.ALPHA);
        return true;
    }

}

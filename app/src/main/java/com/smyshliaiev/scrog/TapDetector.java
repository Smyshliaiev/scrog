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
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * @author Anton Smyshliaiev (anton.emale@gmail.com)
 */
class TapDetector extends GestureDetector.SimpleOnGestureListener {

    private static final int ALPHA = 150;
    private boolean mTextDoubleTapped = false;
    private Paint mPaint;
    private HUDView mView;

    TapDetector(Paint mPaint, HUDView mView) {
        this.mPaint = mPaint;
        this.mView = mView;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        if(!mTextDoubleTapped){
            mView.setBackgroundColor(Color.BLACK);
            mPaint.setColor(Color.argb(255, 235, 235, 235));
            mTextDoubleTapped = true;
        }
        else{
            mView.setBackgroundColor(Color.GRAY);
            mPaint.setColor(Color.argb(255, 0, 0, 0));
            mTextDoubleTapped = false;
        }
        mView.getBackground().setAlpha(ALPHA);
        return true;
    }

}

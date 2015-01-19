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

import android.graphics.Canvas;
import android.graphics.Paint;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * brief
 *
 * @author Anton Smyshliaiev (anton.emale@gmail.com)
 */
class TextObjectManager {

    private Paint mPaint;
    private int mMaxLines = 10;
    private int mMaxSymbols = 100;

    private ConcurrentLinkedQueue<String> list = new ConcurrentLinkedQueue<String>();

    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");

    public TextObjectManager(Paint paint) {
        this.mPaint = paint;
    }

    public void setMaxLines(int maxLines) {
        this.mMaxLines = maxLines;
    }

    public void setMaxSymbols(int maxSymbols) {
        this.mMaxSymbols = maxSymbols;
    }

    public void addLine(String line){

        Calendar cal = Calendar.getInstance();
        line = sdf.format(cal.getTime()) + ": " + line;

        if(line.length() > mMaxSymbols) {
            LinkedList<String> localList = splitString(line);
            list.addAll(localList);
        }else {
            list.add(line);
        }

        while(list.size() > mMaxLines){
            list.remove();
        }
    }

    private LinkedList<String> splitString(String line){
        LinkedList<String> localList = new LinkedList<String>();

        int length = line.length();
        int remain = line.length();

        int startPos = 0;
        int endPos = mMaxSymbols;

        do{
            String str = line.substring(startPos, endPos);
            localList.add(str);

            remain -= str.length();
            startPos = endPos;
            endPos += ((remain < mMaxSymbols) ? remain : mMaxSymbols);

        }while((endPos<=length)&&(remain > 0));

        return localList;
    }

    public void drawObjects(Canvas canvas){

        int delta = 1;
        for(String str: list){
            int y = (int)(mPaint.getTextSize())*delta;
            mPaint.getTextSize();
            canvas.drawText(str, 10, y, mPaint);
            delta++;
        }
    }

    public void clear(){
        list.clear();
    }

}

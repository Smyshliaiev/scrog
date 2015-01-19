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

import android.content.Context;
import android.content.Intent;

import java.lang.reflect.InvocationHandler;

/**
 * Class provides UI logging.
 * Use init(Context) to start with it.
 * Call destroy() when finished.
 * Call i(String) to print the line of a text.
 * Call setWindowSize(int, int) for setting initial values of the width and height.
 *
 * @author Anton Smyshliaiev (anton.emale@gmail.com)
 */
public enum Scrog {
    INSTANCE;
    private static final String ACTION_STRING_SCROG_SERVICE = "ToScrogService";
    private static Context mContext;

    private Scrog() {
    }

    /**
     * Call it for printing the line.
     * @param text - Text to print.
     */
    public static void i(String text){
        INSTANCE.printLine(text);
    }

    /**
     * Call it for init UI logger.
     *
     * @param context - Android Context of your application.
     */
    public static void init(Context context){
        INSTANCE.start(context);
    }

    /**
     * Call it when finish your work.
     */
    public static void destroy() {
        INSTANCE.stop();
    }

    /**
     * Set initial values for window.
     *
     * @param width
     * @param height
     */
    public static void setWindowSize(int width, int height){
        ScrogSingleton.INSTANCE.setWindowSize(width, height);
    }

    private void start(Context context){
        this.mContext = context;
        mContext.startService(new Intent(mContext, ScrogService.class));
    }
    private static void stop() {
        mContext.stopService(new Intent(mContext, ScrogService.class));
    }
    private void printLine(String text) {
        Intent new_intent = new Intent();
        new_intent.setAction(ACTION_STRING_SCROG_SERVICE);
        new_intent.putExtra("DATA", text);
        mContext.sendBroadcast(new_intent);
    }

}

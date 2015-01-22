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

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.CountDownLatch;

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
    private ServiceConnection sConn;
    private CountDownLatch mLatchStarted;

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


    private void start(Context context){
        this.mContext = context;
        mLatchStarted = new CountDownLatch(1);
        sConn = new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder binder) {
                mLatchStarted.countDown();
                mContext.unbindService(sConn);
            }

            public void onServiceDisconnected(ComponentName name) {
            }
        };

        Intent intentStart = new Intent(mContext, ScrogService.class);
        mContext.startService(intentStart);
        mContext.bindService(intentStart, sConn, mContext.BIND_AUTO_CREATE);
    }
    private void stop() {
        Intent intentStop = new Intent(mContext, ScrogService.class);
        mContext.stopService(intentStop);
    }
    private void printLine(final String text) {
        Thread threadPrint = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mLatchStarted.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent new_intent = new Intent();
                new_intent.setAction(ACTION_STRING_SCROG_SERVICE);
                new_intent.putExtra("DATA", text);
                mContext.sendBroadcast(new_intent);
            }
        });
        threadPrint.start();
    }

}

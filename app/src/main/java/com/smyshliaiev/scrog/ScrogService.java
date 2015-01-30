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

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 * brief
 *
 * @author Anton Smyshliaiev (anton.emale@gmail.com)
 */

public class ScrogService extends Service {

    private static final int PID_PROCESS_SCROG = 1402;
    private static final String ACTION_STRING_SERVICE = "ToScrogService";
    private static final String LOG_TAG = ScrogService.class.getSimpleName();

    public void onCreate() {
        super.onCreate();
        init();

        if (serviceReceiver != null) {
            IntentFilter intentFilter = new IntentFilter(ACTION_STRING_SERVICE);
            registerReceiver(serviceReceiver, intentFilter);
        }

    }

    private void init(){
        ScrogSingleton.INSTANCE.init(this);
    }


    public int onStartCommand(Intent intent, int flags, int startId) {
        ScrogSingleton.INSTANCE.printLine("You can: move(drag), change color(double tap), resize(down-right corner pick) or clear(upper-right corner tap) this area.");
        startForeground(PID_PROCESS_SCROG, new Notification());
        return super.onStartCommand(intent, flags, startId);
    }

    public void onDestroy() {
        super.onDestroy();
        destroy();
        unregisterReceiver(serviceReceiver);
        stopForeground(true);
    }

    public IBinder onBind(Intent intent) {
        return new Binder();
    }


    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    public boolean onUnbind(Intent intent) {
        return true;
    }

    private void destroy(){
        stopForeground(true);
        ScrogSingleton.INSTANCE.destroy();
    }

    private BroadcastReceiver serviceReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            ScrogSingleton.INSTANCE.printLine(intent.getStringExtra("DATA"));
        }
    };

}
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
import android.view.WindowManager;

/**
 * @author Anton Smyshliaiev (anton.emale@gmail.com)
 */
enum ScrogSingleton {
    INSTANCE;

    private static HUDView mView;

    private ScrogSingleton() {
    }

    public void init(Context context) {
        mView = new HUDView(context);
        mView.initView();
    }

    public void destroy() {
        mView.destroyView();
    }

    public void setWindowSize(int w, int h) {
        mView.setWidthHeight(w, h);
    }

    public void printLine(String str) {
        mView.setText(str);
        mView.updateViewLayout();
    }
}

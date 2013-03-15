package com.oneous.log4android;

import android.util.Log;

/*
    Log4Android - Simple Logging Wrapper Library for Android.

    Copyright (c) 2013 Abdullah Al Mamun (Oronno) <oronno@oneous.com>
    http://www.oneous.com

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

public class Logger {

    private static String TAG;
    private static final String DELIM_STR = "{}";
    private String prefixMsg;

    private Logger(Class c) {
        this.TAG = getTag(c);
        this.prefixMsg = c.getName() + " >> ";
    }

    private Logger(String tag) {
        this.TAG = tag;
        this.prefixMsg = "";
    }

    /**
     * Return Logger implementation object.
     * Fully qualified Class name will printed as prefix with log message.
     * TAG will be derive from the package name.
     * So, if your package start as com.google.map.... then "google" will use as TAG.
     *
     * @param c The Class need to log
     * @return Logger implementation object
     */
    public static Logger getLogger(Class c) {
        return new Logger(c);
    }

    /**
     * Return Logger implementation object.
     * Provided tag string will be used as TAG.
     * Will not print Fully qualified Class name.
     *
     * @param tag Used to identify the source of a log message.
     * @return Logger implementation object
     */
    public static Logger getLogger(String tag) {
        return new Logger(tag);
    }

    public void verbose(Object obj) {
        Log.v(TAG, getString(obj));
    }

    public void verbose(String message, Object... args) {
        Log.v(TAG, formatMessage(message, args));
    }

    public void debug(Object obj) {
        Log.v(TAG, getString(obj));
    }

    public void debug(String message, Object... args) {
        Log.d(TAG, formatMessage(message, args));
    }

    public void info(Object obj) {
        Log.v(TAG, getString(obj));
    }

    public void info(String message, Object... args) {
        Log.i(TAG, formatMessage(message, args));
    }

    public void info(Throwable e, String message, Object... args) {
        Log.i(TAG, formatMessage(message, args), e);
    }

    public void warn(Object obj) {
        Log.v(TAG, getString(obj));
    }

    public void warn(String message, Object... args) {
        Log.w(TAG, formatMessage(message, args));
    }

    public void warn(Throwable e, String message, Object... args) {
        Log.w(TAG, formatMessage(message, args), e);
    }

    public void error(Object obj) {
        Log.v(TAG, getString(obj));
    }

    public void error(String message, Object... args) {
        Log.e(TAG, formatMessage(message, args));
    }

    public void error(Throwable e, String message, Object... args) {
        Log.e(TAG, formatMessage(message, args), e);
    }

    public void wtf(Object obj) {
        Log.v(TAG, getString(obj));
    }

    public void wtf(String message, Object... args) {
        Log.wtf(TAG, formatMessage(message, args));
    }

    public void wtf(Throwable e, String message, Object... args) {
        Log.wtf(TAG, formatMessage(message, args), e);
    }

    /**
     * Handy function to get a loggable stack trace from a Throwable
     *
     * @param tr Throwable Object
     * @return stack trace of Throwable object
     */
    public static String getStackTraceString(Throwable tr) {
        return Log.getStackTraceString(tr);
    }

    private String formatMessage(String message, Object... args) {

        StringBuilder sbMessage = new StringBuilder(prefixMsg + message);

        if (message == null || args == null) {
            return sbMessage.toString();
        }

        for (Object arg : args) {
            int index = sbMessage.indexOf(DELIM_STR);
            if (index == -1) {
                return sbMessage.toString();
            }
            sbMessage.replace(index, index + DELIM_STR.length(), arg == null ? "null" : arg.toString());
        }
        return sbMessage.toString();
    }

    private String getTag(Class c) {
        String className = c.getName();

        String tag[] = className.split("\\.");

        if (tag == null || tag.length < 2) {
            return "com.oneous.log4android"; // Default TAG when can't derive from package name
        } else {
            return tag[1];
        }
    }

    private String getString(Object obj) {
        if (obj == null) {
            return prefixMsg + "null";
        }

        return prefixMsg + obj.toString();
    }
}

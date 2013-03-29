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

    private Logger(Class clazz) {
        this.TAG = getTag(clazz);
        this.prefixMsg = clazz.getName() + " >> ";
    }

    private Logger(String tag) {
        this.TAG = tag;
        this.prefixMsg = "";
    }

    private Logger(Class clazz, String tag) {
        this.TAG = tag;
        this.prefixMsg = clazz.getName() + " >> ";
    }

    /**
     * Return Logger implementation object.
     * Fully qualified Class name will printed as prefix with log message.
     * TAG will be derive from the package name.
     * So, if your package start as com.google.map.... then "google" will use as TAG.
     *
     * @param clazz The Class need to log
     * @return Logger implementation object
     */
    public static Logger getLogger(Class clazz) {
        return new Logger(clazz);
    }

    /**
     * Return Logger implementation object.
     * Provided tag string will be used as TAG.
     * Will not print Fully qualified Class name with Message.
     *
     * @param tag Used to identify the source of a log message.
     * @return Logger implementation object
     */
    public static Logger getLogger(String tag) {
        return new Logger(tag);
    }

    /**
     * Return Logger implementation object.
     * Provided tag string will be used as TAG.
     * Fully qualified Class name will printed as prefix with log message.
     *
     * @param clazz The Class need to log
     * @param tag   Used to identify the source of a log message.
     * @return Logger implementation object
     */
    public static Logger getLogger(Class clazz, String tag) {
        return new Logger(clazz, tag);
    }

    public void verbose(Object obj) {
        Log.v(TAG, getString(obj));
    }

    /**
     * Log a message at the VERBOSE level according to the specified format and arguments.
     * Can pass Throwable instance as last argument. Like
     * <code>log.verbose("Exception caught, where, var1={}, var2={}", var1, var2, throwable);</code>
     *
     * @param message the format string
     * @param args    the arguments. Throwable can be passed as last argument and will print accordingly.
     */
    public void verbose(String message, Object... args) {
        Throwable tr = getThrowable(args);

        if (tr == null) {
            Log.v(TAG, formatMessage(message, args));
        } else {
            Log.v(TAG, formatMessage(message, args), tr);
        }
    }

    public void debug(Object obj) {
        Log.v(TAG, getString(obj));
    }

    /**
     * Log a message at the DEBUG level according to the specified format and arguments.
     * Can pass Throwable instance as last argument. Like
     * <code>log.debug("Exception caught, where, var1={}, var2={}", var1, var2, throwable);</code>
     *
     * @param message the format string
     * @param args    the arguments. Throwable can be passed as last argument and will print accordingly.
     */
    public void debug(String message, Object... args) {
        Throwable tr = getThrowable(args);

        if (tr == null) {
            Log.d(TAG, formatMessage(message, args));
        } else {
            Log.d(TAG, formatMessage(message, args), tr);
        }
    }

    public void info(Object obj) {
        Log.v(TAG, getString(obj));
    }

    public void info(String message, Object... args) {
        Throwable tr = getThrowable(args);

        if (tr == null) {
            Log.i(TAG, formatMessage(message, args));
        } else {
            Log.i(TAG, formatMessage(message, args), tr);
        }
    }

    @Deprecated
    public void info(Throwable e, String message, Object... args) {
        Log.i(TAG, formatMessage(message, args), e);
    }

    public void warn(Object obj) {
        Log.v(TAG, getString(obj));
    }

    /**
     * Log a message at the WARN level according to the specified format and arguments.
     * Can pass Throwable instance as last argument. Like
     * <code>log.warn("Exception caught, where, var1={}, var2={}", var1, var2, throwable);</code>
     *
     * @param message the format string
     * @param args    the arguments. Throwable can be passed as last argument and will print accordingly.
     */
    public void warn(String message, Object... args) {
        Throwable tr = getThrowable(args);

        if (tr == null) {
            Log.w(TAG, formatMessage(message, args));
        } else {
            Log.w(TAG, formatMessage(message, args), tr);
        }
    }

    @Deprecated
    public void warn(Throwable e, String message, Object... args) {
        Log.w(TAG, formatMessage(message, args), e);
    }

    public void error(Object obj) {
        Log.v(TAG, getString(obj));
    }

    /**
     * Log a message at the ERROR level according to the specified format and arguments.
     * Can pass Throwable instance as last argument. Like
     * <code>log.error("Error Occur, var1={}, var2={}", var1, var2, throwable);</code>
     *
     * @param message the format string
     * @param args    the arguments. Throwable can be passed as last argument and will print accordingly.
     */
    public void error(String message, Object... args) {
        Throwable tr = getThrowable(args);

        if (tr == null) {
            Log.e(TAG, formatMessage(message, args));
        } else {
            Log.e(TAG, formatMessage(message, args), tr);
        }
    }

    /**
     * Log an exception (throwable) at the ERROR level with an
     * accompanying message.
     *
     * @param message the message accompanying the exception
     * @param tr      the exception (throwable) to log
     */
    public void error(String message, Throwable tr) {
        Log.e(TAG, message, tr);
    }

    /**
     * Deprecated. Instead print log with throwable like
     * <code>log.error("Error Occur", throwable);</code>
     *
     * @param tr      the exception (throwable) to log
     * @param message the message accompanying the exception
     * @param args    the arguments
     */
    @Deprecated
    public void error(Throwable tr, String message, Object... args) {
        Log.e(TAG, formatMessage(message, args), tr);
    }

    public void wtf(Object obj) {
        Log.v(TAG, getString(obj));
    }

    /**
     * What a Terrible Failure: Report a condition that should never happen.
     * The error will always be logged at level ASSERT with the call stack.
     *
     * Can pass Throwable instance as last argument. Like
     * <code>log.wtf("Exception caught, where, var1={}, var2={}", var1, var2, throwable);</code>
     *
     * @param message the format string
     * @param args    the arguments. Throwable can be passed as last argument and will print accordingly.
     */
    public void wtf(String message, Object... args) {
        Log.wtf(TAG, formatMessage(message, args));
    }

    @Deprecated
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

    /**
     * Checks to see whether or not a log for the specified tag is loggable
     * at the specified level. The default level of any tag is set to INFO.
     * This means that any level above and including INFO will be logged.
     * Before you make any calls to a logging method you should check to see
     * if your tag should be logged
     * .
     * @param tag The tag to check.
     * @param level The level to check.
     * @return Whether or not that this is allowed to be logged.
     */
    public static boolean isLoggable (String tag, int level) {
        return Log.isLoggable(tag, level);
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

    private Throwable getThrowable(Object[] args) {
        if (args == null || args.length == 0) {
            return null;
        }

        Object lastObject = args[args.length - 1];
        if (lastObject instanceof Throwable) {
            return (Throwable) lastObject;
        }

        return null;
    }
}

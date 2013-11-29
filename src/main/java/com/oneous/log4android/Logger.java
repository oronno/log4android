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

    private static final String DELIM_STR = "{}";

    private static boolean logDisabled;

    private String tag;
    private String prefixMsg;

    private Logger(Class clazz, boolean useSimpleName) {
        this.tag = getTag(clazz);
        if (useSimpleName) {
            this.prefixMsg = getPrefixMessage(clazz.getSimpleName());
        } else {
            this.prefixMsg = getPrefixMessage(clazz.getName());
        }
    }

    private Logger(String tag) {
        this.tag = tag;
        this.prefixMsg = "";
    }

    private Logger(Class clazz, String tag) {
        this.tag = tag;
        this.prefixMsg = getPrefixMessage(clazz.getName());
    }

    /**
     * Return Logger implementation object.
     * Fully qualified Class name will printed as prefix with log message.
     * tag will be derive from the package name. Like, if your package
     * start as com.google.map.... then "google" will use as tag.
     *
     * @param clazz The Class need to log
     * @return Logger implementation object
     */
    public static Logger getLogger(Class clazz) {
        return new Logger(clazz, false);
    }

    /**
     * Return Logger implementation object.
     * Simple class name will printed with log message if useSimpleName is true
     *
     * @param clazz         The Class need to log
     * @param useSimpleName Indicate whether print Simple Class name or Fully Qualified Class name
     * @return Logger implementation object
     */
    public static Logger getLogger(Class clazz, boolean useSimpleName) {
        return new Logger(clazz, useSimpleName);
    }

    /**
     * Return Logger implementation object.
     * Provided tag-string will be used as 'tag'.
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
     * Provided tag-string will be used as 'tag'.
     * Fully qualified Class name will printed as prefix with log message.
     *
     * @param clazz The Class need to log
     * @param tag   Used to identify the source of a log message.
     * @return Logger implementation object
     */
    public static Logger getLogger(Class clazz, String tag) {
        return new Logger(clazz, tag);
    }

    /**
     * Can be used to disable printing logs. This should be call once and preferably from
     * the class extends 'Application'. This is useful while you might want to disable log printing
     * before publishing app to PlayStore.
     * This will disable printing log for VERBOSE, DEBUG, INFO, WARN level.
     *
     * @param value true to disable printing logs (Default false)
     */
    public static void disableLogging(boolean value) {
        Logger.logDisabled = value;
    }

    /**
     * Log object at the VERBOSE level
     *
     * @param obj If object null, 'null' will be printed, otherwise obj.toString() will call
     */
    public void verbose(Object obj) {
        if (logDisabled) {
            return;
        }

        Log.v(tag, getString(obj));
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
        if (logDisabled) {
            return;
        }

        Throwable tr = getThrowable(args);

        if (tr == null) {
            Log.v(tag, formatMessage(message, args));
        } else {
            Log.v(tag, formatMessage(message, args), tr);
        }
    }


    /**
     * Log object at the DEBUG level
     *
     * @param obj If object null, 'null' will be printed, otherwise obj.toString() will call
     */
    public void debug(Object obj) {
        if (logDisabled) {
            return;
        }
        Log.d(tag, getString(obj));
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
        if (logDisabled) {
            return;
        }

        Throwable tr = getThrowable(args);

        if (tr == null) {
            Log.d(tag, formatMessage(message, args));
        } else {
            Log.d(tag, formatMessage(message, args), tr);
        }
    }

    /**
     * Log object at the INFO level
     *
     * @param obj If object null, 'null' will be printed, otherwise obj.toString() will call
     */
    public void info(Object obj) {
        if (logDisabled) {
            return;
        }

        Log.i(tag, getString(obj));
    }

    /**
     * Log a message at the INFO level according to the specified format and arguments.
     * Can pass Throwable instance as last argument. Like
     * <code>log.info("Exception caught, where, var1={}, var2={}", var1, var2, throwable);</code>
     *
     * @param message the format string
     * @param args    the arguments. Throwable can be passed as last argument and will print accordingly.
     */
    public void info(String message, Object... args) {
        if (logDisabled) {
            return;
        }

        Throwable tr = getThrowable(args);

        if (tr == null) {
            Log.i(tag, formatMessage(message, args));
        } else {
            Log.i(tag, formatMessage(message, args), tr);
        }
    }

    @Deprecated
    public void info(Throwable e, String message, Object... args) {
        if (logDisabled) {
            return;
        }

        Log.i(tag, formatMessage(message, args), e);
    }


    /**
     * Log object at the WARN level
     *
     * @param obj If object null, 'null' will be printed, otherwise obj.toString() will call
     */
    public void warn(Object obj) {
        if (logDisabled) {
            return;
        }

        Log.w(tag, getString(obj));
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
        if (logDisabled) {
            return;
        }

        Throwable tr = getThrowable(args);

        if (tr == null) {
            Log.w(tag, formatMessage(message, args));
        } else {
            Log.w(tag, formatMessage(message, args), tr);
        }
    }

    @Deprecated
    public void warn(Throwable e, String message, Object... args) {
        if (logDisabled) {
            return;
        }

        Log.w(tag, formatMessage(message, args), e);
    }


    /**
     * Log object at the ERROR level
     *
     * @param obj If object null, 'null' will be printed, otherwise obj.toString() will call
     */
    public void error(Object obj) {
        Log.e(tag, getString(obj));
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
            Log.e(tag, formatMessage(message, args));
        } else {
            Log.e(tag, formatMessage(message, args), tr);
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
        Log.e(tag, message, tr);
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
        Log.e(tag, formatMessage(message, args), tr);
    }


    public void wtf(Object obj) {
        Log.wtf(tag, getString(obj));
    }

    /**
     * What a Terrible Failure: Report a condition that should never happen.
     * The error will always be logged at level ASSERT with the call stack.
     * <p/>
     * Can pass Throwable instance as last argument. Like
     * <code>log.wtf("Exception caught, where, var1={}, var2={}", var1, var2, throwable);</code>
     *
     * @param message the format string
     * @param args    the arguments. Throwable can be passed as last argument and will print accordingly.
     */
    public void wtf(String message, Object... args) {
        Log.wtf(tag, formatMessage(message, args));
    }

    @Deprecated
    public void wtf(Throwable e, String message, Object... args) {
        Log.wtf(tag, formatMessage(message, args), e);
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
     *
     * @param tag   The tag to check.
     * @param level The level to check.
     * @return Whether or not that this is allowed to be logged.
     */
    public static boolean isLoggable(String tag, int level) {
        return Log.isLoggable(tag, level);
    }


    private String formatMessage(String message, Object... args) {

        StringBuilder sbMessage = new StringBuilder(prefixMsg + message);

        if (message != null && args != null) {
            for (Object arg : args) {
                int index = sbMessage.indexOf(DELIM_STR);
                if (index == -1) {
                    break;
                }
                sbMessage.replace(index, index + DELIM_STR.length(), arg == null ? "null" : arg.toString());
            }
        }

        return sbMessage.toString();
    }

    private String getTag(Class c) {
        String className = c.getName();

        String tag[] = className.split("\\.");

        if (tag == null || tag.length < 2) {
            return "com.oneous.log4android"; // Default tag when can't derive from package name
        } else {
            return tag[1];
        }
    }

    private String getString(Object obj) {
        if (obj == null) {
            return formatMessage(null);
        }

        return formatMessage(obj.toString());
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

    private String getPrefixMessage(String className) {
        return className + " >> ";
    }
}

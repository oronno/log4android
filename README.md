log4android
===========

### Log4Android - Simple Logging Wrapper Library for Android

Tired writing TAG each time writing log in Android? Bored with printing variables by concatenating string with "+"??

Come from Java Enterprise Development where infamous Log4J used heavily??

Well, with this Log4Android library, you can easily write log with your old habit
> log.debug("in myMethod, id={}, name={}", ver1, ver2);

You also can easily disable printing logs by calling method Logger.disableLogging(true). This specially
useful when you want to remove log printing while publishing app in playstore.

Features
--------
- Log syntax similar with popular log4j framework
- Automatically added TAG with log message
- Derive TAG from the package name
- Can disable logging by simply calling Logger.disableLogging(true) method preferably from the class extends Application.
- Fully Qualified Class name or SimpleClassName will logged as prefix with log message
- Variable Arguments (more than 2) can be passed for printing unlike log4j framework
- Very lightweight, < 5KB library size!

Quick Start
-----------
First, add **Log4Android-x.x.jar** (can download from **releases** directory) at your android project library

Obtain a logger object in your Android code (Activity, Fragment, Service etc) like:
> private static final Logger log = Logger.getLogger(MyActivity.class);

OR

> private static final Logger log = Logger.getLogger("YOUR_TAG");

OR

> private static final Logger log = Logger.getLogger(MyActivity.class, "YOUR_TAG");

Now, start writing log like:

> log.info("onStart(), id={}, someObject={}", id, someObject);

> log.debug(tvName.getText());

> log.verbose("var1={}, var2={}, var3={}, var4={}", v1, v2, v3, v4);

> log.error(ex, "Error occur while trying to load image with id={}", id);

Also can pass throwable/exception as the last argument which will print accordingly:
> log.error("Exception at row id={}", id, throwable);


API Docs
--------
API Docs can be found here: http://oneous.com/log4android/apidocs/

### Motivation

No other library found for Android Development except "SLF4J Android", provide similar logging mechanism as standard
java logging framework. But "SLF4J Android" library has the following limitation:
> SLF4J try to use full qualified class name as tag. This cause problem when, auto generated log tags that are
<= 23 characters long due to a length restriction of log tags on the Android platform (e.g., com.example.myapp.MyClass
tag translated to c*.e*.m*.MyClass), which can result in the same log tag for different classes
(e.g., com.example.app.MyClass and com.example.anotherapp.MyClass both translate to c*.e*.a*.MyClass);
So, sometimes, you can't distinguish classes. Besides, there is no other way to define your own TAG here.

Also log4j framework does not support variable arguments (more than 2) in a easy way, one have to write like **new Object[]{var1, var2, var3}** which I found annoying.


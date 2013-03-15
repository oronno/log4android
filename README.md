log4android
===========

### Log4Android - Simple Logging Wrapper Library for Android

Tired writing TAG each time writing log in Android? Bored with printing variables by concatenating string??

Come from Java Enterprise App development where infamous Log4J used heavily??

Well, with this Log4Android library, you can easily write log with your old habit
> log.debug("in XXXmethod, id={},name={},version={}", ver1, ver2, ver3);

Features
--------
- Log syntex similar with popular log4j framework
- Automatically added TAG with log message
- Derive TAG from the package name
- Fully qualified Class name will logged as prefix with log message
- Very lightweight, < 5KB library size!

Examples
--------
First, add **Log4Android-1.0.jar** (can download from **releases** directory) at your android project library

Obtain a logger object in your Android code (Activity, Fragment, Service etc) like:
> private static final Logger log = Logger.getLogger(MyActivity.class);

OR

> private static final Logger log = Logger.getLogger("YOUR_TAG");

Now, start writing log like:
> log.info("onStart(), id={}, someObject={}", id, someObject);

> log.debug(tvName.getText());

> log.verbose("var1={}, var2={}, var3={}, var4={}", v1, v2, v3, v4);

> log.error(ex, "Error occur while trying to load image with id={}", id);


### Motivation

No other library found for Android Development except "SLF4J Android", provide similar logging mechanism as standard
java logging framework. But "SLF4J Android" library has the following limitation:
> SLF4J try to use full qualified class name as tag. This cause problem when, auto generated log tags that are
<= 23 characters long due to a length restriction of log tags on the Android platform (e.g., com.example.myapp.MyClass
tag translated to c*.e*.m*.MyClass), which can result in the same log tag for different classes
(e.g., com.example.app.MyClass and com.example.anotherapp.MyClass both translate to c*.e*.a*.MyClass);

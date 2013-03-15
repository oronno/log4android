log4android
===========

### Log4Android - Simple Logging Wrapper Library for Android

Tired writing TAG each time writing log in Android? Bored with printing variables by concatenating string??

Come from Java Enterprise App development where infamous Log4J used heavily??

Well, with this Log4Android library, you can easily write log with your old habit
> log.debug("in XXXmethod, id={},name={},version={}", ver1, ver2, ver3);

Examples
--------
First, add **Log4Android-1.0.jar** (can download from **releases** directory) at your android project library

Obtain a logger object in your Android code (Activity, Fragment, Service etc) like:
> Logger log = Logger.getLogger(MyActivity.class);

Now, start write log like:
> log.info("onStart(), id={}, someObject={}", id, someObject);

> log.debug(tvName.getText());

> log.verbose("var1={}, var2={}, var3={}, var4={}", v1, v2, v3, v4);

> log.error(ex, "Error occur while trying to load image with id={}", id);

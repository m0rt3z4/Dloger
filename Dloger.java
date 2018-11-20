package ir.mdev.dloger;

import android.util.Log;

public class Dloger {
  private static String mTag = "dloger";
  private static boolean mIsEnable = true;

  public static void setTag(String tag) {
    mTag = tag;
  }

  public static void enable() {
    mIsEnable = true;
  }

  public static void disable() {
    mIsEnable = false;
  }

  public static void i() {
    if (!mIsEnable) return;
    Log.i(mTag, "" + getCallerInfo());
  }

  public static void i(Object log) {
    if (!mIsEnable) return;
    Log.i(mTag, getCallerInfo() + ":" + log);
  }

  public static void e(Object log, Exception e) {
    if (!mIsEnable) return;
    Log.d(mTag, getCallerInfo() + ":" + log, e);
  }

  private static String getCallerInfo() {
    StackTraceElement[] elements = Thread.currentThread().getStackTrace();
    String callerClassName = null;
    for (int i = 1; i < elements.length; i++) {
      StackTraceElement element = elements[i];
      if (element.getClassName().indexOf("java.lang.Thread") != 0) {
        if (callerClassName == null) {
          callerClassName = element.getClassName();
        } else if (!callerClassName.equals(element.getClassName())) {
          return element.toString();
        }
      }
    }
    return null;
  }
}

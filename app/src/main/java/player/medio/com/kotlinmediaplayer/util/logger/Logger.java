package player.medio.com.kotlinmediaplayer.util.logger;

import android.util.Log;


/**
 * 说明：
 * Logger.i(String str)-->打印有格式的日志
 * kosmos_d(String str)-->打印Tag：kosmos
 * Zero_d(String str)-->打印tag：Zero
 * Zero_d(String str)-->打印tag：Zero
 * Custom_i(String tag, String str)-->打印传入Tag
 */
public final class Logger {
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int ASSERT = 7;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;

    private static boolean logShow;

    public static void logPrint(boolean logShow) {
        Logger.logShow = logShow;
        init("Logger");
    }

    private static final String DEFAULT_TAG = "Logger";

    private static Printer printer = new LoggerPrinter();

    //no instance
    private Logger() {
    }

    /**
     * It is used to get the settings object in order to change settings
     *
     * @return the settings object
     */
    public static Settings init() {
        return init(DEFAULT_TAG);
    }

    /**
     * It is used to change the tag
     *
     * @param tag is the given string which will be used in Logger as TAG
     */
    public static Settings init(String tag) {
        printer = new LoggerPrinter();
        return printer.init(tag);
    }

    public static void resetSettings() {
        printer.resetSettings();
    }

    public static Printer t(String tag) {
        return printer.t(tag, printer.getSettings().getMethodCount());
    }

    public static Printer t(int methodCount) {
        return printer.t(null, methodCount);
    }

    public static Printer t(String tag, int methodCount) {
        return printer.t(tag, methodCount);
    }

    public static void log(int priority, String tag, String message, Throwable throwable) {
        printer.log(priority, tag, message, throwable);
    }

    public static void d(String message, Object... args) {
        if (logShow)
            printer.d(message, args);
    }

    public static void d(Object object) {
        if (logShow)
            printer.d(object);
    }

    public static void e(String message, Object... args) {
        if (logShow)
            printer.e(null, message, args);
    }

    public static void e(Throwable throwable, String message, Object... args) {
        if (logShow)
            printer.e(throwable, message, args);
    }

    public static void i(String message, Object... args) {
        if (logShow)
            printer.i(message, args);
    }

    public static void v(String message, Object... args) {
        if (logShow)
            printer.v(message, args);
    }

    public static void w(String message, Object... args) {
        if (logShow)
            printer.w(message, args);
    }

    public static void wtf(String message, Object... args) {
        if (logShow)
            printer.wtf(message, args);
    }

    /**
     * Formats the json content and print it
     *
     * @param json the json content
     */
    public static void json(String json) {
        printer.json(json);
    }

    /**
     * Formats the json content and print it
     *
     * @param xml the xml content
     */
    public static void xml(String xml) {
        printer.xml(xml);
    }

    public static void kosmos_d(String str) {
        if (logShow)
            Log.d("kosmos", str);
    }

    public static void kosmos_v(String str) {
        if (logShow)
            Log.v("kosmos", str);
    }

    public static void kosmos_e(String str) {
        if (logShow)
            Log.e("kosmos", str);
    }

    public static void kosmos_i(String str) {
        if (logShow)
            Log.i("kosmos", str);
    }

    public static void kosmos_w(String str) {
        if (logShow)
            Log.w("kosmos", str);
    }

    public static void Zero_d(String str) {
        if (logShow)
            Log.d("Zero", str);
    }

    public static void Zero_v(String str) {
        if (logShow)
            Log.v("Zero", str);
    }

    public static void Zero_e(String str) {
        if (logShow)
            Log.e("Zero", str);
    }

    public static void Zero_i(String str) {
        if (logShow)
            Log.i("Zero", str);
    }

    public static void Zero_w(String str) {
        if (logShow)
            Log.w("Zero", str);
    }


    public static void JessieK_d(String str) {
        if (logShow)
            Log.d("JessieK", str);
    }

    public static void JessieK_v(String str) {
        if (logShow)
            Log.v("JessieK", str);
    }

    public static void JessieK_e(String str) {
        if (logShow)
            Log.e("JessieK", str);
    }

    public static void JessieK_i(String str) {
        if (logShow)
            Log.i("JessieK", str);
    }

    public static void JessieK_w(String str) {
        if (logShow)
            Log.w("JessieK", str);
    }

    public static void Custom_d(String tag, String str) {
        if (logShow)
            Log.d(tag, str);
    }

    public static void Custom_v(String tag, String str) {
        if (logShow)
            Log.v(tag, str);
    }

    public static void Custom_i(String tag, String str) {
        if (logShow)
            Log.i(tag, str);
    }

    public static void Custom_e(String tag, String str) {
        if (logShow)
            Log.e(tag, str);
    }

    public static void Custom_w(String tag, String str) {
        if (logShow)
            Log.w(tag, str);
    }
}

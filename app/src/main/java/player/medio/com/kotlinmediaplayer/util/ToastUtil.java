package player.medio.com.kotlinmediaplayer.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import player.medio.com.kotlinmediaplayer.R;
import player.medio.com.kotlinmediaplayer.base.App;


/**
 * Description:ToastUtil:避免Toast消息提示按照队列来重复提示
 * Events:1.2017/2/14 新增showCustomToast():自定义居中Toast
 * <p>
 * Author: Kosmos
 * Time: 2016/8/29 0029 16:24
 * Email:ZeroProject@foxmail.com
 */
public class ToastUtil {
    private static Handler handler = new Handler(Looper.getMainLooper());

    private static Toast toast = null;

    private static Object synObj = new Object();

    public static final int HIDE_ICON = -1;
    public static final int SHOW_DEFAULT_ICON = -2;

    public static void CustomLong(String msg) {
        CustomBase(msg, true, SHOW_DEFAULT_ICON);
    }

    public static void CustomShort(String msg) {
        CustomBase(msg, false, SHOW_DEFAULT_ICON);
    }

    public static void CustomShort(String msg, int res) {
        CustomBase(msg, false, res);
    }

    private static void CustomBase(final String msg, final boolean isLong, final int res) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        synchronized (synObj) {
                            //加载Toast布局
                            View toastRoot = LayoutInflater.from(App.getInstance()).inflate(R.layout.lay_toast_base, null);
                            //初始化布局控件
                            TextView mTextView = (TextView) toastRoot.findViewById(R.id.toast_base_msg);
                            ImageView mImageView = (ImageView) toastRoot.findViewById(R.id.toast_base_iv);
                            //为控件设置属性
                            mTextView.setText(msg);
                            if (res == HIDE_ICON) {
                                mImageView.setVisibility(View.GONE);
                            } else if (res == SHOW_DEFAULT_ICON) {
                                mImageView.setVisibility(View.VISIBLE);
                            } else {
                                mImageView.setVisibility(View.VISIBLE);
                                mImageView.setImageResource(res);
                            }

                            //Toast的初始化
                            Toast toastStart = new Toast(App.getInstance());

                            //获取屏幕高度
                            WindowManager wm = (WindowManager) App.getInstance().getSystemService(Context.WINDOW_SERVICE);
                            int height = wm.getDefaultDisplay().getHeight();
                            //Toast的Y坐标是屏幕高度的1/3，不会出现不适配的问题
                            toastStart.setGravity(Gravity.BOTTOM, 0, height / 10);
                            if (isLong) {
                                toastStart.setDuration(Toast.LENGTH_LONG);
                            } else {
                                toastStart.setDuration(Toast.LENGTH_SHORT);
                            }
                            toastStart.setView(toastRoot);
                            toastStart.show();
                        }
                    }
                });
            }
        }).start();
    }
    /**
     * @param ctx 使用时的上下文
     * @param msg 提示文字
     */
    public static void ShortMessage(final Context ctx, final String msg) {
        if (TxtUtil.isEmpty(msg)) {
            return;
        }
        new Thread(new Runnable() {

            @Override
            public void run() {
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        synchronized (synObj) {
                            if (toast != null) {
                                toast.setText(msg);
                                toast.setDuration(Toast.LENGTH_SHORT);
                            } else {
                                if (ctx == null)
                                    toast = Toast.makeText(App.getInstance(), msg, Toast.LENGTH_SHORT);
                                else
                                    toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
                            }
                            toast.show();
                        }
                    }
                });
            }
        }).start();
    }

    public static void ShortMessage(String msg) {
        ShortMessage(null, msg);
    }

    /**
     * @param ctx 使用时的上下文
     * @param msg 提示文字
     */
    public static void LongMessage(final Context ctx, final String msg) {
        if (TxtUtil.isEmpty(msg)) {
            return;
        }
        new Thread(new Runnable() {

            @Override
            public void run() {
                handler.post(new Runnable() {

                    @Override
                    public void run() {
                        synchronized (synObj) {
                            if (toast != null) {
                                toast.setText(msg);
                                toast.setDuration(Toast.LENGTH_LONG);
                            } else {
                                if (ctx == null)
                                    toast = Toast.makeText(App.getInstance(), msg, Toast.LENGTH_LONG);
                                else
                                    toast = Toast.makeText(ctx, msg, Toast.LENGTH_LONG);
                            }
                            toast.show();
                        }
                    }
                });
            }
        }).start();
    }

    public static void LongMessage(String msg) {
        LongMessage(null, msg);
    }

    /**
     * 吐出指定的视图，使其显示较长的时间
     *
     * @param context
     * @param view
     */
    public static final void toastL(Context context, View view) { // NO_UCD
        // (unused
        // code)
        Toast toast = new Toast(context);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    /**
     * 吐出指定的视图，使其显示较短的时间
     *
     * @param context
     * @param view
     */
    public static final void toastS(Context context, View view) { // NO_UCD
        // (unused
        // code)
        Toast toast = new Toast(context);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * 吐出一个显示时间较长的提示
     *
     * @param context 上下文对象
     * @param resId   显示内容资源ID
     */
    public static final void toastL(Context context, int resId) { // NO_UCD
        // (unused
        // code)
        Toast.makeText(context, resId, Toast.LENGTH_LONG).show();
    }

    /**
     * 吐出一个显示时间较短的提示
     *
     * @param context 上下文对象
     * @param resId   显示内容资源ID
     */
    public static final void toastS(Context context, int resId) { // NO_UCD
        // (unused
        // code)
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }

    /**
     * 吐出一个显示时间较长的提示
     *
     * @param context 上下文对象
     * @param content 显示内容
     */
    public static final void toastL(Context context, String content) { // NO_UCD
        // (unused
        // code)
        Toast.makeText(context, content, Toast.LENGTH_LONG).show();
    }

    /**
     * 吐出一个显示时间较短的提示
     *
     * @param context 上下文对象
     * @param content 显示内容
     */
    public static final void toastS(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

    /**
     * 吐出一个显示时间较长的提示
     *
     * @param context     上下文对象
     * @param formatResId 被格式化的字符串资源的ID
     * @param args        参数数组
     */
    public static final void toastL(Context context, int formatResId, Object... args) { // NO_UCD
        // (unused
        // code)
        Toast.makeText(context, String.format(context.getString(formatResId), args), Toast.LENGTH_LONG).show();
    }

    /**
     * 吐出一个显示时间较短的提示
     *
     * @param context     上下文对象
     * @param formatResId 被格式化的字符串资源的ID
     * @param args        参数数组
     */
    public static final void toastS(Context context, int formatResId, Object... args) { // NO_UCD
        // (unused
        // code)
        Toast.makeText(context, String.format(context.getString(formatResId), args), Toast.LENGTH_SHORT).show();
    }

    /**
     * 吐出一个显示时间较长的提示
     *
     * @param context 上下文对象
     * @param format  被格式化的字符串
     * @param args    参数数组
     */
    public static final void toastL(Context context, String format, Object... args) { // NO_UCD
        // (unused
        // code)
        Toast.makeText(context, String.format(format, args), Toast.LENGTH_LONG).show();
    }

    /**
     * 吐出一个显示时间较短的提示
     *
     * @param context 上下文对象
     * @param format  被格式化的字符串
     * @param args    参数数组
     */
    public static final void toastS(Context context, String format, Object... args) { // NO_UCD
        // (unused
        // code)
        Toast.makeText(context, String.format(format, args), Toast.LENGTH_SHORT).show();
    }
}

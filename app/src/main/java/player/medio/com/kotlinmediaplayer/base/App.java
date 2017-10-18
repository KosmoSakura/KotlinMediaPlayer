package player.medio.com.kotlinmediaplayer.base;

import android.app.Application;

import player.medio.com.kotlinmediaplayer.util.logger.Logger;

/**
 * Description:
 * <p>
 * Author: Kosmos
 * Time: 2017/6/14 001414:04
 * Email:ZeroProject@foxmail.com
 * Events:
 */
public class App extends Application {
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Logger.logPrint(true);
    }

    public static App getInstance() {
        return instance;
    }
}

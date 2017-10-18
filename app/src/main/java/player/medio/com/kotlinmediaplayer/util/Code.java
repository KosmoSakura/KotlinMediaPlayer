package player.medio.com.kotlinmediaplayer.util;


public interface Code {
    String XXXX = "{XXXX}";//url替换字段

    interface System {
        int BASE = 0xFF000000;
        int Immersive = BASE + 1;//启用沉浸式状态栏
        int DefaultColor = BASE + 2;//启用默认颜色
        int DefaultLight = BASE + 3;//启用白色

    }

}

package player.medio.com.kotlinmediaplayer.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * Description:Activity基类，定义了Activity中公用的部分
 * <p>
 * Author: yi.zhang
 * Time: 2017/4/26 0010
 * E-mail: yi.zhang@rato360.com
 */
public abstract class BaseActivity extends AppCompatActivity {

    //消息传到机制
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
//        ScreenUtil.setStatusBarColor(this, Code.System.DefaultColor);
        //初始化菜单
        initIntent(getIntent());
        initView();
        initData();
        initListener();
    }


    //里面做接收跳转信息初始化等操作
    protected void initIntent(Intent intent) {
        if (intent == null) {
            return;
        }
    }

    protected abstract int getLayoutResId();//所有子类必须实现，绑定Act视图


    protected abstract void initView();//所有子类必须实现，里面做页面初始化，找id，等操作

    protected void initListener() {
    }

    protected void initData() {
    }

    /**
     * 泛型转换findViewById（）方法
     */
    @SuppressWarnings("unchecked")
    public <V extends View> V findView(int resId) {
        return (V) findViewById(resId);
    }

}

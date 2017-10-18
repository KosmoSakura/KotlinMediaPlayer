package player.medio.com.kotlinmediaplayer

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Message
import android.view.View
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import player.medio.com.kotlinmediaplayer.base.BaseActivity
import player.medio.com.kotlinmediaplayer.player.PlayerKit
import player.medio.com.kotlinmediaplayer.player.State
import player.medio.com.kotlinmediaplayer.util.ToastUtil
import player.medio.com.kotlinmediaplayer.util.logger.Logger

class PlayerActivity : BaseActivity() {

    private var seekbar: SeekBar? = null
    private var mContentView: TextView? = null
    private var mControlsView: View? = null
    private var mVisible = false
    private var handle: Handler? = null
    private var i_play: ImageView? = null


    override fun getLayoutResId(): Int {
        return R.layout.activity_player
    }

    fun <V : View> findViewas(resId: Int): V {
        return findViewById(resId) as V
    }

    override fun initView() {
        mVisible = true
        mControlsView = findView(R.id.fullscreen_content_controls)
        mContentView = findView(R.id.fullscreen_content)
        seekbar = findView(R.id.sb_bar)
        i_play = findView(R.id.i_play)
        handle = object : Handler() {
            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)
                if (msg != null) {
                    seekbar?.progress = msg.arg1
                }
            }
        }
        playK = PlayerKit()
        mContentView?.setText("")
        test()
    }

    private fun test() {
        startActivity(Intent(this, PlayerActivity::class.java))
        mContentView?.setOnClickListener { ToastUtil.CustomLong("") }
        mContentView?.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        var a = true
        var b = false
        if (a || b) {

        }
        if (a and b) {

        }
        if (a or b) {

        }
        val s = "fnsiuh"
        for (s in s) {
            println(s)
        }
        val p = Persion()
        p.name = ""
        p.name
        val map1 = hashMapOf<Int, Int>()
        map1.put(1, 1)
        var map2 = mapOf(1 to 2)

        for ((k, v) in map1) {
            print("a:b=$k$v")
        }


    }

    class Persion {
        var name = ""
            get() = field.toUpperCase()
            set(value) {
                field = value
            }
    }

    override fun initListener() {
        mContentView!!.setOnClickListener { toggle() }
        seekbar?.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                var txt = mContentView!!.text
                var desc: String? = null
                desc = "${txt.subSequence(0, 4)}\n播放进度：$p1\nBoolean:$p2"
                mContentView!!.setText(desc)
            }
        })
        playK!!.setOnChangeListener(object : PlayerKit.PlayStateChangeListener {
            override fun onStateChanged(old: State, new: State) {
                Logger.kosmos_d("播放状态：$old -->$new ")
            }
        })
    }

    var isPlay: Boolean = true
    var flag = 0
    var playK: PlayerKit? = null

    fun onEventClick(v: View) {
        when (v.id) {
            R.id.i_pause -> {
                flag = 1
                ToastUtil.ShortMessage("暂停")
                playing(1)
                playK!!.pause()
            }

            R.id.i_play -> {
                if (isPlay) {
                    flag = 2
                    ToastUtil.ShortMessage("开始播放")
                    isPlay = false
                    i_play!!.setImageResource(R.drawable.ic_pause_w)
                    playing(0)
                    playK!!.play()
                } else {
                    flag = 1
                    isPlay = true
                    i_play!!.setImageResource(R.drawable.ic_play)
                    playing(1)
                    playK!!.pause()
                }


            }
            R.id.i_stop -> {
                ToastUtil.ShortMessage("停止")
                if (flag == 4) {
                    ToastUtil.ShortMessage("点击点击*2")
                }
                flag = 4
                playing(-1)
                flag = 1
                isPlay = true
                i_play!!.setImageResource(R.drawable.ic_play)
                playK!!.stop()
            }
        }
    }

    var thre: Thread? = null

    private var x = 0
    fun playing(play: Int) {
        if (thre == null) {
            thre = Thread(Runnable {
                while (x <= 100) {
                    try {
                        Thread.sleep(500)
                    } catch(e: InterruptedException) {
                        e.printStackTrace()
                        return@Runnable
                    }
                    x++
                    var msg = Message.obtain()
                    msg.arg1 = x
                    handle!!.sendMessage(msg)
                }
            })
        }
        when (play) {
            -1 -> {
                var msg = Message.obtain()
                msg.arg1 = 0
                handle!!.sendMessage(msg)
                thre!!.interrupt()
                x = seekbar!!.progress
                ToastUtil.ShortMessage("停止")
            }
            0 -> {
                x = seekbar!!.progress
                if (x == 100) {
                    x = 0
                }
                thre!!.start()
                ToastUtil.ShortMessage("开始")
            }
            1 -> {
                thre!!.interrupt()
                ToastUtil.ShortMessage("暂停")
            }
        }

    }

    private fun toggle() {
        if (mVisible) {
            hide()
        } else {
            show()
        }
    }

    private fun hide() {
        val actionBar = supportActionBar
        actionBar?.hide()
        mControlsView!!.visibility = View.GONE
        mVisible = false
        mContentView!!.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }

    @SuppressLint("InlinedApi")
    private fun show() {
        mContentView!!.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        val actionBar = supportActionBar
        actionBar?.show()
        mControlsView!!.visibility = View.VISIBLE
        mVisible = true

    }
}

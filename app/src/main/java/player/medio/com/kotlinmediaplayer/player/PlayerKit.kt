package player.medio.com.kotlinmediaplayer.player

import kotlin.properties.Delegates

/**
 * Description:
 * <p>
 * Author: Kosmos
 * Time: 2017/6/14 001417:11
 * Email:ZeroProject@foxmail.com
 * Events:
 */
class PlayerKit {
    private var state by Delegates.observable(State.空闲, { prop, old, new ->
        listener?.onStateChanged(old, new)
    })

    private fun sendCmder(cmd: Cmder) {
        when (cmd) {
            is Cmder.Play -> {
                state = State.播放中
                doPlay()
            }
            is Cmder.Resume -> {
                state = State.恢复播放
                doResume()
            }
            is Cmder.Pause -> {
                state = State.暂停
            }

            is Cmder.Seek -> {
            }

            is Cmder.Stop -> {
                doStop()
                state = State.停止
            }


        }
    }

    private fun doPlay() {

    }

    private fun doResume() {

    }

    private fun doStop() {

    }

    interface PlayStateChangeListener {
        fun onStateChanged(old: State, new: State)
    }

    private var listener: PlayStateChangeListener? = null
    fun setOnChangeListener(lis: PlayStateChangeListener) {
        listener = lis
    }

    fun play() {
        sendCmder(Cmder.Play())
    }

    fun resume() {
        sendCmder(Cmder.Resume)
    }

    fun pause() {
        sendCmder(Cmder.Pause)
    }

    fun stop() {
        sendCmder(Cmder.Stop)
    }

    fun seekTo(position: Int) {
        sendCmder(Cmder.Seek(position))
    }
}
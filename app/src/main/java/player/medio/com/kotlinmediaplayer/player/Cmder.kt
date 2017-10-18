package player.medio.com.kotlinmediaplayer.player

import android.support.v4.app.Fragment
import android.widget.Toast

/**
 * Description:sealed 只有指定子类可以继承
 * <p>
 * Author: Kosmos
 * Time: 2017/6/14 001415:00
 * Email:ZeroProject@foxmail.com
 * Events:Cmder类
 */
sealed class Cmder {
    class Play : Cmder()
    class Seek(val position: Int) : Cmder()
    object Resume : Cmder()
    object Stop : Cmder()
    object Pause : Cmder()

    val endf get() = this.toString() == ""
    var cvd = Box("")
}

class Box<T>(t: T) {
    var v = t
    //
    var aa = Ab()
}

data class Ab(var id: Int = 5)

fun Fragment.toast(message: CharSequence, duration: Int = Toast.
        LENGTH_SHORT) {
    Toast.makeText(getActivity(), message, duration).show()
}
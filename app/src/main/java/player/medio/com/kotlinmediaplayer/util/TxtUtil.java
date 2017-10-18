package player.medio.com.kotlinmediaplayer.util;

import android.graphics.Paint;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.TextAppearanceSpan;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;


/**
 * Description:文字处理封装
 * <p>
 * Author: Kosmos
 * Time: 2017/5/10 001013:54
 * Email:ZeroProject@foxmail.com
 * Events:
 */
public class TxtUtil {
    public static String replaceUrl(String newChar, String original) {
        return replace(Code.XXXX, newChar, original);
    }

    public static String replace(String old, String newChar, String original) {
        return original.replace(old, newChar);
    }

    public static boolean isEmpty(TextView tv) {
        if (tv == null) {
            return true;
        } else
            return isEmpty(tv.getText().toString());
    }

    public static boolean isBoolean(Boolean b) {
        if (b == null) {
            return false;
        } else return b;
    }

    public static int isNull(Integer integer) {
        if (integer == null) {
            return -1;
        } else
            return integer;
    }

    /**
     * @param str 待校验字符串
     * @return {@code ""}: 空<br> {@code str}: 不为空返回原str
     */
    public static String isNull(String str) {
        if (isEmpty(str)) {
            return "";
        } else {
            return str;
        }
    }

    public static long isNull(Long l) {
        if (l == null) {
            return 0;
        } else {
            return l;
        }
    }

    /**
     * 判断字符串是否为null或长度为0
     *
     * @param str 待校验字符串
     * @return {@code true}: 空<br> {@code false}: 不为空
     */
    public static boolean isEmpty(String str) {
        return str == null || TextUtils.isEmpty(str.trim()) || str.equalsIgnoreCase("null");
    }


    public static boolean isListEmpty(List list) {
        if (list == null) {
            return true;
        } else return list.size() == 0;
    }

    /**
     * 判断字符串是否为null或全为空格
     *
     * @param s 待校验字符串
     * @return {@code true}: null或全空格<br> {@code false}: 不为null且不全空格
     */
    public static boolean isSpace(String s) {
        return (isEmpty(s) || s.trim().length() == 0);
    }

    /**
     * 判断两字符串是否相等
     *
     * @param a 待校验字符串a
     * @param b 待校验字符串b
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    public static boolean equals(CharSequence a, CharSequence b) {
        if (a == b) return true;
        int length;
        if (a != null && b != null && (length = a.length()) == b.length()) {
            if (a instanceof String && b instanceof String) {
                return a.equals(b);
            } else {
                for (int i = 0; i < length; i++) {
                    if (a.charAt(i) != b.charAt(i)) return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 判断两字符串忽略大小写是否相等
     *
     * @param a 待校验字符串a
     * @param b 待校验字符串b
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    public static boolean equalsIgnoreCase(String a, String b) {
        return (a == b) || (b != null) && (a.length() == b.length()) && a.regionMatches(true, 0, b, 0, b.length());
    }

    /**
     * null转为长度为0的字符串
     *
     * @param s 待转字符串
     * @return s为null转为长度为0字符串，否则不改变
     */
    public static String null2Length0(String s) {
        return s == null ? "" : s;
    }

    /**
     * 首字母大写
     *
     * @param s 待转字符串
     * @return 首字母大写字符串
     */
    public static String upperFirstLetter(String s) {
        if (isEmpty(s) || !Character.isLowerCase(s.charAt(0))) return s;
        return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param s 待转字符串
     * @return 首字母小写字符串
     */
    public static String lowerFirstLetter(String s) {
        if (isEmpty(s) || !Character.isUpperCase(s.charAt(0))) return s;
        return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
    }

    /**
     * @param bodyHTML 富文本字符
     *                 function：格式化字符，显示字符为浅灰色
     */
    public static String getHtmlData(String bodyHTML) {

        String head = "<head>" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
            "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
            "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }

    /**
     * 价格后两位缩小
     */
    public static void setSpannableSize(BigDecimal big, TextView tv) {
        DecimalFormat df = new DecimalFormat("#0.00");
        String str = "¥\b" + df.format(big);
        int lengh = str.length();
        if (lengh - 2 < 0) {
            return;
        }

        int size = (int) tv.getTextSize() - 8;

        SpannableString styledText = new SpannableString(str);
        styledText.setSpan(new TextAppearanceSpan(null, 0, size, tv.getTextColors(), null), lengh - 2, lengh, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        tv.setText(styledText);
    }

    /**
     * @param t 下划线
     */
    public static void getTextUnderLine(TextView t) {
        t.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        t.getPaint().setAntiAlias(true);//抗锯齿
    }

    /**
     * 动态设置字符串的颜色和大小
     *
     * @param t             TextView
     * @param color         将要单色设置的颜色
     * @param startLocation 使用该颜色的起始位置
     * @param endLocation   使用该颜色的终止位置
     * @param large         使用该颜色的字体与该字符串其他字体大小的倍数
     */
    public static void getSpannableString(TextView t, int color, int startLocation, int endLocation, float large) {
        String str = t.getText().toString();
        SpannableString styledText = new SpannableString(str);
        styledText.setSpan(new ForegroundColorSpan(color), startLocation, endLocation, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new RelativeSizeSpan(large), startLocation, endLocation, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        t.setText(styledText);
    }
}

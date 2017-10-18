package player.medio.com.kotlinmediaplayer.util;

import java.math.BigDecimal;

/**
 * Description:单位换算工具类
 * <p>
 * Author: Kosmos
 * Time: 2017/5/26 002614:09
 * Email:ZeroProject@foxmail.com
 * Events: UnitUtil  UnitTool
 */
public class UnitUtil {
    /**
     * 格式化字节单位
     *
     * @param size 单位：字节
     */
    public static String sizeFormatbit(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "\tByte";
        }
        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "\tKB";
        }
        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "\tMB";
        }
        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "\tGB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "\tTB";
    }

    /**
     * 毫秒换成几天前几小时几分钟
     * 1天=86400000毫秒
     *
     * @return 60天 0小时 0分钟 0秒
     */
    public static String sizeFormatTimeAllShow(long millisecond) {
        StringBuilder stringBuilder = new StringBuilder();
        long year = millisecond / 31536000000L;
        long day = (millisecond % 31536000000L) / 86400000;
        long hour = (millisecond % 86400000) / 3600000;
        long minute = (millisecond % 86400000 % 3600000) / 60000;
        long ss = (millisecond % 86400000 % 3600000 % 60000) / 1000;

        if (year > 0) {
            stringBuilder.append(year + "年\b");
        }
        if (day > 0 || year > 0) {
            stringBuilder.append(String.valueOf(day) + "天\b");
        }
        if (hour > 0 || day > 0 || year > 0) {
            stringBuilder.append(String.valueOf(hour) + "小时\b");
        }
        if (minute > 0 || hour > 0 || day > 0 || year > 0) {
            stringBuilder.append(String.valueOf(minute) + "分钟\b");
        }
        if (ss > 0 || minute > 0 || hour > 0 || day > 0 || year > 0) {
            stringBuilder.append(String.valueOf(ss) + "秒\b");
        }
        return stringBuilder.toString();
    }

    /**
     * 毫秒换成几天前几小时几分钟
     * 1天=86400000毫秒
     *
     * @return 16年 160天 45秒
     */
    public static String sizeFormatTime(long millisecond) {
        StringBuilder stringBuilder = new StringBuilder();
        long year = millisecond / 31536000000L;
        long day = (millisecond % 31536000000L) / 86400000;
        long hour = (millisecond % 86400000) / 3600000;
        long minute = (millisecond % 86400000 % 3600000) / 60000;
        long ss = (millisecond % 86400000 % 3600000 % 60000) / 1000;

        if (year > 0) {
            stringBuilder.append(year + "年\b");
        }
        if (day > 0) {
            stringBuilder.append(String.valueOf(day) + "天\b");
        }
        if (hour > 0) {
            stringBuilder.append(String.valueOf(hour) + "小时\b");
        }
        if (minute > 0) {
            stringBuilder.append(String.valueOf(minute) + "分钟\b");
        }
        if (ss > 0) {
            stringBuilder.append(String.valueOf(ss) + "秒\b");
        }
        return stringBuilder.toString();
    }
}

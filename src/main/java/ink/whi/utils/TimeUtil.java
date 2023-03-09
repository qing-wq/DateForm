package ink.whi.utils;

import javax.crypto.interfaces.PBEKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 将世界时间转为时间戳
 */
public class TimeUtil {
    public static long transformTime(String date) throws ParseException {
//        String date = "2022-08-08T17:34:44.000Z";
        String tempTime = date.replace("Z", " UTC");
        Date GMTDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z").parse(tempTime);
        return GMTDate.getTime() + 24*60*60*1000;
    }
}

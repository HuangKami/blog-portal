package com.kami.blog.util;

public class DateHelper {
	public static int differentDaysByMillisecond(long date1,long date2)  {
        int days = (int) ((date2 - date1) / (1000*3600*24));
        return days;
    }
}

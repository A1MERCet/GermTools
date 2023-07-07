package net.mcbbs.a1mercet.germtools.util;

import java.util.Date;

public class UtilDate
{
    public static boolean dayPassed(Date d) {return UtilDate.toDay(System.currentTimeMillis())>UtilDate.toDay(d.getTime());}
    public static boolean weekPassed(Date d) {return UtilDate.toDay(System.currentTimeMillis())/7L>UtilDate.toDay(d.getTime())/7L;}
    public static boolean monthPassed(Date d) {return UtilDate.toDay(System.currentTimeMillis())/30L>UtilDate.toDay(d.getTime())/30L;}

    public static long getSecond(long var)
    {
        return var/1000L;
    }
    public static long getMinute(long var)
    {
        return var/1000L/60L;
    }
    public static long gethour(long var)
    {
        return var/1000L/60L/60L;
    }
    public static long getDay(long var)
    {
        return var/1000L/60L/60L/24L;
    }

    public static long tickToSecond(long tick)
    {
        return tick/20L*1000L;
    }
    public static long tickToMinute(long tick)
    {
        return tick/20L*1000L*60L;
    }
    public static long tickTohour(long tick)
    {
        return tick/20L*1000L*60L*60L;
    }
    public static long tickToDay(long tick)
    {
        return tick/20L*1000L*60L*60L*24L;
    }

    public static long toSecond(long var)
    {
        return 1000L*var;
    }
    public static long toMinute(long var)
    {
        return 1000L*60L*var;
    }
    public static long toHour(long var)
    {
        return 1000L*60L*60L*var;
    }
    public static long toDay(long var)
    {
        return 1000L*60L*60L*24L*var;
    }

    public static long getSecond(Date d1, Date d2)
    {
        long l1 = d1.getTime(),l2 = d2.getTime();
        long var = Math.max(l1,l2)-Math.min(l1,l2);
        return getSecond(var);
    }
    public static long getMinute(Date d1,Date d2)
    {
        long l1 = d1.getTime(),l2 = d2.getTime();
        long var = Math.max(l1,l2)-Math.min(l1,l2);
        return getMinute(var);
    }
    public static long gethour(Date d1,Date d2)
    {
        long l1 = d1.getTime(),l2 = d2.getTime();
        long var = Math.max(l1,l2)-Math.min(l1,l2);
        return gethour(var);
    }
    public static long getDay(Date d1,Date d2)
    {
        long l1 = d1.getTime(),l2 = d2.getTime();
        long var = Math.max(l1,l2)-Math.min(l1,l2);
        return getDay(var);
    }

    public static Date afterSecond(Date d1, long var)
    {
        return new Date(d1.getTime()+toSecond(var));
    }
    public static Date afterMinute(Date d1, long var)
    {
        return new Date(d1.getTime()+toMinute(var));
    }
    public static Date afterHour(Date d1, long var)
    {
        return new Date(d1.getTime()+toHour(var));
    }
    public static Date afterDay(Date d1, long var)
    {
        return new Date(d1.getTime()+toDay(var));
    }

    public static Date beforeSecond(Date d1, long var)
    {
        return new Date(d1.getTime()-toSecond(var));
    }
    public static Date beforeMinute(Date d1, long var)
    {
        return new Date(d1.getTime()-toMinute(var));
    }
    public static Date beforeHour(Date d1, long var)
    {
        return new Date(d1.getTime()-toHour(var));
    }
    public static Date beforeDay(Date d1, long var)
    {
        return new Date(d1.getTime()-toDay(var));
    }
}

package org.sweet.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BusinessTime extends AbstractDate<BusinessTime> {

    public static final BusinessTime BEGIN_OF_DAY = new BusinessTime(0, 0, 0, 0);

    public static final BusinessTime END_OF_DAY = new BusinessTime(23, 59, 59, 999);

    public static final String ISO = "HH:mm:ss.SSS";

    public static BusinessTime parse(String iso) {
        try {
            return new BusinessTime(new SimpleDateFormat(ISO).parse(iso));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Failed to parse time <" + iso + "> with pattern <" + ISO + ">", e);
        }
    }

    private static final long serialVersionUID = 1788226690797666874L;

    private final int hour;

    private final int minute;

    private final int second;

    private final int millisecond;

    public BusinessTime(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();

        calendar.setTime(date);

        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);
        this.millisecond = calendar.get(Calendar.MILLISECOND);
    }

    protected BusinessTime(final int hour, final int minute, final int second, final int millisecond) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        this.millisecond = millisecond;
    }

    @Override
    public Calendar asCalendar() {
        Calendar calendar = GregorianCalendar.getInstance();

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.YEAR, 1901);

        merge(calendar);

        return calendar;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }

    public int getMillisecond() {
        return millisecond;
    }

    public int compareTo(BusinessTime other) {
        int result = hour - other.hour;

        if (result == 0) {
            result = minute - other.minute;

            if (result == 0) {
                result = second - other.second;

                if (result == 0) {
                    result = millisecond - other.millisecond;
                }
            }
        }

        return result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + hour;
        result = prime * result + millisecond;
        result = prime * result + minute;
        result = prime * result + second;

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        BusinessTime other = (BusinessTime) obj;

        if (hour != other.hour)
            return false;
        if (millisecond != other.millisecond)
            return false;
        if (minute != other.minute)
            return false;
        if (second != other.second)
            return false;

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(12);

        if (hour < 10) {
            sb.append('0');
        }

        sb.append(hour);
        sb.append(':');

        if (minute < 10) {
            sb.append('0');
        }

        sb.append(minute);
        sb.append(':');

        if (second < 10) {
            sb.append('0');
        }

        sb.append(second);
        sb.append('.');

        if (millisecond < 100) {
            sb.append('0');

            if (millisecond < 10) {
                sb.append('0');
            }
        }

        sb.append(millisecond);

        return sb.toString();
    }

    @Override
    protected void merge(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);
    }
}

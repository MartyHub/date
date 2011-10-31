package org.sweet.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.sweet.date.range.DateRange;
import org.sweet.date.utils.Way;
import org.sweet.date.xml.BusinessDateXmlAdapter;


@XmlJavaTypeAdapter(value = BusinessDateXmlAdapter.class)
public class BusinessDate extends AbstractDate<BusinessDate> {

    public static final BusinessDate BEGIN_OF_TIME = new BusinessDate(1, 0, 1901);

    public static final BusinessDate END_OF_TIME = new BusinessDate(31, 12, 9999);

    public static final String ISO = "yyyy-MM-dd";

    public static BusinessDate parse(String iso) {
        try {
            return new BusinessDate(new SimpleDateFormat(ISO).parse(iso));
        } catch (ParseException e) {
            throw new IllegalArgumentException("Failed to parse date <" + iso + "> with pattern <" + ISO + ">", e);
        }
    }

    public static BusinessDate today() {
        return new BusinessDate(new Date());
    }

    private static final long serialVersionUID = 1788226690797666874L;

    private final int day;

    private final int month;

    private final int year;

    public BusinessDate(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();

        calendar.setTime(date);

        this.day = calendar.get(Calendar.DAY_OF_MONTH);
        this.month = calendar.get(Calendar.MONTH) + 1;
        this.year = calendar.get(Calendar.YEAR);
    }

    protected BusinessDate(final int day, final int month, final int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public Calendar asCalendar() {
        Calendar calendar = GregorianCalendar.getInstance();

        merge(calendar);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public BusinessDate previous() {
        return process(Way.BACKWARD, 1);
    }

    public BusinessDate next() {
        return process(Way.FORWARD, 1);
    }

    public BusinessDate previous(final int nbDays) {
        return process(Way.BACKWARD, nbDays);
    }

    public BusinessDate next(final int nbDays) {
        return process(Way.FORWARD, nbDays);
    }

    public BusinessDate process(Way way, final int nbDays) {
        Calendar calendar = asCalendar();

        calendar.add(Calendar.DAY_OF_MONTH, way.getIncrement() * Math.abs(nbDays));

        return new BusinessDate(calendar.getTime());
    }

    public BusinessDate processWithoutWeekEnd(Way way, final int nbDays) {
        Calendar calendar = asCalendar();
        final int increment = way.getIncrement();

        for (int i = Math.abs(increment * nbDays); i > 0;) {
            calendar.add(Calendar.DAY_OF_MONTH, increment);

            int day = calendar.get(Calendar.DAY_OF_WEEK);

            while (day == Calendar.SATURDAY || day == Calendar.SUNDAY) {
                calendar.add(Calendar.DAY_OF_MONTH, increment);

                day = calendar.get(Calendar.DAY_OF_WEEK);
            }

            --i;
        }

        return new BusinessDate(calendar.getTime());
    }

    public BusinessDate nextMonth() {
        Calendar calendar = asCalendar();

        calendar.add(Calendar.MONTH, 1);

        return new BusinessDate(calendar.getTime());
    }

    public String format(String pattern) {
        return new SimpleDateFormat(pattern).format(asDate());
    }

    public DateRange<TechnicalDate> getDayRange() {
        TechnicalDate start = new TechnicalDate(this, BusinessTime.BEGIN_OF_DAY);
        TechnicalDate end = new TechnicalDate(next(), BusinessTime.BEGIN_OF_DAY);

        return new DateRange<TechnicalDate>(start, end);
    }

    public int compareTo(BusinessDate other) {
        int result = year - other.year;

        if (result == 0) {
            result = month - other.month;

            if (result == 0) {
                result = day - other.day;
            }
        }

        return result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + day;
        result = prime * result + month;
        result = prime * result + year;

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

        BusinessDate other = (BusinessDate) obj;

        if (day != other.day)
            return false;
        if (month != other.month)
            return false;
        if (year != other.year)
            return false;

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(10);

        sb.append(year);
        sb.append('-');

        if (month < 10) {
            sb.append('0');
        }

        sb.append(month);
        sb.append('-');

        if (day < 10) {
            sb.append('0');
        }

        sb.append(day);

        return sb.toString();
    }

    @Override
    protected void merge(Calendar calendar) {
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
    }
}

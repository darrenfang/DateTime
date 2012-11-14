package util;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

public final class DateTime implements Serializable, Comparable<DateTime>,
		Comparator<DateTime> {
	private static final long serialVersionUID = -8984010892578112458L;

	/**
	 * Default Format
	 */
	public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	private Calendar _c = Calendar.getInstance();
	/**
	 * Default SimpleDateFormat
	 */
	private static SimpleDateFormat _defaultFormat = new SimpleDateFormat(
			DEFAULT_DATETIME_FORMAT);

	public DateTime() {
		_c.setTime(new Date());
	}

	public DateTime(Calendar c) {
		_c = c;
	}

	public DateTime(Date date) {
		_c.setTime(date);
	}

	public DateTime(java.sql.Date sqlDate) {
		_c.setTime(sqlDate);
	}

	public DateTime(long timestamp) {
		_c.setTime(new Date(timestamp));
	}

	public DateTime(int year, int month, int day) {
		_c.set(Calendar.YEAR, year);
		_c.set(Calendar.MONTH, month - 1);
		_c.set(Calendar.DAY_OF_MONTH, day);
		_c.set(Calendar.HOUR_OF_DAY, 0);
		_c.set(Calendar.MINUTE, 0);
		_c.set(Calendar.SECOND, 0);
		_c.set(Calendar.MILLISECOND, 0);
	}

	public DateTime(int year, int month, int day, int hour, int minute,
			int second, int millisecond) {
		_c.set(Calendar.YEAR, year);
		_c.set(Calendar.MONTH, month - 1);
		_c.set(Calendar.DAY_OF_MONTH, day);
		_c.set(Calendar.HOUR_OF_DAY, hour);
		_c.set(Calendar.MINUTE, minute);
		_c.set(Calendar.SECOND, second);
		_c.set(Calendar.MILLISECOND, millisecond);
	}

	public static DateTime now() {
		return new DateTime();
	}

	@Override
	public String toString() {
		return _defaultFormat.format(_c.getTime());
	}

	public String toString(String format) {
		return new SimpleDateFormat(format).format(_c.getTime());
	}

	public long getTime() {
		return _c.getTime().getTime();
	}

	public Date getDate() {
		return _c.getTime();
	}

	public Date getSqlDate() {
		return new java.sql.Date(_c.getTime().getTime());
	}

	public int getYear() {
		return _c.get(Calendar.YEAR);
	}

	public int getMonth() {
		return _c.get(Calendar.MONTH) + 1;
	}

	public int getDay() {
		return _c.get(Calendar.DAY_OF_MONTH);
	}

	public int getHour() {
		return _c.get(Calendar.HOUR_OF_DAY);
	}

	public int getMinute() {
		return _c.get(Calendar.MINUTE);
	}

	public int getSecond() {
		return _c.get(Calendar.SECOND);
	}

	public int getMilliSecond() {
		return _c.get(Calendar.MILLISECOND);
	}

	public int getDayOfWeek() {
		return _c.get(Calendar.DAY_OF_WEEK);
	}

	public int getDayOfYear() {
		return _c.get(Calendar.DAY_OF_YEAR);
	}

	public DateTime addYears(int years) {
		_c.add(Calendar.YEAR, years);
		return new DateTime(_c.getTime());
	}

	public DateTime addMonths(int months) {
		_c.add(Calendar.MONTH, months);
		return new DateTime(_c.getTime());
	}

	public DateTime addDays(int days) {
		_c.add(Calendar.DAY_OF_MONTH, days);
		return new DateTime(_c.getTime());
	}

	public DateTime addHours(int hours) {
		_c.add(Calendar.HOUR, hours);
		return new DateTime(_c.getTime());
	}

	public DateTime addMinutes(int minutes) {
		_c.add(Calendar.MINUTE, minutes);
		return new DateTime(_c.getTime());
	}

	public DateTime addSeconds(int seconds) {
		_c.add(Calendar.SECOND, seconds);
		return new DateTime(_c.getTime());
	}

	public DateTime addMilliseconds(int milliseconds) {
		_c.add(Calendar.MILLISECOND, milliseconds);
		return new DateTime(_c.getTime());
	}

	@Override
	public int compareTo(DateTime other) {
		return this.getDate().compareTo(other.getDate());
	}

	@Override
	public int compare(DateTime date1, DateTime date2) {
		return date1.compareTo(date2);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_c == null) ? 0 : _c.getTime().hashCode());
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
		DateTime other = (DateTime) obj;
		if (_c == null) {
			if (other._c != null)
				return false;
		} else if (!_c.getTime().equals(other._c.getTime()))
			return false;
		return true;
	}

	/**
	 * Parse Date from the specific format
	 * 
	 * @param time
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static DateTime parse(String time, String format)
			throws ParseException {
		SimpleDateFormat f = new SimpleDateFormat(format);
		return new DateTime(f.parse(time));
	}

	/**
	 * Parse Date from the default format
	 * 
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static DateTime parse(String time) throws ParseException {
		return new DateTime(_defaultFormat.parse(time));
	}

	public boolean before(DateTime other) {
		return this.getTime() < other.getTime();
	}

	public boolean after(DateTime other) {
		return this.getTime() > other.getTime();
	}

	public long diff(DateTime other) {
		return this.getTime() - other.getTime();
	}

	/**
	 * Truncate the date with the specific format
	 * 
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public DateTime truncate(String format) throws ParseException {
		return parse(this.toString(format), format);
	}
}

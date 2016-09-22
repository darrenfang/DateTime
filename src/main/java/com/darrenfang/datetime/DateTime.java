package com.darrenfang.datetime;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public final class DateTime implements Serializable, Comparable<DateTime>,
		Comparator<DateTime> {

	public enum Weekday {
		SUNDAY(0), MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(
				6);

		private int no;

		Weekday(int no) {
			this.no = no;
		}

		public int getNo() {
			return no;
		}

	}

	private static final long serialVersionUID = -8984010892578112458L;

	/**
	 * 标准GTM时间(1970-01-01 00:00:00)
	 */
	public static final DateTime STANDARD_GTM_TIME = new DateTime(0);

	/**
	 * 默认日期格式(yyyy-MM-dd HH:mm:ss)
	 */
	public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 每天毫秒数
	 */
	public static final long MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
	/**
	 * 每天秒数
	 */
	public static final long SECONDS_PER_DAY = 60 * 60 * 24;
	/**
	 * 每天分钟数
	 */
	public static final long MINUTES_PER_DAY = 60 * 24;
	/**
	 * 每天小时数
	 */
	public static final long HOURS_PER_DAY = 24;

	/**
	 * 每小时毫秒数
	 */
	public static final long MILLISECONDS_PER_HOUR = 1000 * 60 * 60;
	/**
	 * 每小时秒数
	 */
	public static final long SECONDS_PER_HOUR = 60 * 60;
	/**
	 * 每小时分钟数
	 */
	public static final long MINUTES_PER_HOUR = 60;

	/**
	 * 每分钟毫秒数
	 */
	public static final long MILLISECONDS_PER_MINUTE = 1000 * 60;
	/**
	 * 每分钟秒数
	 */
	public static final long SECONDS_PER_MINUTE = 60;

	/**
	 * 每秒毫秒数
	 */
	public static final long MILLISECONDS_PER_SECOND = 1000;

	/**
	 * Calendar Instance
	 */
	private Calendar _c = Calendar.getInstance();

	/**
	 * 以当前时间创建DateTime对象
	 */
	public DateTime() {
		_c.setTime(new Date());
	}

	/**
	 * 根据Calendar对象创建DateTime对象
	 * 
	 * @param c
	 */
	public DateTime(Calendar c) {
		_c = c;
	}

	/**
	 * 根据java.util.Date对象创建DateTime对象
	 * 
	 * @param c
	 */
	public DateTime(Date date) {
		_c.setTime(date);
	}

	/**
	 * 根据java.sql.Date对象创建DateTime对象
	 * 
	 * @param c
	 */
	public DateTime(java.sql.Date sqlDate) {
		_c.setTime(sqlDate);
	}

	/**
	 * 根据timestamp创建DateTime对象
	 * 
	 * @param timestamp
	 */
	public DateTime(long timestamp) {
		_c.setTime(new Date(timestamp));
	}

	/**
	 * 根据年, 月, 日创建DateTime对象
	 * 
	 * @param year
	 *            年份
	 * @param month
	 *            月份 (区别于Calendar, 此处设置1月时为1)
	 * @param day
	 *            日 (此处设置1号时为1)
	 */
	public DateTime(int year, int month, int day) {
		_c.set(Calendar.YEAR, year);
		_c.set(Calendar.MONTH, month - 1);
		_c.set(Calendar.DAY_OF_MONTH, day);
		_c.set(Calendar.HOUR_OF_DAY, 0);
		_c.set(Calendar.MINUTE, 0);
		_c.set(Calendar.SECOND, 0);
		_c.set(Calendar.MILLISECOND, 0);
	}

	/**
	 * 根据年, 月, 日, 小时, 分钟, 秒, 毫秒创建DateTime对象
	 * 
	 * @param year
	 *            年份
	 * @param month
	 *            月份 (区别于Calendar, 此处设置1月时为1)
	 * @param day
	 *            日 (此处设置1号时为1)
	 * @param hour
	 *            小时
	 * @param minute
	 *            分钟
	 * @param second
	 *            秒
	 * @param millisecond
	 *            毫秒
	 */
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

	/**
	 * 根据当前时间创建DateTime对象
	 * 
	 * @return
	 */
	public static DateTime now() {
		return new DateTime();
	}

	public static String format(Date date) {
		return DateFormatUtils.format(date, DEFAULT_DATETIME_FORMAT);
	}

	public static String format(Date date, String format) {
		return DateFormatUtils.format(date, format);
	}

	public static String format(java.sql.Date date) {
		return DateFormatUtils.format(date, DEFAULT_DATETIME_FORMAT);
	}

	public static String format(java.sql.Date date, String format) {
		return DateFormatUtils.format(date, format);
	}

	public static String format(long millisecond) {
		return DateFormatUtils.format(millisecond, DEFAULT_DATETIME_FORMAT);
	}

	public static String format(long millisecond, String format) {
		return DateFormatUtils.format(millisecond, format);
	}

	@Override
	public String toString() {
		return toString(DEFAULT_DATETIME_FORMAT);
	}

	/**
	 * 根据指定格式格式化时间
	 * 
	 * @param format
	 * @return
	 */
	public String toString(String format) {
		if (_c == null) {
			return null;
		}

		return DateFormatUtils.format(_c, format);
	}

	/**
	 * 设置DateTime对象的年, 月, 日
	 * 
	 * @param year
	 *            年份
	 * @param month
	 *            月份 (区别于Calendar, 此处设置1月时为1)
	 * @param day
	 *            日 (此处设置1号时为1)
	 * @return
	 */
	public DateTime set(int year, int month, int day) {
		this.setYear(year);
		this.setMonth(month);
		this.setDay(day);
		return this;
	}

	/**
	 * 设置DateTime对象的年, 月, 日, 小时, 分钟, 秒, 毫秒
	 * 
	 * @param year
	 *            年份
	 * @param month
	 *            月份 (区别于Calendar, 此处设置1月时为1)
	 * @param day
	 *            日 (此处设置1号时为1)
	 * @param hour
	 *            小时
	 * @param minute
	 *            分钟
	 * @param second
	 *            秒
	 * @param millisecond
	 *            毫秒
	 * @return
	 */
	public DateTime set(int year, int month, int day, int hour, int minute,
			int second, int millisecond) {
		this.setYear(year);
		this.setMonth(month);
		this.setDay(day);
		this.setHour(hour);
		this.setMinute(minute);
		this.setSecond(second);
		this.setMilliSecond(millisecond);
		return this;
	}

	/**
	 * 设置DateTime对象的小时, 分钟, 秒, 毫秒
	 * 
	 * @param hour
	 *            小时
	 * @param minute
	 *            分钟
	 * @param second
	 *            秒
	 * @param millisecond
	 *            毫秒
	 * @return
	 */
	public DateTime set(int hour, int minute, int second, int millisecond) {
		this.setHour(hour);
		this.setMinute(minute);
		this.setSecond(second);
		this.setMilliSecond(millisecond);
		return this;
	}

	/**
	 * 获取当前时间戳
	 * 
	 * @return
	 */
	public long getTime() {
		return _c.getTime().getTime();
	}

	/**
	 * 转换为 java.util.Date 对象
	 * 
	 * @return
	 */
	public Date getDate() {
		return _c.getTime();
	}

	/**
	 * 转换为 java.sql.Date 对象
	 * 
	 * @return
	 */
	public Date getSqlDate() {
		return new java.sql.Date(_c.getTime().getTime());
	}

	/**
	 * 获取年份
	 * 
	 * @return
	 */
	public int getYear() {
		return _c.get(Calendar.YEAR);
	}

	/**
	 * 设置年份
	 * 
	 * @param year
	 * @return
	 */
	public DateTime setYear(int year) {
		_c.set(Calendar.YEAR, year);
		return this;
	}

	/**
	 * 获取月份 (1月为1)
	 * 
	 * @return
	 */
	public int getMonth() {
		return _c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 设置月份 (1月为1)
	 * 
	 * @param month
	 * @return
	 */
	public DateTime setMonth(int month) {
		_c.set(Calendar.MONTH, month - 1);
		return this;
	}

	/**
	 * 获取天
	 * 
	 * @return
	 */
	public int getDay() {
		return _c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 设置天
	 * 
	 * @param day
	 * @return
	 */
	public DateTime setDay(int day) {
		_c.set(Calendar.DATE, day);
		return this;
	}

	/**
	 * 获取小时
	 * 
	 * @return
	 */
	public int getHour() {
		return _c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 设置小时
	 * 
	 * @param hour
	 * @return
	 */
	public DateTime setHour(int hour) {
		_c.set(Calendar.HOUR_OF_DAY, hour);
		return this;
	}

	/**
	 * 获取分钟
	 * 
	 * @return
	 */
	public int getMinute() {
		return _c.get(Calendar.MINUTE);
	}

	/**
	 * 设置分钟
	 * 
	 * @param minute
	 * @return
	 */
	public DateTime setMinute(int minute) {
		_c.set(Calendar.MINUTE, minute);
		return this;
	}

	/**
	 * 获取秒
	 * 
	 * @return
	 */
	public int getSecond() {
		return _c.get(Calendar.SECOND);
	}

	/**
	 * 设置秒
	 * 
	 * @param second
	 * @return
	 */
	public DateTime setSecond(int second) {
		_c.set(Calendar.SECOND, second);
		return this;
	}

	/**
	 * 获取毫秒
	 * 
	 * @return
	 */
	public int getMilliSecond() {
		return _c.get(Calendar.MILLISECOND);
	}

	/**
	 * 设置毫秒
	 * 
	 * @param milliSecond
	 * @return
	 */
	public DateTime setMilliSecond(int milliSecond) {
		_c.set(Calendar.MILLISECOND, milliSecond);
		return this;
	}

	/**
	 * 获取是一个星期中的第几天
	 * 
	 * @return
	 */
	public int getDayOfWeek() {
		return _c.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取是一年中的第几天
	 * 
	 * @return
	 */
	public int getDayOfYear() {
		return _c.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 日期加上 years 年
	 * 
	 * @param years
	 * @return
	 */
	public DateTime addYears(int years) {
		_c.add(Calendar.YEAR, years);
		return this;
	}

	/**
	 * 日期加上 months 月
	 * 
	 * @param months
	 * @return
	 */
	public DateTime addMonths(int months) {
		_c.add(Calendar.MONTH, months);
		return this;
	}

	/**
	 * 日期加上 days 天
	 * 
	 * @param days
	 * @return
	 */
	public DateTime addDays(int days) {
		_c.add(Calendar.DAY_OF_MONTH, days);
		return this;
	}

	/**
	 * 日期加上 days 天
	 * 
	 * @param hours
	 * @return
	 */
	public DateTime addHours(int hours) {
		_c.add(Calendar.HOUR, hours);
		return this;
	}

	/**
	 * 日期加上 minutes 分
	 * 
	 * @param minutes
	 * @return
	 */
	public DateTime addMinutes(int minutes) {
		_c.add(Calendar.MINUTE, minutes);
		return this;
	}

	/**
	 * 日期加上 seconds 秒
	 * 
	 * @param seconds
	 * @return
	 */
	public DateTime addSeconds(int seconds) {
		_c.add(Calendar.SECOND, seconds);
		return this;
	}

	/**
	 * 日期加上 milliseconds 毫秒
	 * 
	 * @param milliseconds
	 * @return
	 */
	public DateTime addMilliseconds(int milliseconds) {
		_c.add(Calendar.MILLISECOND, milliseconds);
		return this;
	}

	/**
	 * 日期比较<br>
	 * 若大于另一日期, 返回1, 小于返回-1, 相等返回0
	 */
	public int compareTo(DateTime other) {
		return this.getDate().compareTo(other.getDate());
	}

	/**
	 * 日期比较<br>
	 * 若大于另一日期, 返回1, 小于返回-1, 相等返回0
	 */
	public int compare(DateTime date1, DateTime date2) {
		return date1.compareTo(date2);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((_c == null) ? 0 : _c.hashCode());
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
		} else if (!_c.equals(other._c))
			return false;
		return true;
	}

	/**
	 * 根据指定的格式解析日期
	 * 
	 * @param time
	 *            如果为null, 返回null
	 * @param format
	 *            如果为null, 则使用默认格式(yyyy-MM-dd HH:mm:ss)
	 * @return
	 * @throws ParseException
	 */
	public static DateTime parse(String time, String format)
			throws ParseException {
		if (StringUtils.isBlank(time)) {
			return null;
		}

		if (StringUtils.isBlank(format)) {
			format = DEFAULT_DATETIME_FORMAT;
		}

		return new DateTime(DateUtils.parseDate(time, format));
	}

	/**
	 * 根据默认的格式(yyyy-MM-dd HH:mm:ss)解析日期
	 * 
	 * @param time
	 *            如果为null, 返回null
	 * @return
	 * @throws ParseException
	 */
	public static DateTime parse(String time) throws ParseException {
		return parse(time, DEFAULT_DATETIME_FORMAT);
	}

	/**
	 * 根据指定的格式解析日期, 当出现错误时, 返回defaultValue
	 * 
	 * @param time
	 *            如果为null, 返回null
	 * @param format
	 *            如果为null, 则使用默认格式(yyyy-MM-dd HH:mm:ss)
	 * @return
	 */
	public static DateTime tryParse(String time, String format,
			DateTime defaultValue) {

		if (StringUtils.isBlank(time)) {
			return defaultValue;
		}

		try {
			if (StringUtils.isBlank(format)) {
				format = DEFAULT_DATETIME_FORMAT;
			}

			return new DateTime(DateUtils.parseDate(time, format));
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 根据默认格式(yyyy-MM-dd HH:mm:ss)解析日期, 当出现错误时, 返回defaultValue
	 * 
	 * @param time
	 *            如果为null, 返回null
	 * @return
	 */
	public static DateTime tryParse(String time, DateTime defaultValue) {
		if (StringUtils.isBlank(time)) {
			return defaultValue;
		}

		try {
			return new DateTime(
					DateUtils.parseDate(time, "yyyy-MM-dd HH:mm:ss"));
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 判断日期是否在other之前
	 * 
	 * @param other
	 * @return 相等时返回false
	 */
	public boolean before(DateTime other) {
		return this.getTime() < other.getTime();
	}

	/**
	 * 判断日期是否在other之后
	 * 
	 * @param other
	 * @return 相等时返回false
	 */
	public boolean after(DateTime other) {
		return this.getTime() > other.getTime();
	}

	/**
	 * 计算2个日期之间的毫秒差
	 * 
	 * @param other
	 * @return
	 */
	public long diff(DateTime other) {
		return this.getTime() - other.getTime();
	}

	/**
	 * 按指定的格式截断日期
	 * 
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public DateTime truncate(String format) throws ParseException {
		return parse(this.toString(format), format);
	}

	/**
	 * 按指定的格式截断日期, 出现错误时返回defaultValue
	 * 
	 * @param format
	 * @return
	 */
	public DateTime tryTruncate(String format, DateTime defaultValue) {
		try {
			return parse(this.toString(format), format);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/**
	 * 获得当前时间是星期几
	 * 
	 * @return
	 */
	public Weekday getWeekday() {
		Weekday weekday = null;

		int dayOfWeek = this._c.get(Calendar.DAY_OF_WEEK);
		switch (dayOfWeek) {
		case 1:
			weekday = Weekday.SUNDAY;
			break;
		case 2:
			weekday = Weekday.MONDAY;
			break;
		case 3:
			weekday = Weekday.TUESDAY;
			break;
		case 4:
			weekday = Weekday.WEDNESDAY;
			break;
		case 5:
			weekday = Weekday.THURSDAY;
			break;
		case 6:
			weekday = Weekday.FRIDAY;
			break;
		case 7:
			weekday = Weekday.SATURDAY;
			break;
		}

		return weekday;
	}

	/**
	 * 获取当前时间之后的第几个星期几的时间
	 * 
	 * @param weekday
	 *            需要获取<b>weekday</b>的时间
	 * @param span
	 *            第<b>span</b>个星期之后的时间。<br />
	 *            如果为0，表示获取这个星期内的<b>weekday</b>的时间。如果<b>weekday</b>表示的时间在当前星期之前，
	 *            则获取的时间前移。<br />
	 *            如果大于0，时间往后移。<br />
	 *            小于0，时间往前移。
	 * @return
	 */
	public DateTime addWeekdays(Weekday weekday, int span) {
		int diff = weekday.getNo() - this.getWeekday().getNo();
		return this.addDays(diff + 7 * span);
	}

}

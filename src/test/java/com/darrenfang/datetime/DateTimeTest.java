package com.darrenfang.datetime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import com.darrenfang.datetime.DateTime.Weekday;

public class DateTimeTest {
	@Test
	public void testField() {
		assertEquals(new DateTime(0), DateTime.STANDARD_GTM_TIME);
		assertEquals("yyyy-MM-dd HH:mm:ss", DateTime.DEFAULT_DATETIME_FORMAT);
		assertEquals(1000 * 60 * 60 * 24, DateTime.MILLISECONDS_PER_DAY);
		assertEquals(60 * 60 * 24, DateTime.SECONDS_PER_DAY);
		assertEquals(60 * 24, DateTime.MINUTES_PER_DAY);
		assertEquals(24, DateTime.HOURS_PER_DAY);
		assertEquals(1000 * 60 * 60, DateTime.MILLISECONDS_PER_HOUR);
		assertEquals(60 * 60, DateTime.SECONDS_PER_HOUR);
		assertEquals(60, DateTime.MINUTES_PER_HOUR);
		assertEquals(1000 * 60, DateTime.MILLISECONDS_PER_MINUTE);
		assertEquals(60, DateTime.SECONDS_PER_MINUTE);
		assertEquals(1000, DateTime.MILLISECONDS_PER_SECOND);
	}

	@Test
	public void howToConstructor() {
		DateTime dateTime = new DateTime(2013, 1, 1);
		assertEquals("2013-01-01", dateTime.toString("yyyy-MM-dd"));
		assertEquals("2013-01-01 00:00:00", dateTime.toString());
		assertEquals("13-01-01", dateTime.toString("yy-MM-dd"));

		dateTime = new DateTime(2014, 9, 1, 12, 0, 0, 0);
		assertEquals("2014-09-01", dateTime.toString("yyyy-MM-dd"));
		assertEquals("2014-09-01 12:00:00", dateTime.toString());
		assertEquals("14-09-01", dateTime.toString("yy-MM-dd"));

		Date date = new Date(0);
		dateTime = new DateTime(date);
		assertEquals("1970-01-01", dateTime.toString("yyyy-MM-dd"));
		assertEquals("1970-01-01 08:00:00", dateTime.toString());
		assertEquals("70-01-01", dateTime.toString("yy-MM-dd"));

		java.sql.Date sqlDate = new java.sql.Date(0);
		dateTime = new DateTime(sqlDate);
		assertEquals("1970-01-01", dateTime.toString("yyyy-MM-dd"));
		assertEquals("1970-01-01 08:00:00", dateTime.toString());
		assertEquals("70-01-01", dateTime.toString("yy-MM-dd"));

		dateTime = new DateTime(0);
		assertEquals("1970-01-01", dateTime.toString("yyyy-MM-dd"));
		assertEquals("1970-01-01 08:00:00", dateTime.toString());
		assertEquals("70-01-01", dateTime.toString("yy-MM-dd"));

		Calendar calendar = Calendar.getInstance();
		// Calendar 的 Month 以 0 开始
		calendar.set(2015, 1, 1, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		dateTime = new DateTime(calendar);
		assertEquals("2015-02-01", dateTime.toString("yyyy-MM-dd"));
		assertEquals("2015-02-01 00:00:00", dateTime.toString());
		assertEquals("15-02-01", dateTime.toString("yy-MM-dd"));
	}

	@Test
	public void testGetMethods() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2013, 0, 2, 3, 4, 5);
		calendar.set(Calendar.MILLISECOND, 6);

		DateTime dateTime = new DateTime(2013, 1, 2, 3, 4, 5, 6);
		assertEquals(2013, dateTime.getYear());
		assertEquals(1, dateTime.getMonth());
		assertEquals(2, dateTime.getDay());
		assertEquals(3, dateTime.getHour());
		assertEquals(4, dateTime.getMinute());
		assertEquals(5, dateTime.getSecond());
		assertEquals(6, dateTime.getMilliSecond());
		assertEquals(2, dateTime.getDayOfYear());
		assertEquals(4, dateTime.getDayOfWeek());
		assertEquals(calendar.getTime(), dateTime.getDate());
		assertEquals(new java.sql.Date(calendar.getTime().getTime()),
				dateTime.getSqlDate());
		assertEquals(calendar.getTime().getTime(), dateTime.getTime());
	}

	@Test
	public void testSetMethods() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2013, 0, 2, 3, 4, 5);
		calendar.set(Calendar.MILLISECOND, 6);

		DateTime dateTime = new DateTime();
		dateTime.setYear(2013);
		dateTime.setMonth(1);
		dateTime.setDay(2);
		dateTime.setHour(3);
		dateTime.setMinute(4);
		dateTime.setSecond(5);
		dateTime.setMilliSecond(6);

		assertEquals(2013, dateTime.getYear());
		assertEquals(1, dateTime.getMonth());
		assertEquals(2, dateTime.getDay());
		assertEquals(3, dateTime.getHour());
		assertEquals(4, dateTime.getMinute());
		assertEquals(5, dateTime.getSecond());
		assertEquals(6, dateTime.getMilliSecond());
		assertEquals(2, dateTime.getDayOfYear());
		assertEquals(4, dateTime.getDayOfWeek());
		assertEquals(calendar.getTime(), dateTime.getDate());
		assertEquals(new java.sql.Date(calendar.getTime().getTime()),
				dateTime.getSqlDate());
		assertEquals(calendar.getTime().getTime(), dateTime.getTime());

		dateTime = new DateTime();
		dateTime.set(2013, 1, 2, 3, 4, 5, 6);

		assertEquals(2013, dateTime.getYear());
		assertEquals(1, dateTime.getMonth());
		assertEquals(2, dateTime.getDay());
		assertEquals(3, dateTime.getHour());
		assertEquals(4, dateTime.getMinute());
		assertEquals(5, dateTime.getSecond());
		assertEquals(6, dateTime.getMilliSecond());
		assertEquals(2, dateTime.getDayOfYear());
		assertEquals(4, dateTime.getDayOfWeek());
		assertEquals(calendar.getTime(), dateTime.getDate());
		assertEquals(new java.sql.Date(calendar.getTime().getTime()),
				dateTime.getSqlDate());
		assertEquals(calendar.getTime().getTime(), dateTime.getTime());

		dateTime = new DateTime();
		dateTime.set(2013, 1, 2);

		assertEquals(2013, dateTime.getYear());
		assertEquals(1, dateTime.getMonth());
		assertEquals(2, dateTime.getDay());
	}

	@Test
	public void testAddMethods() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2014, 1, 3, 4, 5, 6);
		calendar.set(Calendar.MILLISECOND, 7);

		DateTime dateTime = new DateTime(2013, 1, 2, 3, 4, 5, 6);
		dateTime.addYears(1).addMonths(1).addDays(1).addHours(1).addMinutes(1)
				.addSeconds(1).addMilliseconds(1);

		assertEquals(2014, dateTime.getYear());
		assertEquals(2, dateTime.getMonth());
		assertEquals(3, dateTime.getDay());
		assertEquals(4, dateTime.getHour());
		assertEquals(5, dateTime.getMinute());
		assertEquals(6, dateTime.getSecond());
		assertEquals(7, dateTime.getMilliSecond());
		assertEquals(34, dateTime.getDayOfYear());
		assertEquals(2, dateTime.getDayOfWeek());
		assertEquals(calendar.getTime(), dateTime.getDate());
		assertEquals(new java.sql.Date(calendar.getTime().getTime()),
				dateTime.getSqlDate());
		assertEquals(calendar.getTime().getTime(), dateTime.getTime());
	}

	@Test
	public void testCompare() {
		DateTime small = new DateTime(2013, 1, 2, 3, 4, 5, 6);
		DateTime big = new DateTime(2013, 1, 2, 3, 4, 5, 7);
		DateTime smallEquals = new DateTime(2013, 1, 2, 3, 4, 5, 6);

		assertEquals(true, small.compareTo(big) < 0);
		assertEquals(true, big.compareTo(small) > 0);
		assertEquals(true, small.compareTo(smallEquals) == 0);

		assertEquals(true, small.compare(small, big) < 0);
		assertEquals(true, big.compare(big, small) > 0);
		assertEquals(true, small.compare(small, smallEquals) == 0);

		assertTrue(small.before(big));
		assertTrue(big.after(small));
		assertFalse(small.before(smallEquals));
		assertFalse(small.after(smallEquals));

		assertEquals(small, smallEquals);
		assertNotEquals(small, big);

		assertEquals(1, big.diff(small));
	}

	@Test
	public void testParse() throws ParseException {
		DateTime dateTime = DateTime.parse("2013-01-02 03:04:05");

		assertEquals(2013, dateTime.getYear());
		assertEquals(1, dateTime.getMonth());
		assertEquals(2, dateTime.getDay());
		assertEquals(3, dateTime.getHour());
		assertEquals(4, dateTime.getMinute());
		assertEquals(5, dateTime.getSecond());
		assertEquals(2, dateTime.getDayOfYear());
		assertEquals(4, dateTime.getDayOfWeek());

		dateTime = DateTime.parse("2013-01-02", "yyyy-MM-dd");

		assertEquals(2013, dateTime.getYear());
		assertEquals(1, dateTime.getMonth());
		assertEquals(2, dateTime.getDay());
	}

	@Test(expected = ParseException.class)
	public void misuseParse() throws ParseException {
		DateTime.parse("12-039");
	}

	@Test
	public void testTryParse() {
		DateTime dateTime = DateTime.tryParse("2013-01-02 03:04:05",
				DateTime.STANDARD_GTM_TIME);

		assertEquals(2013, dateTime.getYear());
		assertEquals(1, dateTime.getMonth());
		assertEquals(2, dateTime.getDay());
		assertEquals(3, dateTime.getHour());
		assertEquals(4, dateTime.getMinute());
		assertEquals(5, dateTime.getSecond());
		assertEquals(2, dateTime.getDayOfYear());
		assertEquals(4, dateTime.getDayOfWeek());

		dateTime = DateTime.tryParse("2013-01-02", "yyyy-MM-dd",
				DateTime.STANDARD_GTM_TIME);

		assertEquals(2013, dateTime.getYear());
		assertEquals(1, dateTime.getMonth());
		assertEquals(2, dateTime.getDay());

		dateTime = DateTime.tryParse("", DateTime.STANDARD_GTM_TIME);
		assertEquals(DateTime.STANDARD_GTM_TIME, dateTime);

		dateTime = DateTime.tryParse("", null);
		assertNull(dateTime);
	}

	@Test
	public void testTruncate() throws ParseException {
		DateTime dateTime = new DateTime(2013, 1, 2, 3, 4, 5, 6);
		dateTime = dateTime.truncate("yyyy-MM-dd");
		assertEquals(2013, dateTime.getYear());
		assertEquals(1, dateTime.getMonth());
		assertEquals(2, dateTime.getDay());
		assertEquals(0, dateTime.getHour());
		assertEquals(0, dateTime.getMinute());
		assertEquals(0, dateTime.getSecond());
	}

	@Test
	public void misuseTruncate() throws ParseException {
		DateTime dateTime = new DateTime(2013, 1, 2, 3, 4, 5, 6);
		dateTime = dateTime.truncate("123");
		assertEquals("1970-01-01 00:00:00", dateTime.toString());
	}

	@Test
	public void testTryTruncate() {
		DateTime dateTime = new DateTime(2013, 1, 2, 3, 4, 5, 6);
		dateTime = dateTime.tryTruncate("yyyy-MM-dd", null);
		assertNotNull(dateTime);
		assertEquals(2013, dateTime.getYear());
		assertEquals(1, dateTime.getMonth());
		assertEquals(2, dateTime.getDay());
		assertEquals(0, dateTime.getHour());
		assertEquals(0, dateTime.getMinute());
		assertEquals(0, dateTime.getSecond());

		dateTime = dateTime.tryTruncate("", null);
		assertNull(dateTime);
	}

	@Test
	public void testNull() throws ParseException {
		// Error
		// DateTime dateTime = new DateTime(null);

		DateTime dateTime = null;
		assertNull(dateTime);

		dateTime = DateTime.parse(null);
		assertNull(dateTime);

		dateTime = DateTime.parse(null, null);
		assertNull(dateTime);

		dateTime = DateTime.parse("2013-01-01 00:00:00", null);
		assertEquals("2013-01-01 00:00:00", dateTime.toString());

		dateTime = DateTime.tryParse(null, null);
		assertNull(dateTime);

		dateTime = DateTime.tryParse(null, null, null);
		assertNull(dateTime);

		dateTime = DateTime.tryParse("2013-01-01", null);
		assertNull(dateTime);
		// java.lang.NullPointerException
		// assertEquals(null, dateTime.toString());
	}

	@Test
	public void testFormat() {
		assertEquals("2013-01-02 03:04:05",
				DateTime.format(new DateTime(2013, 1, 2, 3, 4, 5, 6).getDate()));

		assertEquals("2013-01-02", DateTime.format(new DateTime(2013, 1, 2, 3,
				4, 5, 6).getDate(), "yyyy-MM-dd"));

		assertEquals("2013-01-02 03:04:05",
				DateTime.format(new DateTime(2013, 1, 2, 3, 4, 5, 6).getTime()));

		assertEquals("2013-01-02", DateTime.format(new DateTime(2013, 1, 2, 3,
				4, 5, 6).getTime(), "yyyy-MM-dd"));

		assertEquals("2013-01-02 03:04:05", DateTime.format(new DateTime(2013,
				1, 2, 3, 4, 5, 6).getSqlDate()));

		assertEquals("2013-01-02", DateTime.format(new DateTime(2013, 1, 2, 3,
				4, 5, 6).getSqlDate(), "yyyy-MM-dd"));
	}

	@Test
	public void testGetWeekday() {
		DateTime dateTime = new DateTime(2013, 1, 1);
		assertTrue(dateTime.getWeekday() == Weekday.TUESDAY);

		dateTime = new DateTime(2012, 12, 31);
		assertTrue(dateTime.getWeekday() == Weekday.MONDAY);

		dateTime = new DateTime(2012, 2, 29);
		assertTrue(dateTime.getWeekday() == Weekday.WEDNESDAY);

		dateTime = new DateTime(2013, 12, 12);
		assertTrue(dateTime.getWeekday() == Weekday.THURSDAY);

		dateTime = new DateTime(2013, 12, 31);
		assertTrue(dateTime.getWeekday() == Weekday.TUESDAY);
	}

	@Test
	public void testAddWeekdays() {
		DateTime dateTime = new DateTime(2013, 1, 1);
		dateTime.addWeekdays(Weekday.FRIDAY, 0);
		assertEquals(new DateTime(2013, 1, 4), dateTime);

		dateTime = new DateTime(2013, 1, 1);
		dateTime.addWeekdays(Weekday.FRIDAY, 1);
		assertEquals(new DateTime(2013, 1, 11), dateTime);

		dateTime = new DateTime(2013, 1, 1);
		dateTime.addWeekdays(Weekday.FRIDAY, -1);
		assertEquals(new DateTime(2012, 12, 28), dateTime);

		dateTime = new DateTime(2013, 12, 12);
		dateTime.addWeekdays(Weekday.MONDAY, 0);
		assertEquals(new DateTime(2013, 12, 9), dateTime);

		dateTime = new DateTime(2013, 12, 12);
		dateTime.addWeekdays(Weekday.MONDAY, 1);
		assertEquals(new DateTime(2013, 12, 16), dateTime);

		dateTime = new DateTime(2013, 12, 12);
		dateTime.addWeekdays(Weekday.MONDAY, -1);
		assertEquals(new DateTime(2013, 12, 2), dateTime);
	}
}

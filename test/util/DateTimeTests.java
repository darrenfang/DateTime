package util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;

public class DateTimeTests {
	@Test
	public void testConstructor() {
		Calendar c = Calendar.getInstance();
		Date now = c.getTime();
		long timestamp = now.getTime();

		DateTime dt1 = new DateTime(c);
		DateTime dt2 = new DateTime(now);
		DateTime dt3 = new DateTime(timestamp);

		Assert.assertEquals(dt1.getDate(), dt2.getDate());
		Assert.assertEquals(dt2.getDate(), dt3.getDate());

		c = Calendar.getInstance();
		c.set(Calendar.YEAR, 2010);
		c.set(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 10);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		DateTime dt4 = new DateTime(c.getTime());
		Assert.assertEquals("2010-02-10 00:00:00", dt4.toString());

		Date date = c.getTime();
		DateTime dt5 = new DateTime(date);
		Assert.assertEquals("2010-02-10 00:00:00", dt5.toString());

		long timestamp2 = date.getTime();
		DateTime dt6 = new DateTime(timestamp2);
		Assert.assertEquals("2010-02-10 00:00:00", dt6.toString());

		DateTime dt7 = new DateTime(2012, 1, 1);
		Assert.assertEquals("2012-01-01 00:00:00", dt7.toString());

		DateTime dt8 = new DateTime(2012, 1, 1, 10, 20, 30, 0);
		Assert.assertEquals("2012-01-01 10:20:30", dt8.toString());

		java.sql.Date dt = new java.sql.Date(dt8.getTime());
		DateTime dt9 = new DateTime(dt);
		Assert.assertEquals("2012-01-01 10:20:30", dt9.toString());
	}

	@Test
	public void testToString() {
		DateTime dt = new DateTime(2012, 9, 21);
		Assert.assertEquals("2012-09-21 00:00:00", dt.toString());
		Assert.assertEquals("2012-09-21", dt.toString("yyyy-MM-dd"));

		DateTime dt2 = new DateTime(2012, 1, 20);
		Assert.assertEquals("2012-01-20 00:00:00", dt2.toString());
		Assert.assertEquals("2012-01-20", dt2.toString("yyyy-MM-dd"));

		DateTime dt3 = new DateTime(2012, 1, 4, 20, 10, 3, 10);
		Assert.assertEquals("2012-01-04 20:10:03", dt3.toString());
		Assert.assertEquals("2012-01-04", dt3.toString("yyyy-MM-dd"));
	}

	@Test
	public void testGets() {
		DateTime dt = new DateTime(2012, 9, 2, 10, 20, 30, 40);
		Assert.assertEquals(2, dt.getDay());
		Assert.assertEquals(1, dt.getDayOfWeek());
		Assert.assertEquals(246, dt.getDayOfYear());
		Assert.assertEquals(10, dt.getHour());
		Assert.assertEquals(40, dt.getMilliSecond());
		Assert.assertEquals(20, dt.getMinute());
		Assert.assertEquals(9, dt.getMonth());
		Assert.assertEquals(30, dt.getSecond());
		Assert.assertEquals(1346552430040L, dt.getTime());
		Assert.assertEquals(2012, dt.getYear());
		Assert.assertEquals(1346552430040L, dt.getDate().getTime());
		Assert.assertEquals(1346552430040L, dt.getSqlDate().getTime());
	}

	@Test
	public void testaddYears() {
		DateTime dt = new DateTime(2012, 9, 2, 10, 20, 30, 40);
		Assert.assertEquals("2013-09-02 10:20:30", dt.addYears(1).toString());
		Assert.assertEquals("2012-09-02 10:20:30", dt.addYears(-1).toString());
		Assert.assertEquals("2011-09-02 10:20:30", dt.addYears(-1).toString());
	}

	@Test
	public void testaddMonths() {
		DateTime dt = new DateTime(2012, 9, 2, 10, 20, 30, 40);
		Assert.assertEquals("2012-10-02 10:20:30", dt.addMonths(1).toString());
		Assert.assertEquals("2012-09-02 10:20:30", dt.addMonths(-1).toString());
		Assert.assertEquals("2012-08-02 10:20:30", dt.addMonths(-1).toString());
	}

	@Test
	public void testaddDays() {
		DateTime dt = new DateTime(2012, 9, 2, 10, 20, 30, 40);
		Assert.assertEquals("2012-09-03 10:20:30", dt.addDays(1).toString());
		Assert.assertEquals("2012-09-02 10:20:30", dt.addDays(-1).toString());
		Assert.assertEquals("2012-09-01 10:20:30", dt.addDays(-1).toString());
		Assert.assertEquals("2012-08-31 10:20:30", dt.addDays(-1).toString());
	}

	@Test
	public void testaddHours() {
		DateTime dt = new DateTime(2012, 9, 2, 10, 20, 30, 40);
		Assert.assertEquals("2012-09-02 11:20:30", dt.addHours(1).toString());
		Assert.assertEquals("2012-09-02 10:20:30", dt.addHours(-1).toString());
		Assert.assertEquals("2012-09-02 09:20:30", dt.addHours(-1).toString());
		Assert.assertEquals("2012-09-01 21:20:30", dt.addHours(-12).toString());
	}

	@Test
	public void testAddMinutes() {
		DateTime dt = new DateTime(2012, 9, 2, 10, 20, 30, 40);
		Assert.assertEquals("2012-09-02 10:21:30", dt.addMinutes(1).toString());
		Assert.assertEquals("2012-09-02 11:21:30", dt.addMinutes(60).toString());
		Assert.assertEquals("2012-09-02 11:20:30", dt.addMinutes(-1).toString());
		Assert.assertEquals("2012-09-02 10:20:30", dt.addMinutes(-60)
				.toString());
	}

	@Test
	public void testAddSeconds() {
		DateTime dt = new DateTime(2012, 9, 2, 10, 20, 30, 40);
		Assert.assertEquals("2012-09-02 10:20:31", dt.addSeconds(1).toString());
		Assert.assertEquals("2012-09-02 10:21:31", dt.addSeconds(60).toString());
		Assert.assertEquals("2012-09-02 10:21:30", dt.addSeconds(-1).toString());
		Assert.assertEquals("2012-09-02 10:20:30", dt.addSeconds(-60)
				.toString());
	}

	@Test
	public void testAddMilliseconds() {
		DateTime dt = new DateTime(2012, 9, 2, 10, 20, 30, 40);
		Assert.assertEquals("2012-09-02 10:20:30.041", dt.addMilliseconds(1)
				.toString("yyyy-MM-dd HH:mm:ss.SSS"));

		Assert.assertEquals("2012-09-02 10:20:31.041", dt.addMilliseconds(1000)
				.toString("yyyy-MM-dd HH:mm:ss.SSS"));

		Assert.assertEquals("2012-09-02 10:20:31.040", dt.addMilliseconds(-1)
				.toString("yyyy-MM-dd HH:mm:ss.SSS"));

		Assert.assertEquals("2012-09-02 10:20:30.040", dt
				.addMilliseconds(-1000).toString("yyyy-MM-dd HH:mm:ss.SSS"));
	}

	@Test
	public void testCompareTo() {
		DateTime dt1 = new DateTime(2012, 9, 2, 10, 20, 30, 40);
		DateTime dt2 = new DateTime(2012, 9, 2, 10, 20, 30, 40);
		DateTime dt3 = new DateTime(2011, 9, 2, 10, 20, 30, 40);

		Assert.assertEquals(0, dt1.compareTo(dt2));
		Assert.assertEquals(1, dt1.compareTo(dt3));
		Assert.assertEquals(-1, dt3.compareTo(dt2));
	}

	@Test
	public void testCompare() {
		DateTime dt1 = new DateTime(2012, 9, 2, 10, 20, 30, 40);
		DateTime dt2 = new DateTime(2012, 9, 2, 10, 20, 30, 40);
		DateTime dt3 = new DateTime(2011, 9, 2, 10, 20, 30, 40);

		Assert.assertEquals(0, dt1.compare(dt1, dt2));
		Assert.assertEquals(1, dt1.compare(dt1, dt3));
		Assert.assertEquals(-1, dt3.compare(dt3, dt2));
	}

	@Test
	public void testEqual() {
		DateTime dt1 = new DateTime(2012, 1, 1);
		DateTime dt2 = new DateTime(2012, 1, 1);
		Assert.assertEquals(true, dt1.equals(dt2));

		DateTime dt3 = new DateTime(2012, 1, 1);
		DateTime dt4 = new DateTime(2012, 1, 2);
		Assert.assertEquals(false, dt3.equals(dt4));

		DateTime dt5 = new DateTime(2012, 1, 1, 10, 20, 30, 40);
		DateTime dt6 = new DateTime(2012, 1, 1);
		Assert.assertEquals(false, dt5.equals(dt6));
	}

	@Test
	public void testParse() throws ParseException {
		DateTime dt1 = DateTime.parse("2012-01-01", "yyyy-MM-dd");
		Assert.assertEquals("2012-01-01 00:00:00", dt1.toString());
		Assert.assertEquals("2012-01-01", dt1.toString("yyyy-MM-dd"));

		DateTime dt2 = DateTime.parse("2012-01-01 10:20:30");
		Assert.assertEquals("2012-01-01 10:20:30", dt2.toString());
		Assert.assertEquals("2012-01-01", dt2.toString("yyyy-MM-dd"));
	}

	@Test
	public void testBefore() {
		DateTime dt1 = new DateTime(2012, 9, 2, 10, 20, 30, 40);
		DateTime dt2 = new DateTime(2011, 9, 2, 10, 20, 30, 40);
		DateTime dt3 = new DateTime(2011, 9, 2, 10, 20, 30, 40);
		Assert.assertEquals(false, dt1.before(dt2));
		Assert.assertEquals(true, dt2.before(dt1));
		Assert.assertEquals(false, dt3.before(dt2));
	}

	@Test
	public void testAfter() {
		DateTime dt1 = new DateTime(2012, 9, 2, 10, 20, 30, 40);
		DateTime dt2 = new DateTime(2011, 9, 2, 10, 20, 30, 40);
		DateTime dt3 = new DateTime(2011, 9, 2, 10, 20, 30, 40);
		Assert.assertEquals(true, dt1.after(dt2));
		Assert.assertEquals(false, dt2.after(dt1));
		Assert.assertEquals(false, dt2.after(dt3));
	}

	@Test
	public void testDiff() {
		DateTime dt1 = new DateTime(2012, 9, 1, 10, 20, 30, 40);
		DateTime dt2 = new DateTime(2012, 9, 2, 10, 20, 30, 40);
		DateTime dt3 = new DateTime(2012, 9, 2, 10, 20, 30, 40);

		Assert.assertEquals(-1000 * 60 * 60 * 24, dt1.diff(dt2));
		Assert.assertEquals(1000 * 60 * 60 * 24, dt2.diff(dt1));
		Assert.assertEquals(0, dt3.diff(dt2));
	}

	@Test
	public void testFormat() throws ParseException {
		DateTime dt = new DateTime(2012, 9, 1, 10, 20, 30, 40);
		Assert.assertEquals(
				"2012-09-01 10:20:30.040",
				dt.truncate("yyyy-MM-dd HH:mm:ss.SSS").toString(
						"yyyy-MM-dd HH:mm:ss.SSS"));

		Assert.assertEquals("2012-09-01 00:00:00.000", dt
				.truncate("yyyy-MM-dd").toString("yyyy-MM-dd HH:mm:ss.SSS"));
	}
}

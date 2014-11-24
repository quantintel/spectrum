/*
 * Copyright (c) 2014  Paul Bernard
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Spectrum Finance is based in part on:
 *        QuantLib. http://quantlib.org/
 *
 */

package org.quantintel.ql.time

import java.util.{Calendar => JCalendar, Locale => JLocale, Formatter => JFormatter, Date => JDate}
import java.lang.{StringBuilder => JStringBuilder}

import org.quantintel.ql.time.Month._
import org.quantintel.ql.time.TimeUnit._
import org.quantintel.ql.time.Weekday._
import org.quantintel.ql.util.{Observability, Observable}

import scala.annotation.tailrec

/**
 * The Date class is not a generalization of Date in the conventional sense.
 * It is an implementation that imposes specific limitations which
 * are intended to make it compatible with various legacy systems
 * that are heavily used in the financial services industry.
 *
 * Mistakes in Lotus 1-2-3 were intentionally re-implemented in Excel.
 * For example: 1900 is actual not a leap but is included as one and
 * as such the implementation will behave as if February 29, 1900
 * exists.
 *
 * Additionally, the date range supported by Excel's serial number
 * methodology is limited to date between 1/1/1900 to 12/31/2199
 * inclusive.  This implementation mirrors that limitation.
 *
 * While these choices are generally offensive from an accuracy
 * perspective dutifully replicating them here is a function of
 * pragmatism and consistency with the libraries that inspired
 * this implementation.
 *
 * @author Paul Bernard
 * @version 1.0
 */
class Date() extends Comparable[Date] with Observable with Observability {

  var serialNumber = 0L

  def this(sn: Long){
    this()
    serialNumber = sn
    checkSerialNumber()
  }


  def this(dt: Int) {
    this(dt.toLong)
  }

  /**
   * Constructor for an instance of Date
   *
   * Maximum supported date: Date(31, 12, 2199)
   * Minimum supported date: Date(1, 1, 1901)
   *
   * An attempt to set a date outside this range will
   * result in a throw Exception("Date's serial number is outside allowed range")
   *
   * @author Paul Bernard
   * @since 1.0
   *
   * @param day the day of the month
   * @param month the month of the year
   * @param year they year
   */
  def this(day: Int, month: Int, year: Int) {
    this()
    require(year > 1900 && year < 2200, " out of bound.  It must be in [1901,2199]")
    require(month > 0 && month < 13, "Month " + month + " outside January-December range [1, 12]")

    val leap : Boolean = Date.isLeapYr(year)
    val len : Int = Date.monthLength(month, leap)
    val offset : Int = Date.monthOffset(month, leap)
    require(day <= len && day >0,
          "day outside month (" + month + ") day- range ")

    this.serialNumber = day + offset + Date.yearOffset(year)
  }

  /**
   * Constructor for an instance of Date
   *
   * Maximum supported date: Date(31, Month.DECEMBER, 2199)
   * Minimum supported date: Date(1, Month.JANUARY, 1901)
   *
   * An attempt to set a date outside this range will
   * result in a throw Exception("Date's serial number is outside allowed range")
   *
   * @author Paul Bernard
   * @since 1.0
   *
   * @param day the day of the month
   * @param month the month of the year
   * @param year the year
   */
  def this(day: Int, month: Month, year: Int) {
    this(day, month.id, year)
  }


  /**
   * Constructor for an instance of Date
   * Provides a bridge between the traditional Java Date object and this library
   *
   * Maximum supported date: December 31, 2199
   * Minimum supported date: January 1, 1901
   *
   * An attempt to set a date outside this range will
   * result in a throw Exception("Date's serial number is outside allowed range")
   *
   * @author Paul Bernard
   * @since 1.0
   *
   * @param date The Java Date used to initial a version of QL Date
   */
  def this(date: JDate)  {
    this()
    val c: JCalendar  = JCalendar.getInstance()
    c.setTime(date)
    val d = c.get(JCalendar.DAY_OF_MONTH)
    val m = c.get(JCalendar.MONTH)
    val y = c.get(JCalendar.YEAR)
    serialNumber = Date.fromDMY(d, m + 1, y) // java months are 0 based, so need to adjust
  }

  /**
   *
   * Based upon the date that this object represents
   * returns the current day of the week:
   * SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
   * The enumerations integer values range from 1 to 7
   *
   * @see Weekday enumeration
   * @author Paul Bernard
   * @since 1.0
   *
   * @return the Day of the week
   */
  def weekday : Weekday = {
    val w : Int = (serialNumber % 7).asInstanceOf[Int]
    Weekday.valueOf(if (w == 0L) 7 else w)
  }

  /**
   * The current integer representation of the day of the month.
   *
   * @since 1.0
   * @return The day of the month in integer format (from 1 to up to 31)
   */
  def dayOfMonth : Int = {
    dayOfYear - Date.monthOffset(month.id, this.isLeapYear)
  }

  /**
   * The year that this object represents within a range (inclusive): 1901 - 2199
   *
   * @author Paul Bernard
   * @since 1.0
   *
   * @return year
   */
  def dayOfYear : Int = {
    (serialNumber - Date.yearOffset(year) ).asInstanceOf[Int]
  }

  /**
   * The month of the year returned as a Month enumeration
   *
   * @author Paul Bernard
   * @since 1.0
   *
   * @return the month of the year
   */
  def month : Month = {
    val d: Int = dayOfYear
    var m: Int = d / 30 + 1
    val leap : Boolean = Date.isLeapYr(year)
    while(d <= Date.monthOffset(m, leap)) {
      m = m - 1
    }
    while(d > Date.monthOffset(m + 1, leap)){
      m = m + 1
    }
    Month.valueOf(m)
  }

  /**
   * The year associated with this Date
   *
   * @author Paul Bernard
   * @since 1.0
   *
   * @return the year
   */
  def year : Int = {
    var y: Int = ((serialNumber / 365) + 1900).asInstanceOf[Int]
    if(serialNumber <= Date.yearOffset(y)) y = y -1
    y
  }

  /**
   * Add a specified number of days to the existing referenced date.
   *
   * @author Paul Bernard
   * @version 1.0
   *
   *
   * @param days the number of days to add.
   * @return a mutated version of the date advanced N number of days.
   */
  def += (days: Int) : Date = {
    serialNumber = serialNumber + days
    checkSerialNumber()
    this
  }

  /**
   * Add a "period" which consists of a specific number of days, months or
   * years to the existing date.
   *
   * @param period the period of time to add
   * @return a mutated version of the date advanced by the amount specified in the period.
   */
  def +=(period: Period): Date = {
    serialNumber = advance(this, period.length, period.units).serialNumber
    checkSerialNumber()
    this
  }

  def -=(days: Int) : Date = {
    serialNumber = serialNumber - days
    checkSerialNumber()
    this
  }

  def -=(period: Period) : Date = {
    serialNumber = advance(this, -period.length, period.units).serialNumber
    checkSerialNumber()
    this
  }

  /**
   * Increment the date by one day returning a reference to this
   *
   * May throw Exception("Date's serial number is outside allowed range")
   * if result date would be before min serial number
   *
   * @author Paul Bernard
   * @since 1.0
   *
   * @return an incremented version of this
   */
  def ++ : Date = {
    serialNumber = serialNumber + 1
    checkSerialNumber()
    this
  }

  /**
   * Decrement the date by one day returning a reference to this
   *
   * May throw Exception("Date's serial number is outside allowed range")
   * if resulting date would exceed max serial number
   *
   * @author Paul Bernard
   * @since 1.0
   *
   * @return
   */
  def -- : Date = {
    serialNumber = serialNumber - 1
    checkSerialNumber()
    this
  }


  def + (days: Int) : Date = Date(serialNumber + days)

  def + (period: Period) : Date = {
    advance(this, period.length, period.units)
  }


  def - (days: Int) : Date = {
    Date(serialNumber - days)
  }

  def - (period: Period) : Date = {
    advance(this, -1 * period.length, period.units)
  }

  def - (another: Date): Long = {
    serialNumber - another.serialNumber
  }


  /**
   * Evaluates if the date in reference is the same
   * date as another date in terms of it's value.
   *
   * @since 1.0
   * @author Paul Bernard
   *
   * @param another some other date to compare to
   * @return true if the dates in terms of value are equal otherwise false
   */
  def == (another: Date) : Boolean = {
    serialNumber == another.serialNumber
  }

  /**
   *
   * Evaluates if the date in reference is not the same
   * date as another in terms of it's value.
   *
   * @since 1.0
   * @author Paul Bernard
   *
   * @param another some other date to compare to
   * @return true if the reference date falls on the same date s the argument date, otherwise false
   */
  def !=(another: Date) : Boolean = {
    serialNumber != another.serialNumber
  }

  /**
   * Evaluates if the date in reference is earlier than
   * another date.
   *
   * @since 1.0
   * @author Paul Bernard
   *
   * @param another some other date to compare to
   * @return true if the reference date is less than the argument date, otherwise false
   */
  def < (another: Date) : Boolean = {
    serialNumber < another.serialNumber
  }
  /**
   * Evaluates if the date in reference is earlier or falls on the same
   * date as the argument date.
   *
   * @since 1.0
   * @author Paul Bernard
   *
   * @param another some other date to compare to.
   * @return true if the reference date is less than or falls on the same date as the argument date, otherwise false
   */
  def <=(another: Date): Boolean = {
    serialNumber <= another.serialNumber
  }

  /**
   * Evaluates if the date in reference is after the argument date.
   *
   * @since 1.0
   * @author Paul Bernard
   *
   * @param another some other date to compare to
   * @return true if the reference date is after argument date, otherwise false
   */
  def > (another: Date) : Boolean = {
    serialNumber > another.serialNumber
  }

  /**
   * Evaluates if the date in reference is after or falls on the same date as the argument date, otherwise false
   *
   * @since 1.0
   * @author Paul Bernard
   *
   * @param another sone other date to compare to
   * @return true if after or the same date as argument date, otherwise false
   */
  def >= (another: Date) : Boolean = {
    serialNumber >= another.serialNumber
  }

  /**
   * Evaluates if the date being referenced is the last of of its
   * respective month.
   *
   * @since 1.0
   * @author Paul Bernard
   *
   * @return true if the reference date is the last day of its respective month
   */
  def isEndOfMonth : Boolean = {
    Date.isEndOfMonth(this)
  }


  /**
   * Evaluates if the date being referenced is a null date
   * A null date is the one exception to the min and max serial number limitations.
   * A null date will have a serial number of 0
   *
   * @since 1.0
   * @author Paul Bernard
   *
   * @return true if the date has a serial number of 0
   */
  def isNull : Boolean = {
    serialNumber<=0
  }

  /**
   * Evaluates if the date in reference is also the current date from
   * the perspective of the JVM it is running within based upon
   * the java.util.Calendar implementation.
   *
   * @author Paul Bernard
   * @see java.util.Calendar@getInstance
   * @since 1.0
   *
   * @return true if the date in reference is the current date.
   */
  def isToday : Boolean = {

    // A substantial variation from the implementation that inspired this one
    // is required due to the java platform

    val cal: JCalendar = java.util.Calendar.getInstance
    val d : Int = cal.get(JCalendar.DAY_OF_MONTH)
    val m : Int = cal.get(JCalendar.MONTH)
    val y : Int = cal.get(JCalendar.YEAR)
    serialNumber == Date.fromDMY(d, m + 1, y) // java months are 0 based and require adjustment
  }

  def longDate: JDate = new LongDate()

  def shortDate: JDate = new ShortDate()

  def isoDate: JDate = new ISODate()

  def compareTo(o: Date) : Int = {
    if (this == o) return 0
    if (this <= o) return -1
    1
  }

  def hasCode: Int = serialNumber.asInstanceOf[Int]

  /**
   * Evaluates from an identity perspective if two
   * dates are one and the same object or if
   * the two objects are from a value perspective the same date.
   *
   * @since 1.0
   * @author Paul Bernard
   *
   * @param anObject another object reference to compare to
   * @return true if the two object references refer to the same object or are equivalent
   */
  override def equals(anObject: Any) : Boolean = {
    if (super.equals(anObject)) return true
    if (anObject == null) return false
    anObject.isInstanceOf[Date] && anObject.asInstanceOf[Date].fEquals(this)
  }

  protected def fEquals(other: Date): Boolean = eq(other)

  override def toString : String = longDate.toString

  override def clone : Date = {
    super.clone.asInstanceOf[Date]
  }

  /**
   * TODO: analysis required
   * It is currently unclear whether calling this
   * should mutate the current object or not.
   *
   * @return a serial number representing the current date
   */
  def todaysSerialNumber : Long = {
    val cal : JCalendar = JCalendar.getInstance()
    val d : Int = cal.get(JCalendar.DAY_OF_MONTH)
    val m : Int = cal.get(JCalendar.MONTH)
    val y : Int = cal.get(JCalendar.YEAR)
    Date.fromDMY(d, m + 1, y)
  }

  /**
   * The checkSerialNumber method validates that the serial
   * number is always within a valid range of values inclusive of
   * and between the minimumSerialNumber and the maximumSerialNumber
   *
   * @since 1.0
   * @author Paul Bernard
   */
  private def checkSerialNumber()  {

    require(serialNumber >= Date.minimumSerialNumber &&
              serialNumber <= Date.maximumSerialNumber,
              "Date's serial number (" + serialNumber + ") outside allowed range [" +
                Date.minimumSerialNumber + "-" + Date.maximumSerialNumber + "], i.e. [" +
                Date.minDate + "-" + Date.maxDate + "]")
  }


  /**
   * Returns a date that is a specified number of units forward.
   * @param date the initial date
   * @param n number of units to forward
   * @param units the unit type
   * @return the new date.
   */
  private def advance (date: Date, n: Int, units: TimeUnit) : Date = {

    import org.quantintel.ql.time.TimeUnit._

    units match {
      case DAYS => date + n
      case WEEKS => date + 7 * n
      case MONTHS =>
        var d = date.dayOfMonth
        var m = date.month.id + n
        var y = date.year
        while(m > 12) {
          m = m - 12
          y = y + 1
        }
        while(m <1){
          m = m + 12
          y = y -1
        }

        require(y >= 1900 && y <= 2199,
              "year " + y + " out of bounds. It must be in [1901,2199]")

        val length = Date.monthLength(m, Date.isLeapYr(y))
        if (d > length) d = length
        Date(Date.fromDMY(d, m, y))

      case YEARS =>

        var d: Int = date.dayOfMonth
        val m : Month = date.month
        val y: Int = date.year + n


        require(y >= 1900 && y <= 2199,
             "year " + y + " out of bounds.  It must be in [1901, 2199]")

        if (d == 29 && m == FEBRUARY && !Date.isLeapYr(y)) d = 28
        Date(Date.fromDMY(d, m.id, y))

      case _ => throw new Exception("undefined time units")
    }
  }


  /**
   * Returns true if the date in reference is a leap year, otherwise false
   *
   * @author Paul Bernard
   * @since 1.0
   *
   * @return true if leap year.
   */
  def isLeapYear : Boolean = {
    Date.isLeapYr(this.year)
  }


  /**
   * The inner LongDate Class is being used exclusive
   * to provide an alternative toString implementation
   *
   * @since 1.0
   * @author Paul Bernard
   */
  private class LongDate() extends JDate() {

    setTime((serialNumber-25569)*86400000L)

    override def toString: String = {

      if (isNull) "null date"
      else {
        val sb : JStringBuilder = new JStringBuilder()
        val formatter = new JFormatter(sb, JLocale.US)
        formatter.format("%02d/%02d/%4d",
          month.id.asInstanceOf[AnyRef],
          dayOfMonth.asInstanceOf[AnyRef],
          year.asInstanceOf[AnyRef])
        sb.toString
      }

    }

  }

  class ShortDate extends JDate(){

    setTime((serialNumber-25569)*86400000L)

    override def toString: String = {
      if (isNull) "null date"
      else {
        val sb : JStringBuilder = new JStringBuilder()
        val formatter = new JFormatter(sb, JLocale.US)
        formatter.format("%02d/%02d/%4d",
          month.id.asInstanceOf[AnyRef],
          dayOfMonth.asInstanceOf[AnyRef],
          year.asInstanceOf[AnyRef])
        sb.toString
      }
    }

  }

  class ISODate extends JDate() {

    setTime((serialNumber-25569)*86400000L)

    override def toString: String = {
      if (isNull) "null date"
      else {

        val sb : JStringBuilder = new JStringBuilder()
        val formatter = new JFormatter(sb, JLocale.US)
        val c = JCalendar.getInstance
        c.setTime(this)
        formatter.format("%04d-%02d-%02d", List(c.get(JCalendar.YEAR), c.get(JCalendar.MONTH) + 1,
          c.get(JCalendar.DAY_OF_MONTH)))
        sb.toString
      }
    }


  }

  def assign(serialNumber: Long): Date = {
    this.serialNumber = serialNumber
    this
  }



}


object Date {

  def apply() : Date = new Date()

  def apply(serialNumber: Long)  : Date = {
    new Date(serialNumber)
  }

  def apply(date: JDate) : Date = {
    new Date(date)
  }



  def apply(d: Int, m: Month, y: Int) : Date = new Date(d, m, y)

  def apply(dayOfMonth: Int, month: Int, year: Int) : Date = {

    val m = month match {
      case 1 => JANUARY
      case 2 => FEBRUARY
      case 3 => MARCH
      case 4 => APRIL
      case 5 => MAY
      case 6 => JUNE
      case 7 => JULY
      case 8 => AUGUST
      case 9 => SEPTEMBER
      case 10 => OCTOBER
      case 11 => NOVEMBER
      case 12 => DECEMBER
    }

    Date(dayOfMonth, m, year)
  }

  private val  monthLengthArr : Array[Int] = Array(
   31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 )

  private val monthLeapLengthArr: Array[Int] = Array(
    31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 )


  private val monthOffsetArr : Array[Int] = Array(
    0, 31, 59, 90, 120, 151,        // Jan - Jun
    181, 212, 243, 273, 304, 334,   // Jun - Dec
    365 // used in dayOfMonth to bracket day
  )

  private val monthLeapOffsetArr : Array[Int] = Array(
    0, 31, 60, 91, 121, 152,        // Jan - Jun
    182, 213, 244, 274, 305, 335,   // Jun - Dec
    366 // used in dayOfMonth to bracket day
  )


  private val yearOffsetArr : Array[Int] = Array(
    // 1900-1909
    0,  366,  731, 1096, 1461, 1827, 2192, 2557, 2922, 3288,
    // 1910-1919
    3653, 4018, 4383, 4749, 5114, 5479, 5844, 6210, 6575, 6940,
    // 1920-1929
    7305, 7671, 8036, 8401, 8766, 9132, 9497, 9862,10227,10593,
    // 1930-1939
    10958,11323,11688,12054,12419,12784,13149,13515,13880,14245,
    // 1940-1949
    14610,14976,15341,15706,16071,16437,16802,17167,17532,17898,
    // 1950-1959
    18263,18628,18993,19359,19724,20089,20454,20820,21185,21550,
    // 1960-1969
    21915,22281,22646,23011,23376,23742,24107,24472,24837,25203,
    // 1970-1979
    25568,25933,26298,26664,27029,27394,27759,28125,28490,28855,
    // 1980-1989
    29220,29586,29951,30316,30681,31047,31412,31777,32142,32508,
    // 1990-1999
    32873,33238,33603,33969,34334,34699,35064,35430,35795,36160,
    // 2000-2009
    36525,36891,37256,37621,37986,38352,38717,39082,39447,39813,
    // 2010-2019
    40178,40543,40908,41274,41639,42004,42369,42735,43100,43465,
    // 2020-2029
    43830,44196,44561,44926,45291,45657,46022,46387,46752,47118,
    // 2030-2039
    47483,47848,48213,48579,48944,49309,49674,50040,50405,50770,
    // 2040-2049
    51135,51501,51866,52231,52596,52962,53327,53692,54057,54423,
    // 2050-2059
    54788,55153,55518,55884,56249,56614,56979,57345,57710,58075,
    // 2060-2069
    58440,58806,59171,59536,59901,60267,60632,60997,61362,61728,
    // 2070-2079
    62093,62458,62823,63189,63554,63919,64284,64650,65015,65380,
    // 2080-2089
    65745,66111,66476,66841,67206,67572,67937,68302,68667,69033,
    // 2090-2099
    69398,69763,70128,70494,70859,71224,71589,71955,72320,72685,
    // 2100-2109
    73050,73415,73780,74145,74510,74876,75241,75606,75971,76337,
    // 2110-2119
    76702,77067,77432,77798,78163,78528,78893,79259,79624,79989,
    // 2120-2129
    80354,80720,81085,81450,81815,82181,82546,82911,83276,83642,
    // 2130-2139
    84007,84372,84737,85103,85468,85833,86198,86564,86929,87294,
    // 2140-2149
    87659,88025,88390,88755,89120,89486,89851,90216,90581,90947,
    // 2150-2159
    91312,91677,92042,92408,92773,93138,93503,93869,94234,94599,
    // 2160-2169
    94964,95330,95695,96060,96425,96791,97156,97521,97886,98252,
    // 2170-2179
    98617,98982,99347,99713,100078,100443,100808,101174,101539,101904,
    // 2180-2189
    102269,102635,103000,103365,103730,104096,104461,104826,105191,105557,
    // 2190-2199
    105922,106287,106652,107018,107383,107748,108113,108479,108844,109209,
    // 2200
    109574)


  private val yearIsLeapArr : Array[Boolean] = Array(
    // 1900 is leap in agreement with Excel's bug
    // 1900 is out of valid date range anyway
    // 1900-1909
    true,false,false,false, true,false,false,false, true,false,
    // 1910-1919
    false,false, true,false,false,false, true,false,false,false,
    // 1920-1929
    true,false,false,false, true,false,false,false, true,false,
    // 1930-1939
    false,false, true,false,false,false, true,false,false,false,
    // 1940-1949
    true,false,false,false, true,false,false,false, true,false,
    // 1950-1959
    false,false, true,false,false,false, true,false,false,false,
    // 1960-1969
    true,false,false,false, true,false,false,false, true,false,
    // 1970-1979
    false,false, true,false,false,false, true,false,false,false,
    // 1980-1989
    true,false,false,false, true,false,false,false, true,false,
    // 1990-1999
    false,false, true,false,false,false, true,false,false,false,
    // 2000-2009
    true,false,false,false, true,false,false,false, true,false,
    // 2010-2019
    false,false, true,false,false,false, true,false,false,false,
    // 2020-2029
    true,false,false,false, true,false,false,false, true,false,
    // 2030-2039
    false,false, true,false,false,false, true,false,false,false,
    // 2040-2049
    true,false,false,false, true,false,false,false, true,false,
    // 2050-2059
    false,false, true,false,false,false, true,false,false,false,
    // 2060-2069
    true,false,false,false, true,false,false,false, true,false,
    // 2070-2079
    false,false, true,false,false,false, true,false,false,false,
    // 2080-2089
    true,false,false,false, true,false,false,false, true,false,
    // 2090-2099
    false,false, true,false,false,false, true,false,false,false,
    // 2100-2109
    false,false,false,false, true,false,false,false, true,false,
    // 2110-2119
    false,false, true,false,false,false, true,false,false,false,
    // 2120-2129
    true,false,false,false, true,false,false,false, true,false,
    // 2130-2139
    false,false, true,false,false,false, true,false,false,false,
    // 2140-2149
    true,false,false,false, true,false,false,false, true,false,
    // 2150-2159
    false,false, true,false,false,false, true,false,false,false,
    // 2160-2169
    true,false,false,false, true,false,false,false, true,false,
    // 2170-2179
    false,false, true,false,false,false, true,false,false,false,
    // 2180-2189
    true,false,false,false, true,false,false,false, true,false,
    // 2190-2199
    false,false, true,false,false,false, true,false,false,false,
    // 2200
    false)


  /**
   * Returns an instance of the current date.
   * @return the current date
   */
  def todaysDate: Date = {
    val cal : JCalendar = JCalendar.getInstance()
    val d  : Int = cal.get(JCalendar.DAY_OF_MONTH)
    val m  : Int = cal.get(JCalendar.MONTH)
    val y : Int  = cal.get(JCalendar.YEAR)
    new Date(d, m + 1, y)
  }


  def minDate : Date = new Date(minimumSerialNumber)

  def maxDate : Date = new Date(maximumSerialNumber)

  def isLeapYr(year: Int): Boolean = {
    Date.yearIsLeapArr(year - 1900)
  }

  /**
   * Returns the last day of the month in the month of the date provided.
   * @param date Any date in the month to be used as reference
   * @return The last day of the month based upon the date referenced.
   */
  def endOfMonth(date: Date): Date = {
    val m : Int = date.month.id
    val y : Int = date.year
    Date(monthLength(m, isLeapYr(y)), m, y)
  }

  def isEndOfMonth(date: Date) : Boolean = {
    date.dayOfMonth == monthLength(date.month.id, isLeapYr(date.year))
  }

  def nextWeekday (d: Date, w: Weekday) : Date = {
    val wd : Int = d.weekday.id
    val dow: Int = w.id
    d + (if (wd > dow) 7 else 0) - wd + dow
  }

  @tailrec def nthWeekday(n: Int, w: Weekday, m: Month, y: Int) : Date = {
    nthWeekday(n, w, m, y)
  }

  def nthWeekday(nth: Int, dayOfWeek: Weekday, m: Int, y: Int) : Date = {

    require(nth>0, "zeroth day of week in a given (month, year) is undefined")
    require(nth<6, "no more than 5 weekday in a given (month, year)")

    val first: Int = new Date(1, m, y).weekday.id
    val skip = nth - (if (dayOfWeek.id >= first) 1 else 0)
    new Date(1 + dayOfWeek.id - first + skip * 7, m, y)
  }



  def min(t: Date*) : Date = {
    if (t.length == 0) new Date()
    else {
      var min :Date = t(0)
      for (i <- 1 until (t.length-1)){
          val curr : Date = t(i)
          if (curr < min) min = curr
      }
      min
    }
  }

  def max(t: Date*) : Date = {
    if (t.length == 0) new Date()
    else {
      var max :Date = t(0)
      for (i <- 1 until (t.length-1)){
        val curr : Date = t(i)
        if (curr > max) max = curr
      }
      max
    }
  }

  def minimumSerialNumber : Long = 367

  def maximumSerialNumber : Long = 109574

  def fromDMY(d: Int, m: Int, y: Int)  : Long = {

    val leap: Boolean = isLeapYr(y)
    val offset: Int = monthOffset(m, leap)
    val result: Long = d + offset + yearOffset(y)
    result
  }

  def monthLength(m: Int, leapYear: Boolean) : Int = {
    if (leapYear) Date.monthLeapLengthArr(m -1) else Date.monthLengthArr(m -1)
  }

  def monthOffset (m: Int, leapYear: Boolean) : Int = {

    if (leapYear)
      Date.monthLeapOffsetArr(m -1)
        else Date.monthOffsetArr(m -1)
  }



  def yearOffset(year: Int) : Long = {
    yearOffsetArr(year - 1900)
  }

  /**
   * Returns the position of the first date in a list of dates that
   * is equal to or greater than the date provided.
   *
   * @param dates The list of dates
   * @param value The date used for comparison
   * @return an integer representing the position of the first date that is equal to or greater
   */
  def lowerBound(dates: List[Date], value: Date) : Int = {

    var len = dates.size
    var from = 0
    var half = 0
    var middle = 0

    while(len > 0){
      half = len >> 1
      middle = from
      middle = middle + half

      if(value.compareTo(dates(middle)) == 1){
        from = middle
        from = from + 1
        len = len - half -1
      } else {
        len = half
      }
    }
    from
  }

  /**
   * Returns the position of the first element in the list that is
   * greater than the referenced date.
   *
   * @param dates A list of Dates
   * @param value A date to compare to each item it the list
   * @return the index of the first date greater than the date provided
   */
  def upperBound(dates: List[Date], value: Date) : Int = {

    var len : Int = dates.size
    var from : Int = 0
    var half : Int = 0
    var middle : Int = 0

    while(len > 0){
      half = half >> 1
      middle = from
      middle = middle + half

      if(value.compareTo(dates(middle)) == -1) {
        len = half
      } else {
        from = middle
        from = from + 1
        len = len - half -1
      }
    }
    from
  }





}

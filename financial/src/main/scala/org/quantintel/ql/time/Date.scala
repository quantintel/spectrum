package org.quantintel.ql.time

import java.util.{Calendar => JCalendar, Locale => JLocale, Formatter => JFormatter, Date => JDate}
import java.lang.{StringBuilder => JStringBuilder}

import org.quantintel.ql.time.Month._
import org.quantintel.ql.time.TimeUnit._
import org.quantintel.ql.time.Weekday._

class Date (var serialNumber: Long) {

  checkSerialNumber

  def this() {
    this(0)
  }

  def this(day: Int, month: Month, year: Int) {
    this(Date.fromDMY(day, month.id, year))
  }

  def this(day: Int, month: Int, year: Int) {
    this(Date.fromDMY(day, month, year))
  }


  def this(date: JDate)  {
    this(0)
    val c: JCalendar  = JCalendar.getInstance();
    c.setTime(date);
    val d = c.get(JCalendar.DAY_OF_MONTH);
    val m = c.get(JCalendar.MONTH);
    val y = c.get(JCalendar.YEAR);
    serialNumber = Date.fromDMY(d, m+1, y);
  }

  def weekday : Weekday = {
    val w : Int = (serialNumber % 7).asInstanceOf[Int]
    Weekday.valueOf(if (w == 0L) 7 else w)
  }

  def dayOfMonth : Int = {
    dayOfYear - Date.monthOffset(month.id, this.isLeapYear)
  }

  def dayOfYear : Int = {
    (serialNumber - Date.yearOffset(year) ).asInstanceOf[Int]
  }

  def month : Month = {
    val d: Int = dayOfYear
    var m: Int = d / 30 + 1
    val leap : Boolean = isLeapYear
    while(d <= Date.monthOffset(m, leap)) {
      m = m - 1
    }
    while(d>Date.monthOffset(m + 1, leap)){
      m = m + 1
    }
    Month.valueOf(m)
  }

  def year : Int = {
    var y: Int = ((serialNumber / 365) + 1900).asInstanceOf[Int]
    if(serialNumber <= Date.yearOffset(y)) y = y -1
    y
  }

  def addAssign(days: Int) : Date = {
    serialNumber = serialNumber + days
    checkSerialNumber
    this
  }

  def addAssign(period: Period): Date = {
    serialNumber = advance(this, period.length, period.units)
    checkSerialNumber
    this
  }

  def subAssign(days: Int) : Date = {
    serialNumber = serialNumber - days
    checkSerialNumber
    this
  }

  def subAssign(period: Period) : Date = {
    serialNumber = advance(this, -1 * period.length, period.units)
    checkSerialNumber
    this
  }

  def inc : Date = {
    serialNumber = serialNumber + 1
    checkSerialNumber
    this
  }

  def dec : Date = {
    serialNumber = serialNumber - 1
    checkSerialNumber
    this
  }

  def add(days: Int) : Date = Date(serialNumber + days)

  def add(period: Period) : Date = {
    Date(advance(this, period.length, period.units))
  }

  def sub(days: Int) : Date = {
    Date(serialNumber - days)
  }

  def sub(period: Period) : Date = {
    Date(advance(this, -1 * period.length, period.units))
  }

  def sub(another: Date): Long = {
    serialNumber - another.serialNumber
  }


  def eq (another: Date) : Boolean = {
    serialNumber == another.serialNumber
  }

  def ne (another: Date) : Boolean = {
    serialNumber != another.serialNumber
  }
  def lt (another: Date) : Boolean = {
    serialNumber < another.serialNumber
  }

  def le (another: Date) : Boolean = {
    serialNumber <= another.serialNumber
  }

  def gt (another: Date) : Boolean = {
    serialNumber > another.serialNumber
  }

  def ge (another: Date) : Boolean = {
    serialNumber >= another.serialNumber
  }

  def isEndOfMonth : Boolean = {
    Date.isEndOfMonth(this)
  }


  def isNull : Boolean = {
    serialNumber<=0
  }

  def isToday : Boolean = {
    val cal: JCalendar = java.util.Calendar.getInstance
    val d : Int = cal.get(JCalendar.DAY_OF_MONTH)
    val m : Int = cal.get(JCalendar.MONTH)
    val y : Int = cal.get(JCalendar.YEAR)
    serialNumber == Date.fromDMY(d, m+1, y)
  }

  def longDate: JDate = new LongDate()

  def shortDate: JDate = new ShortDate()

  def isoDate: JDate = new ISODate()

  def compareTo(o: Date) : Int = {
    if (this.equals(o)) return 0
    if (this.le(o)) return -1
    1
  }

  def hasCode: Int = serialNumber.asInstanceOf[Int]

  override def equals(anObject: Any) : Boolean = {
    if (this == anObject) return true
    if (anObject == null) return false
    anObject.isInstanceOf[Date] && anObject.asInstanceOf[Date].fEquals(this)
  }

  def fEquals(other: Date): Boolean = eq(other)

  //override def toString = longDate.toString

  override def clone : Date = {
    super.clone.asInstanceOf[Date]
  }

  def todaysSerialNumber : Long = {
    val cal : JCalendar = JCalendar.getInstance();
    val d : Int = cal.get(JCalendar.DAY_OF_MONTH);
    val m : Int = cal.get(JCalendar.MONTH);
    val y : Int = cal.get(JCalendar.YEAR);
    Date.fromDMY(d, m+1, y);
  }

  def checkSerialNumber = {
    if (!((serialNumber >= Date.minimumSerialNumber) && (serialNumber <= Date.maximumSerialNumber) ) && serialNumber!=0)
      throw new Exception("Date's serial number is outside allowed range")
  }


  private def advance (date: Date, n: Int, units: TimeUnit) : Long = {

    import org.quantintel.ql.time.TimeUnit._

    units match {
      case DAYS => return n + date.serialNumber
      case WEEKS => return 7 * n + date.serialNumber
      case MONTHS => {
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
        val length = Date.monthLength(m, Date.isLeapYr(y))
        if (d > length) d = length
        return Date.fromDMY(d, m, y)
      }
      case YEARS => {

        var d: Int = date.dayOfMonth
        val m : Month = date.month
        val y: Int = date.year + n

        if (d == 29 && m == FEBRUARY && !Date.isLeapYr(y)) d = 28
        return Date.fromDMY(d, m.id, y)
      }
      case _ => throw new Exception("undefined time units")
    }
  }



  def isLeapYear : Boolean = {

    Date.isLeapYr(this.year)
  }


  private class LongDate() extends JDate() {

    setTime((serialNumber-25569)*86400000L)

    override def toString(): String = {

      if (isNull) return "null date"
      else {
        //val sb : JStringBuilder = new JStringBuilder()
        //val formatter = new JFormatter(sb, JLocale.US)
        //formatter.format("%s %d, %d", Array(month.id, dayOfMonth, year))
        //sb.toString
        println("known values: " + month.id + " " + dayOfMonth + " " + year)
        System.exit(1)
        "Date: " + month.id + " " + dayOfMonth + " " + year
      }

    }




  }

  class ShortDate extends JDate(){

    setTime((serialNumber-25569)*86400000L)

    override def toString(): String = {
      if (isNull) return "null date"
      else {
        val sb : JStringBuilder = new JStringBuilder()
        val formatter = new JFormatter(sb, JLocale.US)
        formatter.format("%02d/%02d/%4d", Array(month.id, dayOfMonth, year))
        sb.toString
      }
    }

  }

  class ISODate extends JDate() {

    setTime((serialNumber-25569)*86400000L)

    override def toString(): String = {
      if (isNull) return "null date"
      else {

        val sb : JStringBuilder = new JStringBuilder()
        val formatter = new JFormatter(sb, JLocale.US)
        val c = JCalendar.getInstance
        c.setTime(this)
        formatter.format("%04d-%02d-%02d", List(c.get(JCalendar.YEAR), c.get(JCalendar.MONTH)+1,
          c.get(JCalendar.DAY_OF_MONTH)))
        sb.toString
      }
    }


  }



}





object Date {

  def apply()  = new Date()

  def apply(serialNumber: Long) = {
    new Date(serialNumber)
  }


  def apply(m: Month, d: Int, y: Int)  = new Date(d, m, y)

  def apply(month: Int, dayOfMonth: Int, year: Int) : Date = {

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

    Date(m, dayOfMonth, year)
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


  //type DayOfMonth = Int
  //type Year = Int



  def todaysDate: Date = {
    val cal : JCalendar = JCalendar.getInstance();
    val d  : Int = cal.get(JCalendar.DAY_OF_MONTH);
    val m  : Int = cal.get(JCalendar.MONTH);
    val y : Int  = cal.get(JCalendar.YEAR);
    new Date(d, m + 1, y);
  }


  def minDate : Date = new Date(minimumSerialNumber)

  def maxDate : Date = new Date(maximumSerialNumber)

  def isLeapYr(year: Int): Boolean = {
    Date.yearIsLeapArr(year - 1900)
  }

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
    Date(d.serialNumber + (if (wd > dow) 7 else 0) - wd + dow)
  }

  def nthWeekday(n: Int, w: Weekday, m: Month, y: Int) : Date = {
    nthWeekday(n, w, m, y)
  }

  def nthWeekday(nth: Int, dayOfWeek: Weekday, month: Int, year: Int) : Date = {
    val m: Int = month
    val y: Int = year
    val dow = dayOfWeek.id
    val first: Int = new Date(1, m, y).weekday.id
    val skip = nth - (if (dow >= first) 1 else 0)
    new Date(1 + dow - first + skip * 7, m, y)
  }



  def min(t: Date*) : Date = {
    if (t.length == 0) new Date()
    else {
      var min :Date = t(0)
      for (i <- 1 until (t.length-1)){
          val curr : Date = t(i)
          if (curr.lt(min)) min = curr
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
        if (curr.gt(max)) max = curr
      }
      max
    }
  }

  def minimumSerialNumber : Long = 367

  def maximumSerialNumber : Long = 109574

  private def fromDMY(d: Int, m: Int, y: Int)  : Long = {
    val leap: Boolean = isLeapYr(y);
    var len : Int = monthLength(m, leap);
    val offset: Int = monthOffset(m, leap);
    val result: Long = d + offset + Date.yearOffset(y);
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



  /*

  private def createJavaDate (d: Date) : java.util.Date = {
    val date = d.year + "/" + d.month + "/" + d.dayOfMonth
    val sdf = new SimpleDateFormat("yyyy/MM/dd")
    sdf.parse(date)
  }

  private def createJavaDate(month: Month, day: DayOfMonth, year: Year) : java.util.Date = {
    val cal = JCalendar.getInstance()
    cal.set(JCalendar.MONTH, month.id)
    cal.set(JCalendar.DAY_OF_MONTH, day)
    cal.set(JCalendar.YEAR, year)
    cal.getTime()
  }

  private def createQuantDate(date: java.util.Date) : Date = {
    val cal = JCalendar.getInstance()
    cal.setTime(date)
    val year = cal.get(JCalendar.YEAR)
    val month = cal.get(JCalendar.MONTH)
    val day = cal.get(JCalendar.DAY_OF_MONTH)
    Date(month, day, year)
  }



  private def createDateTuple (date: java.util.Date) : (Month, DayOfMonth, Year) = {

    val yearFormat = new SimpleDateFormat("yyyy")
    val monthFormat = new SimpleDateFormat("MM")
    val dayFormat = new SimpleDateFormat("dd")

    val year = yearFormat.format(date)
    val month = monthFormat.format(date)
    val day = dayFormat.format(date)

    val d = day.toInt

    val m = month match {
      case "jan" => JANUARY
      case "feb" => FEBRUARY
      case "mar" => MARCH
      case "apr" => APRIL
      case "may" => MAY
      case "jun" => JUNE
      case "jul" => JULY
      case "aug" => AUGUST
      case "sep" => SEPTEMBER
      case "oct" => OCTOBER
      case "nov" => NOVEMBER
      case "dec" => DECEMBER
    }

    val y = year.toInt

    (m, d, y)
  }
  */

}

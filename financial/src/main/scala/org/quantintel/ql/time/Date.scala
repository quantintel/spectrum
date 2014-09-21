package org.quantintel.ql.time

import java.text.SimpleDateFormat
import java.util.{Calendar => JCalendar}


import org.quantintel.ql.time.Month._
import org.quantintel.ql.time.Weekday._

class Date (var month: Month, var dayOfMonth: Int,  var year: Int) {


  def == (that: Date) : Boolean = {
    if (this.dayOfMonth==that.dayOfMonth && this.month==that.month && this.year==that.year) true else false
  }

  def != (other: Date) : Boolean = {
    !(this==other)
  }
  def < (that: Date) : Boolean = {
    Date.createJavaDate(this).before(Date.createJavaDate(that))
  }

  def <= (that: Date) : Boolean = {
    (this < that) || (this == that)
  }

  def > (that: Date) : Boolean = {
    Date.createJavaDate(this).after(Date.createJavaDate(that))
  }

  def >= (that: Date) : Boolean = {
    (this > that) || (this == that)
  }

  def isEndOfMonth : Boolean = {
    Date.isEndOfMonth(this)
  }

  def isWeekDay : Boolean = {
    Date.isWeekday(this)
  }

  def weekday : Weekday = {
    Date.weekday(this)
  }

  def dayOfYear : Int = {
    Date.dayOfYear(this)
  }

  def isLeapYear : Boolean = {
    Date.isLeap(this.year)
  }

  def isBetween(start: Date, end: Date) : Boolean = {
    start < this && this < end
  }



}

object Date {

  type DayOfMonth = Int
  type Year = Int

  def apply(m: Month, d: DayOfMonth, y: Year) : Date = new Date(m, d, y)

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

    this(m, dayOfMonth, year)
  }

  def todaysDate: Date = {
    val today = JCalendar.getInstance().getTime
    val t = createDateTuple(today)
    Date(t._1, t._2, t._3)
  }

  def maxDate : Date = Date(DECEMBER, 31, Int.MaxValue)

  def minDate : Date = {
    val min = new java.util.Date(0L)
    val t = createDateTuple(min)
    Date(t._1, t._2, t._3)
  }

  def isLeap(year: Int): Boolean = {
    val cal = java.util.Calendar.getInstance()
    cal.set(java.util.Calendar.YEAR, year)
    cal.getActualMaximum(JCalendar.DAY_OF_YEAR) > 365
  }

  def endOfMonth(date: Date): Date = {
    val cal = JCalendar.getInstance()
    cal.set(JCalendar.MONTH, date.month.id)
    cal.set(JCalendar.YEAR, date.year)
    cal.set(JCalendar.DAY_OF_MONTH, date.dayOfMonth)
    cal.set(JCalendar.DATE, cal.getActualMaximum(JCalendar.DATE))
    val tup = createDateTuple(cal.getTime)
    Date(tup._1, tup._2, tup._3)
  }

  def isEndOfMonth(date: Date) : Boolean = {
    endOfMonth(date)==date
  }

  def dayOfYear(date: Date) : Int = {
    val cal = JCalendar.getInstance()
    cal.setTime(createJavaDate(date))
    val doy : Int = cal.get(JCalendar.DAY_OF_YEAR)
    doy
  }


  def isWeekday(date: Date) : Boolean = {
    val cal = JCalendar.getInstance()
    cal.setTime(createJavaDate(date))
    val dow = cal.get(JCalendar.DAY_OF_WEEK)

    val nd : Boolean = dow match {
      case JCalendar.SUNDAY => false
      case JCalendar.SATURDAY => false
      case _ => true
    }
    nd
  }

  def weekday (date: Date) : Weekday = {
    val cal = JCalendar.getInstance()
    cal.setTime(createJavaDate(date))
    val dow = cal.get(JCalendar.DAY_OF_WEEK)
    val ret : Weekday = dow match {
      case JCalendar.SUNDAY => SUNDAY
      case JCalendar.MONDAY => MONDAY
      case JCalendar.TUESDAY => TUESDAY
      case JCalendar.WEDNESDAY => WEDNESDAY
      case JCalendar.THURSDAY => THURSDAY
      case JCalendar.FRIDAY => FRIDAY
      case JCalendar.SATURDAY => SATURDAY
    }
    ret
  }

  def isWeekend(date: Date) : Boolean = {
    if (!isWeekday(date)) true else false
  }

  def nextWeekday (date: Date) : Date = {

     val cal = JCalendar.getInstance()
     cal.setTime(createJavaDate(date))
     val dow = cal.get(JCalendar.DAY_OF_WEEK)

     val nd : java.util.Date = dow match {
       case JCalendar.FRIDAY =>
         cal.add(JCalendar.DATE, 3)
         cal.getTime
       case JCalendar.SATURDAY =>
         cal.add(JCalendar.DATE, 2)
         cal.getTime
       case _ =>
         cal.add(JCalendar.DATE, 1)
         cal.getTime
     }
    createQuantDate(nd)

  }

  def nthWeekday(size: Int, weekday: Weekday.type, month : Month, y: Year) : Date = ???


  private def createJavaDate (d: Date) : java.util.Date = {
    val date = d.year + "/" + d.month + "/" + d.dayOfMonth
    val sdf = new SimpleDateFormat("yyyy/MM/dd")
    sdf.parse(date)
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


}

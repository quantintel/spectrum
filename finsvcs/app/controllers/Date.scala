package controllers

import play.api._
import play.api.mvc._
import org.quantintel.ql.time.{Date => Dt}

import org.quantintel.lang.numeric._


object Date extends Controller {

  def today = Action {
    Ok(views.html.today(String.valueOf(Dt.todaysDate.serialNumber)))
  }

  def todaySimpleFmt = Action {
    Ok(views.html.todaySimpleFmt(String.valueOf(Dt.todaysDate)))
  }

  def simpleFmt(num: Long) = Action {
    Ok(views.html.today(String.valueOf(Dt(num))))
  }

  def weekday = Action {
    Ok(views.html.weekday(String.valueOf(Dt.todaysDate.weekday)))
  }

  def dtWeekday(num: Long) = Action {
    Ok(views.html.weekday(String.valueOf(Dt(num).weekday)))
  }

  def dtstrWeekday(mm: Int, dd: Int, yy: Int) = Action {
    Ok(views.html.weekday(String.valueOf(Dt(dd, mm, yy).weekday)))
  }

  def year = Action {
    Ok(views.html.year(String.valueOf(Dt.todaysDate.year)))
  }

  def dtYear (num: Long) = Action {
    Ok(views.html.year(String.valueOf(Dt(num).year)))
  }

  def dtstrYear (mm: Int, dd: Int, yy: Int) = Action {
    Ok(views.html.year(String.valueOf(Dt(dd, mm, yy).year)))
  }

  def month = Action {
    Ok(views.html.month(String.valueOf(Dt.todaysDate.month)))
  }

  def dtMonth (num: Long) = Action {
    Ok(views.html.month(String.valueOf(Dt(num).month)))
  }

  def dtstrMonth (mm: Int, dd: Int, yy: Int) = Action {
    Ok(views.html.month(String.valueOf(Dt(dd, mm, yy).month)))
  }

  def dayOfMonth = Action {
    Ok(views.html.dayOfMonth(String.valueOf(Dt.todaysDate.dayOfMonth)))
  }

  def dtDayOfMonth (num: Long) = Action {
    Ok(views.html.dayOfMonth(String.valueOf(Dt(num).dayOfMonth)))
  }

  def dtstrDayOfMonth (mm: Int, dd: Int, yy: Int) = Action {
    Ok(views.html.dayOfMonth(String.valueOf(Dt(dd, mm, yy).dayOfMonth)))
  }

  def dayOfYear = Action {
    Ok(views.html.dayOfYear(String.valueOf(Dt.todaysDate.dayOfYear)))
  }

  def dtDayOfYear (num: Long)  = Action {
    Ok(views.html.dayOfYear(String.valueOf(Dt(num).dayOfYear)))
  }

  def dtstrDayOfYear (mm: Int, dd: Int, yy: Int)  = Action {
    Ok(views.html.dayOfYear(String.valueOf(Dt(dd, mm, yy).dayOfYear)))
  }

  def isLeapYear = Action {
    Ok(views.html.isLeapYear(String.valueOf(Dt.todaysDate.isLeapYear)))
  }

  def dtIsLeapYear (num: Long)  = Action {
    Ok(views.html.isLeapYear(String.valueOf(Dt(num).isLeapYear)))
  }

  def dtstrIsLeapYear (mm: Int, dd: Int, yy: Int)  = Action {
    Ok(views.html.isLeapYear(String.valueOf(Dt(dd, mm, yy).isLeapYear)))
  }






}
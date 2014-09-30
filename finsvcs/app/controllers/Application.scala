package controllers

import play.api._
import play.api.mvc._
import org.quantintel.ql.time.Date

object Application extends Controller {

  def today = Action {
    Ok(views.html.today(String.valueOf(Date.todaysDate.serialNumber)))
  }

  def todaySimpleFmt = Action {
    Ok(views.html.todaySimpleFmt(String.valueOf(Date.todaysDate)))
  }

  def weekday = Action {
    Ok(views.html.weekday(String.valueOf(Date.todaysDate.weekday)))
  }

  def year = Action {
    Ok(views.html.year(String.valueOf(Date.todaysDate.year)))
  }

  def month = Action {
    Ok(views.html.month(String.valueOf(Date.todaysDate.month)))
  }

  def dayOfMonth = Action {
    Ok(views.html.dayOfMonth(String.valueOf(Date.todaysDate.dayOfMonth)))
  }

  def dayOfYear = Action {
    Ok(views.html.dayOfYear(String.valueOf(Date.todaysDate.dayOfYear)))
  }

  def isLeapYear = Action {
    Ok(views.html.isLeapYear(String.valueOf(Date.todaysDate.isLeapYear)))
  }

  def index = Action {
    Ok(views.html.index("Your new application is ready.: Index"))
  }

}
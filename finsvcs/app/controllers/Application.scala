package controllers

import org.quantintel.ql.time.daycounters.Actual360
import play.api._
import play.api.mvc._
import org.quantintel.ql.time.Date

import org.quantintel.lang.numeric._


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

  def yearFraction (method: Int, round: Int, dd1: Int, mm1: Int, yy1: Int, dd2: Int, mm2: Int, yy2: Int) = Action {

    val d1 = Date(dd1, mm1, yy1)
    val d2 = Date(dd2, mm2, yy2)

    method match {
      case 1 => {
        val yf :Double = Actual360().yearFraction(d1, d2)
        Ok(views.html.dayCount(String.valueOf(yf.rounded(round))))
      }
      case _ => Ok(views.html.dayCount(String.valueOf("no value")))
    }

  }

  def yearFractionRange (method: Int, round: Int, d1: String, d2: String) = Action {


    val dd1Parts : Array[String] = d1.split("/")
    val dd2Parts : Array[String] = d2.split("/")


    val dd1 = dd1Parts(0).replaceFirst("^0+(?!$)", "").toInt
    val mm1 = dd1Parts(1).replaceFirst("^0+(?!$)", "").toInt
    val yy1 = dd1Parts(2).toInt

    val dd2 = dd2Parts(0).replaceFirst("^0+(?!$)", "").toInt
    val mm2 = dd2Parts(1).replaceFirst("^0+(?!$)", "").toInt
    val yy2 = dd2Parts(2).toInt

    val date1 = Date(dd1, mm1, yy1)
    val date2 = Date(dd2, mm2, yy2)


    method match {

      case 1 => {
        val yf :Double = Actual360().yearFraction(date1, date2)
        Ok(views.html.dayCount(String.valueOf(yf.rounded(round))))
      }
      case _ => Ok(views.html.dayCount(String.valueOf("no value")))
    }

  }




  def index = Action {
    Ok(views.html.index("Your new application is ready.: Index"))
  }

}
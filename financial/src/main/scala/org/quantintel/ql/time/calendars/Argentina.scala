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

package org.quantintel.ql.time.calendars

import org.quantintel.ql.time.{Calendar, Western, Date}
import org.quantintel.ql.time.Month.Month
import org.quantintel.ql.time.Month._
import org.quantintel.ql.time.Weekday.Weekday
import org.quantintel.ql.time.Weekday._


object ArgentinaEnum extends Enumeration {

  type ArgentinaEnum = Value
  val MERVAL = Value(1)

  def valueOf(market: Int)  = market match {
    case 1 => MERVAL
    case _ => throw new Exception("Valid units = 1")
  }

}


/**
 * Argentinian Calendar
 *
 * Holidays for the Buenos Aires stock Exchange
 *
 * Reference: http://www.merval.sba.com.ar/
 *
 * Saturdays
 * Sundays
 * New Year's Day, JANUARY 1st
 * Holy Thursday
 * Good Friday
 * Labour Day, May 1st
 * May Revolution, May 25th
 * Death of General Manuel Belgrano, third Monday of June
 * Independence Day, July 9th
 * Death of General Jose de San Marten, third Monday of August
 *  Columbus Day, October 12th (moved to preceding Monday if
 *           on Tuesday or Wednesday and to following if on Thursday
 *           or Friday)
 * Immaculate Conception, December 8th
 * Christmas Eve, December 24th
 * New Year's Eve, December 31th
 *
 *
 * @author Paul Bernard
 */
object Argentina {

  import org.quantintel.ql.time.calendars.ArgentinaEnum._

  def apply() : Calendar = new MERVAL

  def apply(market: ArgentinaEnum) : Calendar = {
    market match {
      case MERVAL => new MERVAL
    }
  }

  private class MERVAL extends Western {

    override def name = "Buenos Aires stock exchange"

    override def isBusinessDay(date: Date) : Boolean = {

      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd = date.dayOfYear
      val m : Month = date.month
      val y : Int = date.year
      val em : Int = easterMonday(y)

      if (isWeekend(w)
        || (d ==1 && m == JANUARY)          // New Years
        || (dd == em - 4)                   // Holy Thursday
        || (dd == em -3)                    // Good Friday
        || (d == 1 && m == MAY)             // Labour Day
        || (d == 25 && m == MAY)            // May Revolution
        || (d >= 15 && d <= 21 && w == MONDAY && m == JUNE) // Death of General Manuel Belgrano
        || (d == 9 && m == JULY)      // Independence Day
        || (d >= 15 && d <= 21 && w ==  MONDAY && m == AUGUST) // Death of General Jos de San Marten
        || ((d == 10 || d == 11 || d == 12 || d == 15 || d == 16)
        && w  == MONDAY && m == OCTOBER)  // columbus day
        || (d == 8 && m == DECEMBER)        // Immaculate Conception
        || (d == 24 && m == DECEMBER)       // Christmas Eve
        || ((d == 31 || (d == 30 && w == FRIDAY)) && m == DECEMBER))
        false else true
    }
  }


}



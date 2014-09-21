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

import org.quantintel.ql.time.Month._
import org.quantintel.ql.time.Weekday._
import org.quantintel.ql.time.{Calendar, Date, Western}


object CanadaEnum extends Enumeration {

  type CanadaEnum = Value
  val SETTLEMENT = Value(1)
  val TSX = Value(2)

  def valueOf(market: Int) = market match {
    case 1 => SETTLEMENT
    case 2 => TSX
    case _ => throw new Exception("Valid units = 1 to 2")
  }

}
/**
 * Banking holidays:
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st (possibly moved to Monday)
 *  Family Day, third Monday of February (since 2008)
 *  Good Friday
 *  Easter Monday</li>
 *  Victoria Day, The Monday on or preceding 24 May
 *  Canada Day, July 1st (possibly moved to Monday)
 *  Provincial Holiday, first Monday of August
 *  Labour Day, first Monday of September
 *  Thanksgiving Day, second Monday of October
 *  Remembrance Day, November 11th (possibly moved to Monday)
 *  Christmas, December 25th (possibly moved to Monday or Tuesday)
 *  Boxing Day, December 26th (possibly moved to Monday or Tuesday)
 *
 *
 * Holidays for the Toronto stock exchange (TSX):
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st (possibly moved to Monday)
 *  Family Day, third Monday of February (since 2008)
 *  Good Friday
 *  Easter Monday
 *  Victoria Day, The Monday on or preceding 24 May
 *  Canada Day, July 1st (possibly moved to Monday)
 *  Provincial Holiday, first Monday of August
 *  Labour Day, first Monday of September
 *  Thanksgiving Day, second Monday of October
 *  Christmas, December 25th (possibly moved to Monday or Tuesday)
 *  Boxing Day, December 26th (possibly moved to Monday or  Tuesday)
 *
 *
 *  @author Paul Bernard
 *
 */
object Canada {

  import org.quantintel.ql.time.calendars.CanadaEnum._

  def apply() : Calendar =  new Settlement

  def apply(market: CanadaEnum) : Calendar = {
    market match {
      case SETTLEMENT => new Settlement
      case TSX => new TSX
    }
  }

  private class Settlement extends Western {


    override def name = "Canada"

    override def isBusinessDay(date: Date) : Boolean = {

      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd = date.dayOfYear
      val m : Month = date.month
      val y : Int = date.year
      val em : Int = easterMonday(y)


      if (isWeekend(w)
        || ((d == 1 || (d == 2 && w == MONDAY)) && m == JANUARY)                    // New Year's Day (possibly moved to Monday)
        || ((d >= 15 && d <= 21) && w == MONDAY && m == FEBRUARY && y >= 2008)      // Family Day (third MONDAY in February, since 2008)
        || (dd == em-3)                                                             // Good Friday
        || (dd == em)                                                               // Easter MONDAY
        || (d > 17 && d <= 24 && w == MONDAY && m == MAY)                           // The MONDAY on or preceding 24 MAY (Victoria Day)
        || ((d == 1 || ((d == 2 || d == 3) && w == MONDAY)) && m==JULY)             // JULY 1st, possibly moved to MONDAY (Canada Day)
        || (d <= 7 && w == MONDAY && m == AUGUST)                                   // first MONDAY of AUGUST (Provincial Holiday)
        || (d <= 7 && w == MONDAY && m == SEPTEMBER)                                // first MONDAY of September (Labor Day)
        || (d > 7 && d <= 14 && w == MONDAY && m == OCTOBER)                        // second MONDAY of October (Thanksgiving Day)
        || ((d == 11 || ((d == 12 || d == 13) && w == MONDAY)) && m == NOVEMBER)    // November 11th (possibly moved to MONDAY)
        || ((d == 25 || (d == 27 && (w == MONDAY || w == TUESDAY)))  && m == DECEMBER)   // Christmas (possibly moved to MONDAY or Tuesday)
        || ((d == 26 || (d == 28 && (w == MONDAY || w == TUESDAY))) && m == DECEMBER)     // Boxing Day (possibly moved to MONDAY or TUESDAY)
      ) false else true
    }

  }

  private class TSX extends Western {

    override def name = "TSX"

    override def isBusinessDay(date: Date) : Boolean = {

      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd = date.dayOfYear
      val m : Month = date.month
      val y : Int = date.year
      val em : Int = easterMonday(y)

      if (isWeekend(w)
        || ((d == 1 || (d == 2 && w == MONDAY)) && m == JANUARY)                        // New Year's Day (possibly moved to MONDAY)
        || ((d >= 15 && d <= 21) && w == MONDAY && m == FEBRUARY && y >= 2008)          // Family Day (third MONDAY in FEBRUARY, since 2008)
        || (dd == em-3)                                                                 // Good Friday
        || (dd == em)                                                                   // Easter MONDAY
        || (d > 17 && d <= 24 && w == MONDAY && m == MAY)                               // The MONDAY on or preceding 24 MAY (Victoria Day)
        || ((d == 1 || ((d == 2 || d == 3) && w == MONDAY)) && m==JULY)                 // JULY 1st, possibly moved to MONDAY (Canada Day)
        || (d <= 7 && w == MONDAY && m == AUGUST)                                       // first MONDAY of AUGUST (Provincial Holiday)
        || (d <= 7 && w == MONDAY && m == SEPTEMBER)                                    // first MONDAY of SEPTEMBER (Labor Day)
        || (d > 7 && d <= 14 && w == MONDAY && m == OCTOBER)                            // second MONDAY of OCTOBER (Thanksgiving Day)
        || ((d == 25 || (d == 27 && (w == MONDAY || w == TUESDAY))) && m == DECEMBER)   // Christmas (possibly moved to MONDAY or TUESDAY)
        || ((d == 26 || (d == 28 && (w == MONDAY || w == TUESDAY)))  && m == DECEMBER) )      // Boxing Day (possibly moved to MONDAY or TUESDAY)
        false else true

    }

  }



}




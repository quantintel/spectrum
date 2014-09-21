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


object AustraliaEnum extends Enumeration {

  type AustraliaEnum = Value
  val AUSTRALIA = Value(1)

  def valueOf(market: Int)  = market match {
    case 1 => AUSTRALIA
    case _ => throw new Exception("Valid units = 1")
  }
}



/**
 * Australian calendar
 * Holidays:
 * Saturdays
 * Sundays
 * New Year's Day, JANUARY 1st
 * Australia Day, JANUARY 26th (possibly moved to MONDAY)
 * Good Friday
 * Easter MONDAY
 * ANZAC Day. April 25th (possibly moved to MONDAY)
 * Queen's Birthday, second MONDAY in June
 * Bank Holiday, first MONDAY in August
 * Labour Day, first MONDAY in October
 * Christmas, December 25th (possibly moved to MONDAY or TUESDAY)
 * Boxing Day, December 26th (possibly moved to MONDAY or TUESDAY)
 *
 * @author Paul Bernard
 */
object Australia {

  import org.quantintel.ql.time.calendars.AustraliaEnum._

  def apply() : Calendar = new Settlement

  def apply(mkt: AustraliaEnum) : Calendar = mkt match {
    case AUSTRALIA => new Settlement
  }

  private class Settlement extends Western {

    override def name  = "Australia"

    override def isBusinessDay(date: Date) : Boolean = {

      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd = date.dayOfYear
      val m : Month = date.month
      val y : Int = date.year
      val em : Int = easterMonday(y)

      if(isWeekend(w)
        || (d == 1  && m == JANUARY)                                // New Year's Day (possibly moved to Monday)
        || ((d == 26 || ((d == 27 || d == 28) && w == MONDAY)) &&   m == JANUARY)  // Australia Day, JANUARY 26th (possibly moved to Monday)
        || (dd == em-3)                                             // Good Friday
        || (dd == em)                                               // Easter Monday
        || ((d == 25 || (d == 26 && w == MONDAY)) && m == APRIL)    // ANZAC Day, April 25th (possibly moved to Monday)
        || ((d > 7 && d <= 14) && w == MONDAY && m == JUNE)         // Queen's Birthday, second Monday in June
        || (d <= 7 && w == MONDAY && m == AUGUST)                   // Bank Holiday, first Monday in August
        || (d <= 7 && w == MONDAY&& m == OCTOBER)                  // Labour Day, first Monday in October
        || ((d == 25 || (d == 27 && (w == MONDAY || w == TUESDAY))) && m == DECEMBER) // Christmas, December 25th (possibly Monday or Tuesday)
        || ((d == 26 || (d == 28 && (w == MONDAY || w == TUESDAY))) && m == DECEMBER)) // Boxing Day, DECEMBER 26th (possibly MONDAY or TUESDAY)
        false else true

    }

  }




}

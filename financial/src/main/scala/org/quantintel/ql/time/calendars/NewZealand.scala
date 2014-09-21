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

object NewZealandEnum extends Enumeration {

  type NewZealandEnum = Value
  val NEWZEALAND = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => NEWZEALAND
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * New Zealand Holidays:
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st (possibly moved to Monday or Tuesday)
 *  Day after New Year's Day, JANUARY 2st (possibly moved to
 *           Monday or Tuesday)
 *  Anniversary Day, Monday nearest JANUARY 22nd
 *  Waitangi Day. February 6th
 *  Good Friday
 *  Easter Monday
 *  ANZAC Day. April 25th
 *  Queen's Birthday, first Monday in June
 *  Labour Day, fourth Monday in October
 *  Christmas, December 25th (possibly moved to Monday or Tuesday)
 *  Boxing Day, December 26th (possibly moved to Monday or Tuesday)
 *
 * Reference: http://www.nzx.com
 *
 * @author Paul Bernard
 */
object NewZealand {

  def apply: Calendar = new NewZealand

  import org.quantintel.ql.time.calendars.NewZealandEnum._

  def apply(market: NewZealandEnum): Calendar = {
    market match {
      case NEWZEALAND => new NewZealand
      case _ => throw new Exception("Valid units = 1")
    }
  }

  private class NewZealand extends Western {

    override def name = "New Zealand"

    override def isBusinessDay(date: Date): Boolean = {

      // standard dependencies
      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd: Int = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year
      val em: Int = easterMonday(y)

      if (isWeekend(w)
        || ((d == 1 || (d == 3 && (w == MONDAY || w == TUESDAY))) && m == JANUARY)  // New Year's Day (possibly moved to Monday or Tuesday)
        || ((d == 2 || (d == 4 && (w == MONDAY || w == TUESDAY))) && m == JANUARY) // Day after New Year's Day (possibly moved to Mon or TUESDAY)
        || ((d >= 19 && d <= 25) && w == MONDAY && m == JANUARY) // Anniversary Day, MONDAY nearest JANUARY 22nd
        || (d == 6 && m == FEBRUARY)  // Waitangi Day. February 6th
        || (dd == em-3) // Good Friday
        || (dd == em) // Easter MONDAY
        || (d == 25 && m == APRIL) // ANZAC Day. April 25th
        || (d <= 7 && w == MONDAY && m == JUNE) // Queen's Birthday, first MONDAY in June
        || ((d >= 22 && d <= 28) && w == MONDAY && m == OCTOBER) // Labour Day, fourth MONDAY in October
        || ((d == 25 || (d == 27 && (w == MONDAY || w == TUESDAY))) && m == DECEMBER) // Christmas, December 25th (possibly MONDAY or TUESDAY)
        || ((d == 26 || (d == 28 && (w == MONDAY || w == TUESDAY))) && m == DECEMBER))  // Boxing Day, DECEMBER 26th (possibly MONDAY or TUESDAY)
        false else true
    }
  }

}

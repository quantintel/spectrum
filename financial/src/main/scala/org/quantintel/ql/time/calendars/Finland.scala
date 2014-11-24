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
import org.quantintel.ql.time.{Impl, Calendar, Date, Western}

object FinlandEnum extends Enumeration {

  type FinlandEnum = Value
  val FINLAND = Value(1)

  def valueOf(market: Int) : FinlandEnum  = market match {
    case 1 => FINLAND
    case _ => throw new Exception("Valid units = 1")
  }

}

object Finland {

  def apply () : Calendar = {
    new Finland()
  }

  def apply(market: org.quantintel.ql.time.calendars.FinlandEnum.FinlandEnum) : Calendar = {
    new Finland(market)
  }

}

/**
 *
 * Finnish calendar
 * Holidays:
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Epiphany, JANUARY 6th
 *  Good Friday
 *  Easter Monday
 *  Ascension Thursday<
 *  Labour Day, May 1st
 *  Midsummer Eve (Friday between June 18-24)
 *  Independence Day, December 6th
 *  Christmas Eve, December 24th
 *  Christmas, December 25th
 *  Boxing Day, December 26th
 *
 * @author Paul Bernard
 */
class Finland extends Calendar {

  impl = new Finland

  import org.quantintel.ql.time.calendars.FinlandEnum._

  def this(market: org.quantintel.ql.time.calendars.FinlandEnum.FinlandEnum) {
    this
    market match {
      case FINLAND => impl = new Finland
      case _ => throw new Exception("Valid units = 1")
    }
  }

  private class Finland extends Western {

    override def name : String = "Finland"

    override def isBusinessDay(date: Date): Boolean = {

      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year
      val em: Int = easterMonday(y)

      if (isWeekend(w)
        || (d == 1 && m == JANUARY)        // New Year's Day
        || (d == 6 && m == JANUARY)       // Epiphany
        || (dd == em - 3)                   // Good Friday
        || (dd == em)                     // Easter Monday
        || (dd == em + 38)                  // Ascension Thursday
        || (d == 1 && m == MAY)           // Labour Day
        || (w == FRIDAY && (d >= 18 && d <= 24) && m == JUNE)   // Midsummer Eve (Friday between June 18-24)
        || (d == 6 && m == DECEMBER)       // Independence Day
        || (d == 24 && m == DECEMBER)      // Christmas Eve
        || (d == 25 && m == DECEMBER)     // Christmas
        || (d == 26 && m == DECEMBER))    // Boxing Day
        false else true
    }

}



}

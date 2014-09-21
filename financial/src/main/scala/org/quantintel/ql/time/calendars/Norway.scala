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

object NorwayEnum extends Enumeration {

  type NorwayEnum = Value
  val NORWAY = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => NORWAY
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * Norwegian calendar
 * Holidays:
 *  Saturdays
 *  Sundays
 *  Holy Thursday
 *  Good Friday
 *  Easter Monday
 *  Ascension
 *  Whit(Pentecost) Monday
 *  New Year's Day, JANUARY 1st
 *  May Day, May 1st
 *  National Independence Day, May 17st
 *  Christmas, December 25th
 *  Boxing Day, December 26th
 *
 * @author Paul Bernard
 */
object Norway {

  def apply(): Calendar = new Norway

  import org.quantintel.ql.time.calendars.NorwayEnum._

  def apply(market: NorwayEnum): Calendar = {
    market match {
      case NORWAY => new Norway
      case _ => throw new Exception("Valid units = 1")
    }
  }

  private class Norway extends Western {

    override def name = "Norway"

    override def isBusinessDay(date: Date): Boolean = {

      // standard dependencies
      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd: Int = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year
      val em: Int = easterMonday(y)

      if (isWeekend(w)
        || (dd == em - 4) // Holy Thursday
        || (dd == em - 3) // Good Friday
        || (dd == em) // Easter Monday
        || (dd == em + 38) // Ascension Thursday
        || (dd == em + 49) // Whit Monday
        || (d == 1 && m == JANUARY) // New Year's Day
        || (d == 1 && m == MAY) // May Day
        || (d == 17 && m == MAY) // National Independence Day
        || (d == 25 && m == DECEMBER) // Christmas
        || (d == 26 && m == DECEMBER)) // Boxing Day
        false
      else true
    }
  }

}

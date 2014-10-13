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
import org.quantintel.ql.time.{Impl, Date, Western, Calendar}

object IcelandEnum extends Enumeration {
  type IcelandEnum = Value
  val ICEX= Value(1)

  def valueOf(market: Int) = market match {
    case 1 => ICEX
    case _ => throw new Exception("Valid units = 1")
  }

}

object Iceland {

  def apply() : Calendar = {
    new Iceland()
  }

  def apply(market: org.quantintel.ql.time.calendars.IcelandEnum.IcelandEnum) : Calendar = {
    new Iceland(market)
  }

}

/**
 *
 * Icelandic calendars
 * Holidays for the Iceland stock exchange
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st (possibly moved to Monday)
 *  Holy Thursday
 *  Good Friday
 *  Easter Monday
 *  First day of Summer (third or fourth Thursday in April)
 *  Labour Day, May 1st
 *  Ascension Thursday
 *  Pentecost Monday
 *  Independence Day, June 17th
 *  Commerce Day, first Monday in August
 *  Christmas, December 25th
 *  Boxing Day, December 26th
 *
 * Reference: http://www.icex.is/is/calendar?languageID=1
 *
 * @author Paul Bernard
 */
class Iceland extends Calendar {

  impl = new Iceland

  import org.quantintel.ql.time.calendars.IcelandEnum._

  def this(market: org.quantintel.ql.time.calendars.IcelandEnum.IcelandEnum){
    this
    market match {
      case ICEX => impl = new Iceland
      case _ => throw new Exception("Valid units = 1")
    }
  }

  private class Iceland extends Western {

    override def name = "Iceland stock exchange"

    override def isBusinessDay(date: Date): Boolean = {

      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year
      val em: Int = easterMonday(y)

      if (isWeekend(w)
        || ((d == 1 || ((d == 2 || d == 3) && w == MONDAY))  && m == JANUARY)  // New Year's Day (possibly moved to Monday)
        || (dd == em - 4) // Holy Thursday
        || (dd == em - 3) // Good Friday
        || (dd == em) // Easter MONDAY
        || (d >= 19 && d <= 25 && w == THURSDAY && m == APRIL) // First day of Summer
        || (dd == em + 38) // Ascension THURSDAY
        || (dd == em + 49)  // Pentecost MONDAY
        || (d == 1 && m == MAY)  // Labour Day
        || (d == 17 && m == JUNE) // Independence Day
        || (d <= 7 && w == MONDAY && m == AUGUST) // Commerce Day
        || (d == 25 && m == DECEMBER) // Christmas
        || (d == 26 && m == DECEMBER)) // Boxing Day
      false else true

    }
  }

}
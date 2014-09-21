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
import org.quantintel.ql.time.{Orthodox, Date, Calendar}
import org.quantintel.ql.time.Weekday._

object UkraineEnum extends Enumeration {

  type UkraineEnum = Value
  val USE = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => USE
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * Holidays for the Ukrainian stock exchange
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Orthodox Christmas, JANUARY 7th
 *  International Women's Day, March 8th
 *  Easter Monday
 *  Holy Trinity Day, 50 days after Easter
 *  International Workers Solidarity Days, May 1st and 2n
 *  Victory Day, May 9th
 *  Constitution Day, June 28th
 *  Independence Day, August 24th
 *  Note: Holidays falling on a Saturday or Sunday are moved to the following Monday.
 *
 * Reference: http://www.ukrse.kiev.ua/eng/
 *
 * @author Paul Bernard
 */
object Ukraine  {

  def apply(): Calendar = new Use

  import org.quantintel.ql.time.calendars.UkraineEnum._

  def apply(market: UkraineEnum ): Calendar = {
    market match {
        case USE => new Use
      case _ => throw new Exception("Valid units = 1")
    }
  }

  private class Use extends Orthodox {

    override def name = "Ukrainian stock exchange"

    override def isBusinessDay(date: Date): Boolean = {

      // standard dependencies
      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd: Int = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year
      val em: Int = easterMonday(y)

      if (isWeekend(w)
        || ((d == 1 || ((d == 2 || d == 3) && w == MONDAY)) && m == JANUARY) // New Year's Day (possibly moved to Monday)
        || ((d == 7 || ((d == 8 || d == 9) && w == MONDAY)) && m == JANUARY) // Orthodox Christmas
        || ((d == 8 || ((d == 9 || d == 10) && w == MONDAY)) && m == MARCH) // Women's Day
        || (dd == em) // Orthodox Easter MONDAY
        || (dd == em + 49)  // Holy Trinity Day
        || ((d == 1 || d == 2 || (d == 3 && w == MONDAY)) && m == MAY) // Workers' Solidarity Days
        || ((d == 9 || ((d == 10 || d == 11) && w == MONDAY)) && m == MAY)  // Victory Day
        || (d == 28 && m == JUNE) // Constitution Day
        || (d == 24 && m == AUGUST)) // Independence Day
        false
      else true
    }
  }

}

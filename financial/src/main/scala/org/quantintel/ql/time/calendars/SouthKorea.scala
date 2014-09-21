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
import org.quantintel.ql.time.{Date, Calendar}

object SouthKoreaEnum extends Enumeration {

  type SouthKoreaEnum = Value
  val SETTLEMENT = Value(1)
  val KRX = Value(2)

  def valueOf(market: Int) = market match {
    case 1 => SETTLEMENT
    case 2 => KRX
    case _ => throw new Exception("Valid units = 1 or 2")
  }

}

/**
 *
 * South Korean public non business days:
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Independence Day, March 1st
 *  Arbour Day, April 5th (until 2005)
 *  Labour Day, May 1st
 *  Children's Day, May 5th
 *  Memorial Day, June 6th
 *  Constitution Day, July 17th (until 2007)
 *  Liberation Day, August 15th
 *  National Fondation Day, October 3th
 *  Christmas Day, December 25th
 *
 * Other holidays for which no rule is given (data available for 2004-2010 only:)
 *  Lunar New Year, the last day of the previous lunar year, JANUARY 1st, 2nd in lunar calendar
 *  Election Days
 *  National Assemblies
 *  Presidency
 *  Regional Election Days
 *  Buddha's birthday, April 8th in lunar calendar
 *  Harvest Moon Day, August 14th, 15th, 16th in lunar calendar
 *
 * Holidays for the Korea exchange
 *  Public holidays as listed above
 *  Year-end closing
 *
 * Reference:
 *  http://www.krx.co.kr
 *  http://www.dooriworld.com/daishin/holiday/holiday.html
 *
 * @author Paul Bernard
 */
object SouthKorea {

  def apply: Calendar = new Krx

  import org.quantintel.ql.time.calendars.SouthKoreaEnum ._

  def apply(market: SouthKoreaEnum ): Calendar = {
    market match {
      case SETTLEMENT => new Settlement
      case KRX => new Krx

      case _ => throw new Exception("Valid units = 1")
    }
  }

  private class Settlement extends Calendar {

    def name = "South-Korean settlement"

    def isWeekend(w: Weekday) : Boolean = w ==SATURDAY || w == SUNDAY


    override def isBusinessDay(date: Date): Boolean = {

      // standard dependencies
      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val m: Month = date.month
      val y: Int = date.year

      if (isWeekend(w)
        || (d == 1 && m == JANUARY)  // New Year's Day
        || (d == 1 && m == MARCH) // Independence Day
        || (d == 5 && m == APRIL && y <= 2005)  // Arbour Day
        || (d == 1 && m == MAY) // Labour Day
        || (d == 5 && m == MAY)  // Children's Day
        || (d == 6 && m == JUNE)  // Memorial Day
        || (d == 17 && m == JULY && y <= 2007)   // Constitution Day
        || (d == 15 && m == AUGUST) // Liberation Day
        || (d == 3 && m == OCTOBER) // National Foundation Day
        || (d == 25 && m == DECEMBER) // Christmas Day
        // Lunar New Year
        || ((d == 21 || d == 22 || d == 23) && m == JANUARY && y == 2004)
        || ((d == 8 || d == 9 || d == 10) && m == FEBRUARY && y == 2005)
        || ((d == 28 || d == 29 || d == 30) && m == JANUARY && y == 2006)
        || (d == 19 && m == FEBRUARY && y == 2007)
        || ((d == 6 || d == 7 || d == 8) && m == FEBRUARY && y == 2008)
        || ((d == 25 || d == 26 || d == 27) && m == JANUARY && y == 2009)
        || ((d == 13 || d == 14 || d == 15) && m == FEBRUARY && y == 2010)
        // Election Day 2004
        || (d == 15 && m == APRIL && y == 2004) // National Assembly
        || (d == 31 && m == MAY && y == 2006) // Regional election
        || (d == 19 && m == DECEMBER && y == 2007) // Presidency
        || (d == 9 && m == APRIL && y == 2008)
        // Buddha's birthday
        || (d == 26 && m == MAY && y == 2004)
        || (d == 15 && m == MAY && y == 2005)
        || (d == 5 && m == MAY && y == 2006)
        || (d == 24 && m == MAY && y == 2007)
        || (d == 12 && m == MAY && y == 2008)
        || (d == 2 && m == MAY && y == 2009)
        || (d == 21 && m == MAY && y == 2010)
        // Harvest Moon Day
        || ((d == 27 || d == 28 || d == 29) && m == SEPTEMBER && y == 2004)
        || ((d == 17 || d == 18 || d == 19) && m == SEPTEMBER && y == 2005)
        || ((d == 5 || d == 6 || d == 7) && m == OCTOBER && y == 2006)
        || ((d == 24 || d == 25 || d == 26) && m == SEPTEMBER && y == 2007)
        || ((d == 13 || d == 14 || d == 15) && m == SEPTEMBER && y == 2008)
        || ((d == 2 || d == 3 || d == 4) && m == OCTOBER && y == 2009)
        || ((d == 21 || d == 22 || d == 23) && m == SEPTEMBER && y == 2010))
        false else true
    }
  }

  private class Krx extends Settlement {

    override def name = "South-Korean exchange"

    override def isBusinessDay(date: Date): Boolean = {

      // standard dependencies
      val d: Int = date.dayOfMonth
      val m: Month = date.month
      val y: Int = date.year

      if (!super.isBusinessDay(date)) false
      else if (
             (d == 31 && m == DECEMBER && y == 2004)
          || (d == 30 && m == DECEMBER && y == 2005)
          || (d == 29 && m == DECEMBER && y == 2006)
          || (d == 31 && m == DECEMBER && y == 2007))
        false else true
      }
    }



}

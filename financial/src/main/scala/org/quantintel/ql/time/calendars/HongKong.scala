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

object HongKongEnum extends Enumeration {

  type HongKongEnum = Value
  val HKEX = Value(1)

  def valueOf(market: Int) : HongKongEnum = market match {
    case 1 => HKEX
    case _ => throw new Exception("Valid units = 1")
  }

}

object HongKong {

  def apply() : Calendar = {
    new HongKong()
  }

  def apply(market: org.quantintel.ql.time.calendars.HongKongEnum.HongKongEnum) : Calendar = {
    new HongKong(market)
  }

}

/**
 *
 * Hong Kong calendars Holidays:
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st (possibly moved to Monday)
 *  Ching Ming Festival, April 5th
 *  Good Friday
 *  Easter Monday
 *  Labor Day, May 1st
 *  SAR Establishment Day, July 1st (possibly moved to Monday)
 *  National Day, October 1st (possibly moved to Monday)
 *  Christmas, December 25th
 *  Boxing Day, December 26th (possibly moved to Monday)
 *
 * Other holidays for which no rule is given (data available for 2004-2007 only:)
 *  Lunar New Year
 *  Chinese New Year
 *  Buddha's birthday
 *  Tuen NG Festival
 *  Mid-autumn Festival
 *  Chung Yeung Festival

 *
 * Reference:
 *  http://www.hkex.com.hk
 *
 * @author Paul Bernard
 */
class HongKong extends Calendar {

  impl = new HKEx

  import org.quantintel.ql.time.calendars.HongKongEnum._

  def this(market: org.quantintel.ql.time.calendars.HongKongEnum.HongKongEnum) {
    this
    market match {
      case HKEX => impl = new HKEx
      case _ => throw new Exception("Valid units = 1")
    }
  }

  private class HKEx extends Western {

    override def name : String = "Hong Kong stock exchange"

    override def isBusinessDay(date: Date): Boolean = {

      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year
      val em: Int = easterMonday(y)

      if (isWeekend(w)
          || ((d == 1 || ((d == 2 || d == 3) && w == MONDAY)) && m == JANUARY)  // New Year's Day
          || (d == 5 && m == APRIL) // Ching Ming Festival
          || (dd == em - 3)  // Good Friday
          || (dd == em) // Easter MONDAY
          || (d == 1 && m == MAY) // Labor Day
          || ((d == 1 || ((d == 2 || d == 3) && w == MONDAY)) && m == JULY) // SAR Establishment Day
          || ((d == 1 || ((d == 2 || d == 3) && w == MONDAY)) && m == OCTOBER) // National Day
          || (d == 25 && m == DECEMBER) // Christmas Day
          || ((d == 26 || ((d == 27 || d == 28) && w == MONDAY)) && m == DECEMBER)) // Boxing Day
        false
      else if ((y == 2004) &&
            (((d == 22 || d == 23 || d == 24) && m == JANUARY)   // Lunar New Year
            || (d == 26 && m == MAY)    // Buddha's birthday
            || (d == 22 && m == JUNE)   // Tuen NG festival
            || (d == 29 && m == SEPTEMBER)  // Mid-autumn festival
            || (d == 29 && m == SEPTEMBER)))   // Chung Yeung
        false
      else if ((y == 2005) &&
          (((d == 9 || d == 10 || d == 11) && m == FEBRUARY)  // Lunar New Year
            || (d == 16 && m == MAY)   // Buddha's birthday
            || (d == 11 && m == JUNE)  // Tuen NG festival
            || (d == 19 && m == SEPTEMBER)  // Mid-autumn festival
            || (d == 11 && m == OCTOBER)))  // Chung Yeung festival
         false
      else if ((y == 2006) &&
          (((d >= 28 && d <= 31) && m == JANUARY)  // Lunar New Year
            || (d == 5 && m == MAY) // Buddha's birthday
            || (d == 31 && m == MAY)  // Tuen NG festival
            || (d == 7 && m == OCTOBER) // Mid-autumn festival
            || (d == 30 && m == OCTOBER)))  // Chung Yeung festival
          false
      else if ((y == 2007) &&
          (((d >= 17 && d <= 20) && m == FEBRUARY)  // Lunar New Year
            || (d == 24 && m == MAY)  // Buddha's birthday
            || (d == 19 && m == JUNE) // Tuen NG festival
            || (d == 26 && m == SEPTEMBER)  // Mid-autumn festival
            || (d == 19 && m == OCTOBER))) // Chung Yeung festival
          false
       else if ((y == 2008) &&
          (((d >= 7 && d <= 9) && m == FEBRUARY) // Lunar New Year
            || (d == 4 && m == APRIL) // Ching Ming Festival
            || (d == 12 && m == MAY)   // Buddha's birthday
            || (d == 9 && m == JUNE)  // Tuen NG festival
            || (d == 15 && m == SEPTEMBER)  // Mid-autumn festival
            || (d == 7 && m == OCTOBER))) // Chung Yeung festival
          false
        else true

      }

  }

}

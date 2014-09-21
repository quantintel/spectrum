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
import org.quantintel.ql.time.{Date, Western, Calendar}

object TaiwanEnum extends Enumeration {

  type TaiwanEnum = Value
  val TSEC = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => TSEC
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * Taiwanese calendars Holidays for the Taiwan stock exchange
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Peace Memorial Day, February 28
 *  Labor Day, May 1st
 *  Double Tenth National Day, October 10th
 *
 * Other holidays for which no rule is given (data available for 2002-2007 only:)
 *  Chinese Lunar New Year
 *  Tomb Sweeping Day
 *  Dragon Boat Festival
 *  Moon Festival
 *
 * Reference: http://www.tse.com.tw/en/trading/trading_days.php
 *
 * @author Paul Bernard
 */
object Taiwan {

  def apply: Calendar = new Tsec

  import org.quantintel.ql.time.calendars.TaiwanEnum._

  def apply(market: TaiwanEnum ): Calendar = {
    market match {
      case TSEC => new Tsec
      case _ => throw new Exception("Valid units = 1")
    }
  }

  private class Tsec extends Calendar {

    override def name = "Taiwan stock exchange"

    def isWeekend(w: Weekday) = w == SATURDAY || w == SUNDAY

    override def isBusinessDay(date: Date): Boolean = {

      // standard dependencies
      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd: Int = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year

      if (isWeekend(w)
        || (d == 1 && m == JANUARY) // New Year's Day
        || (d == 28 && m == FEBRUARY) // Peace Memorial Day
        || (d == 1 && m == MAY) // Labor Day
        || (d == 10 && m == OCTOBER)  // Double Tenth
        false
      else if ((y == 2002) &&
        // Dragon Boat Festival and Moon Festival fall on Saturday
        (// Chinese Lunar New Year
          (d >= 9 && d <= 17 && m == FEBRUARY)
            // Tomb Sweeping Day
            || (d == 5 && m == APRIL)))
        false
      else if ((y == 2003) &&
        // Tomb Sweeping Day falls on Saturday
        (// Chinese Lunar New Year
          ((d >= 31 && m == JANUARY) || (d <= 5 && m == FEBRUARY))
            // Dragon Boat Festival
            || (d == 4 && m == JUNE)
            // Moon Festival
            || (d == 11 && m == SEPTEMBER)))
        false
      else if ((y == 2004) &&
        // Tomb Sweeping Day falls on Sunday
        (// Chinese Lunar New Year
          (d >= 21 && d <= 26 && m == JANUARY)
            // Dragon Boat Festival
            || (d == 22 && m == JUNE)
            // Moon Festival
            || (d == 28 && m == SEPTEMBER)))
        false
      else if ((y == 2005) &&
        // Dragon Boat and Moon Festival fall on Saturday or Sunday
        (// Chinese Lunar New Year
          (d >= 6 && d <= 13 && m == FEBRUARY)
            // Tomb Sweeping Day
            || (d == 5 && m == APRIL)
            // make up for Labor Day, not seen in other years
            || (d == 2 && m == MAY)))
        false
      else if ((y == 2006) &&
        // Dragon Boat and Moon Festival fall on Saturday or Sunday
        (// Chinese Lunar New Year
          ((d >= 28 && m == JANUARY) || (d <= 5 && m == FEBRUARY))
            // Tomb Sweeping Day
            || (d == 5 && m == APRIL)
            // Dragon Boat Festival
            || (d == 31 && m == MAY)
            // Moon Festival
            || (d == 6 && m == OCTOBER)))
        false
      else if ((y == 2007) &&
        (// Chinese Lunar New Year
          (d >= 17 && d <= 25 && m == FEBRUARY)
            // Tomb Sweeping Day
            || (d == 5 && m == APRIL)
            // adjusted holidays
            || (d == 6 && m == APRIL)
            || (d == 18 && m == JUNE)
            // Dragon Boat Festival
            || (d == 19 && m == JUNE)
            // adjusted holiday
            || (d == 24 && m == SEPTEMBER)
            // Moon Festival
            || (d == 25 && m == SEPTEMBER)))
        false
      else if ((y == 2008) &&
        (// Chinese Lunar New Year
          (d >= 4 && d <= 11 && m == FEBRUARY)
            // Tomb Sweeping Day
            || (d == 4 && m == APRIL)))
        false else true

      }
  }


}

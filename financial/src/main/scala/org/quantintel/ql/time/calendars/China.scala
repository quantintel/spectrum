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
 * Scala Finance is based in part on:
 *        QuantLib. http://quantlib.org/
 *
 */

package org.quantintel.ql.time.calendars

import org.quantintel.ql.time.Month._
import org.quantintel.ql.time.Weekday._
import org.quantintel.ql.time.{Date, Western, Calendar}


object ChinaEnum extends Enumeration {

  type ChinaEnum = Value
  val SSE = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => SSE
    case _ => throw new Exception("Valid units = 1")
  }

}


/**
 * Chinese calendar Holidays:
 *   Saturdays
 *   SUNDAYs
 *   New Year's day, JANUARY 1st (possibly followed by one or two more holidays)
 *   Labour Day, first week in May
 *   National Day, one week from October 1st
 *
 * Other holidays for which no rule is given (data available for 2004-2008 only):
 *  Chinese New Year
 *  Ching Ming Festival
 *  Tuen Ng Festival
 *  Mid-Autumn Festival
 *
 * Reference: http://www.sse.com.cn/sseportal/en_us/ps/home.shtml
 *
 * @author Paul Bernard
 */
object China  {

  import org.quantintel.ql.time.calendars.ChinaEnum._

  def apply : Calendar = new SSE

  def apply(market: ChinaEnum) : Calendar = {
    market match {
      case SSE => new SSE
    }
  }

  private class SSE extends Western {

    override def name = "Shanghai stock exchange"

    override def isWeekend(w: Weekday) : Boolean = {
      w == SATURDAY || w == SUNDAY
    }

    override def isBusinessDay(date: Date): Boolean = {

      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val m: Month = date.month
      val y: Int = date.year

      if (isWeekend(w)

        || (d == 1 && m == JANUARY)
        || (d == 3 && m == JANUARY && y == 2005)
        || ((d == 2 || d == 3) && m == JANUARY && y == 2006)
        || (d <= 3 && m == JANUARY && y == 2007)
        || (d == 31 && m == DECEMBER && y == 2007)
        || (d == 1 && m == JANUARY && y == 2008)
        || (d == 1 && m == JANUARY && y == 200)
        || (d == 2 && m == JANUARY && y == 2009)                                                      // New Year's Day
        || (d >= 19 && d <= 28 && m == JANUARY && y == 2004) || (d >= 7 && d <= 15 && m == FEBRUARY && y == 2005)
        || (((d >= 26 && m == JANUARY) || (d <= 3 && m == FEBRUARY)) && y == 2006)
        || (d >= 17 && d <= 25 && m == FEBRUARY && y == 2007)
        || (d >= 6 && d <= 12 && m == FEBRUARY && y == 2008)
        || (d >= 26 && d <= 30 && m == JANUARY && y == 2009)                                          // Chinese New Year
        || (d == 4 && m == APRIL && y == 2008)
        || (d == 6 && m == APRIL && y == 2009)                                                        // Ching Ming Festival
        || (d >= 1 && d <= 7 && m == MAY && y <= 2007) || (d >= 1 && d <= 2 && m == MAY && y == 2008)
        || (d == 1 && m == MAY && y == 2009)                                                          // Labor Day
        || (d == 9 && m == JUNE && y == 2008) || (d >= 28 && d <= 29 && m == MAY && y == 2009)        // Tuen Ng Festival
        || (d == 15 && m == SEPTEMBER && y == 2008)                                                   // Mid-Autumn Festival
        || (d >= 1 && d <= 7 && m == OCTOBER && y <= 2007) || (d >= 29 && m == SEPTEMBER && y == 2008)    // National Day
        || (d <= 3 && m == OCTOBER && y == 2008) || (d >= 1 && d <= 8 && m == OCTOBER && y == 2009))
        false else true

    }
  }

}

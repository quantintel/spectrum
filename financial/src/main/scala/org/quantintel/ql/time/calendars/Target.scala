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
import org.quantintel.ql.time.{Western, Date, Calendar}
import org.quantintel.ql.time.Weekday._

object TargetEnum extends Enumeration {

  type TargetEnum = Value
  val TARGET = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => TARGET
    case _ => throw new Exception("Valid units = 1")
  }

}


/**
 *
 * TARGET calendar relative to the European Central Bank
 * This is a holiday calendar representing  the
 * Trans-european Automated Real-time Gross Express-settlement Transfer
 * system calendar.
 *
 * Saturdays
 * Sundays
 * New Year's Day, JANUARY 1st
 * Good Friday (since 2000)
 * Easter Monday (since 2000)
 * Labour Day, May 1st (since 2000)
 * Christmas, December 25th
 * Day of Goodwill, December 26th (since 2000)
 * December 31st (1998, 1999, and 2001)
 *
 * Reference: http://www.ecb.int
 *
 * @author Paul Bernard
 */
object Target  {

  def apply(): Calendar = new Target

  import org.quantintel.ql.time.calendars.TargetEnum._

  def apply(market: TargetEnum ): Calendar = {
    market match {
      case TARGET => new Target
      case _ => throw new Exception("Valid units = 1")
    }
  }

  private class Target extends Western  {

    override def name = "TARGET"

    override def isBusinessDay(date: Date): Boolean = {

      // standard dependencies
      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd: Int = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year
      val em: Int = easterMonday(y)

      if (isWeekend(w)
        || (d == 1 && m == JANUARY) // New Year's Day
        || (dd == em - 3 && y >= 2000)  // Good Friday
        || (dd == em && y >= 2000) // Easter Monday
        || (d == 1 && m == MAY && y >= 2000)  // Labour Day
        || (d == 25 && m == DECEMBER) // Christmas
        || (d == 26 && m == DECEMBER && y >= 2000)   // Day of Goodwill
        || (d == 31 && m == DECEMBER && (y == 1998 || y == 1999 || y == 2001)))  // December 31st, 1998, 1999, and 2001 only
        false else true
    }
  }

}

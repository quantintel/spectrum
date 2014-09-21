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

object SwitzerlandEnum extends Enumeration {

  type SwitzerlandEnum = Value
  val SWITZERLAND = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => SWITZERLAND
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * Swiss calendar Holidays:
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Berchtoldstag, JANUARY 2nd
 *  Good Friday
 *  Easter Monday
 *  Ascension Day
 *  Whit Monday
 *  Labour Day, May 1st
 *  National Day, August 1st
 *  Christmas, December 25th
 *  St. Stephen's Day, December 26th
 *
 * @author Paul Bernard
 */
object Switzerland  {

  def apply: Calendar = new Switzerland

  import org.quantintel.ql.time.calendars.SwitzerlandEnum._

  def apply(market: SwitzerlandEnum ): Calendar = {
    market match {
      case SWITZERLAND => new Switzerland
      case _ => throw new Exception("Valid units = 1")
    }
  }

  private class Switzerland extends Western {

    override def name = "Switzerland"

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
        || (d == 2 && m == JANUARY)  // Berchtoldstag
        || (dd == em - 3) // Good Friday
        || (dd == em) // Easter Monday
        || (dd == em + 38)   // Ascension Day
        || (dd == em + 49)  // Whit Monday
        || (d == 1 && m == MAY) // Labour Day
        || (d == 1 && m == AUGUST)  // National Day
        || (d == 25 && m == DECEMBER) // Christmas
        || (d == 26 && m == DECEMBER)) // St. Stephen's Day
        false
      else true
    }
  }

}

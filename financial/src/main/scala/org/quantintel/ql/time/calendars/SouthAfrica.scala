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

object SouthAfricaEnum extends Enumeration {

  type SouthAfricaEnum = Value
  val SOUTHAFRICA = Value(1)

  def valueOf(market: Int) : SouthAfricaEnum = market match {
    case 1 => SOUTHAFRICA
    case _ => throw new Exception("Valid units = 1")
  }

}

object SouthAfrica {

  def apply(): Calendar = {
    new SouthAfrica()
  }

  def apply(market: org.quantintel.ql.time.calendars.SouthAfricaEnum.SouthAfricaEnum) : Calendar = {
    new SouthAfrica(market)
  }


}

/**
 *
 * South African non business days
 *  Saturdays
 *  Sundays
 *  New Year's day, January 1
 *  Human Rights Day, March 21
 *  Good Friday, Friday before Easter Monday
 *  Family Day/Easter Monday
 *  Freedom Day, April 27
 *  Worker's Day, May 1
 *  Youth Day, June 16
 *  National Women's Day, August 9
 *  Heritage Day/National Braai Day, September 24
 *  Day of Reconciliation, December 16
 *  Christmas Day, December 25
 *  Day of Goodwill, December 26
 *
 * Note: Any holiday falling on a Sunday moves to the following Monday
 *
 * Reference: http://en.wikipedia.org/wiki/Public_holidays_in_South_Africa
 *
 * @author Paul Bernard
 */
class SouthAfrica extends Calendar  {

  impl = new SouthAfrica

  import org.quantintel.ql.time.calendars.SouthAfricaEnum._

  def this(market: org.quantintel.ql.time.calendars.SouthAfricaEnum.SouthAfricaEnum) {
    this
    market match {
      case SOUTHAFRICA => impl = new SouthAfrica
      case _ => throw new Exception("Valid units = 1")
    }
  }

  private class SouthAfrica extends Western {

    addHoliday(Date(12, 31, 1999))
    addHoliday(Date(1, 2, 2000))
    addHoliday(Date(1, 3,2000))
    addHoliday(Date(5,2, 2008))
    addHoliday(Date(4,22,2009))

    override def name : String = "South Africa"

    override def isBusinessDay(date: Date): Boolean = {

      // standard dependencies
      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd: Int = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year
      val em: Int = easterMonday(y)

      if (isWeekend(w)
        || ((d == 1 || (d == 2 && w == MONDAY)) && m == JANUARY)  // New Year's Day, January 1
        || ((d == 21 || (d == 22 && w == MONDAY)) && m == MARCH)  // Human Rights Day, March 21
        || (dd == em - 3)  // Good Friday
        || (dd == em) // Easter Monday/Family Day
        || ((d == 27 || (d == 28 && w == MONDAY)) && m == APRIL)   // Freedom Day, April 27
        || ((d == 1 || (d == 2 && w == MONDAY)) && m == MAY)   // Worker's Day, May 1
        || ((d == 16 || (d == 17 && w == MONDAY)) && m == JUNE) // Youth Day, June 16
        || ((d == 9 || (d == 10 && w == MONDAY)) && m == AUGUST)   // National Women's Day, August 9
        || ((d == 24 || (d == 25 && w == MONDAY)) && m == SEPTEMBER)   // Heritage Day, September 24
        || ((d == 16 || (d == 17 && w == MONDAY)) && m == DECEMBER) // Day of Reconciliation, December 16
        || ((d == 25 || (d == 26 && w == MONDAY)) && m == DECEMBER) // Christmas Day, December 25
        || ((d == 26 || (d == 27 && w == MONDAY)) && m == DECEMBER))  // Day of Goodwill, December 26
        false
      else true
    }
  }

}

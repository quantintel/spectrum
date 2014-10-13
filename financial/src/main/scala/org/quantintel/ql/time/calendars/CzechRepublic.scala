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


object CzechRepublicEnum extends Enumeration {

  type CzechRepublicEnum = Value
  val PSE = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => PSE
    case _ => throw new Exception("Valid units = 1")
  }

}

object CzechRepublic {

  def apply() : Calendar = {
    new CzechRepublic()
  }

  def apply(market: org.quantintel.ql.time.calendars.CzechRepublicEnum.CzechRepublicEnum) : Calendar = {
    new CzechRepublic(market)
  }

}

/**
 *
 * Czech calendars Holidays for the Prague stock exchange
 *  Saturdays
 *  Sundays<
 *  New Year's Day, JANUARY 1st
 *  Easter Monday
 *  Labour Day, May 1st
 *  Liberation Day, May 8th
 *  SS. Cyril and Methodius, July 5th
 *  Jan Hus Day, July 6th
 *  Czech Statehood Day, September 28th
 *  Independence Day, October 28th
 *  Struggle for Freedom and Democracy Day, November 17th
 *  Christmas Eve, December 24th
 *  Christmas, December 25th
 *  St. Stephen, December 26th
 *
 * Reference: http://www.pse.cz/
 *
 * @author Paul Bernard
 */
class CzechRepublic extends Calendar  {

  import org.quantintel.ql.time.calendars.CzechRepublicEnum._

  impl = new PSE

  def this(market: org.quantintel.ql.time.calendars.CzechRepublicEnum.CzechRepublicEnum)  {
    this
    market match {
      case PSE => impl = new PSE
    }
  }



  private class PSE extends Western {


    override def name = "Prague stock exchange of Czech Republic"

    override def isBusinessDay(date: Date): Boolean = {

      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd = date.dayOfYear
      val m : Month = date.month
      val y : Int = date.year
      val em : Int = easterMonday(y)

      if (isWeekend(w)
        || (d == 1 && m == JANUARY)                   // New Year's Day
        || (dd == em)                                 // Easter Monday
        || (d == 1 && m == MAY)                       // Labour Day
        || (d == 8 && m == MAY)                       // Liberation Day
        || (d == 5 && m == JULY)                      // SS. Cyril and Methodius
        || (d == 6 && m == JULY)                      // Jan Hus Day
        || (d == 28 && m == SEPTEMBER)                // Czech Statehood Day
        || (d == 28 && m == OCTOBER)                  // Independence Day
        || (d == 17 && m == NOVEMBER)                 // Struggle for Freedom and Democracy Day
        || (d == 24 && m == DECEMBER)                 // Christmas Eve
        || (d == 25 && m == DECEMBER)                 // Christmas
        || (d == 26 && m == DECEMBER)                 // St. Stephen
        || (d == 2 && m == JANUARY && y == 2004)
        || (d == 31 && m == DECEMBER && y == 2004))   // unidentified closing days for stock exchange
        false else true

    }
  }


}

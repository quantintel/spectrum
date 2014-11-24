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

object SlovakiaEnum extends Enumeration {

  type SlovakiaEnum = Value
  val BSSE = Value(1)

  def valueOf(market: Int) : SlovakiaEnum = market match {
    case 1 => BSSE
    case _ => throw new Exception("Valid units = 1")
  }

}

object Slovakia {

  def apply(): Calendar = {
    new Slovakia()
  }

  def apply(market: org.quantintel.ql.time.calendars.SlovakiaEnum.SlovakiaEnum): Calendar = {
    new Slovakia(market)
  }

}

/**
 *
 * Slovak calendars
 * Holidays for the Bratislava stock exchange
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Epiphany, JANUARY 6th
 *  Good Friday
 *  Easter Monday
 *  May Day, May 1st
 *  Liberation of the Republic, May 8th
 *  SS. Cyril and Methodius, July 5th
 *  Slovak National Uprising, August 29th
 *  Constitution of the Slovak Republic, September 1st
 *  Our Lady of the Seven Sorrows, September 15th
 *  All Saints Day, November 1st
 *  Freedom and Democracy of the Slovak Republic, November 17th
 *  Christmas Eve, December 24th
 *  Christmas, December 25th
 *  St. Stephen, December 26th
 *
 * Reference: http://www.bsse.sk/
 *
 * @author Paul Bernard
 */
class Slovakia extends Calendar {

  impl = new Bsse

  import org.quantintel.ql.time.calendars.SlovakiaEnum._

  def this(market: org.quantintel.ql.time.calendars.SlovakiaEnum.SlovakiaEnum) {
    this
    market match {
      case BSSE => impl = new Bsse
      case _ => throw new Exception("Valid units = 1")
    }
  }

  private class Bsse extends Western {

    override def name : String = "Bratislava stock exchange"

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
        || (d == 6 && m == JANUARY) // Epiphany
        || (dd == em - 3) // Good Friday
        || (dd == em) // Easter Monday
        || (d == 1 && m == MAY) // May Day
        || (d == 8 && m == MAY) // Liberation of the Republic
        || (d == 5 && m == JULY)  // SS. Cyril and Methodius
        || (d == 29 && m == AUGUST) // Slovak National Uprising
        || (d == 1 && m == SEPTEMBER) // Constitution of the Slovak Republic
        || (d == 15 && m == SEPTEMBER)  // Our Lady of the Seven Sorrows
        || (d == 1 && m == NOVEMBER)  // All Saints Day
        || (d == 17 && m == NOVEMBER)  // Freedom and Democracy of the Slovak Republic
        || (d == 24 && m == DECEMBER)  // Christmas Eve
        || (d == 25 && m == DECEMBER) // Christmas
        || (d == 26 && m == DECEMBER)  // St. Stephen
        || (d >= 24 && d <= 31 && m == DECEMBER && y == 2004)  // unidentified closing days for stock exchange
        || (d >= 24 && d <= 31 && m == DECEMBER && y == 2005))
        false
      else true
    }
  }

}

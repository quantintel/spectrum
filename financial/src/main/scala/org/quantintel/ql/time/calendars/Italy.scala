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

object ItalyEnum extends Enumeration {

  type ItalyEnum = Value
  val SETTLEMENT = Value(1)
  val EXCHANGE = Value(2)

  def valueOf(market: Int) = market match {
    case 1 => SETTLEMENT
    case 2 => EXCHANGE
    case _ => throw new Exception("Valid units = 1")
  }

}

object Italy {

  def apply(): Calendar = {
    new Italy()
  }

  def apply(market: org.quantintel.ql.time.calendars.ItalyEnum.ItalyEnum): Calendar = {
    new Italy(market)
  }

}

/**
 *
 * Italian calendars Public holidays:
 *  Saturdays
 *  Sundays
 *  New Year's Day, January 1st
 *  Epiphany, January 6th
 *  Easter Monday
 *  Liberation Day, April 25th
 *  Labour Day, May 1st
 *  Republic Day, June 2nd (since 2000)
 *  Assumption, August 15th
 *  All Saint's Day, November 1st
 *  Immaculate Conception Day, December 8th
 *  Christmas Day, December 25th
 *  St. Stephen's Day, December 26th

 *
 * Holidays for the stock exchange
 *  Saturdays
 *  Sundays
 *  New Year's Day, January 1st
 *  Good Friday
 *  Easter Monday
 *  Labour Day, May1st
 *  Assumption, August 15th
 *  Christmas' Eve, December 24th
 *  Christmas, December 25th
 *  St. Stephen, December 26th
 *  New Year's Eve, December 31st
 *
 * Reference: http://www.borsaitalia.it
 *
 * @author Paul Bernard
 */
class Italy extends Calendar  {

  impl = new Settlement

  import org.quantintel.ql.time.calendars.ItalyEnum._

  def this(market: org.quantintel.ql.time.calendars.ItalyEnum.ItalyEnum) {
    this
    market match {
      case SETTLEMENT => impl = new Settlement
      case EXCHANGE => impl = new Exchange
      case _ => throw new Exception("Valid units = 1 to 2")
    }
  }

  private class Settlement extends Western {

    override def name = "Italian Settlement"

    override def isBusinessDay(date: Date): Boolean = {

      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year
      val em: Int = easterMonday(y)

      if (isWeekend(w)    // weekends
        || (d == 1 && m == JANUARY)   // New Year's Day
        || (d == 6 && m == JANUARY) // Epiphany
        || (dd == em) // Easter Monday
        || (d == 25 && m == APRIL)  // Liberation Day
        || (d == 1 && m == MAY)  // Labour Day
        || (d == 2 && m == JUNE && y >= 2000) // Republic Day
        || (d == 15 && m == AUGUST) // Assumption
        || (d == 1 && m == NOVEMBER)  // All Saints' Day
        || (d == 8 && m == DECEMBER)  // Immaculate Conception
        || (d == 25 && m == DECEMBER)  // Christmas
        || (d == 26 && m == DECEMBER) // St. Stephen
        || (d == 31 && m == DECEMBER && y == 1999)) // DECEMBER 31st, 1999 only
        false else true
    }
  }

  private class Exchange extends Western {

    override def name = "Milan stock exchange"

    override def isBusinessDay(date: Date): Boolean = {

      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year
      val em: Int = easterMonday(y)

      if (isWeekend(w)
        || (d == 1 && m == JANUARY) // New Year's Day
        || (dd == em - 3) // Good Friday
        || (dd == em)  // Easter Monday
        || (d == 1 && m == MAY)  // Labour Day
        || (d == 15 && m == AUGUST) // Assumption
        || (d == 24 && m == DECEMBER) // Christmas' Eve
        || (d == 25 && m == DECEMBER) // Christmas
        || (d == 26 && m == DECEMBER) // St. Stephen
        || (d == 31 && m == DECEMBER)) // New Year's Eve
        false else true

    }
  }

}

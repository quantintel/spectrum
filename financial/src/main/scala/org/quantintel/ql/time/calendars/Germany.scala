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

object GermanyEnum extends Enumeration {
  type GermanyEnum = Value
  val SETTLEMENT = Value(1)
  val FRANKFURTSTOCKEXCHANGE = Value(2)
  val XETRA = Value(3)
  val EUREX = Value(4)


  def valueOf(market: Int) = market match {
    case 1 => SETTLEMENT
    case 2 => FRANKFURTSTOCKEXCHANGE
    case 3 => XETRA
    case 4 => EUREX
    case _ => throw new Exception("Valid units = 1 to 4")
  }

}

object Germany {

  def apply() : Calendar = {
    new Germany()
  }

  def apply(market: org.quantintel.ql.time.calendars.GermanyEnum.GermanyEnum) : Calendar = {
    new Germany(market)
  }


}

/**
 *
 * German calendars
 *
 * Public holidays:
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Good Friday
 *  Easter Monday
 *  Ascension Thursday
 *  Whit Monday
 *  Corpus Christi
 *  Labour Day, May 1st
 *  National Day, October 3rd
 *  Christmas Eve, December 24th
 *  Christmas, December 25th
 *  Boxing Day, December 26th
 *  New Year's Eve, December 31st
 *
 * Holidays for the Frankfurt Stock exchange
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Good Friday
 *  Easter Monday
 *  Labour Day, May 1st
 *  Christmas' Eve, December 24th
 *  Christmas, December 25th
 *  Christmas Holiday, December 26th
 *  New Year's Eve, December 31st
 *
 * Holidays for the Xetra exchange
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Good Friday
 *  Easter Monday
 *  Labour Day, May 1st
 *  Christmas' Eve, December 24th
 *  Christmas, December 25th
 *  Christmas Holiday, December 26th
 *  New Year's Eve, December 31st
 *
 * Holidays for the Eurex exchange
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Good Friday
 *  Easter Monday
 *  Labour Day, May 1st
 *  Christmas' Eve, December 24th
 *  Christmas, December 25th
 *  Christmas Holiday, December 26th
 *  New Year's Eve, December 31st
 *
 * References:
 *  http://deutsche-boerse.com/
 *  http://deutsche-boerse.com/
 *  http://www.eurexchange.com/index.html
 *
 * @author Paul Bernard
 *
 */
class Germany extends Calendar {

  impl =  new Settlement

  import org.quantintel.ql.time.calendars.GermanyEnum._

  def this(market: org.quantintel.ql.time.calendars.GermanyEnum.GermanyEnum) {
    this
    market match {
      case SETTLEMENT => impl = new Settlement
      case FRANKFURTSTOCKEXCHANGE => impl = new FrankfurtStockExchange
      case XETRA => impl = new Xetra
      case EUREX => impl = new Eurex
      case _ => throw new Exception("Valid units = 1")
    }
  }

  private class Settlement extends Western {

    override def name = "German Settlement"

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
        || (dd == em) // Easter Monday
        || (dd == em + 38) // Ascension Thursday
        || (dd == em + 49) // Whit Monday
        || (dd == em + 59) // Corpus Christi
        || (d == 1 && m == MAY) // Labour Day
        || (d == 3 && m == OCTOBER) // National Day
        || (d == 24 && m == DECEMBER) // Christmas Eve
        || (d == 25 && m == DECEMBER) // Christmas
        || (d == 26 && m == DECEMBER) // Boxing Day
        || (d == 31 && m == DECEMBER)) // New Year's Eve
        false
      else true
    }
  }

  private class FrankfurtStockExchange extends Western {

    override def name = "Frankfurt stock exchange"

    override def isBusinessDay(date: Date): Boolean = {

      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year
      val em: Int = easterMonday(y)

      if (isWeekend(w)
        || (d == 1 && m == JANUARY)             // New Year's Day
        || (dd == em - 3)                       // Good Friday
        || (dd == em)                           // Easter Monday
        || (d == 1 && m == MAY)                 // Labour Day
        || (d == 24 && m == DECEMBER)           // Christmas' Eve
        || (d == 25 && m == DECEMBER)           // Christmas
        || (d == 26 && m == DECEMBER)           // Christmas Day
        || (d == 31 && m == DECEMBER))          // New Year's Eve
        false
      else true
    }
  }

  private class Xetra extends Western {

    override def name = "Xetra"

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
        || (dd == em) // Easter Monday
        || (d == 1 && m == MAY) // Labour Day
        || (d == 24 && m == DECEMBER) // Christmas' Eve
        || (d == 25 && m == DECEMBER) // Christmas
        || (d == 26 && m == DECEMBER) // Christmas Day
        || (d == 31 && m == DECEMBER)) // New Year's Eve
        false
      else true
    }
  }


  private class Eurex extends Western {

    override def name = "Eurex"

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
        || (dd == em) // Easter Monday
        || (d == 1 && m == MAY) // Labour Day
        || (d == 24 && m == DECEMBER) // Christmas' Eve
        || (d == 25 && m == DECEMBER) // Christmas
        || (d == 26 && m == DECEMBER) // Christmas Day
        || (d == 31 && m == DECEMBER)) // New Year's Eve
        false
      else true

    }
  }

}

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

import org.teleapp.ql.time.Month._
import org.teleapp.ql.time.Weekday._
import org.teleapp.ql.time.{Calendar, Date, Western}


object BrazilEnum extends Enumeration {

  type BrazilEnum = Value
  val SETTLEMENT = Value(1)
  val BOVESPA = Value(2)

  def valueOf(market: Int) = market match {
    case 1 => SETTLEMENT
    case 2 => BOVESPA
    case _ => throw new Exception("Valid units = 1 to 2")
  }

}
/**
 * Banking holidays:
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Tiradentes's Day, April 21th
 *  Labour Day, May 1st
 *  Independence Day, September 21th
 *  Nossa Sra. Aparecida Day, October 12th
 *  All Souls Day, November 2nd
 *  Republic Day, November 15th
 *  Black Awareness Day, November 20th
 *  Christmas, December 25th
 *  Passion of Christ
 *  Carnival
 *  Corpus Christi
 *
 * Holidays for the Bovespa stock exchange:
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Sao Paulo City Day, JANUARY 25th
 *  Tiradentes's Day, April 21th
 *  Labour Day, May 1st
 *  Revolution Day, July 9th
 *  Independence Day, September 21th
 *  Nossa Sra. Aparecida Day, October 12th
 *  All Souls Day, November 2nd
 *  Republic Day, November 15th
 *  Black Awareness Day, November 20th (since 2007)
 *  Christmas, December 25th
 *  Passion of Christ
 *  Carnival
 *  Corpus Christi
 *  the last business day of the year
 *
 * @author Paul Bernard
 */
object Brazil {

  import org.teleapp.ql.time.calendars.BrazilEnum._

  def apply : Calendar = new Settlement

  def apply(market: BrazilEnum) : Calendar = {
    market match {
      case SETTLEMENT => new Settlement
      case BOVESPA => new BOVESPA
    }
  }

  private class Settlement extends Western {

    override def name = "Brazil"

    override def isBusinessDay(date: Date) : Boolean = {

      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd = date.dayOfYear
      val m : Month = date.month
      val y : Int = date.year
      val em : Int = easterMonday(y)

      if (isWeekend(w)
        || (d == 1 && m == JANUARY)      // New Year's Day
        || (d == 21 && m == APRIL)       // Tiradentes Day
        || (d == 1 && m == MAY)          // Labor Day
        || (d == 7 && m == SEPTEMBER)    // Independence Day
        || (d == 12 && m == OCTOBER)     // Nossa Sra. Aparecida Day
        || (d == 2 && m == NOVEMBER)     // All Souls Day
        || (d == 15 && m == NOVEMBER)    // Republic Day
        || (d == 25 && m == DECEMBER)    // Christmas
        || (dd == em - 3)                      // Passion of Christ
        || (dd == em - 49 || dd == em - 48)    // Carnival
        || (dd == em + 59))                     // Corpus Christi
        false else true
    }

  }

  private class BOVESPA extends Western {

    override def name = "BOVESPA"

    override def isBusinessDay(date: Date) : Boolean = {

      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd = date.dayOfYear
      val m : Month = date.month
      val y : Int = date.year
      val em : Int = easterMonday(y)

      if (isWeekend(w)

        || (d == 1 && m == JANUARY)                 // New Year's Day
        || (d == 25 && m == JANUARY)                // Sao Paulo City Day
        || (d == 21 && m == APRIL)                  // Tiradentes Day
        || (d == 1 && m == MAY)                     // Labor Day
        || (d == 9 && m == JULY)                    // Revolution Day
        || (d == 20 && m == NOVEMBER && y >= 2007)  // Black Awareness Day
        || (d == 24 && m == DECEMBER)               // Christmas Eve
        || (d == 25 && m == DECEMBER)               // Christmas
        || (dd == em - 3)                           // Passion of Christ / Good Friday
        || (dd == em - 49 || dd == em - 48)         // Carnival
        || (dd == em + 59)                          // Corpus Christi
        || (m == DECEMBER && (d == 31 || (d >= 29 && w == FRIDAY))))  // last business day of the year
        false else true

    }
  }



}




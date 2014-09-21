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

object SaudiArabiaEnum extends Enumeration {

  type SaudiArabiaEnum = Value
  val TADAWUL = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => TADAWUL
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * Saudi Arabia calendar
 *  Thursdays
 *  Fridays
 *  National Day of Saudi Arabia, September 23rd
 *
 *  Note: Other holidays for which no rule is given (data available for 2004-2005 only:)
 *
 * @author Paul Bernard
 */
object SaudiArabia {

  def apply(): Calendar = new Tadawul

  import org.quantintel.ql.time.calendars.SaudiArabiaEnum._

  def apply(market: SaudiArabiaEnum): Calendar = {
    market match {
      case TADAWUL => new Tadawul
      case _ => throw new Exception("Valid units = 1")
    }
  }

  private class Tadawul extends Western {

    override def name = "Tadawul"

    override def isWeekend(w: Weekday) : Boolean = w == THURSDAY || w == FRIDAY

    override def isBusinessDay(date: Date): Boolean = {

      // standard dependencies
      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd: Int = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year
      val em: Int = easterMonday(y)

      if (isWeekend(w)
        || (d == 23 && m == SEPTEMBER)      // National Day
        || (d >= 1 && d <= 6 && m == FEBRUARY && y == 2004) // Eid Al-Adha
        || (d >= 21 && d <= 25 && m == JANUARY && y == 2005)
        || (d >= 25 && d <= 29 && m == NOVEMBER && y == 2004) // Eid Al-Fitr
        || (d >= 14 && d <= 18 && m == NOVEMBER && y == 2005))
        false else true
    }
  }



}

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
import org.quantintel.ql.time.{Impl, Western, Date, Calendar}

object HungaryEnum extends Enumeration {

  type HungaryEnum = Value
  val HUNGARY = Value(1)

  def valueOf(market: Int) : HungaryEnum  = market match {
    case 1 => HUNGARY
    case _ => throw new Exception("Valid units = 1")
  }

}

object Hungary {

  def apply() : Calendar = {
    new Hungary()
  }

  def apply(market: org.quantintel.ql.time.calendars.HungaryEnum.HungaryEnum): Calendar = {
    new Hungary(market)
  }

}

/**
 *
 * Hungarian calendar
 *
 * Holidays:
 *  Saturdays
 *  Sundays
 *  Easter Monday
 *  Whit(Pentecost) Monday
 *  New Year's Day, JANUARY 1st
 *  National Day, March 15th
 *  Labour Day, May 1st
 *  Constitution Day, August 20th
 *  Republic Day, October 23rd
 *  All Saints Day, November 1st
 *  Christmas, December 25th
 *  2nd Day of Christmas, December 26th
 *
 * @author Paul Bernard
 */
class Hungary extends Calendar {

  impl = new Hungary

  import org.quantintel.ql.time.calendars.HungaryEnum._

  def this(market: org.quantintel.ql.time.calendars.HungaryEnum.HungaryEnum)  {
    this
    market match {
      case HUNGARY => impl = new Hungary
      case _ => throw new Exception("Valid units = 1")
    }
  }

  private class Hungary extends Western {

    override def name : String = "Hungary"

    override def isBusinessDay(date: Date): Boolean = {

      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year
      val em: Int = easterMonday(y)

      if (isWeekend(w)
        || (dd == em)     // Easter Monday
        || (dd == em + 49)  // Whit Monday
        || (d == 1  && m == JANUARY)   // New Year's Day
        || (d == 15  && m == MARCH) // National Day
        || (d == 1  && m == MAY)  // Labour Day
        || (d == 20  && m == AUGUST)   // Constitution Day
        || (d == 23  && m == OCTOBER) // Republic DaY
        || (d == 1  && m == NOVEMBER)  // All Saints Day
        || (d == 25 && m == DECEMBER)  // Christmas
        || (d == 26 && m == DECEMBER))  // 2nd Day of Christmas
        false else true

    }
  }

}

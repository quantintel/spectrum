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

object TurkeyEnum extends Enumeration {

  type TurkeyEnum = Value
  val TURKEY = Value(1)

  def valueOf(market: Int) : TurkeyEnum = market match {
    case 1 => TURKEY
    case _ => throw new Exception("Valid units = 1")
  }

}

object Turkey {

  def apply(): Calendar = {
    new Turkey()
  }

  def apply(market: org.quantintel.ql.time.calendars.TurkeyEnum.TurkeyEnum): Calendar = {
    new Turkey(market)
  }

}

/**
 * Holidays for the National Stock Exchange
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  National Holidays (April 23rd, May 19th, August 30th, October 29th
 *  Local Holidays (Kurban, Ramadan; 2004 to 2009 only)
 *
 * @author Paul Bernard
 */
class Turkey extends Calendar {

  impl = new Turkey

  import org.quantintel.ql.time.calendars.TurkeyEnum._

  def this(market: org.quantintel.ql.time.calendars.TurkeyEnum.TurkeyEnum ){
    this
    market match {
      case TURKEY => impl = new Turkey
      case _ => throw new Exception("Valid units = 1")
    }
  }

  private class Turkey extends Impl {

    override def name  : String = "Turkey"

    def isWeekend(w: Weekday) : Boolean =  w == SATURDAY || w == SUNDAY

    override def isBusinessDay(date: Date): Boolean = {

      // standard dependencies
      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd: Int = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year

      if (isWeekend(w)
        || (d == 1 && m == JANUARY) // New Year's Day
        || (d == 23 && m == APRIL)   // 23 nisan / National Holiday
        || (d == 19 && m == MAY) // 19 may National Holiday
        || (d == 30 && m == AUGUST) // 30 aug/ National Holiday
        || (d == 29 && m == OCTOBER)) // /29 ekim National Holiday
        false
      // Local Holidays
      else if ((y == 2004) &&
         ((m == FEBRUARY && d <= 4)  // kurban
          || (m == NOVEMBER && d >= 14 && d <= 16)))  // ramazan
          false
     else if ((y == 2005) &&
        ((m == JANUARY && d >= 19 && d <= 21) // kurban
          || (m == NOVEMBER && d >= 2 && d <= 5)))  // ramazan
        false
     else if ((y == 2006) &&

        ((m == JANUARY && d >= 9 && d <= 13)  // kurban
          || (m == OCTOBER && d >= 23 && d <= 25) // ramazan
          || (m == DECEMBER && d >= 30))) // kurban
         false
      else if ((y == 2007) &&
        ((m == JANUARY && d <= 4) // kurban
          || (m == OCTOBER && d >= 11 && d <= 14) // ramazan
          || (m == DECEMBER && d >= 19 && d <= 23)) ) // kurban
        false
      else if ((y == 2008) &&
         ((m == SEPTEMBER && d >= 29) || (m == OCTOBER && d <= 2)  // ramazan
          || (m == DECEMBER && d >= 7 && d <= 11))) // kurban
          false else true

    }
  }

}

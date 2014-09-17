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

import org.quantintel.ql.time.Month._
import org.quantintel.ql.time.Weekday._
import org.quantintel.ql.time.{Date, Western, Calendar}


object DenmarkEnum extends Enumeration {
  type DenmarkEnum = Value
  val DENMARK = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => DENMARK
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * Danish calendar
 * Holidays:
 *  Saturdays
 *  Sundays
 *  Maunday Thursday
 *  Good Friday
 *  Easter Monday
 *  General Prayer Day, 25 days after Easter Monday
 *  Ascension
 *  Whit (Pentecost) Monday
 *  New Year's Day, JANUARY 1st
 *  Constitution Day, June 5th
 *  Christmas, December 25th
 *  Boxing Day, December 26th
 *
 *  Reference: http://nordic.nasdaqomxtrader.com/trading/tradinghours/
 *
 * @author Paul Bernard
 */
object Denmark {

  import org.quantintel.ql.time.calendars.DenmarkEnum._

  def apply : Calendar = new Denmark

  def apply(market: DenmarkEnum) : Calendar = {
    market match {
      case DENMARK => new Denmark
    }
  }



  private class Denmark extends Western {

    override def name = "Denmark"

    override def isBusinessDay(date: Date): Boolean = {

      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year
      val em: Int = easterMonday(y)

      if (isWeekend(w)                      // Maunday Thursday
        || (dd == em-4)                     // Good Friday
        || (dd == em-3)                     // Easter Monday
        || (dd == em)                       // General Prayer Day
        || (dd == em+25)
        || (dd == em+38)                    // Ascension
        || (dd == em+49)                    // Whit Monday
        || (d == 1  && m == JANUARY)        // New Year's Day
        || (d == 5  && m == JUNE)           // Constitution Day, June 5th
        || (d == 25 && m == DECEMBER)       // Christmas
        || (d == 26 && m == DECEMBER)       // Boxing Day
        || (d == 24 && m == DECEMBER && (y == 2008 || y == 2009 || y == 2007))    // Christmas eve
        || (d == 31 && m == DECEMBER && (y == 2008 || y == 2009 || y == 2007))     // new year eve
        || (d == 22 && m == MAY && y == 2009))
        false else true
    }

  }

}

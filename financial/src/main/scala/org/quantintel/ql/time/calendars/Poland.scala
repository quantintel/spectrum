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
import org.quantintel.ql.time.{Impl, Calendar, Date, Western}

object PolandEnum extends Enumeration {

  type PolandEnum = Value
  val POLAND = Value(1)

  def valueOf(market: Int) : PolandEnum = market match {
    case 1 => POLAND
    case _ => throw new Exception("Valid units = 1")
  }

}

object Poland {

  def apply() : Calendar = {
    new Poland()
  }

  def apply(market: org.quantintel.ql.time.calendars.PolandEnum.PolandEnum) : Calendar = {
    new Poland(market)
  }

}

/**
 *
 * Polish Holidays:
 *  Saturdays
 *  Sundays
 *  Easter Monday
 *  Corpus Christi
 *  New Year's Day, JANUARY 1st
 *  May Day, May 1st
 *  Constitution Day, May 3rd
 *  Assumption of the Blessed Virgin Mary, August 15th
 *  All Saints Day, November 1st
 *  Independence Day, November 11th
 *  Christmas, December 25th
 *  2nd Day of Christmas, December 26th
 *
 * Reference: http://www.gpw.pl
 *
 * @author Paul Bernard
 */
class Poland extends Calendar  {

  impl = new Poland

  import org.quantintel.ql.time.calendars.PolandEnum._

  def this(market: org.quantintel.ql.time.calendars.PolandEnum.PolandEnum){
    this
    market match {
      case POLAND => impl = new Poland
      case _ => throw new Exception("Valid units = 1")
    }
  }

  private class Poland extends Western {

    override def name : String = "Poland"

    override def isBusinessDay(date: Date): Boolean = {

      // standard dependencies
      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd: Int = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year
      val em: Int = easterMonday(y)

      if (isWeekend(w)
        || (dd == em) // Easter Monday
        || (dd == em + 59)  // Corpus Christi
        || (d == 1  && m == JANUARY) // New Year's Day
        || (d == 1  && m == MAY)   // May Day
        || (d == 3  && m == MAY)  // Constitution Day
        || (d == 15  && m == AUGUST) // Assumption of the Blessed Virgin Mary
        || (d == 1  && m == NOVEMBER) // All Saints Day
        || (d ==11  && m == NOVEMBER)  // Independence Day
        || (d == 25 && m == DECEMBER)  // Christmas
        || (d == 26 && m == DECEMBER))  // 2nd Day of Christmas
        false else true
    }
  }

}

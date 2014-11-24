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
import org.quantintel.ql.time.{Impl, Date, Calendar}

object JapanEnum extends Enumeration {

  type JapanEnum = Value
  val JAPAN = Value(1)

  def valueOf(market: Int) : JapanEnum  = market match {
    case 1 => JAPAN
    case _ => throw new Exception("Valid units = 1")
  }

}

object Japan {

  def apply() : Calendar = {
    new Japan()
  }

  def apply(market: org.quantintel.ql.time.calendars.JapanEnum.JapanEnum): Calendar = {
    new Japan(market)
  }

}

/**
 *
 * Japanese calendar
 * Holidays:
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Bank Holiday, JANUARY 2nd
 *  Bank Holiday, JANUARY 3rd
 *  Coming of Age Day, 2nd Monday in JANUARY
 *  National Foundation Day, February 11th
 *  Vernal Equinox
 *  Greenery Day, April 29th
 *  Constitution Memorial Day, May 3rd
 *  Holiday for a Nation, May 4th
 *  Children's Day, May 5th
 *  Marine Day, 3rd Monday in July
 *  Respect for the Aged Day, 3rd Monday in September
 *  Autumnal Equinox
 *  Health and Sports Day, 2nd Monday in October
 *  National Culture Day, November 3rd
 *  Labor Thanksgiving Day, November 23rd
 *  Emperor's Birthday, DECEMBER 23rd
 *  Bank Holiday, DECEMBER 31st
 *  a few one-shot holidays
 *
 *  Note: Holidays falling on a Sunday are observed on the Monday following
 *        except for the bank holidays associated with the new year.
 *
 * Reference: http://www.tse.or.jp
 *
 * @author Paul Bernard
 */
class Japan extends Calendar  {

  impl = new Japan

  import org.quantintel.ql.time.calendars.JapanEnum._

  def this (market: org.quantintel.ql.time.calendars.JapanEnum.JapanEnum){
    this
    market match {
      case JAPAN => impl = new Japan
      case _ => throw new Exception("Valid units = 1")
    }
  }

  private class Japan extends Impl {

    override def name : String = "Japan"

    override def isWeekend(w: Weekday) : Boolean = w == SATURDAY || w == SUNDAY

    override def isBusinessDay(date: Date): Boolean = {

      // standard dependencies
      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val m: Month = date.month
      val y: Int = date.year

      // Equinox dependencies
      val exactVernalEquinoxTime : Double = 20.69115
      val exactAutumnalEquinoxTime : Double = 23.09
      val diffPerYear : Double = 0.242194
      val movingAmount : Double = (y - 2000) * diffPerYear
      val numberOfLeapYears : Int = (y - 2000) / 4 + (y - 2000) / 100 - (y - 2000) / 400
      val ve : Int = (exactVernalEquinoxTime  + movingAmount - numberOfLeapYears).asInstanceOf[Int] // vernal equinox day
      val ae : Int = (exactAutumnalEquinoxTime + movingAmount - numberOfLeapYears).asInstanceOf[Int]// autumnal equinox day


      if (isWeekend(w)
        || (d == 1  && m == JANUARY)  // New Year's Day
        || (d == 2  && m == JANUARY)  // Bank Holiday
        || (d == 3  && m == JANUARY)   // Bank Holiday
        || (w == MONDAY && (d >= 8 && d <= 14) && m == JANUARY  && y >= 2000) // Coming of Age Day (2nd Monday in JANUARY),
        || ((d == 15 || (d == 16 && w == MONDAY)) && m == JANUARY && y < 2000)   // was JANUARY 15th until 2000
        || ((d == 11 || (d == 12 && w == MONDAY)) && m == FEBRUARY)   // National Foundation Day
        || ((d == ve || (d == ve + 1 && w == MONDAY)) && m == MARCH)  // Vernal Equinox
        || ((d == 29 || (d == 30 && w == MONDAY)) && m == APRIL)  // Greenery Day
        || (d == 3  && m == MAY)  // Constitution Memorial Day
        || (d == 4  && m == MAY)  // Holiday for a Nation
        || ((d == 5  || (d == 6 && w == MONDAY)) && m == MAY) // Children's Day
        || (w == MONDAY && (d >= 15 && d <= 21) && m == JULY && y >= 2003)
        || ((d == 20 || (d == 21 && w == MONDAY)) && m == JULY && y >= 1996 && y < 2003) // Marine Day (3rd MONDAY in July),
                                                                      // was July 20th until 2003, not a holiday before 1996
        || (w == MONDAY && (d >= 15 && d <= 21) && m == SEPTEMBER && y >= 2003)  // Respect for the Aged Day (3rd MONDAY in September),
        || ((d == 15 || (d == 16 && w == MONDAY)) && m == SEPTEMBER  && y < 2003)     // was September 15th until 2003
        // If a single day falls between Respect for the Aged Day
        // and the Autumnal Equinox, it is holiday
        || (w == TUESDAY && d + 1 == ae && d >= 16 && d <= 22 && m == SEPTEMBER && y >= 2003)
        || ((d == ae || (d == ae + 1 && w == MONDAY)) && m == SEPTEMBER) // Autumnal Equinox
        // Health and Sports Day (2nd MONDAY in October),
        // was October 10th until 2000
        || (w == MONDAY && (d >= 8 && d <= 14) && m == OCTOBER && y >= 2000)
        || ((d == 10 || (d == 11 && w == MONDAY)) && m == OCTOBER && y < 2000)
        || ((d == 3  || (d == 4 && w == MONDAY)) && m == NOVEMBER)  // National Culture Day
        || ((d == 23 || (d == 24 && w == MONDAY)) && m == NOVEMBER)  // Labor Thanksgiving Day
        || ((d == 23 || (d == 24 && w == MONDAY)) && m == DECEMBER && y >= 1989)   // Emperor's Birthday
        || (d == 31 && m == DECEMBER) // Bank Holiday
        || (d == 10 && m == APRIL && y == 1959) // Marriage of Prince Akihito
        || (d == 24 && m == FEBRUARY && y == 1989)  // Rites of Imperial Funeral
        || (d == 12 && m == NOVEMBER && y == 1990)  // Enthronement Ceremony
        || (d == 9 && m == JUNE && y == 1993))  // Marriage of Prince Naruhito
        false else true

    }
  }

}

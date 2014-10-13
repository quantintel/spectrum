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

object IndiaEnum extends Enumeration {

  type IndiaEnum = Value
  val NSE = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => NSE
    case _ => throw new Exception("Valid units = 1")
  }

}

object India {

  def apply(): Calendar = {
    new India()
  }

  def apply(market: org.quantintel.ql.time.calendars.IndiaEnum.IndiaEnum): Calendar = {
    new India(market)
  }

}

/**
 *
 * Indian calendars
 * Holidays for the National Stock Exchange
 *  Saturdays
 *  Sundays
 *  Republic Day, JANUARY 26th
 *  Good Friday
 *  Ambedkar Jayanti, April 14th
 *  Independence Day, August 15th
 *  Gandhi Jayanti, October 2nd
 *  Christmas, December 25th
 *
 * Other holidays for which no rule is given (data available for
 * 2005-2008
 *  Bakri Id
 *  Moharram
 *  Mahashivratri
 *  Holi
 *  Ram Navami
 *  Mahavir Jayanti
 *  Id-E-Milad
 *  Maharashtra Day
 *  Buddha Pournima
 *  Ganesh Chaturthi
 *  Dasara
 *  Laxmi Puja
 *  Bhaubeej
 *  Ramzan Id
 *  Guru Nanak Jayanti
 *
 * TODO: This implementation has holidays only up to year 2008.
 *
 * Reference:
 *    http://www.nse-india.com/
 *
 * @author Paul Bernard
 */
class India extends Calendar {

  impl = new Nse

  import org.quantintel.ql.time.calendars.IndiaEnum._

  def this(market: org.quantintel.ql.time.calendars.IndiaEnum.IndiaEnum){
    this
    market match {
      case NSE => impl = new Nse
      case _ => throw new Exception("Valid units = 1")
    }
  }

  private class Nse extends Western {

    override def name = "National Stock Exchange of India"

    override def isBusinessDay(date: Date): Boolean = {

      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year
      val em: Int = easterMonday(y)

      if (isWeekend(w)
        || (d == 26 && m == JANUARY)    // Republic Day
        || (dd == em-3)                  // Good Friday
        || (d == 14 && m == APRIL)      // Ambedkar Jayanti
        || (d == 15 && m == AUGUST)     // Independence Day
        || (d == 2 && m == OCTOBER)     // Gandhi Jayanti
        || (d == 25 && m == DECEMBER))   // Christmas
        false
      else if ((y == 2005) &&
          //  Moharram, Holi, Maharashtra Day, and Ramzan Id fall
          // on Saturday or Sunday in 2005
          ((d == 21 && m == JANUARY)   // Bakri Id
            || (d == 7 && m == SEPTEMBER) // Ganesh Chaturthi
            || (d == 12 && m == OCTOBER)  // Dasara
            || (d == 1 && m == NOVEMBER)  // Laxmi Puja
            || (d == 3 && m == NOVEMBER)  // Bhaubeej
            || (d == 15 && m == NOVEMBER))) // Guru Nanak Jayanti
        false
      else if ((y == 2006) &&
          ((d == 11 && m == JANUARY)   // Bakri Id
            || (d == 9 && m == FEBRUARY)  // Moharram
            || (d == 15 && m == MARCH)   // Holi
            || (d == 6 && m == APRIL) // Ram Navami
            || (d == 11 && m == APRIL)  // Mahavir Jayanti
            || (d == 1 && m == MAY) // Maharashtra Day
            || (d == 24 && m == OCTOBER)  // Bhaubeej
            || (d == 25 && m == OCTOBER)))  // Ramzan Id
        false
      else if ((y == 2007) &&
         ((d == 1 && m == JANUARY) // Bakri Id
            || (d == 30 && m == JANUARY)  // Moharram
            || (d == 16 && m == FEBRUARY) // Mahashivratri
            || (d == 27 && m == MARCH)  // Ram Navami
            || (d == 1 && m == MAY)  // Maharashtra Day
            || (d == 2 && m == MAY) // Buddha Pournima
            || (d == 9 && m == NOVEMBER)  // Laxmi Puja
            || (d == 21 && m == DECEMBER))) // Bakri Id (again)
        false
      else if ((y == 2008) &&
        ((d == 6 && m == MARCH)  // Mahashivratri
            || (d == 20 && m == MARCH)  // Id-E-Milad
            || (d == 18 && m == APRIL)  // Mahavir Jayanti
            || (d == 1 && m == MAY) // Maharashtra Day
            || (d == 19 && m == MAY)  // Buddha Pournima
            || (d == 3 && m == SEPTEMBER) // Ganesh Chaturthi
            || (d == 2 && m == OCTOBER) // Ramzan Id
            || (d == 9 && m == OCTOBER) // Dasara
            || (d == 28 && m == OCTOBER)  // Laxmi Puja
            || (d == 30 && m == OCTOBER)  // Bhau bhij
            || (d == 13 && m ==  NOVEMBER)   // Gurunanak Jayanti
            || (d == 9 && m == DECEMBER)))   // Bakri Id
        false
      else true

    }
  }

}

/*
 * Copyright (c) 2014  Paul Bernard
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you MAY not use this file except in compliance with the License.
 * You MAY obtain a copy of the License at
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

object IndonesiaEnum extends Enumeration {

  type IndonesiaEnum = Value
  val BEJ = Value(1)
  val JSX = Value(2)

  def valueOf(market: Int) = market match {
    case 1 => BEJ
    case 2 => JSX
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * Indonesian calendars Holidays for the Jakarta stock exchange
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Good Friday
 *  Ascension of Jesus Christ
 *  Independence Day, AUGUST 17th
 *  Christmas, DECEMBER 25th
 *
 * Other holidays for which no rule is given
 *  Idul Adha
 *  Ied Adha
 *  Imlek
 *  Moslem's New Year Day
 *  Chinese New Year
 *  Nyepi (Saka's New Year)
 *  Birthday of Prophet Muhammad SAW
 *  Waisak
 *  Ascension of Prophet Muhammad SAW
 *  Idul Fitri
 *  Ied Fitri
 *  Other national leaves
 *
 * TODO: data available for 2005-2008 only
 *
 * Reference: http://www.jsx.co.id/
 *
 * @author Paul Bernard
 */
object Indonesia {

  def apply: Calendar = new Bej

  import org.quantintel.ql.time.calendars.IndonesiaEnum._

  def apply(market: IndonesiaEnum): Calendar = {
    market match {
      // Jakarta stock exchange is know by two id's : required reference
      case BEJ => new Bej
      case JSX => new Bej
      case _ => throw new Exception("Valid units = 1 to 2")
    }
  }

  private class Bej extends Western {

    override def name = "Jakarta stock Exchange"

    override def isBusinessDay(date: Date): Boolean = {

      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year
      val em: Int = easterMonday(y)

      if (isWeekend(w)
        || (d == 1 && m == JANUARY)   // New Year's Day
        || (dd == em - 3)    // Good Friday
        || (dd == em + 38)    // Ascension Thursday
        || (d == 17 && m == AUGUST)   // Independence Day
        || (d == 25 && m == DECEMBER))    // Christmas
        false
      else if ((y == 2005) &&
         ((d == 21 && m == JANUARY) // Idul Adha
            || (d == 9 && m == FEBRUARY)  // Imlek
            || (d == 10 && m == FEBRUARY) // Moslem's New Year Day
            || (d == 11 && m == MARCH)  // Nyepi
            || (d == 22 && m == APRIL)  // Birthday of Prophet Muhammad SAW
            || (d == 24 && m == MAY)  // Waisak
            || (d == 2 && m == SEPTEMBER) // Ascension of Prophet Muhammad SAW
            || ((d == 3 || d == 4) && m == NOVEMBER)  // Idul Fitri
            || ((d == 2 || d == 7 || d == 8) && m == NOVEMBER) || (d == 26 && m == DECEMBER)))  // National leaves
          false
      else if ((y == 2006) &&
        ((d == 10 && m == JANUARY) // Idul Adha
            || (d == 31 && m == JANUARY)  // Moslem's New Year Day
            || (d == 30 && m == MARCH)  // Nyepi
            || (d == 10 && m == APRIL)   // Birthday of Prophet Muhammad SAW
            || (d == 21 && m == AUGUST) // Ascension of Prophet Muhammad SAW
            || ((d == 24 || d == 25) && m == OCTOBER) // Idul Fitri
            || ((d == 23 || d == 26 || d == 27) && m == OCTOBER)))  // National leaves
        false
     else if ((y == 2007) &&
        ((d == 19 && m == MARCH) // Nyepi
            || (d == 1 && m == JUNE)// Waisak
            || (d == 20 && m == DECEMBER) // Ied Adha
            || (d == 18 && m == MAY) || ((d == 12 || d == 15 || d == 16) && m == OCTOBER) // National leaves
            || ((d == 21 || d == 24) && m == OCTOBER)))
     false
     else if ((y == 2007) &&
        (((d == 10 || d == 11) && m == JANUARY)  // Islamic New Year
            || ((d == 7 || d == 8) && m == FEBRUARY)  // Chinese New Year
            || (d == 7 && m == MARCH) // Saka's New Year
            || (d == 20 && m == MARCH)  // Birthday of the prophet Muhammad SAW
            || (d == 20 && m == MAY)  // Vesak Day
            || (d == 30 && m == JULY) // Isra' Mi'raj of the prophet Muhammad SAW
            || (d == 30 && m == SEPTEMBER) || ((d == 1 || d == 2 || d == 3) && m == OCTOBER)  // Ied Fitr
            || (d == 8 && m == DECEMBER) // Ied Adha // Zahid: it is Saturday, should not be here
            || (d == 29 && m == DECEMBER) // Islamic New Year // Zahid: it is Saturday, should not be here
            || (d == 31 && m == DECEMBER)  // New Year's Eve
            || (d == 18 && m == AUGUST)))  // National leave
        false
      // New holidays: QL97 has only up to year 2007

      else if ((y == 2008) &&
        ((d == 10 && m == JANUARY) // Islamic New Year 1429 H
            || (d == 11 && m == JANUARY)  // National Leave
            || (d == 7 && m == FEBRUARY)  // Chinese New Year
            || (d == 8 && m == FEBRUARY)  // Trading Holiday
            || (d == 7 && m == MARCH)  // Saka's New Year
            || (d == 20 && m == MARCH)  // Birthday of the prophet Muhammad
            || (d == 20 && m == MAY)  // Vesak Day
            || (d == 30 && m == JULY)  // Isra' Mi'raj of the prophet Muhammad
            || (d == 18 && m == AUGUST) || (d == 30 && m == SEPTEMBER)  // National Leave
            || (d == 1 && m == OCTOBER) || (d == 2 && m == OCTOBER) // Ied Fitr 1 Syawal
            || (d == 3 && m == OCTOBER) // National Leave
            || (d == 8 && m == DECEMBER)  // Ied Adha
            || (d == 29 && m == DECEMBER) // Islamic New Year
            || (d == 31 && m == DECEMBER)))   // New Year's Eve
      false
      else if ((y == 2009) &&
       ((d == 2 && m == JANUARY)  // Public Holiday
            || (d == 26 && m == JANUARY)  // Chinese New Year
            || (d == 26 && m == MARCH)  // Saka's New Year
            || (d == 9 && m == MARCH) // Birthday of the prophet Muhammad
            || (d == 20 && m == JULY)  // Isra' Mi'raj of the prophet Muhammad
            || (d == 18 && m == SEPTEMBER) || (d == 23 && m == SEPTEMBER) // Public Holiday
            || (d == 21 && m == SEPTEMBER) || (d == 22 && m == SEPTEMBER)  // Ied Fitr 1 Syawal
            || (d == 27 && m == NOVEMBER) // Ied Adha
            || (d == 18 && m == DECEMBER) // Islamic New Year
            || (d == 24 && m == DECEMBER) // Public Holiday
            || (d == 31 && m == DECEMBER))) // Trading Holiday
        false
      else true

    }
  }



}

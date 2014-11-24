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

object SingaporeEnum extends Enumeration {

  type SingaporeEnum = Value
  val SGX = Value(1)

  def valueOf(market: Int) : SingaporeEnum  = market match {
    case 1 => SGX
    case _ => throw new Exception("Valid units = 1")
  }

}

object Singapore {

  def apply(): Calendar = {
    new Singapore()
  }

  def apply(market: org.quantintel.ql.time.calendars.SingaporeEnum.SingaporeEnum): Calendar = {
    new Singapore(market)
  }


}

/**
 *
 * Singaporean non business days
 *  Saturdays
 *  Sundays
 *  New Year's day, JANUARY 1st
 *  Good Friday
 *  Labour Day, May 1st
 *  National Day, August 9th
 *  Christmas, December 25th
 *
 * Other holidays for which no rule is given
 *    (data available for 2004-2008 only)
 *  Chinese New Year
 *  Hari Raya Haji
 *  Vesak Day
 *  Deepavali
 *  Diwali
 *  Hari Raya Puasa
 *
 * Reference: http://www.ses.com.sg
 *
 * @author Paul Bernard
 */
class Singapore extends Calendar {

  impl = new Sgx

  import org.quantintel.ql.time.calendars.SingaporeEnum._

  def this(market: org.quantintel.ql.time.calendars.SingaporeEnum.SingaporeEnum){
    this
    market match {
      case SGX => impl = new Sgx
      case _ => throw new Exception("Valid units = 1")
    }
  }

  private class Sgx extends Western {

    override def name : String = "Singapore Exchange"

    override def isBusinessDay(date: Date): Boolean = {

      // standard dependencies
      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd: Int = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year
      val em: Int = easterMonday(y)

      if (isWeekend(w)
        || (d == 1 && m == JANUARY) // New Year's Day
        || (dd == em - 3) // Good Friday
        || (d == 1 && m == MAY) // Labor Day
        || (d == 9 && m == AUGUST)  // National Day
        || (d == 25 && m == DECEMBER)  // Christmas Day
        || ((d == 22 || d == 23) && m == JANUARY && y == 2004)  // Chinese New Year
        || ((d == 9 || d == 10) && m == FEBRUARY && y == 2005)
        || ((d == 30 || d == 31) && m == JANUARY && y == 2006)
        || ((d == 19 || d == 20) && m == FEBRUARY && y == 2007)
        || ((d == 7 || d == 8) && m == FEBRUARY && y == 2008)
        || ((d == 26 || d == 27) && m == JANUARY && y == 2009) //Zahid
        || ((d == 1 || d == 2) && m == FEBRUARY && y == 2004) // Hari Raya Haji
        || (d == 21 && m == JANUARY && y == 2005)
        || (d == 10 && m == JANUARY && y == 2006)
        || (d == 2 && m == JANUARY && y == 2007)
        || (d == 20 && m == DECEMBER && y == 2007)
        || (d == 8 && m == DECEMBER && y == 2008)
        || (d == 2 && m == JUNE && y == 2004)  // Vesak Poya Day
        || (d == 22 && m == MAY && y == 2005)
        || (d == 12 && m == MAY && y == 2006)
        || (d == 31 && m == MAY && y == 2007)
        || (d == 18 && m == MAY && y == 2008)
        || (d == 11 && m == NOVEMBER && y == 2004)  // Deepavali
        || (d == 8 && m == NOVEMBER && y == 2007)
        || (d == 28 && m == OCTOBER && y == 2008)
        || (d == 27 && m == NOVEMBER && y == 2009) //Zahid
        || (d == 1 && m == NOVEMBER && y == 2005) // Diwali
        || ((d == 14 || d == 15) && m == NOVEMBER && y == 2004) // Hari Raya Puasa
        || (d == 3 && m == NOVEMBER && y == 2005)
        || (d == 24 && m == OCTOBER && y == 2006)
        || (d == 13 && m == OCTOBER && y == 2007)
        || (d == 1 && m == OCTOBER && y == 2008)
      ) false else true
    }
  }
}

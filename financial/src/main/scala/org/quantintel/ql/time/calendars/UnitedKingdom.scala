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
import org.quantintel.ql.time.{Western, Date, Orthodox, Calendar}

object UnitedKingdomEnum extends Enumeration {

  type UnitedKingdomEnum = Value
  val SETTLEMENT = Value(1)
  val EXCHANGE = Value(2)
  val METALS = Value(3)

  def valueOf(market: Int) = market match {
    case 1 => SETTLEMENT
    case 2 => EXCHANGE
    case 3 => METALS
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 * United Kingdom calendars Public holidays
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st (possibly moved to Monday)
 *  Good Friday
 *  Easter Monday
 *  Early May Bank Holiday, first Monday of May
 *  Spring Bank Holiday, last Monday of May
 *  Summer Bank Holiday, last Monday of August
 *  Christmas Day, DECEMBER 25th (possibly moved to Monday or Tuesday)
 *  Boxing Day, DECEMBER 26th (possibly moved to Monday or Tuesday)
 *
 * Holidays for the stock exchange:
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st (possibly moved to Monday)
 *  Good Friday
 *  Easter Monday
 *  Early May Bank Holiday, first Monday of May
 *  Spring Bank Holiday, last Monday of May
 *  Summer Bank Holiday, last Monday of August
 *  Christmas Day, DECEMBER 25th (possibly moved to Monday or Tuesday)
 *  Boxing Day, DECEMBER 26th (possibly moved to Monday or Tuesday)
 *
 * Holidays for the metals exchange:
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st (possibly moved to Monday)
 *  Good Friday
 *  Easter Monday
 *  Early May Bank Holiday, first Monday of May
 *  Spring Bank Holiday, last Monday of May
 *  Summer Bank Holiday, last Monday of August
 *  Christmas Day, DECEMBER 25th (possibly moved to Monday or Tuesday)
 *  Boxing Day, DECEMBER 26th (possibly moved to Monday or Tuesday)
 *
 * Reference: http://www.dti.gov.uk/er/bankhol.htm
 *
 * @author Paul Bernard
 */
object UnitedKingdom {

  def apply(): Calendar = new Settlement

  import org.quantintel.ql.time.calendars.UnitedKingdomEnum._

  def apply(market: UnitedKingdomEnum ): Calendar = {
    market match {
      case SETTLEMENT => new Settlement
      case EXCHANGE => new Exchange
      case METALS => new Metals
      case _ => throw new Exception("Valid units = 1 to 4")
    }
  }

  private class Settlement extends Western {

    override def name = "UK settlement"

    override def isBusinessDay(date: Date): Boolean = {

      // standard dependencies
      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd: Int = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year
      val em: Int = easterMonday(y)

      if (isWeekend(w)
        // New Year's Day (possibly moved to Monday)
        || ((d == 1 || ((d == 2 || d == 3) && w == MONDAY)) && m == JANUARY)
        // Good Friday
        || (dd == em - 3)
        // Easter MONDAY
        || (dd == em)
        // first MONDAY of May (Early May Bank Holiday)
        || (d <= 7 && w == MONDAY && m == MAY)
        // last MONDAY of MAY (Spring Bank Holiday)
        || (d >= 25 && w == MONDAY && m == MAY && y != 2002)
        // last MONDAY of August (Summer Bank Holiday)
        || (d >= 25 && w == MONDAY && m == AUGUST)
        // Christmas (possibly moved to MONDAY or Tuesday)
        || ((d == 25 || (d == 27 && (w == MONDAY || w == TUESDAY))) && m == DECEMBER)
        // Boxing Day (possibly moved to MONDAY or TUESDAY)
        || ((d == 26 || (d == 28 && (w == MONDAY || w == TUESDAY))) && m == DECEMBER)
        // June 3rd, 2002 only (Golden Jubilee Bank Holiday)
        // June 4rd, 2002 only (special Spring Bank Holiday)
        || ((d == 3 || d == 4) && m == JUNE && y == 2002)
        // June, 5th, 2012 only (Queens Diamond Jubilee)
        || (d == 5 && m == JUNE && y == 2012)
        // April, 29th, 2011 only (Royal Wedding)
        || (d == 29 && m == APRIL && y == 2011)
        // DECEMBER 31st, 1999 only
        || (d == 31 && m == DECEMBER && y == 1999))
        false else true

    }
  }

  private class Exchange extends Western {

    override def name = "London stock exchange"

    override def isBusinessDay(date: Date): Boolean = {

      // standard dependencies
      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd: Int = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year
      val em: Int = easterMonday(y)

      if (isWeekend(w)
        // New Year's Day (possibly moved to MONDAY)
        || ((d == 1 || ((d == 2 || d == 3) && w == MONDAY)) && m == JANUARY)
        // Good Friday
        || (dd == em - 3)
        // Easter MONDAY
        || (dd == em)
        // first MONDAY of MAY (Early MAY Bank Holiday)
        || (d <= 7 && w == MONDAY && m == MAY)
        // last MONDAY of MAY (Spring Bank Holiday)
        || (d >= 25 && w == MONDAY && m == MAY && y != 2002)
        // last MONDAY of AUGUST (Summer Bank Holiday)
        || (d >= 25 && w == MONDAY && m == AUGUST)
        // Christmas (possibly moved to MONDAY or TUESDAY)
        || ((d == 25 || (d == 27 && (w == MONDAY || w == TUESDAY))) && m == DECEMBER)
        // Boxing Day (possibly moved to MONDAY or TUESDAY)
        || ((d == 26 || (d == 28 && (w == MONDAY || w == TUESDAY))) && m == DECEMBER)
        // JUNE 3rd, 2002 only (Golden Jubilee Bank Holiday)
        // JUNE 4rd, 2002 only (special Spring Bank Holiday)
        || ((d == 3 || d == 4) && m == JUNE && y == 2002)
        // June, 5th, 2012 only (Queens Diamond Jubilee)
        || (d == 5 && m == JUNE && y == 2012)
        // April, 29th, 2011 only (Royal Wedding)
        || (d == 29 && m == APRIL && y == 2011)
        // DECEMBER 31st, 1999 only
        || (d == 31 && m == DECEMBER && y == 1999))
        false else true

    }
  }

  private class Metals extends Western {

    override def name = "London metals exchange"

    override def isBusinessDay(date: Date): Boolean = {

      // standard dependencies
      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd: Int = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year
      val em: Int = easterMonday(y)

      if (isWeekend(w)
        // New Year's Day (possibly moved to MONDAY)
        || ((d == 1 || ((d == 2 || d == 3) && w == MONDAY)) && m == JANUARY)
        // Good Friday
        || (dd == em - 3)
        // Easter MONDAY
        || (dd == em)
        // first MONDAY of MAY (Early MAY Bank Holiday)
        || (d <= 7 && w == MONDAY && m == MAY)
        // last MONDAY of MAY (Spring Bank Holiday)
        || (d >= 25 && w == MONDAY && m == MAY && y != 2002)
        // last MONDAY of AUGUST (Summer Bank Holiday)
        || (d >= 25 && w == MONDAY && m == AUGUST)
        // Christmas (possibly moved to MONDAY or TUESDAY)
        || ((d == 25 || (d == 27 && (w == MONDAY || w == TUESDAY))) && m == DECEMBER)
        // Boxing Day (possibly moved to MONDAY or TUESDAY)
        || ((d == 26 || (d == 28 && (w == MONDAY || w == TUESDAY))) && m == DECEMBER)
        // JUNE 3rd, 2002 only (Golden Jubilee Bank Holiday)
        // JUNE 4rd, 2002 only (special Spring Bank Holiday)
        || ((d == 3 || d == 4) && m == JUNE && y == 2002)
        // June, 5th, 2012 only (Queens Diamond Jubilee)
        || (d == 5 && m == JUNE && y == 2012)
        // April, 29th, 2011 only (Royal Wedding)
        || (d == 29 && m == APRIL && y == 2011)
        // DECEMBER 31st, 1999 only
        || (d == 31 && m == DECEMBER && y == 1999))
        false else true

    }
  }

}

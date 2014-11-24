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


object UnitedStatesEnum extends Enumeration {

  type UnitedStatesEnum = Value
  val SETTLEMENT = Value(1)
  val NYSE = Value(2)
  val GOVERNMENTBOND = Value(3)
  val NERC = Value(4)

  def valueOf(market: Int) : UnitedStatesEnum = market match {
    case 1 => SETTLEMENT
    case 2 => NYSE
    case 3 => GOVERNMENTBOND
    case 4 => NERC
    case _ => throw new Exception("Valid units = 1 to 4")
  }

}

object UnitedStates {

  def apply(): Calendar = {
    new UnitedStates()
  }

  def apply(market: org.quantintel.ql.time.calendars.UnitedStatesEnum.UnitedStatesEnum): Calendar = {
    new UnitedStates(market)
  }

}

/**
 * United States calendars
 * Public holidays
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st (possibly moved to Monday if actually on
 *  Sunday, or to Friday if on Saturday)
 *  Martin Luther King's birthday, third Monday in JANUARY
 *  Presidents' Day (a.k.a. Washington's birthday), third Monday in February
 *  Memorial Day, last Monday in May
 *  Independence Day, July 4th (moved to Monday if Sunday or Friday if
 *      Saturday)
 *  Labor Day, first Monday in September
 *  Columbus Day, second Monday in October
 *  Veterans' Day, November 11th (moved to Monday if Sunday or Friday if
 *      Saturday)
 *  Thanksgiving Day, fourth Thursday in November
 *  Christmas, December 25th (moved to Monday if Sunday or Friday if
 *  Saturday)
 *
 *
 *
 * Holidays for the stock exchange
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st (possibly moved to Monday if actually on
 *        Sunday)
 *  Martin Luther King's birthday, third Monday in JANUARY (since 1998)
 *  Presidents' Day (a.k.a. Washington's birthday), third Monday in February
 *  Good Friday
 *  Memorial Day, last Monday in May
 *  Independence Day, July 4th (moved to Monday if Sunday or Friday if
 *        Saturday)
 *  Labor Day, first Monday in September
 *  Thanksgiving Day, fourth Thursday in November
 *  Presidential election day, first Tuesday in November of election years
 *      (until 1980)
 *  Christmas, December 25th (moved to Monday if Sunday or Friday if
 *        Saturday)
 *  Special historic closings (see http://www.nyse.com/pdfs/closings.pdf)
 *
 *
 *
 * Holidays for the government bond market
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st (possibly moved to Monday if actually on
 *      Sunday)
 *  Martin Luther King's birthday, third Monday in JANUARY
 *  Presidents' Day (a.k.a. Washington's birthday), third Monday in February
 *  Good Friday
 *  Memorial Day, last Monday in May
 *  Independence Day, July 4th (moved to Monday if Sunday or Friday if
 *      Saturday)
 *  Labor Day, first Monday in September
 *  Columbus Day, second Monday in October
 *  Veterans' Day, November 11th (moved to Monday if Sunday or Friday if
 *      Saturday)
 *  Thanksgiving Day, fourth Thursday in November
 *  Christmas, December 25th (moved to Monday if Sunday or Friday if
 *  Saturday)
 *
 *
 *
 * Holidays for the North American Energy Reliability Council
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st (possibly moved to Monday if actually on
 *    Sunday)
 *  Memorial Day, last Monday in May
 *  Independence Day, July 4th (moved to Monday if Sunday)
 *  Labor Day, first Monday in September
 *  Thanksgiving Day, fourth Thursday in November
 *  Christmas, December 25th (moved to Monday if Sunday)
 *
 * References:
 *  http://www.opm.gov/fedhol
 *  http://www.nyse.com
 *  http://www.bondmarkets.com
 *  http://www.nerc.com/~oc/offpeaks.html
 *
 *  @author Paul Bernard
 *
 */
class UnitedStates extends Calendar {

  import org.quantintel.ql.time.calendars.UnitedStatesEnum._

  impl =  new Settlement

  def this(market: org.quantintel.ql.time.calendars.UnitedStatesEnum.UnitedStatesEnum) {
    this
    market match {
      case SETTLEMENT => impl = new Settlement
      case NYSE => impl = new NYSE
      case GOVERNMENTBOND => impl = new GovernmentBond
      case NERC => impl = new Nerc
    }
  }




  private class Settlement extends Western {

   override def name : String = "US Settlement"

   override def isBusinessDay(date: Date): Boolean = {

      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val m: Month = date.month

      if (isWeekend(w)
        || ((d == 1 || (d == 2 && w == MONDAY)) && m == JANUARY)                                                // New Year's Day (possibly moved to Monday if on Sunday)
        || (d == 31 && w == FRIDAY && m == DECEMBER)                                                            // (or to Friday if on Saturday)
        || ((d >= 15 && d <= 21) && w == MONDAY && m == JANUARY)                                                // Martin Luther King's birthday (third Monday in January)
        || ((d >= 15 && d <= 21) && w == MONDAY && m == FEBRUARY)                                               // Washington's birthday (third Monday in February)
        || (d >= 25 && w == MONDAY && m == MAY)
        || ((d == 4 || (d == 5 && w == MONDAY) || (d == 3 && w == FRIDAY)) && m == JULY)                        // Independence Day (Monday if Sunday or Friday if Saturday)
        || (d <= 7 && w == MONDAY && m == SEPTEMBER)                                                            // Labor Day (first Monday in September)
        || ((d >= 8 && d <= 14) && w == MONDAY && m == OCTOBER)                                                 // Columbus Day (second Monday in October)
        || ((d == 11 || (d == 12 && w == MONDAY) || (d == 10 && w == FRIDAY)) && m == NOVEMBER)                 // Veteran's Day (Monday if Sunday or Friday if Saturday)
        || ((d >= 22 && d <= 28) && w == THURSDAY && m == NOVEMBER)                                             // Thanksgiving Day (fourth Thursday in November)
        || ((d == 25 || (d == 26 && w == MONDAY) ||  (d == 24 && w == FRIDAY)) && m == DECEMBER))               // Christmas (Monday if Sunday or Friday if Saturday)
        false else true

    }

  }

  private class NYSE extends Western {

    override def name : String = "New York stock exchange"

    override def isBusinessDay(date: Date): Boolean = {

      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year
      val em: Int = easterMonday(y)

      if (isWeekend(w)
        || ((d == 1 || (d == 2 && w == MONDAY)) && m == JANUARY)          // New Year's Day (possibly moved to Monday if on Sunday)
        || ((d >= 15 && d <= 21) && w == MONDAY && m == FEBRUARY)         // Washington's birthday (third Monday in FEBRUARY)
        || (dd == em-3)                                                   // Good FRIDAY
        || (d >= 25 && w == MONDAY && m == MAY)                           // Memorial Day (last MONDAY in MAY)
        || ((d == 4 || (d == 5 && w == MONDAY) ||
                      (d == 3 && w == FRIDAY)) && m == JULY)              // Independence Day (MONDAY if Sunday or FRIDAY if Saturday)
        || (d <= 7 && w == MONDAY && m == SEPTEMBER)                      // Labor Day (first MONDAY in SEPTEMBER)
        || ((d >= 22 && d <= 28) && w == THURSDAY && m == NOVEMBER)       // Thanksgiving Day (fourth THURSDAY in NOVEMBER)
        || ((d == 25 || (d == 26 && w == MONDAY) ||
            (d == 24 && w == FRIDAY)) && m == DECEMBER)                   // Christmas (MONDAY if Sunday or FRIDAY if Saturday)
      ) false
      else if ((y >= 1998) &&
        (((d >= 15 && d <= 21) && w == MONDAY && m == JANUARY)   // Martin Luther King's birthday (third Weekday.MONDAY in JANUARY)
            || (y == 2004 && m == JUNE && d == 11)                        // President Reagan's funeral
            || (y == 2001 && m == SEPTEMBER && (11 <= d && d <= 14))      // Month.SEPTEMBER 11, 2001
            || (y == 2007 && m == JANUARY && d == 2))                    // President Ford's funeral
      ) false
      else if ((y <= 1980) &&
        (((y % 4 == 0) && m == NOVEMBER && d <= 7 && w == TUESDAY)      // presidential election days
          || (y == 1977 && m == JULY && d == 14)                        // 1977 Blackout
          || (y == 1973 && m == JANUARY && d == 25)                     // Funeral of former President Lyndon B. Johnson.
          || (y == 1972 && m == DECEMBER && d == 28)                    // Funeral of former President Harry S. Truman
          || (y == 1969 && m == JULY && d == 21)                        // National Day of Participation for the lunar exploration.
          || (y == 1969 && m == MARCH && d == 31)                       // Funeral of former President Eisenhower.
          || (y == 1969 && m == FEBRUARY && d == 10)                    // Closed all day - heavy snow.
          || (y == 1968 && m == JULY && d == 5)                         // Day after Independence Day.
          || (y == 1968 && dd >= 163 && w == WEDNESDAY)                 // Month.JUNE 12-Dec. 31, 1968
                                                                       // Four day week (closed on Wednesdays) - Paperwork Crisis
          )) false
        else if  (y == 1994 && m == APRIL && d == 27)                   // Nixon's funeral
          false else true

    }

  }

  private class GovernmentBond extends Western {

    override def name : String = "US government bond market"

    override def isBusinessDay(date: Date): Boolean = {

      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year
      val em: Int = easterMonday(y)

      if (isWeekend(w)
        || ((d == 1 || (d == 2 && w == MONDAY)) && m == JANUARY)                        // New Year's Day (possibly moved to MONDAY if on Sunday)
        || ((d >= 15 && d <= 21) && w == MONDAY && m == JANUARY)                        // Martin Luther King's birthday (third MONDAY in JANUARY)
        || ((d >= 15 && d <= 21) && w == MONDAY && m == FEBRUARY)                        // Washington's birthday (third MONDAY in FEBRUARY)
        || (dd == em-3)                                                                  // Good FRIDAY
        || (d >= 25 && w == MONDAY && m == MAY)                                         // Memorial Day (last Monday in MAY)
        || ((d == 4 || (d == 5 && w == MONDAY) ||
                  (d == 3 && w == FRIDAY)) && m == JULY)                                // Independence Day (Monday if Sunday or FRIDAY if Saturday)
        || (d <= 7 && w == MONDAY && m == SEPTEMBER)                                    // Labor Day (first Monday in SEPTEMBER)
        || ((d >= 8 && d <= 14) && w == MONDAY && m == OCTOBER)                         // Columbus Day (second Monday in October)
        || ((d == 11 || (d == 12 && w == MONDAY) ||
                  (d == 10 && w == FRIDAY)) && m == NOVEMBER)                           // Veteran's Day (Monday if Sunday or FRIDAY if Saturday)
        || ((d >= 22 && d <= 28) && w == THURSDAY && m == NOVEMBER)                     // Thanksgiving Day (fourth THURSDAY in NOVEMBER)
        || ((d == 25 || (d == 26 && w == MONDAY) ||
                   (d == 24 && w == FRIDAY)) && m == DECEMBER))                         // Christmas (Monday if Sunday or FRIDAY if Saturday)
      false else true

    }
  }

  private class Nerc extends Western {

    override def name : String = "North American Energy Reliability Council"

    override def isBusinessDay(date: Date): Boolean = {

      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val m: Month = date.month

      if (isWeekend(w)
        || ((d == 1 || (d == 2 && w == MONDAY)) && m == JANUARY) // New Year's Day (possibly moved to Monday if on Sunday)
        || (d >= 25 && w == MONDAY && m == MAY) // Memorial Day (last Monday in MAY)
        || ((d == 4 || (d == 5 && w == MONDAY)) && m == JULY) // Independence Day (Monday if Sunday)
        || (d <= 7 && w == MONDAY && m == SEPTEMBER) // Labor Day (first Monday in SEPTEMBER)
        || ((d >= 22 && d <= 28) && w == THURSDAY && m == NOVEMBER) // Thanksgiving Day (fourth THURSDAY in NOVEMBER)
        || ((d == 25 || (d == 26 && w == MONDAY)) && m == DECEMBER)) // Christmas (Monday if Sunday)
        false
      else true
    }
  }

}

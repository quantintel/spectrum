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

object SouthKoreaEnum extends Enumeration {

  type SouthKoreaEnum = Value
  val SETTLEMENT = Value(1)
  val KRX = Value(2)

  def valueOf(market: Int) = market match {
    case 1 => SETTLEMENT
    case 2 => KRX
    case _ => throw new Exception("Valid units = 1 or 2")
  }

}

/**
 *
 * South Korean calendars Public holidays:
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Independence Day, March 1st
 *  Arbour Day, April 5th (until 2005)
 *  Labour Day, May 1st
 *  Children's Day, May 5th
 *  Memorial Day, June 6th
 *  Constitution Day, July 17th (until 2007)
 *  Liberation Day, August 15th
 *  National Fondation Day, October 3th
 *  Christmas Day, December 25th
 *
 * Other holidays for which no rule is given (data available for 2004-2010 only:)
 *  Lunar New Year, the last day of the previous lunar year, JANUARY 1st, 2nd in lunar calendar
 *  Election Days
 *  National Assemblies
 *  Presidency
 *  Regional Election Days
 *  Buddha's birthday, April 8th in lunar calendar
 *  Harvest Moon Day, August 14th, 15th, 16th in lunar calendar
 *
 * Holidays for the Korea exchange
 *  Public holidays as listed above
 *  Year-end closing
 *
 * Reference:
 *  http://www.krx.co.kr
 *  http://www.dooriworld.com/daishin/holiday/holiday.html
 *
 * @author Paul Bernard
 */
object SouthKorea {

}

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

object ItalyEnum extends Enumeration {

  type ItalyEnum = Value
  val SETTLEMENT = Value(1)
  val EXCHANGE = Value(2)

  def valueOf(market: Int) = market match {
    case 1 => SETTLEMENT
    case 2 => EXCHANGE
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * Italian calendars Public holidays:
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Epiphany, JANUARY 6th
 *  Easter Monday
 *  Liberation Day, April 25th
 *  Labour Day, May 1st
 *  Republic Day, June 2nd (since 2000)
 *  Assumption, August 15th
 *  All Saint's Day, November 1st
 *  Immaculate Conception Day, December 8th
 *  Christmas Day, December 25th
 *  St. Stephen's Day, December 26th

 *
 * Holidays for the stock exchange
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Good Friday
 *  Easter Monday
 *  Labour Day, May 1st
 *  Assumption, August 15th
 *  Christmas' Eve, December 24th
 *  Christmas, December 25th
 *  St. Stephen, December 26th
 *  New Year's Eve, December 31st
 *
 * Reference: http://www.borsaitalia.it
 *
 * @author Paul Bernard
 */
object Italy  {

}

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

object GermanyEnum extends Enumeration {
  type GermanyEnum = Value
  val SETTLEMENT = Value(1)
  val FRANKFURTSTOCKEXCHANGE = Value(2)
  val XETRA = Value(3)
  val EUREX = Value(4)


  def valueOf(market: Int) = market match {
    case 1 => SETTLEMENT
    case 2 => FRANKFURTSTOCKEXCHANGE
    case 3 => XETRA
    case 4 => EUREX
    case _ => throw new Exception("Valid units = 1 to 4")
  }

}

/**
 *
 * German calendars
 *
 * Public holidays:
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Good Friday
 *  Easter Monday
 *  Ascension Thursday
 *  Whit Monday
 *  Corpus Christi
 *  Labour Day, May 1st
 *  National Day, October 3rd
 *  Christmas Eve, December 24th
 *  Christmas, December 25th
 *  Boxing Day, December 26th
 *  New Year's Eve, December 31st
 *
 * Holidays for the Frankfurt Stock exchange
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Good Friday
 *  Easter Monday
 *  Labour Day, May 1st
 *  Christmas' Eve, December 24th
 *  Christmas, December 25th
 *  Christmas Holiday, December 26th
 *  New Year's Eve, December 31st
 *
 * Holidays for the Xetra exchange
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Good Friday
 *  Easter Monday
 *  Labour Day, May 1st
 *  Christmas' Eve, December 24th
 *  Christmas, December 25th
 *  Christmas Holiday, December 26th
 *  New Year's Eve, December 31st
 *
 * Holidays for the Eurex exchange
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Good Friday<
 *  Easter Monday
 *  Labour Day, May 1st
 *  Christmas' Eve, December 24th
 *  Christmas, December 25th
 *  Christmas Holiday, December 26th
 *  New Year's Eve, December 31st
 *
 * References:
 *  http://deutsche-boerse.com/
 *  http://deutsche-boerse.com/
 *  http://www.eurexchange.com/index.html
 *
 * @author Paul Bernard
 *
 */
object Germany  {

}

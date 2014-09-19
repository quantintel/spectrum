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

object SlovakiaEnum extends Enumeration {

  type SlovakiaEnum = Value
  val BSSE = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => BSSE
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * Slovak calendars
 * Holidays for the Bratislava stock exchange
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Epiphany, JANUARY 6th
 *  Good Friday
 *  Easter Monday
 *  May Day, May 1st
 *  Liberation of the Republic, May 8th
 *  SS. Cyril and Methodius, July 5th
 *  Slovak National Uprising, August 29th
 *  Constitution of the Slovak Republic, September 1st
 *  Our Lady of the Seven Sorrows, September 15th
 *  All Saints Day, November 1st
 *  Freedom and Democracy of the Slovak Republic, November 17th
 *  Christmas Eve, December 24th
 *  Christmas, December 25th
 *  St. Stephen, December 26th
 *
 * Reference: http://www.bsse.sk/
 *
 * @author Paul Bernard
 */
object Slovakia {

}

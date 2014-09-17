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

object FinlandEnum extends Enumeration {
  type FinlandEnum = Value
  val FINLAND = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => FINLAND
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * Finnish calendar
 * Holidays:
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Epiphany, JANUARY 6th
 *  Good Friday
 *  Easter Monday
 *  Ascension Thursday<
 *  Labour Day, May 1st
 *  Midsummer Eve (Friday between June 18-24)
 *  Independence Day, December 6th
 *  Christmas Eve, December 24th
 *  Christmas, December 25th
 *  Boxing Day, December 26th
 *
 * @author Paul Bernard
 */
object Finland  {

}

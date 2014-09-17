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

object IcelandEnum extends Enumeration {
  type IcelandEnum = Value
  val ICEX= Value(1)

  def valueOf(market: Int) = market match {
    case 1 => ICEX
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * Icelandic calendars
 * Holidays for the Iceland stock exchange
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st (possibly moved to Monday)
 *  Holy Thursday
 *  Good Friday
 *  Easter Monday
 *  First day of Summer (third or fourth Thursday in April)
 *  Labour Day, May 1st
 *  Ascension Thursday
 *  Pentecost Monday
 *  Independence Day, June 17th
 *  Commerce Day, first Monday in August
 *  Christmas, December 25th
 *  Boxing Day, December 26th
 *
 * Reference: http://www.icex.is/is/calendar?languageID=1
 *
 * @author Paul Bernard
 */
object Iceland {

}

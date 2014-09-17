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

object HongKongEnum extends Enumeration {
  type HongKongEnum = Value
  val HKEX = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => HKEX
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * Hong Kong calendars Holidays:
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st (possibly moved to Monday)
 *  Ching Ming Festival, April 5th
 *  Good Friday
 *  Easter Monday
 *  Labor Day, May 1st
 *  SAR Establishment Day, July 1st (possibly moved to Monday)
 *  National Day, October 1st (possibly moved to Monday)
 *  Christmas, December 25th
 *  Boxing Day, December 26th (possibly moved to Monday)
 *
 * Other holidays for which no rule is given (data available for 2004-2007 only:)
 *  Lunar New Year
 *  Chinese New Year
 *  Buddha's birthday
 *  Tuen NG Festival
 *  Mid-autumn Festival
 *  Chung Yeung Festival

 *
 * Reference:
 *  http://www.hkex.com.hk>
 *
 * @author Paul Bernard
 */
object HongKong {

}

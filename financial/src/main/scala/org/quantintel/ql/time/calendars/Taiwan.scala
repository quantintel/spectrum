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

object TaiwanEnum extends Enumeration {

  type TaiwanEnum = Value
  val TSEC = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => TSEC
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * Taiwanese calendars Holidays for the Taiwan stock exchange
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Peace Memorial Day, February 28
 *  Labor Day, May 1st
 *  Double Tenth National Day, October 10th
 *
 * Other holidays for which no rule is given (data available for 2002-2007 only:)
 *  Chinese Lunar New Year
 *  Tomb Sweeping Day
 *  Dragon Boat Festival
 *  Moon Festival
 *
 * Reference: http://www.tse.com.tw/en/trading/trading_days.php
 *
 * @author Paul Bernard
 */
object Taiwan {

}

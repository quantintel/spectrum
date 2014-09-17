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

}

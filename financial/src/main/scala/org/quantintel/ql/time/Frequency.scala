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

package org.quantintel.ql.time

/**
 * @author Paul Bernard
 */
object Frequency extends Enumeration {

  type Frequency = Value
  val NO_FREQUENCY = Value(-1)
  val ONCE = Value(0)
  val ANNUAL = Value(1)
  val SEMIANNUAL = Value(2)
  val EVERY_FOURTH_MONTH = Value(3)
  val QUARTERLY = Value(4)
  val BIMONTHLY = Value(6)
  val MONTHLY = Value(12)
  val EVERY_FOURTH_WEEK = Value(13)
  val BIWEEKLY = Value(26)
  val WEEKLY = Value(52)
  val DAILY = Value(365)
  val OTHER_FREQUENCY = Value(999)

  def valueOf(market: Int)  = market match {
    case -1 => NO_FREQUENCY
    case 0 => ONCE
    case 1 => ANNUAL
    case 2 => SEMIANNUAL
    case 3 => EVERY_FOURTH_MONTH
    case 4 => QUARTERLY
    case 6 => BIMONTHLY
    case 12 => MONTHLY
    case 13 => EVERY_FOURTH_WEEK
    case 26 => BIWEEKLY
    case 52 => WEEKLY
    case 365 => DAILY
    case 999 => OTHER_FREQUENCY
    case _ => throw new Exception("value must be one of -1,0,1,2,3,4,6,12,13,26,52,365,999")
  }


}

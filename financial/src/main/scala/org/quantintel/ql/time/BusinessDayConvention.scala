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
object BusinessDayConventionEnum extends Enumeration {

  type ArgentinaEnum = Value
  val FOLLOWING = Value(1)
  val MODIFIED_FOLLOWING = Value(2)
  val HALF_MONTH_MODIFIED_FOLLOWING = Value(3)
  val PRECEDING = Value(4)
  val MODIFIED_PRECEDING = Value(5)
  val UNJUSTIFIED = Value(6)

  def valueOf(market: Int)  = market match {
    case 1 => FOLLOWING
    case 2 => MODIFIED_FOLLOWING
    case 3 => HALF_MONTH_MODIFIED_FOLLOWING
    case 4 => PRECEDING
    case 5 => MODIFIED_PRECEDING
    case 6 => UNJUSTIFIED
    case _ => throw new Exception("Valid units = 1 to 6")
  }

}

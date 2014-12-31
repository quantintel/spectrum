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

/** Enumeration representing a valid measurement unit for a period
 *  of time. 
 */
object TimeUnit extends Enumeration {

  type TimeUnit = Value
  val DAYS = Value(0)
  val WEEKS = Value(1)
  val MONTHS = Value(2)
  val YEARS = Value(3)

  def valueOf(unit: Int): TimeUnit = unit match {
    case 0 => DAYS
    case 1 => WEEKS
    case 2 => MONTHS
    case 3 => YEARS
    case _ => throw new Exception("Valid units = 1 to 4")
  }

  def getLongFormat(value: TimeUnit) : String = {
    if (value == DAYS) "days"
    if (value == WEEKS) "weeks"
    if (value == MONTHS) "months"
    if (value == YEARS) "years"
    ""
  }

  def getLShortFormat(value: TimeUnit) : String = {
    if (value == DAYS) "D"
    if (value == WEEKS) "W"
    if (value == MONTHS) "M"
    if (value == YEARS) "Y"
    ""
  }


}

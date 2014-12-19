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

/** Used to represent the named days of a typical calendar.
 * 
 * @author Paul Bernard
 */
object Weekday extends Enumeration {

  type Weekday = Value
  val SUNDAY = Value(1)
  val MONDAY = Value(2)
  val TUESDAY = Value(3)
  val WEDNESDAY = Value(4)
  val THURSDAY = Value(5)
  val FRIDAY = Value(6)
  val SATURDAY = Value(7)

  def valueOf(weekday: Int) : Weekday = weekday match {

    case 1 => Weekday.SUNDAY
    case 2 => Weekday.MONDAY
    case 3 => Weekday.TUESDAY
    case 4 => Weekday.WEDNESDAY
    case 5 => Weekday.THURSDAY
    case 6 => Weekday.FRIDAY
    case 7 => Weekday.SATURDAY
    case _ => throw new Exception("Valid units = 1 to 7")

  }

}

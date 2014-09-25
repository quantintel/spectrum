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
object Month extends Enumeration {

  type Month = Value
  val JANUARY = Value(1)
  val FEBRUARY = Value(2)
  val MARCH = Value(3)
  val APRIL = Value(4)
  val MAY = Value(5)
  val JUNE = Value(6)
  val JULY = Value(7)
  val AUGUST = Value(8)
  val SEPTEMBER = Value(9)
  val OCTOBER = Value(10)
  val NOVEMBER = Value(11)
  val DECEMBER = Value(12)

  def valueOf(month: Int) = month match {
    case 1 => Month.JANUARY
    case 2 => Month.FEBRUARY
    case 3 => Month.MARCH
    case 4 => Month.APRIL
    case 5 => Month.MAY
    case 6 => Month.JUNE
    case 7 => Month.JULY
    case 8 => Month.AUGUST
    case 9 => Month.SEPTEMBER
    case 10 => Month.OCTOBER
    case 11 => Month.NOVEMBER
    case 12 => Month.DECEMBER
    case _ => throw new Exception("Valid months = 1 to 12")
  }


}

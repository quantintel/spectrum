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

package org.quantintel.ql.instruments.options

/**
 * @see BarrierOption
 *
 * @author Paul Bernard
 */
object BarrierType extends Enumeration {

  type BarrierType = Value
  val DOWN_IN = Value(0)
  val UP_IN = Value(1)
  val DOWN_OUT = Value(2)
  val UP_OUT = Value(3)
  val UNKNOWN = Value(4)

  def valueOf(unit: Int) = unit match {
    case 0 => DOWN_IN
    case 1 => UP_IN
    case 2 => DOWN_OUT
    case 3 => UP_OUT
    case 4 => UNKNOWN
    case _ => throw new Exception("Valid units = 0 to 4")
  }

  def toString(unit: BarrierType): String = {
    unit match {
      case DOWN_IN => "Down & In"
      case UP_IN => "Up & In"
      case DOWN_OUT => "Down & Out"
      case UP_OUT => "Up & Out"
      case UNKNOWN => "Unknown"
    }

  }

}

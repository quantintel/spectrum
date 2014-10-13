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
object DateGeneration extends Enumeration {

  type DateGeneration = Value
  val BACKWARD = Value(1)  // Backward from termination date to effective date
  val FORWARD = Value(2)  // forward from effective date to termination date
  val ZERO = Value(3)   // no intermediate dates between eff date and term date
  val THIRD_WEDNESDAY = Value(4)  // all dates but eff date and term date are taken to be on the 3rd wed.
  val TWENTIETH = Value(5)  // all dates but the eff date are taken to be the 20th of their month.
                            // Temination date is also modified
  val TWENTIETH_IMM = Value(6)  // all dates but the eff date are taken to be the 20th of an IMM
                                // month.  The termination data is also modified.
  val OLD_CDS = Value(7) // Identical to 20th IMM with unrestricted date ends and long/short stub
                         //   coupon period.
  val CDS = Value(8)    // credit derivatives standard rule

  def valueOf(market: Int)  = market match {
    case 1 => BACKWARD
    case 2 => FORWARD
    case 3 => ZERO
    case 4 => THIRD_WEDNESDAY
    case 5 => TWENTIETH
    case 6 => TWENTIETH_IMM
    case 7 => OLD_CDS
    case 8 => CDS
    case _ => throw new Exception("value must be 1 through 8")
  }

}

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

package org.quantintel.ql.time.daycounters

import org.quantintel.ql.time.Date

/**
 * Enumeration of supported daycount conventions. These enum values are used when constructing
 * a new instance of the [[Actual360]] class.
 * 
 * @author Paul Bernard
 * @author Peter Mularien 
 */
object Actual360Convention extends Enumeration {

  type Actual360Convention = Value
  /** Actual/360. */
  val ACTUAL360 = Value(1)
  /** French (maps to Actual/360). */
  val FRENCH  = Value(2)

  def valueOf(market: Int) : Actual360Convention = market match {
    case 1 => ACTUAL360
    case 2 => FRENCH
    case _ => throw new Exception("Valid units = 1 to 2")
  }

}
/** Used to construct instances of an Actual/360-based day counter. The apply method should be used,
 *  along with the appropriate [[Actual360Convention]], to construct the desired day counter.
 *  
 * @author Paul Bernard
 * @author Peter Mularien 
 */
object Actual360 {

  /** Default factory method, constructs an ACT/360 day counter. 
   *
   * To use other conventions, please use [[Actual360.apply(convention)]].
   * 
   * @return an ACT/360 day counter
   **/
  def apply() : DayCounter = new Actual360

  import org.quantintel.ql.time.daycounters.Actual360Convention._

  /** Factory method used to create a day counter based on the supplied convention.
   *  
   * @param convention the convention to use
   * @return an ACT/360 day counter based on the requested convention type
   */
  def apply(convention: Actual360Convention) : DayCounter = {
    convention match {
      case ACTUAL360 | FRENCH => new Actual360
      case _ => throw new Exception("unknown act/360 convention")
    }
  }

  /** Implementation class of ACT/360 day count method. Also known as MM (Money Market) basis, 
   *  Actual 360, French. Day count is always the actual day count with a denominator of 360.
   *  
   *  References:
   *    4.16(e) 2006 ISDA Definitions.
   */
  class Actual360 extends DayCounter {

    override def name : String ="Actual/360"

    override def yearFraction(dateStart: Date, dateEnd: Date,
                              refPeriodStart: Date, refPeriodEnd: Date) : Double =
      dayCount(dateStart, dateEnd) / 360.0
  }



}





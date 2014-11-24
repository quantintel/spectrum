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


object Actual360Convention extends Enumeration {

  type Actual360Convention = Value
  val ACTUAL360 = Value(1)
  val FRENCH  = Value(2)

  def valueOf(market: Int) : Actual360Convention = market match {
    case 1 => ACTUAL360
    case 2 => FRENCH
    case _ => throw new Exception("Valid units = 1 to 2")
  }

}
/**
 * @author Paul Bernard
 */
object Actual360 {

  def apply() : DayCounter = new Actual360

  import org.quantintel.ql.time.daycounters.Actual360Convention._

  def apply(convention: Actual360Convention) : DayCounter = {
    convention match {
      case ACTUAL360 | FRENCH => new Actual360
      case _ => throw new Exception("unknown act/360 convention")
    }
  }

  class Actual360 extends DayCounter {

    override def name : String ="Actual/360"

    override def yearFraction(dateStart: Date, dateEnd: Date,
                              refPeriodStart: Date, refPeriodEnd: Date) : Double =
      dayCount(dateStart, dateEnd) / 360.0
  }



}





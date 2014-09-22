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

package org.quantintel.ql.time.daycount

import org.quantintel.ql.time.Date


object ActualActualConvention extends Enumeration {

  type ActualActualConvention= Value
  val ISMA = Value(1)
  val BOND = Value(2)
  val ISDA = Value(3)
  val HISTORICAL = Value(4)
  val ACTUAL365 = Value(5)
  val AFB = Value(6)
  val EURO = Value(7)

  def valueOf(market: Int)  = market match {
    case 1 => ISMA
    case 2 => BOND
    case 3 => ISDA
    case 4 => HISTORICAL
    case 5 => ACTUAL365
    case 6 => AFB
    case 7 => EURO
    case _ => throw new Exception("Valid units = 1 to 7")
  }

}


/**
 * @author Paul Bernard
 */
object ActualActual  {

  def apply() = new ISDA

  import org.quantintel.ql.time.daycount.ActualActualConvention._

  def apply(convention: ActualActualConvention) : DayCounter = {
    convention match {
      case ISMA => new ISMA
      case BOND => new ISMA
      case ISDA => new ISDA
      case HISTORICAL => new ISDA
      case ACTUAL365 => new ISDA
      case AFB => new AFB
      case EURO => new AFB
      case _ => throw new Exception("unknown 30/360 convention")
    }
  }

  class ISMA extends DayCounter {

    override def name = "Actual/Actual (ISMA)"

    override def yearFraction(d1: Date, d2: Date, d3: Date, d4: Date) : Double = ???

  }


  class ISDA extends DayCounter {

    override def name = "Actual/Actual (ISDA)"

    override def yearFraction(d1: Date, d2: Date, d3: Date, d4: Date) : Double = ???

  }

  class AFB extends DayCounter {

    override def name = "Actual/Actual (AFB)"

    override def yearFraction(d1: Date, d2: Date, d3: Date, d4: Date) : Double = ???

  }



}

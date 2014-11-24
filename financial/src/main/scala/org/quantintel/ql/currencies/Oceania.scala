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

package org.quantintel.ql.currencies

// Oceania
import org.quantintel.ql.math.Rounding

object OceaniaEnum extends Enumeration {

  type OceaniaEnum = Value
  val AUD = Value(1)
  val NZD = Value(2)

  def valueOf(currencies: Int) : OceaniaEnum  = currencies match {
    case 1 => AUD
    case 2 => NZD
    case _ => throw new Exception("Valid units = 1 or 2")
  }

}



object Oceania {

  import org.quantintel.ql.currencies.OceaniaEnum._

  def apply(currency: OceaniaEnum) : Currency = {
    currency match {
      case AUD => new AUDCurrency()
      case NZD => new NZDCurrency()
    }
  }

  /**
   * Description:             Australian Dollar
   * ISO three-letter code:   AUD
   * Numerical Code:          36
   * Divided by:              100 cents
   */
  class AUDCurrency extends Currency {

    val audData = Data("Australian dollar", "AUD", 36, "A$", "", 100, Rounding(), "%3% %1$.2f")

    data = audData

  }

  /**
   * Description:             New Zealand
   * ISO three-letter code:   NZD
   * Numerical Code:          554
   * Divided by:              100 cents
   */
  class NZDCurrency extends Currency {

    val nzdData = Data("New Zealand dollar", "NZD", 554, "NZ$", "", 100, Rounding(), "%3% %1$.2f")

    data = nzdData

  }

}

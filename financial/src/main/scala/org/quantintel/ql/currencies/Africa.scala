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
 */

package org.quantintel.ql.currencies

import org.quantintel.ql.math.Rounding

object AfricaEnum extends Enumeration {

type AfricaEnum = Value
  val ZAR = Value(1)

  def valueOf(currencies: Int)  = currencies match {
    case 1 => ZAR
    case _ => throw new Exception("Valid units = 1")
  }

}


object Africa {

  import org.quantintel.ql.currencies.AfricaEnum._

  def apply() : Currency = {
    new ZARCurrency()
  }

  def apply(currency: AfricaEnum) : Currency = {
    currency match {
      case ZAR => new ZARCurrency()
    }
  }

  /**
   * Description:           South African rand
   * ISO 4217 code:         ZAR
   * Numerical code:        710
   * Subunit:               100 cents
   *
   * Reference:             http://en.wikipedia.org/wiki/South_African_rand
   *                        https://www.resbank.co.za/pages/default.aspx
   *                        http://www.thefinancials.com/Default.aspx?SubSectionID=curformat
   *
   * @author Paul Bernard
   */
  class ZARCurrency extends Currency {

    val zarData = Data("South-African rand", "ZAR", 710, "R", "", 100, new Rounding(), "# ###.##")

    data = zarData

  }


}



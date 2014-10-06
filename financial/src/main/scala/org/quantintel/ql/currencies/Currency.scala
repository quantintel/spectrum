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

import org.quantintel.ql.math.Rounding

object Currency {

  def apply(name: String,
            code: String,
            numericCode: Int,
            symbol: String,
            fractionSymbol: String,
            fractionPerUnit: Int,
            rounding: Rounding,
            formatString: String,
            triangulationCurrency: Currency): Currency =
    new Currency(name, code, numericCode, symbol, fractionSymbol,
    fractionPerUnit, rounding, formatString, triangulationCurrency)

}

object Data{

  def apply (name: String,
             code: String,
             numericCode: Int,
             symbol: String,
             fractionSymbol: String,
             fractionPerUnit: Int,
             rounding: Rounding,
             formatString: String,
             triangulationCurrency: Currency) : Data = {

    new Data(name, code, numericCode, symbol, fractionSymbol, fractionPerUnit,
            rounding, formatString, triangulationCurrency)
  }

  def apply (name: String,
             code: String,
             numericCode: Int,
             symbol: String,
             fractionSymbol: String,
             fractionPerUnit: Int,
             rounding: Rounding,
             formatString: String) : Data = {

    new Data(name, code, numericCode, symbol, fractionSymbol, fractionPerUnit, rounding, formatString)
  }


}

class Data (var name: String,
            var code: String,
            var numericCode: Int,
            var symbol: String,
            var fractionSymbol: String,
            var fractionPerUnit: Int,
            var rounding: Rounding,
            var formatString: String,
            var triangulationCurrency: Currency) {

  def this(name: String,
           code: String,
           numericCode: Int,
           symbol: String,
           fractionSymbol: String,
           fractionPerUnit: Int,
           rounding: Rounding,
           formatString: String){

    this(name, code, numericCode, symbol, fractionSymbol,
      fractionPerUnit, rounding, formatString, new Currency())
  }


}

/**
 * @author Paul Bernard
 */
class Currency {

  var data : Data = null

  def this (name: String, code: String, numericCode: Int, symbol: String,
             fractionSymbol: String, fractionPerUnit: Int, rounding: Rounding,
             formatString: String, tc: Currency){
    this
    data = Data(name, code, numericCode, symbol, fractionSymbol, fractionPerUnit, rounding, formatString)
  }

  def name : String = data.name
  def code : String = data.code
  def numericCode: Int = data.numericCode
  def symbol: String = data.symbol
  def fractionSymbol : String = data.fractionSymbol
  def fractionPerUnit: Int = data.fractionPerUnit
  def rounding: Rounding = data.rounding
  def formatString: String = data.formatString


}

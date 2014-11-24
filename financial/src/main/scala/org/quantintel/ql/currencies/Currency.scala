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

  def apply : Data = new Data()

}

class Data {

  var name: String = null
  var code: String = null
  var numericCode: Int = 0
  var symbol: String = null
  var fractionSymbol: String = null
  var fractionPerUnit: Int = 0
  var rounding: Rounding = null
  var formatString: String = null
  var triangulationCurrency: Currency = null


  def this (name: String,
            code: String,
            numericCode: Int,
            symbol: String,
            fractionSymbol: String,
            fractionPerUnit: Int,
            rounding: Rounding,
            formatString: String,
            triangulationCurrency: Currency) {

    this
    this.name = name
    this.code = code
    this.numericCode = numericCode
    this.symbol = symbol
    this.fractionSymbol = fractionSymbol
    this.fractionPerUnit = fractionPerUnit
    this.rounding = rounding
    this.formatString = formatString
    this.triangulationCurrency = triangulationCurrency

  }

  def this (name: String,
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



  override def clone: Data = {
    Data(name, code, numericCode, symbol, fractionSymbol, fractionPerUnit, rounding,
      formatString, triangulationCurrency.clone)
  }


}

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


  def == (c1: Currency, c2: Currency) : Boolean = {
    c1.equals(c2)
  }

  def != (c1: Currency, c2: Currency) : Boolean = {
    Currency != (c1, c2)
  }

}

/**
 * @author Paul Bernard
 */
class Currency {

  var data : Data = new Data()

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
  def triangulationCurrency : Currency = data.triangulationCurrency
  def == (currency: Currency) : Boolean = equals(currency)
  def != (currency: Currency) : Boolean = !eq(currency)


  def empty : Boolean = data == null

  override def toString : String = {

    if (!empty) code else "(null currency"

  }

  override def equals(obj: Any): Boolean = {
    if (this == obj) true
    else if (obj == null) false
    else obj.isInstanceOf[Currency] && obj.asInstanceOf[Currency].fEquals(this)

  }

  override def hashCode: Int = {
    val prime : Int = 31
    var result: Int = 1
    result = prime * result + (if (data == null) 0 else data.hashCode)
    result = prime * result + (if (data ==null) 0 else name.hashCode)
    result
  }

  def fEquals(other: Currency): Boolean = {
    if (this.empty && other.empty) true
    else if (this.name.equals(other.name)) true else false
  }

  override def clone : Currency = {
    val currency  = new Currency
    if (data != null) currency.data = data.clone()
    currency
  }



}

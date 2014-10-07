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

import org.quantintel.ql.math.Closeness

import scala.math._



object ConversionTypeEnum extends Enumeration {

  type ConversionTypeEnum = Value
  val NO_CONVERSION = Value(1)
  val BASE_CURRENCY_CONVERSION = Value(2)
  val AUTOMATED_CONVERSION = Value(3)

  def valueOf(currencies: Int)  = currencies match {
    case 1 => NO_CONVERSION
    case 2 => BASE_CURRENCY_CONVERSION
    case 3 => AUTOMATED_CONVERSION
    case _ => throw new Exception("Valid units = 1 or 3")
  }

}

object Money {

  def apply (currency: Currency, value: Double) : Money = {
    new Money(currency, value)
  }

  def apply (value: Double, currency: Currency) : Money = {
    new Money(currency, value)
  }
}

/**
 * @author Paul Bernard
 */
class Money {

  import org.quantintel.ql.currencies.ConversionTypeEnum._

  var conversionType : ConversionTypeEnum = null
  var baseCurrency : Currency = null

  private var m_value : Double = 0.0
  private var m_currency: Currency = null

  def this (currency: Currency, value: Double) {
    this
    m_value = value
    m_currency = currency
  }

  def this (value: Double, currency: Currency) {
    this
    m_value = value
    m_currency = currency
  }

  override def clone : Money = {
    new Money(m_value, m_currency)
  }

  def currency : Currency = m_currency

  def value: Double = m_value

  def rounded : Money  = {
    new Money(m_currency.rounding.round(m_value), m_currency)
  }



  def pos : Money = {
    Money(m_currency, abs(m_value))
  }

  def neg : Money = {
    Money(m_value * -1, m_currency)
  }
  def *= (x: Double) : Money = {
    m_value = m_value * x
    this
  }

  def /= (x: Double): Money = {
    m_value = m_value / x
    this
  }

  def + (money: Money) : Money = {
    var tmp : Money = this.clone()
    tmp += (money)
    tmp
  }

  def += (money: Money) : Money = {

    if (m_currency == Money) {
      m_value += money.value
    } else if (conversionType == BASE_CURRENCY_CONVERSION) {
      convertToBase
      var temp : Money = money.clone
      temp.convertToBase
      this += temp
    } else if (conversionType == AUTOMATED_CONVERSION){
      var temp : Money = money.clone
      temp.convertTo(m_currency)
      this += temp
    } else {
      throw new Exception("currency mismatch and no conversion specified")
    }
    this

  }


  def - (money: Money): Money = {
    var temp : Money = this.clone()
    temp -= (money)
    temp
  }

  def -= (money: Money) : Money = {
    if (m_currency == money.currency){
      m_value -= money.value
    } else if (conversionType == BASE_CURRENCY_CONVERSION){
      convertToBase
      var temp : Money = money.clone
      temp.convertToBase
    } else if (conversionType == AUTOMATED_CONVERSION){
      var temp : Money = money.clone
      temp.convertTo(m_currency)
      this -= temp
    } else {
      throw new Exception("currency mismatch and no conversion specified")
    }
    this


  }

  def * (x: Double) :Money = {
    var temp : Money = this.clone()
    temp *= x
    temp
  }

  def * (value: Double, c: Currency) : Money = { null }
  def * (c: Currency, value: Double) : Money = { null }

  def / (x : Double) : Money = {
    var temp : Money = this.clone()
    temp /= x
    temp
  }

  def / (money: Money) : Double = {
      if (m_currency == money.currency){
        m_value / money.value
      } else if (conversionType == BASE_CURRENCY_CONVERSION) {
        var temp1 : Money = clone
        temp1.convertToBase
        var temp2 : Money = money.clone
        temp2.convertToBase
        this / temp2
      } else if (conversionType == AUTOMATED_CONVERSION){
        var temp : Money = money.clone
        temp.convertTo(money.currency)
        this / temp
      } else {
        throw new Exception("currency mismatch and no conversion specified")
      }

  }

  def == (money: Money) : Boolean = {
      if (currency == money.currency) {
        m_value == money.value
      } else if (conversionType == BASE_CURRENCY_CONVERSION){
        val temp1: Money = clone
        temp1.convertToBase
        val temp2 : Money = money.clone
        temp2.convertToBase
        temp1 == temp2
      } else if (conversionType == AUTOMATED_CONVERSION){
        val temp : Money = money.clone
        temp.convertTo(currency)
        this == temp
      } else {
        throw new Exception("currency mismatch and no conversion specified")
      }


  }

  def != (money: Money) : Boolean = {
    !this.equals(money)
  }

  def > (money: Money) : Boolean = {
    money > this
  }

  def >= (money: Money) : Boolean = {
    money >= this
  }

  def < (money: Money) : Boolean = {
    if (currency == money.currency){
      m_value < money.value
    } else if (conversionType == BASE_CURRENCY_CONVERSION){
      val temp1 : Money = clone
      temp1.convertToBase
      val temp2 : Money = money
      temp2.convertToBase
      temp1 < temp2
    } else if (conversionType == AUTOMATED_CONVERSION){
      val temp : Money = money
      temp.convertTo(currency)
      this < temp
    } else {
      throw new Exception("currency mismatch and no conversion specified")
    }
  }

  def <= (money: Money) : Boolean = {
    if (m_currency == money.currency){
      value <= money.value
    } else if (conversionType == BASE_CURRENCY_CONVERSION){
      val temp1 : Money = clone
      temp1.convertToBase
      val temp2 : Money = money
      temp2.convertToBase
      temp1 < temp2
    } else if (conversionType == AUTOMATED_CONVERSION) {
      val temp : Money = money.clone
      temp.convertTo(currency);
      this < temp
    } else
      throw new Exception("currency mismatch and no conversion specified");
  }

  def close (money: Money, n: Int): Boolean = {
    if (currency == money.currency)
      return Closeness.isClose(m_value, money.value, n);
    else if (conversionType == BASE_CURRENCY_CONVERSION) {
      val temp1: Money = clone
      temp1.convertToBase
      val temp2 : Money = money.clone
      temp2.convertToBase
      temp1.close(temp2, n);
    } else if (conversionType == AUTOMATED_CONVERSION) {
      val temp : Money = money.clone
      temp.convertTo(currency)
      return this.close(temp, n);
    } else
      throw new Exception("currency mismatch and no conversion specified");


  }
  def closeEnough(money: Money, n: Int) : Boolean = {
    if (m_currency == money.currency)
      Closeness.isCloseEnough(m_value, money.value, n);
    else if (conversionType == BASE_CURRENCY_CONVERSION) {
      val temp1 : Money = clone
      temp1.convertToBase
      val temp2: Money = money;
      temp2.convertToBase
      temp1.closeEnough(temp1, n);
    } else if (conversionType == AUTOMATED_CONVERSION) {
      val temp: Money = money;
      temp.convertTo(currency);
      closeEnough(temp, n);
    } else
      throw new Exception("currency mismatch and no conversion specified")
  }


  def convertToBase {
    convertTo(baseCurrency)
  }

  def convertTo(target: Currency) {
    if(currency != target){
      val rate : ExchangeRate = ExchangeRateManager.lookup(currency, target)
      val money: Money = rate.exchange(this).rounded
      m_currency = money.currency
      m_value = money.value
    }

  }

  override def toString = {
    val currency: Currency = m_currency
    val args :Array[Object] = Array(rounded.value.asInstanceOf[Object],
        currency.code.asInstanceOf[Object], currency.symbol.asInstanceOf[Object])

    java.lang.String.format(currency.formatString, args:_*)
  }



}

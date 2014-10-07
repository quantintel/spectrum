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


object RateTypeEnum extends Enumeration {

  type RateTypeEnum = Value
  val DIRECT= Value(1)
  val DERIVED = Value(2)

  def valueOf(currencies: Int)  = currencies match {
    case 1 => DIRECT
    case 2 => DERIVED
    case _ => throw new Exception("Valid units = 1 or 2")
  }

}

object ExchangeRate {

  def chain (r1: ExchangeRate, r2: ExchangeRate) : ExchangeRate = {
    null
  }

  def apply (cur1: Currency, cur2: Currency, num: Double) : ExchangeRate = {
      new ExchangeRate(cur1, cur2, num)
    }

}

/**
 * @author Paul Bernard
 */
class ExchangeRate {

  import org.quantintel.ql.currencies.RateTypeEnum._

  private var m_source :Currency = null
  private var m_target : Currency = null
  private var m_rate : Double = 0.0
  private var m_rateType : RateTypeEnum = null
  var rateChain: (ExchangeRate, ExchangeRate) = null

  def this(toCopy: ExchangeRate) {
    this
    m_source = toCopy.source
    m_target = toCopy.target
    m_rate = toCopy.rate
    m_rateType = toCopy.rateType
    this.rateChain = toCopy.rateChain
  }

  def this(source: Currency, target: Currency, rate: Double){
    this
    m_source = source
    m_target = target
    m_rate = rate
    m_rateType = DIRECT
  }



  def source : Currency = this.m_source

  def target : Currency = this.m_target

  def rateType : RateTypeEnum  = this.m_rateType

  def rate : Double = this.m_rate


  def exchange(amount: Money) : Money = {
    null
  }




}

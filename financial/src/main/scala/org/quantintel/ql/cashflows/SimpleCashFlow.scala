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

package org.quantintel.ql.cashflows

import org.quantintel.ql.time.Date

/**
 * @author Paul Bernard
 */
class SimpleCashFlow extends CashFlow {

  private var pDate: Date = null
  private var amt : Double = 0.00

  def date: Date = pDate.clone
  def date(d: Date) { pDate = d }


  def amount() : Double = amt
  def amount(d: Double)  { amt = d }


  def this(amount: Double, paymentDate: Date) {
    this
    pDate = paymentDate
    amt= amount
  }



}

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
import org.quantintel.ql.time.daycounters.DayCounter
import org.quantintel.ql.util.{Visitor, PolymorphicVisitor}

/**
 * @author Paul Bernard
 */
abstract class Coupon(var nominal: Double,
        var paymentDate: Date,
        var accrualStartDate: Date,
        var accrualEndDate: Date,
        var refPeriodStart: Date,
        var refPeriodEnd : Date) extends CashFlow {

  def this(nominal: Double,
            paymentDate: Date,
            accrualStartDate: Date,
            accrualEndDate: Date) {
    this(nominal, paymentDate, accrualStartDate, accrualEndDate, Date(), Date())
  }

  def rate() : Double

  def dayCounter() : DayCounter

  def accruedAmount(date: Date): Double

  def accrualPeriod: Double = dayCounter().yearFraction(accrualStartDate, accrualEndDate,
            refPeriodStart, refPeriodEnd)

  def accrualDays() : Long = dayCounter().dayCount(accrualStartDate, accrualEndDate)


  override def date() : Date = paymentDate.clone()

  def accept(pv: PolymorphicVisitor): Unit = {
   // val v : Visitor[Coupon] = if (pv!=null) pv.visitor(this.getClass)) else null
   // if (v != null) v.visitor(this) else super.accept(pv)
  }

}

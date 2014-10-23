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

package org.quantintel.ql.instruments.bonds


import org.quantintel.ql.time.BusinessDayConvention._
import org.quantintel.ql.time.{Date, Calendar}

/**
 * @author Paul Bernard
 */
class ZeroCouponBond(settlementDays: Int,
                     calendar: Calendar,
                     faceAmount: Double,
                     maturityDate: Date,
                     paymentConvention: BusinessDayConventionEnum,
                     redemption: Double,
                     issueDate: Date)
                      extends Bond (settlementDays, calendar, issueDate) {

  var m_maturityDate = maturityDate.clone
  var redemptionDate = calendar.adjust(maturityDate, paymentConvention)
  setSingleRedemption(faceAmount, redemption, redemptionDate)

  def this(settlementDays: Int,
           calendar: Calendar,
           faceAmount: Double,
           maturityDate: Date) {
    this(settlementDays, calendar, faceAmount, maturityDate, FOLLOWING, 100.0, new Date)
  }

  def this(settlementDays: Int,
           calendar: Calendar,
           faceAmount: Double,
           maturityDate: Date,
           paymentConvention: BusinessDayConventionEnum,
           redemption: Double) {
    this(settlementDays, calendar, faceAmount, maturityDate, paymentConvention, redemption, new Date)
  }

  def this(settlementDays: Int,
            calendar: Calendar,
            faceAmount: Double,
            maturityDate: Date,
            paymentConvention: BusinessDayConventionEnum){
    this(settlementDays, calendar, faceAmount, maturityDate, paymentConvention, 100.0, new Date)
  }

}

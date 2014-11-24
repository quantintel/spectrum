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

package org.quantintel.ql.indexes.ibor

import org.quantintel.ql.currencies.Currency
import org.quantintel.ql.indexes.InterestRateIndex
import org.quantintel.ql.quotes.Handle
import org.quantintel.ql.termstructures.YieldTermStructure
import org.quantintel.ql.time.BusinessDayConvention.BusinessDayConventionEnum
import org.quantintel.ql.time.daycounters.DayCounter
import org.quantintel.ql.time.{Calendar, Period, Date}

/**
 * @author Paul Bernard
 */
class IborIndex  extends InterestRateIndex {

  private var mTermStructure : Handle[YieldTermStructure] = null
  private var endOfMonth : Boolean = false
  private var convention : BusinessDayConventionEnum = null


  def this(familyName: String, tenor: Period, fixingDays: Int, currency: Currency,
    fixingCalendar: Calendar, convention: BusinessDayConventionEnum,
    endOfMonth: Boolean, dayCounter: DayCounter, h: Handle[YieldTermStructure]){
      this()
      this.convention = convention
      this.mTermStructure = h
      this.endOfMonth = endOfMonth
      if(mTermStructure !=null) {
        termStructure().addObserver(this)
      }
  }

  def this(familyName: String, tenor: Period, fixingDays: Int, currency: Currency,
           fixingCalendar: Calendar, convention: BusinessDayConventionEnum,
           endOfMonth: Boolean, dayCounter: DayCounter){

    this(familyName, tenor, fixingDays, currency, fixingCalendar,
        convention, endOfMonth, dayCounter, new Handle[YieldTermStructure]())
  }

  def clone(h: Handle[YieldTermStructure]) : Handle[IborIndex] = {

    new Handle[IborIndex](IborIndex(familyName, tenor, fixingDays, currency, fixingCalendar(),
      convention, endOfMonth, dayCounter, h))

  }


  override protected def forecastFixing(fixingDate: Date): Double = {
    val fixingValueDate: Date = valueDate(fixingDate)
    val endValueDate : Date = maturityDate(fixingValueDate)
    val fixingDiscount : Double = termStructure().currentLink.discount(fixingValueDate)
    val endDiscount: Double = termStructure().currentLink.discount(endValueDate)

    val fixingPeriod : Double = dayCounter.yearFraction(fixingValueDate, endValueDate)
    (fixingDiscount / endDiscount - 1.0) / fixingPeriod
  }

  override def maturityDate(valueDate: Date): Date = fixingCalendar().advance(valueDate, tenor, convention, endOfMonth)

  override def termStructure() : Handle[YieldTermStructure] = mTermStructure


  def businessDayConvention() : BusinessDayConventionEnum = {
    convention
  }
}

object IborIndex {

  def apply(familyName: String, tenor: Period, fixingDays: Int, currency: Currency,
            fixingCalendar: Calendar, convention: BusinessDayConventionEnum,
            endOfMonth: Boolean, dayCounter: DayCounter, h: Handle[YieldTermStructure])
        : IborIndex  = {

    new IborIndex(familyName, tenor, fixingDays, currency, fixingCalendar,
      convention, endOfMonth, dayCounter, h)
  }

}

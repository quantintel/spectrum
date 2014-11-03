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

package org.quantintel.ql.termstructures

import org.quantintel.ql.quotes.Handle
import org.quantintel.ql.time.Frequency.Frequency
import org.quantintel.ql.time.{Calendar, Date, Period}
import org.quantintel.ql.time.daycounters.{Actual365, DayCounter}
import org.quantintel.ql.time.daycounters.Actual365Convention.ACT365F

/**
 * @author Paul Bernard
 */
abstract class InflationTermStructure extends AbstractTermStructure {

  protected var nominalTermStructure : Handle[YieldTermStructure]
  protected var lag: Period
  protected var frequency: Frequency
  protected var baseRate: Double

  def this(lag: Period,
            frequency: Frequency,
            baseRate: Double,
            yTS: Handle[YieldTermStructure]){
    this()
    this.lag = lag
    this.frequency = frequency
    this.baseRate = baseRate
    this.nominalTermStructure= yTS
    this.dayCounter = Actual365(ACT365F)

  }


  def this(lag: Period,
           frequency: Frequency,
           baseRate: Double,
           yTS: Handle[YieldTermStructure],
           dayCounter: DayCounter){

    this()
    this.dayCounter = dayCounter
    this.nominalTermStructure = yTS
    this.lag = lag
    this.frequency = frequency
    this.baseRate = baseRate

    this.nominalTermStructure.addObserver(this)
  }


  def this(referenceDate: Date,
           lag: Period,
           frequency: Frequency,
           baseRate: Double,
           yTS: Handle[YieldTermStructure],
           calendar: Calendar,
           dayCounter: DayCounter) {
    this()
    this.m_referenceDate = referenceDate
    this.calendar = calendar
    this.dayCounter = dayCounter
    this.nominalTermStructure = yTS
    this.lag = lag
    this.frequency = frequency
    this.baseRate = baseRate
    this.nominalTermStructure.addObserver(this)

  }

  def this(settlementDays: Int,
            calendar: Calendar,
            lag: Period,
            frequency: Frequency,
            baseRate: Double,
            yTS: Handle[YieldTermStructure]){
    this()
    this.settlementDays = settlementDays
    this.calendar = calendar
    this.lag = lag
    this.frequency = frequency
    this.baseRate = baseRate
    this.nominalTermStructure = yTS
    this.dayCounter = Actual365(ACT365F)
  }


}

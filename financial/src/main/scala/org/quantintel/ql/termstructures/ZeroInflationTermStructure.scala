package org.quantintel.ql.termstructures

import org.quantintel.ql.quotes.Handle
import org.quantintel.ql.time.Frequency.Frequency
import org.quantintel.ql.time.{Calendar, Date, Period}
import org.quantintel.ql.time.daycounters.DayCounter

/**
 * @author Paul Bernard
 */
abstract class ZeroInflationTermStructure extends InflationTermStructure {

  def this(dayCounter: DayCounter,
           lag: Period,
           frequency: Frequency,
           baseZeroRate: Double,
           yTS: Handle[YieldTermStructure]){
    this()
    this.lag = lag
    this.frequency = frequency
    this.baseRate = baseZeroRate
    this.nominalTermStructure = yTS
    this.dayCounter = dayCounter
  }


  def this(referenceDate: Date,
            calendar: Calendar,
            dayCounter: DayCounter,
            lag: Period,
            frequency: Frequency,
            baseZeroRate: Double,
            yTS: Handle[YieldTermStructure]) {
    this()
    this.m_referenceDate = referenceDate
    this.lag = lag
    this.frequency = frequency
    this.baseRate = baseZeroRate
    this.nominalTermStructure = yTS
    this.dayCounter = dayCounter

  }

  def this(settlementDays: Int,
            calendar: Calendar,
            dayCounter: DayCounter,
            lag: Period,
            frequency: Frequency,
            baseZeroRate: Double,
            yTS: Handle[YieldTermStructure]) {
    this()
    this.settlementDays = settlementDays
    this.calendar = calendar
    this.lag = lag
    this.frequency = frequency
    this.baseRate = baseZeroRate
    this.nominalTermStructure = yTS
    this.dayCounter = dayCounter
  }


}

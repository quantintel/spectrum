package org.quantintel.ql.termstructures

import org.quantintel.ql.quotes.Handle
import org.quantintel.ql.time.Frequency.Frequency
import org.quantintel.ql.time.{Calendar, Date, Period}
import org.quantintel.ql.time.daycounters.DayCounter

/**
 * @author Paul Bernard
 */
abstract class ZeroInflationTermStructure(dayCounter: DayCounter,
                                 lag: Period,
                                 frequency: Frequency,
                                 baseZeroRate: Double,
                                  yTS: Handle[YieldTermStructure])
  extends InflationTermStructure(lag,
                                frequency,
                                baseZeroRate,
                                yTS,
                                dayCounter) {

  def this(referenceDate: Date,
            calendar: Calendar,
            dayCounter: DayCounter,
            lag: Period,
            frequency: Frequency,
            baseZeroRate: Double,
            yTS: Handle[YieldTermStructure]) {

    this(referenceDate, lag, frequency, baseZeroRate, yTS, calendar, dayCounter)
  }

  def this(settlementDays: Int,
            calendar: Calendar,
            dayCounter: DayCounter,
            lag: Period,
            frequency: Frequency,
            baseZeroRate: Double,
            yTS: Handle[YieldTermStructure]) {
    this(settlementDays, calendar, lag, frequency, baseZeroRate, yTS, dayCounter)
  }


}

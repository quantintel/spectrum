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

import org.quantintel.ql.termstructures.Compounding.Compounding
import org.quantintel.ql.time.{Period, Date}
import org.quantintel.ql.time.Frequency.Frequency
import org.quantintel.ql.time.daycounters.DayCounter

/**
 * @author Paul Bernard
 */
abstract class YieldTermStructure extends TermStructure {

  /**
   * Zero yield rates
   *
   * @param d date
   * @param resultDayCounter The resulting interest rate has the required day counting rule
   * @param comp Compounding type
   * @return the implied zero yield rate for a given date or time.  IN the former case, the time is calculated
   *         as a fraction of a year from the reference date.
   */
  def zeroRate(d: Date, resultDayCounter: DayCounter, comp: Compounding) : InterestRate

  /**
   * Zero yield rates
   *
   * @param d date
   * @param dayCounter Day count method
   * @param comp Compounding date
   * @param freq Frequency
   * @return the implied zero yield rate for a given date or time.  IN the former case, the time is calculated
   *         as a fraction of a year from the reference date.
   */
  def zeroRate(d: Date, dayCounter: DayCounter, comp: Compounding, freq: Frequency) : InterestRate


  /**
   * The resulting interest rate has the same day counting rule used by the term
   * structure.  The same rule should be used for calculating the passed double t.
   * @param d
   * @param dayCounter
   * @param comp
   * @param freq
   * @param extrapolate
   * @return the implied zero yield rate for a given date or time.  In the former case, the
   *         time is calculated as a fraction of a year from the reference date.
   */
  def zeroRate(d: Date, dayCounter: DayCounter, comp: Compounding, freq: Frequency,
               extrapolate: Boolean) : InterestRate

  /**
   * Zero yield rates.
   *
   * @param time
   * @param comp
   * @param freq
   * @param extrapolate The resulting interest rate has the same day counting rule used by the
   *                    term structure.  The same rule should be used for calulating the passed
   *                    double t.
   * @return
   */
  def zeroRate(time: Double, comp: Compounding, freq: Frequency, extrapolate: Boolean): InterestRate

  /**
   * Forward Rates
   * Dates are not adjusted for holidays
   * @param d1
   * @param d2
   * @param resultDayCounter
   * @param comp
   * @return the implied forward rate between two dates or times.  In the former case, times are
   *         calculated as fractions of year from the reference date.  The resulting rate has the required
   *         day counting rule.
   */
  def forwardRate(d1: Date, d2: Date, resultDayCounter: DayCounter, comp: Compounding): InterestRate

  /**
   * Forward Rate
   *
   * @param d1 start date
   * @param d2 end date
   * @param resultDayCounter  Day count rule
   * @param comp Compounding method
   * @param freq Frequency type
   * @return The implied forward interest rate between two dates or times.  In the former case, times
   *         are calculated as fractions of year from the reference date.  The resulting interest rate
   *         has the required day counting rule.
   */
  def forwardRate(d1: Date, d2: Date, resultDayCounter: DayCounter, comp: Compounding, freq: Frequency): InterestRate


  def forwardRate(d1: Date, d2: Date, resultDayCounter: DayCounter, comp: Compounding, freq: Frequency,
                  extrapolate: Boolean) : InterestRate

  def forwardRate(t1: Double, t2: Double, comp: Compounding): InterestRate

  def forwardRate(t1: Double, t2: Double, comp: Compounding, freq: Frequency): InterestRate

  def forwardRate(t1: Double, t2: Double, comp: Compounding, freq: Frequency, extrapolate: Boolean): InterestRate

  def forwardRate(d: Date, p: Period, resultDayCounter: DayCounter, comp: Compounding, freq: Frequency) : InterestRate

  def forwardRate(d: Date, p: Period, resultDayCounter: DayCounter, comp: Compounding, freq: Frequency,
                   extrapolate: Boolean): InterestRate

  def discount(d: Date) : Double

  def discount(d: Date, extrapolate: Boolean): Double

  def discount(t: Double): Double

  def discount(t: Double, extrapolate: Double): Double

  def parRate(tenor: Int, startDate: Date, freq: Frequency, extrapolate: Boolean) : Double

  def parRate(dates: Array[Date], freq: Frequency, extrapolate: Boolean) : Double

  def parRate(times: Array[Double], frequency: Frequency, extrapolate: Boolean): Double





}

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

import org.quantintel.ql.time.{Frequency, Date}
import org.quantintel.ql.time.daycounters.DayCounter
import org.quantintel.ql.termstructures.Compounding._
import org.quantintel.ql.time.Frequency._

/**
 * Encapsulates interest rate compounding algebra.  Invoke day count convention,
 * compounding conventions, conversions between different conventions, discount &
 * compound factor calculations, and implied and/or equivalent rate calculations.
 * @author Paul Bernard
 */
class InterestRate(var rate: Double) {

  var dc: DayCounter = null
  var compound: Compounding = null
  var freqMakesSense : Boolean = false
  var freq: Int = 0

  /**
   * The default constructor returns a 0.0 interest rate
   */
  def this () {
    this(0.0)
  }

  /**
   * Constructor
   *
   * @param r the rate
   * @param dc the DayCounter
   * @param comp the Compounding method
   * @param freq the Frequency
   */
  def this(r: Double, dc: DayCounter, comp: Compounding, freq: Frequency) {
    this(r)
    this.dc = dc
    this.compound = comp
    this.freqMakesSense = false

    if (this.compound == COMPOUNDED || this.compound == SIMPLE_THEN_COMPOUNDED) {
      freqMakesSense = true
      this.freq = freq.id
    }


  }

  def this(r: Double, dc: DayCounter, comp: Compounding) {
    this(r, dc, comp, ANNUAL)
  }

  def this(r: Double, dc: DayCounter) {
    this(r, dc, CONTINUOUS)
  }


  /**
   *
   * @param time Time must be measured using the InterestRate's own day counter
   * @return the compound (also known as: capitalization) factor implied by the rate compound at time t.
   */
  def compoundFactor(time: Double) : Double = {

    val t: Double = time
    val r: Double = rate

    if(compound == SIMPLE){
      1.0 + r * t
    } else if (compound == COMPOUNDED){
      math.pow(1 + r /freq, freq * t)
    } else if (compound == CONTINUOUS){
      math.exp(r * t)
    } else if (compound == SIMPLE_THEN_COMPOUNDED){
      if (t < 1 / freq.asInstanceOf[Double]){
        1.0 + r * t
      } else {
        math.pow(1 + r / freq, freq * t)
      }
    }

    else throw new Exception("unknown compounding convention")

  }

  def compoundFactor(d1: Date, d2: Date) : Double = {
    compoundFactor(d1, d2, new Date, new Date)
  }

  def compoundFactor(d1: Date, d2: Date, refStart: Date, refEnd: Date)  : Double = {
    val t: Double = dc.yearFraction(d1, d2, refStart, refEnd)
    compoundFactor(t)
  }

  def dayCounter : DayCounter = this.dc

  def compounding: Compounding  = this.compound

  def frequency: Frequency = if (freqMakesSense) Frequency.valueOf(this.freq) else NO_FREQUENCY

  /**
   *
   * @param t time must be measured using the InterestRate's own day Calendar
   * @return discount factor implied by the reate compounded at time t
   */
  def discountFactor (t: Double) : Double = {
    val factor: Double = compoundFactor(t)
    1.0d / factor
  }

  /**
   * discount and or compound factor calculations
   *
   * @param d1 start date
   * @param d2 end date
   * @return discount factor implied by the reate compounded between two dates
   */
  def discountFactor (d1: Date, d2: Date) : Double = discountFactor(d1, d2, new Date())

  /**
   * discount and or compound factor calculations
   *
   * @param d1 start date
   * @param d2 end date
   * @param refStart reference start date
   * @return discount factor
   */
  def discountFactor (d1: Date, d2: Date, refStart: Date) : Double = {
    discountFactor(d1, d2, refStart, new Date())
  }

  def discountFactor(d1: Date, d2: Date, refStart: Date, refEnd: Date) : Double = {
    val t: Double = this.dc.yearFraction(d1, d2, refStart, refEnd)
    discountFactor(t)
  }

  def equivalentRate(t: Double, comp: Compounding): InterestRate = {
    equivalentRate(t, comp, ANNUAL)
  }

  def equivalentRate(t: Double, comp: Compounding, freq: Frequency) : InterestRate = {
    InterestRate.impliedRate(compoundFactor(t), t, this.dc, comp, freq)
  }

  def equivalentRate(d1: Date, d2: Date, resultDC: DayCounter, comp: Compounding) : InterestRate = {
    equivalentRate(d1, d2, resultDC, comp, ANNUAL)
  }

  def equivalentRate(d1: Date, d2: Date, resultDC: DayCounter, comp: Compounding, freq: Frequency) : InterestRate = {
    val t1: Double = this.dc.yearFraction(d1, d2)
    val t2: Double = resultDC.yearFraction(d1, d2)
    InterestRate.impliedRate(compoundFactor(t1), t2, resultDC, comp, freq)
  }


  override def toString  : String = {
    if (rate == 0.0) return "null interest rate"

    val sb : java.lang.StringBuilder = new java.lang.StringBuilder()
    sb.append(rate).append(" ").append(dc).append(" ")
    if (compound == SIMPLE) {
      sb.append("simple compounding")
    } else if (compound == COMPOUNDED) {
      if((freq == NO_FREQUENCY.id) || freq == ONCE.id) {
        throw new Exception(" frequency not allowed for this interest rate")
      } else {
          sb.append(freq + " compounding")
      }
    } else if (compound == CONTINUOUS) {
        sb.append("continuous compounding")
    } else if (compound == SIMPLE_THEN_COMPOUNDED) {
        if ((freq == NO_FREQUENCY.id) || (freq == ONCE.id)) {
          throw new Exception(freq + " frequency not allowed for this interest rate")
        } else {
          sb.append("simple compounding up to " + (12 / freq) + " months, then " + freq + " compounding")
        }
    } else {
      throw new Exception("unknown compounding convention")
    }

    sb.toString

  }



}

object InterestRate {

  def impliedRate(c: Double, time: Double, resultDC: DayCounter, comp: Compounding, freq: Frequency) : InterestRate = {

    val t : Double = time
    val f : Double = freq.asInstanceOf[Int]
    var rate : Double = 0.0

    comp match {
      case SIMPLE => rate = (c -1) / t
      case COMPOUNDED => rate = (math.pow(c, 1 / (f * t)) -1 ) * f
      case CONTINUOUS => math.log(c) / t
      case SIMPLE_THEN_COMPOUNDED => if (t < (1 / f)) {
          rate = (c -1) /t
        } else {
          rate = (math.pow(c, 1 / (f * t)) - 1) * f
        }

      case _ => throw new Exception("unknown compounding convention")
    }
    new InterestRate(rate, resultDC, comp, freq)
  }

  def impliedRate(compound: Double , t: Double, resultDC: DayCounter, comp: Compounding) : InterestRate = {
    impliedRate(compound, t, resultDC, comp, ANNUAL)
  }

  def impliedRate(compound: Double, d1: Date, d2: Date, resultDC: DayCounter, comp: Compounding) : InterestRate = {
    impliedRate(compound, d1, d2, resultDC, comp, ANNUAL)
  }

  def impliedRate(compound: Double, d1: Date, d2: Date, resultDC: DayCounter, comp: Compounding, freq: Frequency) :
    InterestRate = {
    val t: Double = resultDC.yearFraction(d1, d2)
    impliedRate(compound, t, resultDC, comp, freq)
  }





}

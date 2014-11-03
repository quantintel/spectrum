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

import org.quantintel.ql.Settings
import org.quantintel.ql.math.interpolations.{DefaultExtraploator, Extrapolator}
import org.quantintel.ql.time.{Calendar, Date}
import org.quantintel.ql.time.daycounters.{Actual365, DayCounter}
import org.quantintel.ql.time.daycounters.Actual365Convention.ACT365F
import org.quantintel.ql.time.TimeUnit.DAYS

/**
 * @author Paul Bernard
 */
abstract class AbstractTermStructure extends TermStructure with Extrapolator  {

  var dayCounter : DayCounter = null
  var updated : Boolean = true
  var settlementDays : Int = 0
  var moving : Boolean = false
  var m_referenceDate: Date = null
  var calendar: Calendar = null


  protected def this(dc: DayCounter){
    this()
    this.dayCounter = dc
    this.calendar = null
    this.settlementDays = 0
    this.moving = false
    this.updated = true
    this.m_referenceDate = null

  }

  protected def this(referenceDate: Date, calendar: Calendar, dc: DayCounter) {

    this()
    this.settlementDays = 0
    this.calendar = calendar
    this.dayCounter = dc
    this.moving = false
    this.updated = true
    this.m_referenceDate = referenceDate

  }

  def this(settlememntDays: Int, calendar: Calendar, dc: DayCounter){
    this()
    this.settlementDays = settlementDays
    this.calendar = calendar
    this.dayCounter = dc

    this.moving = true
    this.updated = false

    val today : Date = new Settings().evaluationDate
    today.addObserver(this)
    this.m_referenceDate = calendar.advance(today, settlementDays, DAYS)
  }


  protected def this(settlementDays: Int, calendar: Calendar){
    this(settlementDays, calendar, Actual365(ACT365F))
  }

  override def timeFromReference(date: Date): Double = {
    dayCounter.yearFraction(referenceDate, date)
  }

  override def maxTime() : Double = timeFromReference(maxDate())

  override def referenceDate() : Date = {
    if (!this.updated){
      val today: Date = new Settings().evaluationDate
      m_referenceDate = calendar.advance(today, settlementDays, DAYS)
    }
    m_referenceDate
  }

  override def update() {
    if(this.moving){
      updated = false
    }
    notifyObservers()
  }


  private val delegatedExtrapolator : DefaultExtraploator = new DefaultExtraploator()

  override def allowsExtrapolation(): Boolean = {
    delegatedExtrapolator.allowsExtrapolation()
  }


  override def disableExtrapolation(): Unit = {
    delegatedExtrapolator.disableExtrapolation()
  }

  override def enableExtrapolation(): Unit = {
    delegatedExtrapolator.enableExtrapolation()
  }
}

object AbstractTermStructure {

 val THIS_METHOD_MUST_BE_OVERRIDDEN = "This method must be overridden"
}

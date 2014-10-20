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

package org.quantintel.ql.indexes

import org.quantintel.ql.currencies.Currency
import org.quantintel.ql.quotes.Handle
import org.quantintel.ql.termstructures.YieldTermStructure
import org.quantintel.ql.time.daycounters.DayCounter
import org.quantintel.ql.time.{Period, Date, Calendar}
import org.quantintel.ql.time.TimeUnit._

/**
 * @author Paul Bernard
 */
abstract class InterestRateIndex (val familyName: String, val tenor: Period,
                         val fixingDays: Int, val currency: Currency,
                         val pFixingCalendar: Calendar, val dayCounter: DayCounter) extends Index {


  this.tenor.normalize

  /**
   *
   * @return name of the index
   */
  override def name(): String = {
    var fn = familyName
    if(tenor.equals(new Period(1, DAYS))){
      fixingDays match {
        case 0 => fn ++= "ON"
        case 1 => fn ++= "TN"
        case 2 => fn ++= "SN"
        case _ => fn ++= tenor.getShortFormat()
      }
    } else {
      fn ++= tenor.getShortFormat()
    }
    fn ++= " "
    fn ++= dayCounter.name
    fn
  }

  /**
   *
   * @return the calendar that defines the valid fixing dates
   */
  override def fixingCalendar(): Calendar = pFixingCalendar


  /**
   *
   * @param fixingDate the actual calendar date of the fixing(no settlement days)
   * @param forecastTodaysFixing
   * @return the fixing at the given date.
   */
  override def fixing(fixingDate: Date, forecastTodaysFixing: Boolean): Double = { 0.00 }


  /**
   *
   * @param fixingDate the fixing date to be tested
   * @return true if the fixing date is valid
   */
  override def isValidFixingDate(fixingDate: Date): Boolean = {
    fixingCalendar.isBusinessDay(fixingDate)
  }

  protected def forecastFixing(fixingDate: Date) : Double

  def termStructure() : Handle[YieldTermStructure]

  def maturityDate(valueDate: Date): Date


}

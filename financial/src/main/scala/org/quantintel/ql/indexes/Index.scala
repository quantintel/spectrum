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

import org.quantintel.ql.math.{Constants, Closeness}
import org.quantintel.ql.time.{TimeSeries, Date, Calendar}
import org.quantintel.ql.util.{Observability, Observer, DefaultObservable, Observable}

/**
 *
 * Abstract representation of a Financial Index.
 *
 * A statistic measure of change in an economy or a securities market.  In the case
 * of financial markets, and index is an imaginary portfolio of securities representing a
 * particular market or a portion of it.  Each index has its own calculation methodology
 * and is usually expressed in terms of a change from a base value.  Thus, the percentage
 * change is more important than the actual numeric value.
 * source - Investopedia 2014
 *
 *
 * @author Paul Bernard
 */
abstract class Index extends Observable with Observability {

  /**
   *
   * @return name of the index
   */
  def name() : String

  /**
   *
   * @return the calendar that defines the valid fixing dates
   */
  def fixingCalendar() : Calendar

  /**
   *
   * @param fixingDate the fixing date to be tested
   * @return true if the fixing date is valid
   */
  def isValidFixingDate(fixingDate: Date): Boolean

  /**
   *
   * @param fixingDate the actual calendar date of the fixing(no settlement days)
   * @param forecastTodaysFixing forecast todays fixing
   * @return the fixing at the given date.
   */
  def fixing (fixingDate: Date, forecastTodaysFixing: Boolean) : Double


  def timeSeries : TimeSeries[Double] = IndexManager.getHistory(name())


  def addFixing(date: Date, value: Double) {
    addFixing(date, value, forceOverwrite = false)
  }

  def addFixing(date: Date, value: Double, forceOverwrite: Boolean)  {

    val tag : String = name()
    var missingFixing: Boolean = false
    var validFixing : Boolean = false
    var noInvalidFixing: Boolean = true
    var noDuplicateFixing : Boolean = true
    val h : TimeSeries[Double] = IndexManager.getHistory(tag)

    validFixing = isValidFixingDate(date)
    val currentValue: Double = h.get(date)
    missingFixing = forceOverwrite || Closeness.isClose(currentValue, Constants.NULL_REAL)
    if (validFixing) {
      if (missingFixing) {
        h.put(date, value)
      } else if (Closeness.isClose(currentValue, value)) {
        // no op
      } else {
        noDuplicateFixing = false
      }
    } else {
      noInvalidFixing = false
    }

    IndexManager.setHistory(tag, h)

  }

  def addFixing(dates: Iterator[Date], values: Iterator[Double], forceOverwite: Boolean){

    val tag : String = name()
    var missingFixing : Boolean = false
    var validFixing : Boolean = false
    var noInvalidFixing: Boolean = true
    var noDuplicateFixing : Boolean = true
    val h : TimeSeries[Double] = IndexManager.getHistory(tag)

    for (date: Date <- dates.toIterator){
      val value : Double = values.next()
      validFixing = isValidFixingDate(date)
      val currentValue : Double = h.get(date)
      missingFixing = forceOverwite || Closeness.isClose(currentValue, Constants.NULL_REAL)
      if(validFixing){
        if(missingFixing) {
          h.put(date, value)
        } else if (Closeness.isClose(currentValue, value)){
          // no op
        } else {
          noDuplicateFixing = false
        }
      } else {
        noInvalidFixing = false
      }
    }

    IndexManager.setHistory(tag, h)

  }

  def clearFixing() {
    IndexManager.clearHistory(name())
  }

  def fixing(fixingDate: Date) : Double = fixing(fixingDate, forecastTodaysFixing = false)



}

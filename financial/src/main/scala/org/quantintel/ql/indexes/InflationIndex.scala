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

import org.quantintel.ql.time.{Calendar, Date}

/**
 * Abstract representation of an inflation adjusted Index and specialization of an index
 * An index that is adjusted based upon or reflect changes in the inflation rate.
 *
 * @author Paul Bernard
 */
abstract class InflationIndex extends Index {
  /**
   *
   * @return name of the index
   */
  override def name(): String = ???

  /**
   *
   * @return the calendar that defines the valid fixing dates
   */
  override def fixingCalendar(): Calendar = ???

  /**
   *
   * @param fixingDate the actual calendar date of the fixing(no settlement days)
   * @param forecastTodaysFixing
   * @return the fixing at the given date.
   */
  override def fixing(fixingDate: Date, forecastTodaysFixing: Boolean): Double = ???

  /**
   *
   * @param fixingDate the fixing date to be tested
   * @return true if the fixing date is valid
   */
  override def isValidFixingDate(fixingDate: Date): Boolean = ???
}

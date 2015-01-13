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

package org.quantintel.ql.time.daycounters

import org.quantintel.ql.time.Date

/** Abstract superclass implementation for a day counter. The purpose of a day counter is to follow
 *  a specified set of rules to calculate the number of days, or the fraction of a year, between
 *  two arbitrary dates. Optionally, some day counters will calculate based upon a secondary reference
 *  date range.
 *  
 *  Note that the details of the calculations are expected to be supplied by the implementation classes. 
 *  
 * @author Paul Bernard
 * @author Peter Mularien
 */
abstract class DayCounter {

  def name : String = "NO_IMPLEMENTATION_PROVIDED"

  /** Calculates the number of days between the two dates provided. This default implementation simply
   *  subtracts the two dates; subclasses are expected to follow specific rules according to their conventions.
   *  
   *  @param dateStart the start date of the date range, typically this date is included in the range
   *  @param dateEnd the end date of the date range, typically this date is excluded from the range
   *  @return the number of days between the given dates, may be negative if the end date is before the start date
   */
  def dayCount(dateStart: Date, dateEnd: Date) : Long = dateEnd - dateStart

  /** Calculates the fraction of a year represented by the time span between the two provided dates. No default
   *  implementation is provided by this abstract class; it is expected that subclasses will implement this method
   *  according to the rules of their conventions.
   *   
   *  @param dateStart the start date of the date range, typically this date is included in the range
   *  @param dateEnd the end date of the date range, typically this date is excluded from the range
   *  @return the fraction of a year represented by the range indicated, may be negative if the end date is before the start date
   */
  def yearFraction(dateStart: Date, dateEnd: Date) : Double =
    yearFraction(dateStart, dateEnd, Date(), Date())

  /** Calculates the fraction of a year represented by the time span between the two provided dates, relative to the
   *  given reference period. No default implementation is provided by this abstract class; it is expected that subclasses 
   *  will implement this method according to the rules of their conventions. 
   *  
   *  Note that some subclasses may not implement this method (or it may return the same result as the yearFraction method
   *  which does not take a reference period); please consult the specific implementation class for details. 
   *   
   *  @param dateStart the start date of the date range, typically this date is included in the range
   *  @param dateEnd the end date of the date range, typically this date is excluded from the range
   *  @param refPeriodStart the start date of the reference date range
   *  @param refPeriodEnd the end date of the reference date range
   *  @return the fraction of a year represented by the range indicated, may be negative if the end date is before the start date
   */
  def yearFraction(dateStart: Date, dateEnd: Date, refPeriodStart: Date, refPeriodEnd: Date)  : Double
}

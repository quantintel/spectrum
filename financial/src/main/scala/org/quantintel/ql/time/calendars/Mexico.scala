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

package org.quantintel.ql.time.calendars

object MexicoEnum extends Enumeration {

  type MexicoEnum = Value
  val BMV = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => BMV
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * Mexican calendars Holidays for the Mexican stock exchange
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Constitution Day, February 5th
 *  Birthday of Benito Juarez, March 21st
 *  Holy Thursday
 *  Good Friday
 *  Labour Day, May 1st
 *  National Day, September 16th
 *  Our Lady of Guadalupe, December 12th
 *  Christmas, December 25th
 *
 * Reference: http://www.bmv.com.mx/
 *
 * @author Paul Bernard
 */
object Mexico {

}

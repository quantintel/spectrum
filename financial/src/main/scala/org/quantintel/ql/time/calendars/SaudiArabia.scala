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

object SaudiArabiaEnum extends Enumeration {

  type SaudiArabiaEnum = Value
  val TADAWUL = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => TADAWUL
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * Saudi Arabia calendar
 *  Thursdays
 *  Fridays
 *  National Day of Saudi Arabia, September 23rd
 *
 *  Note: Other holidays for which no rule is given (data available for 2004-2005 only:)
 *
 * @author Paul Bernard
 */
object SaudiArabia {

}

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

object SingaporeEnum extends Enumeration {

  type SingaporeEnum = Value
  val SGX = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => SGX
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * Singaporean calendar
 * Holidays:
 *  Saturdays
 *  Sundays
 *  New Year's day, JANUARY 1st
 *  Good Friday
 *  Labour Day, May 1st
 *  National Day, August 9th
 *  Christmas, December 25th
 *
 * Other holidays for which no rule is given
 *    (data available for 2004-2008 only)
 *  Chinese New Year
 *  Hari Raya Haji
 *  Vesak Day
 *  Deepavali
 *  Diwali
 *  Hari Raya Puasa
 *
 * Reference: http://www.ses.com.sg
 *
 * @author Paul Bernard
 */
object Singapore  {

}

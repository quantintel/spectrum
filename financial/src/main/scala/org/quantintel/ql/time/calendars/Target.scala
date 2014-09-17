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
 * Scala Finance is based in part on:
 *        QuantLib. http://quantlib.org/
 *
 */

package org.quantintel.ql.time.calendars

object TargetEnum extends Enumeration {

  type TargetEnum = Value
  val TARGET = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => TARGET
    case _ => throw new Exception("Valid units = 1")
  }

}


/**
 *
 * TARGET calendar relative to the European Central Bank
 * This is a holiday calendar representing  the
 * Trans-european Automated Real-time Gross Express-settlement Transfer
 * system calendar.
 *
 * Saturdays
 * Sundays
 * New Year's Day, JANUARY 1st
 * Good Friday (since 2000)
 * Easter Monday (since 2000)
 * Labour Day, May 1st (since 2000)
 * Christmas, December 25th
 * Day of Goodwill, December 26th (since 2000)
 * December 31st (1998, 1999, and 2001)
 *
 * Reference: http://www.ecb.int
 *
 * @author Paul Bernard
 */
object Target  {

}

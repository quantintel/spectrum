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

object SouthAfricaEnum extends Enumeration {

  type SouthAfricaEnum = Value
  val SOUTHAFRICA = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => SOUTHAFRICA
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * South African calendar
 * Holidays:
 *  Saturdays
 *  Sundays
 *  New Year's day, January 1
 *  Human Rights Day, March 21
 *  Good Friday, Friday before Easter Monday
 *  Family Day/Easter Monday
 *  Freedom Day, April 27
 *  Worker's Day, May 1
 *  Youth Day, June 16
 *  National Women's Day, August 9
 *  Heritage Day/National Braai Day, September 24
 *  Day of Reconciliation, December 16
 *  Christmas Day, December 25
 *  Day of Goodwill, December 26
 *
 * Any holiday falling on a Sunday moves to the following Monday
 *
 * Reference: http://en.wikipedia.org/wiki/Public_holidays_in_South_Africa
 *
 * @author Paul Bernard
 */
object SouthAfrica  {

}

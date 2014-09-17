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

object HungaryEnum extends Enumeration {
  type HungaryEnum = Value
  val HUNGARY = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => HUNGARY
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * Hungarian calendar
 *
 * Holidays:
 *  Saturdays
 *  Sundays
 *  Easter Monday
 *  Whit(Pentecost) Monday
 *  New Year's Day, JANUARY 1st
 *  National Day, March 15th
 *  Labour Day, May 1st
 *  Constitution Day, August 20th
 *  Republic Day, October 23rd
 *  All Saints Day, November 1st
 *  Christmas, December 25th
 *  2nd Day of Christmas, December 26th
 *
 * @author Paul Bernard
 */
object Hungary {

}

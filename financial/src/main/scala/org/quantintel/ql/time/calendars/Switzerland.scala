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

object SwitzerlandEnum extends Enumeration {

  type SwitzerlandEnum = Value
  val SWITZERLAND = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => SWITZERLAND
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * Swiss calendar Holidays:
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Berchtoldstag, JANUARY 2nd
 *  Good Friday
 *  Easter Monday
 *  Ascension Day
 *  Whit Monday
 *  Labour Day, May 1st
 *  National Day, August 1st
 *  Christmas, December 25th
 *  St. Stephen's Day, December 26th
 *
 * @author Paul Bernard
 */
object Switzerland  {

}

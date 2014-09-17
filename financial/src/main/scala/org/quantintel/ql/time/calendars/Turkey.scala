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

object TurkeyEnum extends Enumeration {

  type TurkeyEnum = Value
  val TURKEY = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => TURKEY
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 * Holidays for the National Stock Exchange
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  National Holidays (April 23rd, May 19th, August 30th, October 29th
 *  Local Holidays (Kurban, Ramadan; 2004 to 2009 only)
 *
 * @author Paul Bernard
 */
object Turkey  {

}

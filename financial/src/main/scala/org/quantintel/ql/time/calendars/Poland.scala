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

object PolandEnum extends Enumeration {

  type PolandEnum = Value
  val POLAND = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => POLAND
    case _ => throw new Exception("Valid units = 1")
  }

}


/**
 *
 * Polish calendar
 * Holidays:
 *  Saturdays
 *  Sundays
 *  Easter Monday
 *  Corpus Christi
 *  New Year's Day, JANUARY 1st
 *  May Day, May 1st
 *  Constitution Day, May 3rd
 *  Assumption of the Blessed Virgin Mary, August 15th
 *  All Saints Day, November 1st
 *  Independence Day, November 11th
 *  Christmas, December 25th
 *  2nd Day of Christmas, December 26th
 *
 * Reference: http://www.gpw.pl
 *
 * @author Paul Bernard
 */
object Poland  {

}

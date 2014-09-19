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

object NewZealandEnum extends Enumeration {

  type NewZealandEnum = Value
  val NEWZEALAND = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => NEWZEALAND
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * New Zealand calendar
 * Holidays:
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st (possibly moved to Monday or Tuesday)
 *  Day after New Year's Day, JANUARY 2st (possibly moved to
 *           Monday or Tuesday)
 *  Anniversary Day, Monday nearest JANUARY 22nd
 *  Waitangi Day. February 6th
 *  Good Friday
 *  Easter Monday
 *  ANZAC Day. April 25th
 *  Queen's Birthday, first Monday in June
 *  Labour Day, fourth Monday in October
 *  Christmas, December 25th (possibly moved to Monday or Tuesday)
 *  Boxing Day, December 26th (possibly moved to Monday or Tuesday)
 *
 * Reference: http://www.nzx.com
 *
 * @author Paul Bernard
 */
object NewZealand {

}

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

object IndiaEnum extends Enumeration {

  type IndiaEnum = Value
  val NSE = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => NSE
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * Indian calendars
 * Holidays for the National Stock Exchange
 *  Saturdays
 *  Sundays
 *  Republic Day, JANUARY 26th
 *  Good Friday
 *  Ambedkar Jayanti, April 14th
 *  Independence Day, August 15th
 *  Gandhi Jayanti, October 2nd
 *  Christmas, December 25th
 *
 * Other holidays for which no rule is given (data available for
 * 2005-2008
 *  Bakri Id
 *  Moharram
 *  Mahashivratri
 *  Holi
 *  Ram Navami
 *  Mahavir Jayanti
 *  Id-E-Milad
 *  Maharashtra Day
 *  Buddha Pournima
 *  Ganesh Chaturthi
 *  Dasara
 *  Laxmi Puja
 *  Bhaubeej
 *  Ramzan Id
 *  Guru Nanak Jayanti
 *
 * TODO: This implementation has holidays only up to year 2008.
 *
 * Reference:
 *    http://www.nse-india.com/
 *
 * @author Paul Bernard
 */
object India {

}

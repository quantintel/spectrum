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

object IndonesiaEnum extends Enumeration {

  type IndonesiaEnum = Value
  val BEJ = Value(1)
  val JSX = Value(2)

  def valueOf(market: Int) = market match {
    case 1 => BEJ
    case 2 => JSX
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * Indonesian calendars Holidays for the Jakarta stock exchange
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Good Friday
 *  Ascension of Jesus Christ
 *  Independence Day, August 17th
 *  Christmas, December 25th
 *
 * Other holidays for which no rule is given
 *  Idul Adha
 *  Ied Adha
 *  Imlek
 *  Moslem's New Year Day
 *  Chinese New Year
 *  Nyepi (Saka's New Year)
 *  Birthday of Prophet Muhammad SAW
 *  Waisak
 *  Ascension of Prophet Muhammad SAW
 *  Idul Fitri
 *  Ied Fitri
 *  Other national leaves
 *
 * TODO: data available for 2005-2008 only
 *
 * Reference: http://www.jsx.co.id/
 *
 * @author Paul Bernard
 */
object Indonesia {

}

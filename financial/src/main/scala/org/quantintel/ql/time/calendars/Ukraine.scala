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

object UkraineEnum extends Enumeration {

  type UkraineEnum = Value
  val USE = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => USE
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * Holidays for the Ukrainian stock exchange
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Orthodox Christmas, JANUARY 7th
 *  International Women's Day, March 8th
 *  Easter Monday
 *  Holy Trinity Day, 50 days after Easter
 *  International Workers Solidarity Days, May 1st and 2n
 *  Victory Day, May 9th
 *  Constitution Day, June 28th
 *  Independence Day, August 24th
 * Holidays falling on a Saturday or Sunday are moved to the following Monday.
 *
 * Reference: http://www.ukrse.kiev.ua/eng/
 *
 * @author Paul Bernard
 */
object Ukraine  {

}

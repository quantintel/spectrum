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

object JapanEnum extends Enumeration {

  type JapanEnum = Value
  val JAPAN = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => JAPAN
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * Japanese calendar
 * Holidays:
 *  Saturdays
 *  Sundays
 *  New Year's Day, JANUARY 1st
 *  Bank Holiday, JANUARY 2nd
 *  Bank Holiday, JANUARY 3rd
 *  Coming of Age Day, 2nd Monday in JANUARY
 *  National Foundation Day, February 11th
 *  Vernal Equinox
 *  Greenery Day, April 29th
 *  Constitution Memorial Day, May 3rd
 *  Holiday for a Nation, May 4th
 *  Children's Day, May 5th
 *  Marine Day, 3rd Monday in July
 *  Respect for the Aged Day, 3rd Monday in September
 *  Autumnal Equinox
 *  Health and Sports Day, 2nd Monday in October
 *  National Culture Day, November 3rd
 *  Labor Thanksgiving Day, November 23rd
 *  Emperor's Birthday, DECEMBER 23rd
 *  Bank Holiday, DECEMBER 31st
 *  a few one-shot holidays
 *
 *  Note: Holidays falling on a Sunday are observed on the Monday following
 *        except for the bank holidays associated with the new year.
 *
 * Reference: http://www.tse.or.jp">Tokyo Stock Exchange
 *
 * @author Paul Bernard
 */
object Japan  {

}

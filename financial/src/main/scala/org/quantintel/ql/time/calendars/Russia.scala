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

import org.quantintel.ql.time.Month._
import org.quantintel.ql.time.Weekday._
import org.quantintel.ql.time.{Date, Western, Calendar}

object RussiaEnum extends Enumeration {

  type RussiaEnum = Value
  val SETTLEMENT = Value(1)

  def valueOf(market: Int) : RussiaEnum = market match {
    case 1 => SETTLEMENT
    case _ => throw new Exception("Valid units = 1")
  }

}
object Russia {

  def apply(): Calendar = {
    new Russia()
  }

  def apply(market: org.quantintel.ql.time.calendars.RussiaEnum.RussiaEnum): Calendar = {
    new Russia(market)
  }

}


/**
 * @author Paul Bernard
 */
class Russia extends Calendar  {

  impl = new Russia

  import org.quantintel.ql.time.calendars.RussiaEnum._

  def this (market: org.quantintel.ql.time.calendars.RussiaEnum.RussiaEnum){
    this
    market match {
      case SETTLEMENT => impl = new Russia
      case _ => throw new Exception("Valid units = 1")
    }
  }

  private class Russia extends Western {

    override def name : String = "Russia"

    override def isBusinessDay(date: Date): Boolean = {

      // standard dependencies
      val w: Weekday = date.weekday
      val d: Int = date.dayOfMonth
      val dd: Int = date.dayOfYear
      val m: Month = date.month
      val y: Int = date.year
      val em: Int = easterMonday(y)

      if (isWeekend(w)
        || (d >= 1 && d <= 8 && m == JANUARY) // New Year's holidays
        || ((d == 23 || ((d == 24 || d == 25) && w == MONDAY)) && m == FEBRUARY)  // Defender of the Fatherland Day (possibly moved to Monday)
        || ((d == 8 || ((d == 9 || d == 10) && w == MONDAY)) && m == MARCH) // International Women's Day (possibly moved to Monday)
        || ((d == 1 || ((d == 2 || d == 3) && w == MONDAY)) && m == MAY) // Labour Day (possibly moved to Monday)
        || ((d == 9 || ((d == 10 || d == 11) && w == MONDAY)) && m == MAY) // Victory Day (possibly moved to Monday)
        || ((d == 12 || ((d == 13 || d == 14) && w == MONDAY)) &&  m == JUNE) // Russia Day (possibly moved to Monday)
        || ((d == 4 || ((d == 5 || d == 6) && w == MONDAY)) &&  m == NOVEMBER)) // Unity Day (possibly moved to Monday)
      false else true

    }
  }

}

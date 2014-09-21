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

import org.quantintel.ql.time.Weekday._
import org.quantintel.ql.time.{Date, Calendar}


object WeekendsOnlyEnum extends Enumeration {

  type WeekendsOnlyEnum = Value
  val WEEKENDSONLY = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => WEEKENDSONLY
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 * @author Paul Bernard
 */
object WeekendsOnly {

  def apply(): Calendar = new WeekendsOnly

  import org.quantintel.ql.time.calendars.WeekendsOnlyEnum._

  def apply(market: WeekendsOnlyEnum): Calendar = {
    market match {
      case WEEKENDSONLY => new WeekendsOnly
    }
  }

  private class WeekendsOnly extends Calendar {

    override def name = "WeekendsOnly"

    def isWeekend(w: Weekday) : Boolean = {
      w == SATURDAY || w == SUNDAY
    }

    override def isBusinessDay(date: Date): Boolean = {
      isWeekend(date.weekday)
    }
  }



}

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
import org.quantintel.ql.time.{Calendar, Date, Western}

object NullCalendarEnum extends Enumeration {

  type NullCalendarEnum = Value
  val NULLCALENDAR = Value(1)

  def valueOf(market: Int) = market match {
    case 1 => NULLCALENDAR
    case _ => throw new Exception("Valid units = 1")
  }

}

/**
 *
 * Calendar for reproducing theoretical calculations
 *
 * @author Paul Bernard
 */
object NullCalendar  {

  def apply(): Calendar = new NullCalendar

  import org.quantintel.ql.time.calendars.NullCalendarEnum._

  def apply(market: NullCalendarEnum): Calendar = {
    market match {
      case NULLCALENDAR => new NullCalendar
      case _ => throw new Exception("Valid units = 1")
    }
  }

  private class NullCalendar extends Western {

    override def name = "Null"

    override def isWeekend(w: Weekday) : Boolean = false

    override def isBusinessDay(date: Date): Boolean = true
  }

}

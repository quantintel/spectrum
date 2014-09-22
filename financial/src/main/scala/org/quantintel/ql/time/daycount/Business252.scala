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

package org.quantintel.ql.time.daycount

import org.quantintel.ql.time.{Date, Calendar}

/**
 * Created by paulbernard on 9/21/14.
 */
object Business252 {

  import org.quantintel.ql.time.calendars.Brazil

  def apply() = new Business252(Brazil())

  def apply(calendar: Calendar) = {
    new Business252(calendar)
  }

  class Business252 (calendar: Calendar) extends DayCounter {

    override def name = "Business/252(" + calendar.name + ")"

    override def dayCount(d1: Date, d2: Date): Long = calendar.businessDaysBetween(d1, d2)

    override def yearFraction(dateStart: Date, dateEnd: Date,
                              refPeriodStart: Date, refPeriodEnd: Date) : Double = ???

  }

}

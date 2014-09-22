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

import org.quantintel.ql.time.Date

/**
 * @author Paul Bernard
 */
object SimpleDayCounter {

  def apply() : DayCounter = new SimpleDayCounter

  class SimpleDayCounter extends DayCounter {

    private val fallback = Thirty360()

    override def name = "Simple"

    override def dayCount(dateStart: Date, dateEnd: Date) : Long = {
      fallback.dayCount(dateStart, dateEnd)
    }

    override def yearFraction(dateStart: Date, dateEnd: Date,
                              refPeriodStart: Date, refPeriodEnd: Date) : Double = {

      val dm1 : Int = dateStart.dayOfMonth
      val dm2 : Int  = dateEnd.dayOfMonth
      val mm1 : Int  = dateStart.month.id
      val mm2 : Int  = dateEnd.month.id
      val yy1 : Int  = dateStart.year
      val yy2 : Int  = dateEnd.year

      if (dm1 == dm2 ||
        (dm1 > dm2 && Date.isEndOfMonth(dateEnd)) ||
        (dm1 < dm2 && Date.isEndOfMonth(dateStart)))
        (yy2 - yy1) + (mm2 - mm1) / 12.0
      else
        fallback.yearFraction(dateStart, dateEnd, refPeriodStart, refPeriodEnd)


    }
  }
}



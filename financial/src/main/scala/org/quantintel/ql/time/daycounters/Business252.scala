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

package org.quantintel.ql.time.daycounters

import org.joda.time.Months
import org.quantintel.ql.time.{Month, Date, Calendar}
import org.quantintel.ql.time.TimeUnit._

/**
 * @author Paul Bernard
 */
object Business252 {



  val monthlyFigures =  collection.mutable.Map[String, collection.mutable.Map[Int,
    collection.mutable.Map[Int, Long]]]()
  val yearlyFigures = collection.mutable.Map[String, collection.mutable.Map[Int, Long]]()



  //def apply() = new Business252()

  def apply(calendar: Calendar) = {
    new Business252(calendar)
  }

  def sameYear(d1: Date, d2: Date) : Boolean = {
    d1.year == d2.year
  }

  def sameMonth(d1: Date, d2: Date) : Boolean = {
    d1.year == d2.year && d1.month == d2.month
  }

  def businessDays(cache: Map[Int, Map[Int, Long]], calendar: Calendar, month: Int, year: Int)  = {
    if (cache(year)(month) == 0) {
      var d1 = Date(1, month, year)
      var d2 = d1 + 1 * MONTHS.id
      cache(year)(month) += calendar.businessDaysBetween(d1, d1)
    }
      cache(year)(month)
  }

  def businessDays(outerCache: Map[Int, Long], cache: Map[Int, Map[Int, Long]],
                   calendar: Calendar, year: Int): Long = {
    if (outerCache(year) == 0){
      var total : Long = 0
      for (i <- 1 to 12){
        total = total + businessDays(cache, calendar, Month.valueOf(i).id, year)
      }
      outerCache(year) += total
    }
    outerCache(year)
  }


  class Business252 (calendar: Calendar) extends DayCounter {



    override def name = "Business/252(" + calendar.name + ")"

    def dayCount(d1: Date, d2: Date): Long = {

        if (sameMonth(d1, d2) || d1>=d2){
          return calendar.businessDaysBetween(d1, d2)
        } else if(sameYear(d1, d2)) {

          var cache = monthlyFigures(calendar.name)
          var total : Long = 0
          var d: Date = null
          d = Date(1, d1.month, d1.year) + 1 * MONTHS.id
          total = total + calendar.businessDaysBetween(d1, d)

          while(!sameMonth(d, d2)){
            total = total + Business252.businessDays(cache, calendar, d.month, d.year)
            d = d + 1 * MONTHS.id
          }


        } else {

        }
    }

    override def yearFraction(d1: Date, d2: Date,
                              d3: Date, d4: Date) : Double =
          dayCount(d1, d2) / 252.0



  }
*/
}

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

package org.quantintel.ql.daycounters

import org.quantintel.ql.time.{Month, Period, TimeUnit, Date}

/**
 * @author Paul Bernard
 */
object ActualActualConvention extends Enumeration {

  type ActualActualConvention= Value
  val ISMA = Value(1)
  val BOND = Value(2)
  val ISDA = Value(3)
  val HISTORICAL = Value(4)
  val ACTUAL365 = Value(5)
  val AFB = Value(6)
  val EURO = Value(7)

  def valueOf(market: Int)  = market match {
    case 1 => ISMA
    case 2 => BOND
    case 3 => ISDA
    case 4 => HISTORICAL
    case 5 => ACTUAL365
    case 6 => AFB
    case 7 => EURO
    case _ => throw new Exception("Valid units = 1 to 7")
  }

}


/**
 * @author Paul Bernard
 */
object ActualActual  {

  def apply() = new ISDA

  import org.quantintel.ql.daycounters.ActualActualConvention._

  def apply(convention: ActualActualConvention) : DayCounter = {
    convention match {
      case ISMA | BOND => new ISMA
      case ISDA | HISTORICAL | ACTUAL365=> new ISDA
      case AFB | EURO => new AFB
      case _ => throw new Exception("unknown 30/360 convention")
    }
  }

  class ISMA extends DayCounter {

    override def name = "Actual/Actual (ISMA)"

    override def yearFraction(d1: Date, d2: Date, d3: Date, d4: Date): Double = {

      if (d1 equals d2) return 0.0
      if (d1 gt d2) return -yearFraction(d2, d1, d3, d4)

      var refPeriodStart: Date = if (d3 != null) d3 else d1
      var refPeriodEnd: Date = if (d4 != null) d4 else d2

      var months: Int = (0.5 + 12 * (refPeriodEnd.sub(refPeriodStart)) / 365.0).toInt

      import org.quantintel.ql.time.Period

      if(months == 0) {
        refPeriodStart = d1
        refPeriodEnd = d1.add(Period.ONE_YEAR_FORWARD)
        months = 12
      }


      val period: Double = months / 12.0

      if (d2 lt refPeriodEnd) {
        if (d1 gt refPeriodStart)

          return period * dayCount(d1, d2) / dayCount(refPeriodStart, refPeriodEnd)

        else {

          val previousRef = refPeriodStart.add(
            new Period(-months, TimeUnit.MONTHS))
          if (d2 gt refPeriodStart) {
            yearFraction(d1, refPeriodStart, previousRef, refPeriodStart)
              + yearFraction(refPeriodStart, d2, refPeriodStart, refPeriodEnd) }
          else yearFraction(d1, d2, previousRef, refPeriodStart)

        }
      } else {
          var sum : Double = yearFraction(d1, refPeriodEnd, refPeriodStart, refPeriodEnd)
          var i : Int = 0
          var newRefStart: Date = null
          var newRefEnd: Date = null
        var check : Boolean = true
        do {
            newRefStart = refPeriodEnd.add(new Period(months * i, TimeUnit.MONTHS))
            newRefEnd = refPeriodEnd.add(new Period(months * (i + 1), TimeUnit.MONTHS))
            if (d2 lt newRefEnd) check = false
              else {
                sum = sum + period
                i = i + 1
              }
        } while (check)
        sum = sum + yearFraction(newRefStart, d2, newRefStart, newRefEnd)
        sum
      }



    }

  }

  class ISDA extends DayCounter {

    override def name = "Actual/Actual (ISDA)"

    override def yearFraction(dateStart: Date, dateEnd: Date, refPeriodStart: Date, refPeriodEnd: Date): Double = {
      if (dateStart equals dateEnd) return 0.0
      if (dateStart gt dateEnd) -yearFraction(dateEnd, dateStart, new Date, new Date)

      val y1 : Int = dateStart.year
      val y2 : Int = dateEnd.year
      val dib1: Double = if (Date.isLeapYr(dateStart.year)) 366.0 else 365.0
      val dib2: Double = if (Date.isLeapYr(dateEnd.year)) 366.0 else 365.0

      var sum : Double = y2 - y1 - 1

      sum = sum + (dib1 - dateStart.dayOfYear + 1) / dib1
      sum = sum + (dateEnd.dayOfYear - 1) / dib2
      sum

    }

  }

  class AFB extends DayCounter {

    override def name = "Actual/Actual (AFB)"

    override def yearFraction(dateStart: Date, dateEnd: Date,
                              refPeriodStart: Date, refPeriodEnd: Date): Double = {

      if (dateStart equals dateEnd)
        return 0.0

      if (dateStart gt dateEnd)
        return -1.0 * yearFraction(dateEnd, dateStart, new Date, new Date)

      var newD2: Date = dateEnd
      var temp: Date = dateEnd
      var sum: Double = 0.0

      while (temp.gt(dateStart)) {

        temp = newD2.add(Period.ONE_YEAR_BACKWARD)
        if (temp.dayOfMonth == 28 && temp.month.id == 2 && Date.isLeapYr(temp.year)) {
          temp inc
        }
        if (temp.ge(dateStart)) {
          sum = sum + 1.0
          newD2 = temp
        }

      }

      var den: Double = 365.0

      if (Date.isLeapYr(newD2.year)) {
        if ((newD2 gt (new Date(29, Month.FEBRUARY, newD2.year))) &&
          (dateStart le (new Date(29, Month.FEBRUARY, newD2.year))))
          den = den + 1.0
      } else if (Date.isLeapYr(dateStart.year)) {
        if ((newD2 gt (new Date(29, Month.FEBRUARY, dateStart.year))) &&
          (dateStart le (new Date(29, Month.FEBRUARY, dateStart.year))))
          den = den + 1.0
      }
      sum + dayCount(dateStart, newD2) / den
    }


  }

}

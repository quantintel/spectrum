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

import org.quantintel.ql.time.Date

object Actual365Convention extends Enumeration {

  type Actual365Convention = Value

  val ACT365F  = Value(1)
  val ACT365L  = Value(2)
  val ACT365NL  = Value(3)


  def valueOf(market: Int) : Actual365Convention   = market match {
    case 1 => ACT365F
    case 2 => ACT365L
    case 3 => ACT365NL
    case _ => throw new Exception("Valid units = 1 to 3")
  }

}



object Actual365 {

  def apply() : DayCounter = new Actual365Fixed

  import org.quantintel.ql.time.daycounters.Actual365Convention._

  def apply(convention: Actual365Convention) : DayCounter = {
    convention match {
      case ACT365F => new Actual365Fixed
      case ACT365L => new ACT365L
      case ACT365NL => new ACT365NL

      case _ => throw new Exception("unknown act/365 convention")
    }
  }

  /**
   *
   * Also known as: Act/365 Fixed, A/365 Fixed, A/365F, English
   * references:
   *    ISDA 2006 Section 4.16(d)
   *    Mayle 1993
   *
   *
   * @author Paul Bernard
   */
  class Actual365Fixed extends DayCounter {

    override def name : String = "Actual/365 (fixed)"

    override def yearFraction (dateStart: Date, dateEnd: Date,
              refPeriodStart: Date, refPeriodEnd: Date): Double =
                  dayCount(dateStart, dateEnd) / 365.0


  }


  /**
   * The actual number of days is used as the numerator.
   * If the second date falls in a leap year, the denominator is 366, otherwise it is 365.
   */
  class ACT365L extends DayCounter {

    override def name : String = "actual/365 no leap, NL365"

    override def yearFraction(dateStart: Date, dateEnd: Date,
                              refPeriodStart: Date, refPeriodEnd: Date): Double = {

      var d = 0.0
      if (dateEnd.isLeapYear) d = 366.0 else 365.0
      dayCount(dateStart, dateEnd) / d
    }
  }

  /**
   * The actual number of days minus the number of leap days is used as the numerator.
   * The denominator is always 365 days.
   *
   * also defined as:
   * The actual number of days minus the number of leap days is used as the numerator.
   * Denominator = 365
   */
  class ACT365NL extends DayCounter {

    override def name : String = "actual/365 no leap, NL365"

    override def yearFraction(dateStart: Date, dateEnd: Date,
                              refPeriodStart: Date, refPeriodEnd: Date): Double = {

      val startNum = dateStart.serialNumber
      val endNum = dateEnd.serialNumber

      var sum = 0.0
      for(i <- startNum to endNum){
        val dt = Date(i)
        val ld : Boolean = if (dt.month.id ==2 && dt.dayOfMonth==29) false else true
        if (ld) sum = sum + 1
      }

      sum / 365.0

    }
  }

}




/*
 * Copyright (c) 2014-2015  Paul Bernard
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

package org.quantintel.ql.time

import org.quantintel.lang.collections.distributed.{SessionBasedCollection}

/**
 * Functional api around day count capabilities
 *
 * @author Paul Bernard
 */
package object daycounters {



  private def dayCounter(name: String, calendar: Calendar): DayCounter = {

    import org.quantintel.ql.time.daycounters.Business252.Business252

    name match {

      case "ACTUAL/360" => Actual360(Actual360Convention.ACTUAL360)
      case "FRENCH" => Actual360(Actual360Convention.FRENCH)
      case "ACT365F" => Actual365(Actual365Convention.ACT365F)
      case "ACT365L" => Actual365(Actual365Convention.ACT365L)
      case "ACT365NL" => Actual365(Actual365Convention.ACT365NL)
      case x if x == "ISMA" || x == "BOND" => ActualActual(ActualActualConvention.ISMA)
      case x if x == "ISDA" || x == "HISTORICAL" || x == "ACTUAL365"
      => ActualActual(ActualActualConvention.ISDA)
      case x if x == "AFB" || x == "EURO" => ActualActual(ActualActualConvention.AFB)
      case "30/360" => Thirty360(Thirty360Convention.USA)
      case "BOND BASIS" => Thirty360(Thirty360Convention.BONDBASIS)
      case "30E/360" => Thirty360(Thirty360Convention.EUROPEAN)
      case "EUROBOND BASIS" => Thirty360(Thirty360Convention.EUROBONDBASIS)
      case "30E+/360" => Thirty360(Thirty360Convention.EP)
      case "30/360 ISDA" => Thirty360(Thirty360Convention.THIRTY360ISDA)
      case "30/360 US (NASD)" => Thirty360(Thirty360Convention.THIRTY360USNASD)
      case "30E/360 ISDA" => Thirty360(Thirty360Convention.ISDA)
      case "30/360 US" => Thirty360(Thirty360Convention.THIRTY360US)
      case "30/360 SIA" => Thirty360(Thirty360Convention.SIA)
      case "30/360 BMA" => Thirty360(Thirty360Convention.BMA)
      case "30/360 (Italian)" => Thirty360(Thirty360Convention.ITALIAN)
      case "BUS/252" => new Business252(calendar)
      case _ => Actual360(Actual360Convention.ACTUAL360) // default

    }
  }

  /**
   * Calculates day count year fraction
   *
   * @param dcmname day count method name
   * @param cal calendar instance used to calculate day count year fraction.
   * @param dateStart from date
   * @param dateEnd to date
   * @return year faction
   */
  def yearFraction(dcmname: String, cal: Calendar, dateStart : Date, dateEnd :Date) : Double = {
    assert(dcmname=="BUS/252")
    dayCounter(dcmname, cal).yearFraction(dateStart, dateEnd)
  }

  /**
   * Calculates day count year fraction.
   *
   * @param dcmname day count method name
   * @param cal calendar instance used to calculated day count year fraction.
   * @param dateStart start date
   * @param dateEnd end date
   * @param refStartDate reference start date
   * @param refEndDate reference end date
   * @return year fraction
   */
  def yearFraction(dcmname: String, cal: Calendar, dateStart : Date, dateEnd :Date, refStartDate: Date, refEndDate: Date)
  : Double = {
    assert(dcmname=="BUS/252")
    dayCounter(dcmname, cal).yearFraction(dateStart, dateEnd, refStartDate, refEndDate)
  }



  /**
   * Calculates Day count year fraction
   *
   * @param dcmname day count method name
   * @param dateStart start date
   * @param dateEnd end date
   * @return year fraction
   */
  def yearFraction(dcmname: String, dateStart : Date, dateEnd :Date) : Double = {
    assert(dcmname!="BUS/252")
    dayCounter(dcmname, null).yearFraction(dateStart, dateEnd)
  }

  /**
   * Calculates date count year fraction.
   *
   * @param dcmname day count method name
   * @param dateStart start date
   * @param dateEnd end date
   * @param refStartDate reference start date
   * @param refEndDate reference end date
   * @return
   */
  def yearFraction(dcmname: String, dateStart : Date, dateEnd :Date, refStartDate: Date, refEndDate: Date)
      : Double = {
          assert(dcmname!="BUS/252")
          dayCounter(dcmname, null).yearFraction(dateStart, dateEnd, refStartDate, refEndDate)
  }

  /**
   * Calculates day Count
   *
   * @param dcmname day count method name
   * @param dateFrom from date
   * @param dateTo to date
   * @return day count
   */
  def dayCount(dcmname: String, dateFrom: Date, dateTo: Date) : Long = {
    assert(dcmname!="BUS/252")
    dayCounter(dcmname, null).dayCount(dateFrom, dateTo)
  }


  /**
   * Calculates day count
   *
   * @param dcmname day count method name
   * @param cal instance of calendar used to calculate day count
   * @param dateFrom from date
   * @param dateTo to date
   * @return day count
   */
  def dayCount(dcmname: String, cal: Calendar, dateFrom: Date, dateTo: Date) : Long = {
    assert(dcmname=="BUS/252")
    dayCounter(dcmname, cal).dayCount(dateFrom, dateTo)
  }


  /**
   * Returns a Calendar from a given session with the name indicated.
   *
   * @param session a session id
   * @param calendarId calendar id within a session
   * @return a Calendar
   */
  def calendar (session: SessionBasedCollection, calendarId: String) : Calendar = {
    val cal = session.calendar.get(calendarId)
    cal match {
      case Some(c) => c
      case None => null
    }
  }



}

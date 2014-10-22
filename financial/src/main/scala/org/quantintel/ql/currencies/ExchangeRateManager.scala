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
 */

package org.quantintel.ql.currencies

import org.quantintel.ql.Settings
import org.quantintel.ql.time.Date
import org.quantintel.ql.currencies.EuropeEnum._
import org.quantintel.ql.currencies.AmericaEnum._
import org.quantintel.ql.time.Month._
import collection.mutable
import org.quantintel.ql.currencies.RateTypeEnum._

/**
 * @author Paul Bernard
 */
object ExchangeRateManager {

  private var m_data = mutable.Map[Any, List[Entry]]()

  addKnownRates()

  /**
   * Helper class to decide whether a date is in the range of a specific entry
   *
   * @param d the date
   */
  class ValidAt (d: Date) {

    def operator(e: Entry): Boolean = {
      d >= e.startDate && d <= e.endDate
    }
  }


  /**
   * Exchange rate entry stored in the repository
   *
   * @param rate The ExchangeRate
   * @param startDate The start date of the period this ExchangeRate should be used.
   * @param endDate The end date of the period this ExchangeRate should be used.
   */
  class Entry (var rate : ExchangeRate, var startDate: Date, var endDate: Date )


  /**
   * Adds an exchange rate.  The given rate is valid between the given dates
   * @param rate The ExchangeRate to be added.
   * @param startDate The start date of the period for which the provided Exchange rate should be valid.
   * @param endDate The end date of the period for which the provided Exchange rate should be valid.
   */
  def add(rate: ExchangeRate, startDate: Date, endDate: Date) {
    val k : Int = hash(rate.source, rate.target)
    if (m_data(k) == null) {
      m_data += k -> List[Entry]()
    }

    val newEntry = List(new Entry(rate, startDate, endDate))
    m_data += k -> (newEntry ::: m_data(k))

  }

  /**
   * Adds an exchange rate to the repository.  The given rate is valid between min and max date.
   * @param rate The ExchangeRate to be added.
   */
  def add(rate: ExchangeRate) {
    add(rate, Date.minDate, Date.maxDate)
  }

  def lookup(source: Currency, target: Currency) : ExchangeRate = {
    lookup(source, target, Date.todaysDate, DERIVED)
  }


  def lookup(source: Currency, target: Currency, date: Date) : ExchangeRate = {
    lookup(source, target, date, DERIVED)
  }


  def lookup(source: Currency, target: Currency, dt: Date, rateType: RateTypeEnum): ExchangeRate = {

    var date = dt

    if(source == target){
      ExchangeRate(source, target, 1.0)
    }

    if(date.isToday){
      date = new Settings().evaluationDate
    }

    if (rateType == DIRECT) {
      directLookup(source, target, date)
    }
    else if(!source.triangulationCurrency.empty){
      val link : Currency = source.triangulationCurrency
      if (link == target){
        directLookup(source, link, date)
      }
      else
        ExchangeRate.chain(directLookup(source, link, date), lookup(link, target, date))
    }
    else if (!target.triangulationCurrency.empty) {
      val link : Currency = target.triangulationCurrency
      if (source == link){
        directLookup(link, target, date)
      }
      else {
        ExchangeRate.chain(lookup(source, link, date), directLookup(link, target, date))
      }
    } else {
      smartLookup(source, target, date)
    }


  }

  def clear() {

    m_data.clear()
    addKnownRates()

  }

  def hash (c1: Currency, c2: Currency) : Int = {
    Math.min(c1.numericCode, c2.numericCode) * 1000 + Math.max(c1.numericCode, c2.numericCode)
  }

  def hashes(k: Int, c: Currency) : Boolean = {
    c.numericCode == k % 1000 || c.numericCode == k / 1000
  }




  def addKnownRates(): Unit = {
    // currencies obsoleted by Euro
    add(ExchangeRate(Europe(EUR), Europe(ATS), 13.7603), Date(1,JANUARY,1999), Date.maxDate)
    add(ExchangeRate(Europe(EUR), Europe(BEF), 40.3399), Date(1,JANUARY,1999), Date.maxDate)
    add(ExchangeRate(Europe(EUR), Europe(DEM), 1.95583), Date(1,JANUARY,1999), Date.maxDate)
    add(ExchangeRate(Europe(EUR), Europe(ESP), 166.386), Date(1,JANUARY,1999), Date.maxDate)
    add(ExchangeRate(Europe(EUR), Europe(FIM), 5.94573), Date(1,JANUARY,1999), Date.maxDate)
    add(ExchangeRate(Europe(EUR), Europe(FRF), 6.55957), Date(1,JANUARY,1999), Date.maxDate)
    add(ExchangeRate(Europe(EUR), Europe(GRD), 340.750),Date(1,JANUARY,2001), Date.maxDate)
    add(ExchangeRate(Europe(EUR), Europe(IEP), 0.787564), Date(1,JANUARY,1999), Date.maxDate)
    add(ExchangeRate(Europe(EUR), Europe(ITL), 1936.27),Date(1,JANUARY,1999), Date.maxDate)
    add(ExchangeRate(Europe(EUR), Europe(LUF), 40.3399), Date(1,JANUARY,1999), Date.maxDate)
    add(ExchangeRate(Europe(EUR), Europe(NLG), 2.20371), Date(1,JANUARY,1999), Date.maxDate)
    add(ExchangeRate(Europe(EUR), Europe(PTE), 200.482), Date(1,JANUARY,1999), Date.maxDate)
    // others
    add(ExchangeRate(Europe(TRY), Europe(TRL), 1000000.0), Date(1,JANUARY,2005), Date.maxDate)
    add(ExchangeRate(Europe(RON), Europe(ROL), 10000.0), Date(1,JULY,2005), Date.maxDate)
    add(ExchangeRate(America(PEN), America(PEI), 1000000.0), Date(1,JULY,1991), Date.maxDate)
    add(ExchangeRate(America(PEI), America(PEH), 1000.0), Date(1,FEBRUARY, 1985), Date.maxDate)
  }

  def directLookup (source: Currency, target: Currency, date: Date) : ExchangeRate = {
    val rate : ExchangeRate = null
    rate
  }

  def smartLookup (source: Currency, target: Currency, date: Date, forbidden: List[Int]) : ExchangeRate = {
    null
  }

  def smartLookup(source: Currency, target: Currency, date: Date): ExchangeRate = {
    smartLookup(source, target, date, Array[Int]())
  }

  def smartLookup(source: Currency, target: Currency, date: Date, fb: Array[Int])
    : ExchangeRate = {

    var forbidden  = fb

    val direct: ExchangeRate = fetch(source, target, date)

    if(direct != null) direct
    val temp : Array[Int] = forbidden.clone()
    forbidden = Array(temp.length + 1)
    System.arraycopy(temp, 0, forbidden, 0, temp.length)
    forbidden(forbidden.length -1) = source.numericCode

    m_data.foreach { keyVal =>

      if (hashes(keyVal._1.asInstanceOf[Int] , source) && m_data(keyVal._1).nonEmpty) {

        val e : Entry = m_data(keyVal._1)(0)
        val other : Currency =
          if (source == e.rate.source) e.rate.target else e.rate.source

        if (matchVal(forbidden, other.numericCode) == (forbidden.length - 1)) {

          val head : ExchangeRate = fetch(source, other, date)

            if (head != null) {
              val tail : ExchangeRate = smartLookup(other, target, date, forbidden)
              ExchangeRate.chain(head, tail)
            }

        }
      }
    }

  throw new Exception("no conversion available")

  }


  def fetch (source: Currency, target: Currency, date: Date) : ExchangeRate = {
    val rates : List[Entry] = m_data(hash(source, target))
    val i: Int = matchValidateAt(rates, date)
    if (i == rates.size -1) rates(i).rate else null
  }




  private def matchVal(list: Array[Int], value: Int) : Int = {
    for(i <-0 to list.length){
      if (value == list(i)) return i
    }
    -1
  }

  private def matchValidateAt(rates: List[Entry], date: Date) : Int = {
    val va = new ValidAt(date)
    for (i <- 0 to rates.size){
      if (va.operator(rates(i))) return i
    }
    -1

  }






}

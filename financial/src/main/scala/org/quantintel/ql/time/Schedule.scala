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

package org.quantintel.ql.time

import org.quantintel.ql.Settings
import org.quantintel.ql.time.BusinessDayConvention.BusinessDayConventionEnum
import org.quantintel.ql.time.DateGeneration.DateGeneration
import org.quantintel.ql.time.DateGeneration._
import org.quantintel.ql.time.Month.Month
import org.quantintel.ql.time.TimeUnit.{MONTHS, DAYS}
import org.quantintel.ql.time.calendars.NullCalendar
import org.quantintel.ql.time.Weekday._

import scala.collection.mutable.ListBuffer
import scala.util.control.Breaks._


object Schedule {

    def nextTwentieth(d: Date, rule: DateGeneration) : Date = {
        var result : Date = Date(20, d.month, d.year)
      if(result < d) result += 1 * MONTHS.id
      if(rule == TWENTIETH_IMM ||
        rule == OLD_CDS ||
        rule == CDS) {
          val m: Month = result.month
          if (m.id % 3 != 0) {
            val skip: Int = 3 - m.id % 3
            result += skip * MONTHS.id
          }
      }
      result
    }

    def previousTwentieth(d: Date, rule: DateGeneration) : Date = {
      var result : Date = Date(20, d.month, d.year)
      if (result < d) result += 1 * MONTHS.id
      if (rule == TWENTIETH_IMM ||
        rule == OLD_CDS ||
        rule == CDS) {
        val m: Month = result.month
        if (m.id % 3 != 0){
          val skip: Int = m.id % 3
          result -= skip * MONTHS.id
        }

      }
      result
    }


}


/**
 * @author Paul Bernard
 */
class Schedule(val fullInterface: Boolean,
                val calendar: Calendar,
                var convention: BusinessDayConventionEnum,
                val terminationDateConvention: BusinessDayConventionEnum,
                val endOfMonth: Boolean,
                val finalIsRegular: Boolean,
                val dates: ListBuffer[Date],
                val m_isRegular: ListBuffer[Boolean]) {


  private var tenor: Period = null
  private var rule: DateGeneration = null
  private var firstDate: Date = null
  private var nextToLastDate: Date = null

  import org.quantintel.ql.time.BusinessDayConvention._
  import org.quantintel.ql.time.BusinessDayConvention.UNADJUSTED

  def this (effectiveDate: Date,
            terminationDate: Date,
            tenor: Period,
            calendar: Calendar,
            pconvention: BusinessDayConventionEnum,
            terminationDateConvention: BusinessDayConventionEnum,
            rule: DateGeneration,
            endOfMonth: Boolean,
            firstDate: Date,
            nextToLastDate: Date){

    this(true, calendar, pconvention, terminationDateConvention, endOfMonth, true,
      ListBuffer[Date](), ListBuffer[Boolean]())

    var convention = pconvention

    import org.quantintel.lang.collect.insertAt

    this.tenor = tenor
    this.rule = rule
    this.firstDate = firstDate
    this.nextToLastDate = nextToLastDate

    // TODO: assertions.

    val nullCalendar : Calendar = NullCalendar()
    var periods : Int = 1
    var seed: Date = null
    var exitDate: Date = null


    rule match {
      case ZERO =>
        this.tenor = Period(0, DAYS)
        dates.append(effectiveDate)
        dates.append(terminationDate)
        this.m_isRegular.append(true)

      case BACKWARD =>
        dates.append(terminationDate)
        seed = terminationDate.clone
        if(nextToLastDate != null && !nextToLastDate.isNull){
          insertAt(nextToLastDate,0, dates.toList)
          val temp : Date = nullCalendar.advance(seed, (this.tenor * periods) * -1, convention, endOfMonth)
          if (temp != nextToLastDate) insertAt(false, 0, this.m_isRegular.toList)
            else insertAt(true, 0, this.m_isRegular.toList)
          seed = nextToLastDate.clone
        }

        exitDate = effectiveDate.clone
        if(firstDate != null && !firstDate.isNull) exitDate = firstDate.clone
        breakable {
          while(true){
            val temp : Date = nullCalendar.advance(seed, (this.tenor * periods) * -1, convention, endOfMonth)
            if(temp < exitDate) break()
            else {
              insertAt(temp, 0, dates.toList)
              insertAt(true, 0, m_isRegular.toList)
              periods = periods + 1
            }
          }
        }

        if(endOfMonth && calendar.isEndOfMonth(seed)) convention = PRECEDING

        if(calendar.adjust(dates(0), convention) != calendar.adjust(effectiveDate, convention)){
          insertAt(false, 0, m_isRegular.toList)
        }



      case TWENTIETH | TWENTIETH_IMM | THIRD_WEDNESDAY => assert(!endOfMonth, "endOfMonth convention incompatible with " +
            this.rule + " date generation rule")

      case FORWARD =>

        dates.append(effectiveDate)

        seed = effectiveDate.clone

        if (firstDate != null && !firstDate.isNull) {
          dates.append(firstDate)
          val temp: Date = nullCalendar.advance(seed, this.tenor * periods, convention, endOfMonth)
          if (temp != firstDate) {
            this.m_isRegular.append(false)
          } else {
            this.m_isRegular.append(true)
          }
          seed = firstDate.clone
        } else if (rule == TWENTIETH ||
                rule == TWENTIETH_IMM) {
          val next20th: Date = Schedule.nextTwentieth(effectiveDate, rule)
          if (next20th != effectiveDate){
            dates.append(next20th)
            m_isRegular.append(false)
            seed = next20th.clone
          }
        }

        exitDate = terminationDate.clone
        if(nextToLastDate != null && !nextToLastDate.isNull) exitDate = nextToLastDate.clone

        breakable {
            while(true) {
                val temp : Date = nullCalendar.advance(seed, tenor * periods, convention, endOfMonth)
                if(temp > exitDate) break()
                  else {
                  dates.append(temp)
                  m_isRegular.append(true)
                  periods = periods + 1
                }
            }
        }

        if (endOfMonth && calendar.isEndOfMonth(seed)) convention = PRECEDING

        if(calendar.adjust(dates(dates.size - 1), terminationDateConvention)
            != calendar.adjust(terminationDate, terminationDateConvention)) {
          if(rule == TWENTIETH ||
              rule == TWENTIETH_IMM){
            dates.append(Schedule.nextTwentieth(terminationDate, rule))
          } else {
            dates.append(terminationDate)
            m_isRegular.append(false)
          }

        }

        break()



      case _ => throw new Exception("unknown Rule (" + rule + ")")

    }



    // Adjustments




    if(rule == THIRD_WEDNESDAY){
      for (i <- 1 until dates.size-1){
        insertAt(Date.nthWeekday(3, WEDNESDAY, dates(i).month.id , dates(i).year), i, dates.toList)
      }
    }

    for (i <- 0 until dates.size-1){
      insertAt(calendar.adjust(dates(i), convention), i, dates.toList)
    }

    if(terminationDateConvention != UNADJUSTED
      || rule == TWENTIETH
      || rule == TWENTIETH_IMM) {
      insertAt(calendar.adjust(dates(dates.size-1), terminationDateConvention), dates.size-1, dates.toList)
    }

  }

  def this(dates: ListBuffer[Date], calendar: Calendar, convention: BusinessDayConventionEnum) {
    this(false, calendar, convention, convention, false, true, dates, ListBuffer[Boolean]())

    this.tenor = new Period()
    this.rule = FORWARD

  }


  def this (dates: ListBuffer[Date]) {

    this(dates, new NullCalendar(), org.quantintel.ql.time.BusinessDayConvention.UNADJUSTED)
  }

  def this (dates: ListBuffer[Date], calendar: Calendar) {
    this(dates, calendar, org.quantintel.ql.time.BusinessDayConvention.UNADJUSTED)
  }

  def this(effectiveDate: Date,
      terminationDate : Date,
      tenor : Period,
      calendar: Calendar,
      convention: BusinessDayConventionEnum,
      terminationDateConvention: BusinessDayConventionEnum,
      rule: DateGeneration,
      endOfMonth: Boolean) {
    this(effectiveDate, terminationDate, tenor, calendar, convention,
    terminationDateConvention, rule, endOfMonth, Date(), Date())
  }

  def size : Int = dates.size

  def at(i: Int) : Date = dates(i)

  def date(i: Int): Date = dates(i)

  def previousDate(refDate: Date): Date = {
     val index: Int = Date.lowerBound(dates.toList, refDate)
      if (index > 0) dates(index-1).clone
        else Date()
  }

  def nextDate(refDate: Date): Date = {
      val index: Int = Date.lowerBound(dates.toList, refDate)
      if (index < dates.size) dates(index).clone
        else Date()
  }

  def isRegular(i: Int): Boolean = {
    this.m_isRegular(i -1)
  }

  def empty: Boolean = dates.isEmpty

  def startDate : Date = if (dates.isEmpty) null else dates(0)

  def endDate : Date = if (dates.isEmpty) null else dates(dates.size - 1)

  def lowerBound : Int = {
    lowerBound(Date())
  }

  def lowerBound(refDate: Date): Int = {
    val d: Date = if (refDate.isNull) new Settings().evaluationDate else refDate
    Date.lowerBound(dates.toList, d.clone)
  }

  import scala.util.control.Breaks._

  def stdLowerBound(date: Date): Iterator[Date] = {

    val ldates : List[Date] = List[Date]()

    if(dates.size > 0) {
      var index: Int = -1
      breakable { for(i <- 0 until dates.size) {
        val d: Date = dates(i)
        if(d.eq(date)){
          index = i
          break()
        }
      } }
      if (index > 0){
        for (i <- index until dates.size){
          ldates :: dates(i) :: Nil
        }
        return ldates.toIterator

      }

    }
      ldates.toIterator
  }

  def getDatesAfter(date: Date) : Iterator[Date] = {
    stdLowerBound(date)
  }



}

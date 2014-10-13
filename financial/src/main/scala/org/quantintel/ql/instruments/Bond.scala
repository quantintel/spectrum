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

package org.quantintel.ql.instruments


import java.util.Collections

import org.quantintel.ql.Settings
import org.quantintel.ql.cashflows.{SimpleCashFlow, CashFlow, Leg}
import org.quantintel.ql.time.{Date, Calendar}
import scala.collection.JavaConversions._
import org.quantintel.ql.time.TimeUnit.DAYS

import scala.collection.mutable.ArrayBuffer

/**
 * @author Paul Bernard
 */
class Bond extends Instrument {

  protected var settlementDays: Int = 0
  protected var calendar: Calendar = null
  protected var issueDate: Date = null
  protected var coupons: Leg = null
  protected var notionalSchedule : ArrayBuffer[Date] = null
  protected var notionals : ArrayBuffer[Double] = null
  protected var cashflows: Leg = coupons
  protected var redemptions = new Leg()
  protected var maturityDate : Date = null
  protected var settlementValue: Double = 0.0


  def this(settlementDays: Int, calendar: Calendar, iDate: Date, coupons: Leg) {

    this

    this.settlementDays = settlementDays
    this.calendar = calendar
    this.cashflows = coupons
    this.issueDate = issueDate.clone()
    this.notionals = ArrayBuffer[Double]()
    this.notionalSchedule = ArrayBuffer[Date]()
    this.redemptions = Leg()

    if (!coupons.isEmpty){
      cashflows.sortWith(_.date <= _.date)
      maturityDate = coupons.last.date
      //addRedemptionsToCashFlows
    }

    val evaluationDate : Date = new Settings().evaluationDate
    // TODO: evaluationDate.addObserver(this)

  }


  def this(settlementDays: Int, calendar: Calendar){
    this(settlementDays, calendar, new Date, new Leg())
  }

  def this(settlementDays: Int, calendar: Calendar, issueDate: Date){
    this(settlementDays, calendar, issueDate, new Leg())
  }

  def this(settlementDays : Int,
           calendar: Calendar ,
           faceAmount: Double,
           maturityDate: Date,
           issueDate: Date,
           cashflows: Leg) {

    this(settlementDays, calendar, issueDate.clone, cashflows)
    this.maturityDate = maturityDate.clone()
    this.notionalSchedule = ArrayBuffer[Date]()
    this.notionals = ArrayBuffer[Double]()
    this.redemptions = Leg()

    if(!cashflows.isEmpty){
      notionalSchedule.add(new Date())
      notionals.add(faceAmount)

      notionalSchedule.add(maturityDate.clone)
      notionals.add(0.0)

      val last : CashFlow = cashflows.last
      redemptions.add(last)

      cashflows.remove(last)
      cashflows.sortWith(_.date <= _.date)

      cashflows.add(last)

    }

    val evaluationDate : Date = new Settings().evaluationDate
    // TODO: evaluationDate.addObserver(this)


  }



  def this (settlementDays: Int,
            calendar: Calendar,
            faceAmount: Double,
            maturityDate: Date) {
    this(settlementDays, calendar, faceAmount, maturityDate, new Date(), new Leg())
  }

  def this (settlementDays: Int,
            calendar: Calendar ,
            faceAmount: Double,
            maturityDate: Date,
            issueDate: Date) {
    this(settlementDays, calendar, faceAmount, maturityDate, issueDate, new Leg())
  }

  def notional : Double = notional(new Date)


  def notional(date: Date) : Double = {

    var ldate = date

    if (date isNull) ldate = settlementDate

    if (ldate > notionalSchedule(notionalSchedule.size - 1)) return 0.0

    var index: Int = Collections.binarySearch[Date](notionalSchedule, date)

    if (index < 0) index = (index + 1) * -1

    if (date <= notionalSchedule(index)) {
      return notionals(index-1)
    } else {
      if (new Settings().isTodaysPayments){
        return notionals(index-1)
      } else {
        notionals(index)
      }

    }

  }


  def settlementDate : Date = settlementDate(new Date())


  def settlementDate (d: Date): Date = {

    var wdate = d
    if(wdate==Date) wdate = new Settings().evaluationDate
    val settlement: Date = calendar.advance(d, settlementDays, DAYS)

    if (issueDate.isNull) settlement else Date.max(settlement, issueDate.clone)

  }

  def setSingleRedemption(notional: Double, redemption: Double, date: Date): Unit = {

    redemptions.clear()

    notionalSchedule.add(new Date())
    notionals :: notional :: Nil

    notionalSchedule.add(new Date())
    notionals :: 0.0 :: Nil

    val redemptionCashflow : CashFlow = new SimpleCashFlow (notional * redemption/100.0, date)
    cashflows.add(redemptionCashflow)
    redemptions.add(redemptionCashflow)

  }

  def isExpired : Boolean = cashflows.last.hasOccured(settlementDate)




}

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

package org.quantintel.ql.instruments.bonds

import java.util
import java.util.Collections

import org.quantintel.ql.Settings
import org.quantintel.ql.cashflows.{CashFlow, Leg, SimpleCashFlow}
import org.quantintel.ql.instruments.Instrument
import org.quantintel.ql.time.TimeUnit.DAYS
import org.quantintel.ql.time.{Calendar, Date}

import scala.collection.JavaConversions._
import scala.collection.mutable.ArrayBuffer

/**
 * A debt investment in which an investor loans money to an entity (corporate or
 * govermental) that borrows the funds for a defined period of time at a fixed interest
 * rate.  Bond are used by companies, municipalities, states, as well as US and
 * foreign governments to finance a variety of projects and acivities.
 * Bonds are commonly referred to as fixed-income securities.
 * source- Investopedia 2014
 *
 *
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

    if (coupons.nonEmpty){
      cashflows.sortWith(_.date <= _.date)
      maturityDate = coupons.last.date
      //addRedemptionsToCashFlows()
    }

    val evaluationDate : Date = new Settings().evaluationDate
    evaluationDate.addObserver(this)

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

    if(cashflows.nonEmpty){
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
    evaluationDate.addObserver(this)


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

    if (date.isNull) ldate = settlementDate

    if (ldate > notionalSchedule(notionalSchedule.size - 1)) return 0.0

    var index: Int = Collections.binarySearch[Date](notionalSchedule, date)

    if (index < 0) index = (index + 1) * -1

    if (date <= notionalSchedule(index)) {
      notionals(index-1)
    } else {
      if (new Settings().isTodaysPayments){
        notionals(index-1)
      } else {
        notionals(index)
      }

    }

  }


  def settlementDate : Date = settlementDate(new Date())


  def settlementDate (date: Date): Date = {

    var d : Date = null
    if (date.isNull) d = new Settings().evaluationDate else d = date

    val settlement: Date = calendar.advance(d, settlementDays, DAYS)

    if (issueDate.isNull) settlement else Date.max(settlement, issueDate.clone)

  }

  def setSingleRedemption(notional: Double, redemption: Double, date: Date) {

    redemptions.clear()

    notionalSchedule.add(new Date())
    notionals :: notional :: Nil

    notionalSchedule.add(new Date())
    notionals :: 0.0 :: Nil

    val redemptionCashflow : CashFlow = new SimpleCashFlow (notional * redemption/100.0, date)
    cashflows.add(redemptionCashflow)
    redemptions.add(redemptionCashflow)

  }

  def isExpired : Boolean = cashflows.last.hasOccurred(settlementDate)


  protected def addRedemptionsToCashFlow(): Unit = {
    //addRedemptionsToCashflows(new List[Double]())
  }


  protected def addRedemptionsToCashflows(redemptions: List[Double]) {

    calculateNotionalsFromCashflows()
    this.redemptions.clear()

    (1 to notionalSchedule.size -1).foreach(
      (i: Int) => {
        val R : Double = if (i< redemptions.size) redemptions.get(i)
          else if (!redemptions.isEmpty) redemptions.get(redemptions.size-1) else
            100.00
        val amount : Double = (R/100.0)*(this.notionals.get(i-1)-notionals.get(i))
        val redemption = new SimpleCashFlow(amount, notionalSchedule.get(i))
        this.cashflows.add(redemption)
        this.redemptions.add(redemption)
      }
    )
  }

  protected def calculateNotionalsFromCashflows(): Unit = {
    notionalSchedule clear()
    notionals clear()
    var lastPaymentDate : Date = Date()
    notionalSchedule add Date()
    (0 to (cashflows.size - 1)).foreach(
      (i: Int) => {
        val cfObj : Object = cashflows.get(i)
        // TODO:
      }
    )


  }


}

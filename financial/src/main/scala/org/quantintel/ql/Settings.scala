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

package org.quantintel.ql

import org.quantintel.ql.time.Date


object Settings {

  var NEGATIVE_RATES : String  = "NEGATIVE_RATES"

  var EXTRA_SAFETY_CHECKS : String = "EXTRA_SAFETY_CHECKS"

  var TODAYS_PAYMENTS : String = "TODAYS_PAYMENTS"

  var USE_INDEXED_COUPON : String = "USE_INDEXED_COUPON"

  var ENFORCES_TODAYS_HISTORIC_FIXINGS : String  = "ENFORCES_TODAYS_HISTORIC_FIXINGS"

  var REFINE_TO_FULL_MACHINE_PRECISION_USING_HALLEYS_METHOD : String
      = "REFINE_TO_FULL_MACHINE_PRECISION_USING_HALLEYS_METHOD"

  var EVALUATION_DATE : String = "EVALUATION_DATE"

  private val attrs : ThreadAttributes = new ThreadAttributes()

  private class ThreadAttributes extends ThreadLocal[java.util.Map[String, AnyRef]] {

    override def initialValue : java.util.Map[String, AnyRef]  = {
      val map = new java.util.TreeMap[String, AnyRef]
      map.put(ENFORCES_TODAYS_HISTORIC_FIXINGS, false.asInstanceOf[AnyRef])
      map.put(NEGATIVE_RATES, false.asInstanceOf[AnyRef])
      map.put(EXTRA_SAFETY_CHECKS, true.asInstanceOf[AnyRef])
      map.put(TODAYS_PAYMENTS, true.asInstanceOf[AnyRef])
      map.put(USE_INDEXED_COUPON, false.asInstanceOf[AnyRef])
      map.put(REFINE_TO_FULL_MACHINE_PRECISION_USING_HALLEYS_METHOD, false.asInstanceOf[AnyRef])
      map.put(EVALUATION_DATE, new DateProxy)
      map
    }

  }


  private class DateProxy extends Date {

    def value : DateProxy = {
      if (isNull){
        super.assign(todaysSerialNumber)
      }
      this
    }

    def assign (date: Date) : Date = {
      super.assign(date.serialNumber)
      this
    }


  }

}

/**
 * @author Paul Bernard
 */
class Settings {

  import org.quantintel.ql.Settings._

  implicit def b2B(x: Boolean) = java.lang.Boolean.valueOf(x)

  def isNegativeRates : Boolean = {
    val varr : Object = attrs.get.get(NEGATIVE_RATES)
    if (varr==null) false else varr.asInstanceOf[Boolean]
  }

  def isExtraSafetyChecks : Boolean = {
    val varr: Object = attrs.get.get(EXTRA_SAFETY_CHECKS)
    if (varr==null) false else varr.asInstanceOf[Boolean]
  }

  def isTodaysPayments: Boolean = {
    val varr : Object = attrs.get().get(TODAYS_PAYMENTS)
    if (varr==null) false else varr.asInstanceOf[Boolean]
  }

  def isUseIndexedCoupon: Boolean = {
    val varr : Object = attrs.get().get(USE_INDEXED_COUPON)
    if(varr==null) false else varr.asInstanceOf[Boolean]
  }

  def isEnforcesTodaysHistoricFixings: Boolean = {
    val varr : Object = attrs.get().get(ENFORCES_TODAYS_HISTORIC_FIXINGS)
    if(varr==null) false else varr.asInstanceOf[Boolean]
  }

  def isRefineHighPrecisionUsingHalleysMethod : Boolean = {
    val varr : Object = attrs.get().get(REFINE_TO_FULL_MACHINE_PRECISION_USING_HALLEYS_METHOD)
    if (varr==null) false else varr.asInstanceOf[Boolean]
  }

  def setNegativeRates(negativeRates: Boolean) {
    attrs.get().put(NEGATIVE_RATES, negativeRates.asInstanceOf[AnyRef])
  }

  def setExtraSafetyChecks(extraSafetyChecks: Boolean) {
    attrs.get().put(EXTRA_SAFETY_CHECKS, extraSafetyChecks.asInstanceOf[AnyRef])
  }

  def setTodaysPayments(todaysPayments: Boolean) {
    attrs.get().put(TODAYS_PAYMENTS, todaysPayments.asInstanceOf[AnyRef])
  }

  def setUseIndexedCoupon(todaysPayments: Boolean) {
    attrs.get().put(USE_INDEXED_COUPON, todaysPayments.asInstanceOf[AnyRef])
  }


  def setEnforcesTodaysHistoricFixings(enforceTodaysHistoricFixings: Boolean) {
    attrs.get().put(ENFORCES_TODAYS_HISTORIC_FIXINGS, enforceTodaysHistoricFixings.asInstanceOf[AnyRef])
  }

  def setRefineHighPrecisionUsingHalleysMethod(refineToFullMachinePrecisionUsingHalleysMethod: Boolean) {
    attrs.get().put(REFINE_TO_FULL_MACHINE_PRECISION_USING_HALLEYS_METHOD, refineToFullMachinePrecisionUsingHalleysMethod.asInstanceOf[AnyRef])
  }

  def evaluationDate : Date = {
    attrs.get.get(EVALUATION_DATE).asInstanceOf[DateProxy].value
  }


  def setEvaluationDate(evaluation: Date) : Date = {
    null
  }




}

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

import org.quantintel.ql.pricingengines.{PricingEngine, Arguments, Results}
import org.quantintel.ql.util.LazyObject
import scala.collection.mutable.Map

object Instrument {

  val SHOULD_DEFINE_PRICING_ENGINE : String = "Should define pricing engine"
  val SETUP_ARGUMENTS_NOT_IMPLEMENTED : String = "Instrument#setupArguments() not implemented"

}

/**
 * @author Paul Bernard
 */
abstract class Instrument extends LazyObject  {

  import org.quantintel.ql.instruments.Instrument.SETUP_ARGUMENTS_NOT_IMPLEMENTED

  protected var m_engine : PricingEngine = null

  protected var m_NPV : Double = Double.NaN

  protected var m_errorEstimate : Double = 0.0

  protected def isExpired : Boolean

  protected def setupArguments(a: Arguments): Unit = {
    throw new Exception(SETUP_ARGUMENTS_NOT_IMPLEMENTED)
  }

  def setPricingEngine(engine: PricingEngine): Unit = {
    m_engine = engine
    update
  }

  def NPV : Double = {
    calculate
    m_NPV
  }

  def errorEstimate : Double = {
    calculate
    m_errorEstimate
  }

  def fetchResults(r: Results) {
    val results: ResultsImpl = r.asInstanceOf[ResultsImpl]
    m_NPV = results.value
    m_errorEstimate = results.errorEstimate
  }

  def setupExpired {
    m_NPV = 0.0
    m_errorEstimate = 0.0
  }

  override protected def performCalculations {
    m_engine.reset
    setupArguments(m_engine.getArguments)
    m_engine.getArguments.validate
    m_engine.calculate
    fetchResults(m_engine.getResults)
  }

  override protected def calculate {
    if (isExpired) {
      setupExpired
      calculated = true
    } else {
      super.calculate
    }

  }

  class ResultsImpl extends Results {

    var value : Double = Double.NaN

    var errorEstimate : Double = Double.NaN

    private val m_additionalResults = Map[String, Any]()

    def result(key: String) : Any = {
      m_additionalResults (key)
    }

    def additionalResults : Map[String, Any] = {
      m_additionalResults
    }

    def reset {
      value = Double.NaN
      errorEstimate = Double.NaN
      m_additionalResults.clear

    }

  }



}


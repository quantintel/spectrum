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

package org.quantintel.ql.math



/**
 * @author Paul Bernard
 */
abstract class AbstractSolver1D[F <: Double => Double]{

  val MAX_FUNCTION_EVALUATIONS = 100

  private var maxEvaluations : Int = MAX_FUNCTION_EVALUATIONS
  private var lowerBoundEnforced : Boolean = false
  private var upperBoundEnforced : Boolean = false

  protected var root, xMin, xMax, fxMin, fxMax : Double = 0.0
  protected var evaulationNumber: Int = 0
  protected var lowerBound, upperBound: Double = 0.0

  def this(maxEvaluations: Int, lowerBoundEnforced: Boolean,
            upperBoundenforced: Boolean) {
    this
    setMaxEvaluation(maxEvaluations)
    this.lowerBoundEnforced = lowerBoundEnforced
    this.upperBoundEnforced = upperBoundEnforced
  }

  def setMaxEvaluation(evaluations: Int): Unit = {
    this.maxEvaluations = math.max(1, evaluations)
  }

  def getMaxEvaluations : Int = {
    this.maxEvaluations
  }

  def setLowerBound(lowerBound: Double): Unit = {
    this.lowerBound = lowerBound
    this.lowerBoundEnforced = true
  }

  def setUpperBound(upperBound: Double): Unit = {

  }


}

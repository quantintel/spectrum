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

package org.quantintel.ql.model

import org.quantintel.ql.math.optimization.{ConstraintBase, Constraint}
import org.quantintel.ql.util.{Observability, Observable, Observer}

import scala.collection.mutable.ListBuffer


/**
 * @author Paul Bernard
 */
class CalibratedModel(nArguments: Int) extends Observer with Observable with Observability {

  import scala.collection.JavaConverters._

  private val parameter_array_to_small = "parameter array to small"
  private val parameter_array_to_big = "parameter array to big"

  protected val arguments : List[Parameter] =
    (new java.util.ArrayList[Parameter](nArguments)).asScala.toList
  protected var constraint : Constraint = ???

  override def update() {}






}

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

package org.quantintel.ql.termstructures

/**
 * @author Paul Bernard
 */

object Compounding extends Enumeration {

  type Compounding = Value
  val SIMPLE = Value(1)
  val COMPOUNDED = Value(2)
  val CONTINUOUS = Value(3)
  val SIMPLE_THEN_COMPOUNDED = Value(4)

  def valueOf(compounding: Int) : Compounding  = compounding match {
    case 1 => SIMPLE
    case 2 => COMPOUNDED
    case 3 => CONTINUOUS
    case 4 => SIMPLE_THEN_COMPOUNDED
    case _ => throw new Exception("Valid units = 1 to 4")
  }

}

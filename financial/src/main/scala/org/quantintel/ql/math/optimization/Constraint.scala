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

package org.quantintel.ql.math.optimization

import org.quantintel.ql.math.matrixutilities.Array

/**
 * @author Paul Bernard
 */
abstract class Constraint extends ConstraintBase {

  protected var impl : ConstraintBase = null

  def this(impl: ConstraintBase) {
    this
    this.impl = impl
  }

  def empty() : Boolean = {
    impl == null
  }

  def test(p: org.quantintel.ql.math.matrixutilities.Array) : Boolean = impl.test(p)

  def update(params: org.quantintel.ql.math.matrixutilities.Array,
             direction: org.quantintel.ql.math.matrixutilities.Array, beta: Double) : Double = {

    var diff: Double = beta
    var newParams : org.quantintel.ql.math.matrixutilities.Array = params + (direction * diff)
    var valid : Boolean = test(newParams)
    var icount: Int = 0
    while(!valid) {
      if(icount > 200) {
        throw new Exception("can't update parameter vector")
      }
      diff *= 0.5
      icount = icount + 1
      newParams = params + (direction * diff)
      valid = test(newParams)
    }
    params.fill(newParams)
    diff
  }



}

abstract class ConstraintBase {

  def test(params: org.quantintel.ql.math.matrixutilities.Array) : Boolean


}

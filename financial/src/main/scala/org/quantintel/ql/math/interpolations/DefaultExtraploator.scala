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

package org.quantintel.ql.math.interpolations

/**
 * @author Paul Bernard
 */
class DefaultExtraploator extends Extrapolator {

  private var extrapolate : Boolean = false

  def allowsExtrapolation() : Boolean = extrapolate

  def disableExtrapolation() {
    extrapolate = false
  }

  def enableExtrapolation() {
    extrapolate = true
  }
}

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
 */

package org.quantintel.ql.currencies

// Oceania

/**
 * Description:             Australian Dollar
 * ISO three-letter code:   AUD
 * Numerical Code:          36
 * Divided by:              100 cents
 */
object AUD {
  val iso = "AUD"
  val symbol = "A$"
  val desc = "Australian Dollar"
  val rounding = "%3% %1$.2f"
  val numCode = 36
  val divisor = 100
}

/**
 * Description:             New Zealand
 * ISO three-letter code:   NZD
 * Numerical Code:          554
 * Divided by:              100 cents
 */
object NZD {
  val iso = "NZD"
  val symbol = "NZ$"
  val desc = "New Zealand Dollar"
  val rounding = "%3% %1$.2f"
  val numCode = 554
  val divisor = 100
}

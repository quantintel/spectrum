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

package org.quantintel.ql.instruments.cap

import org.quantintel.ql.instruments.Instrument

/**
 * An interest rate cap is a derivative in which the buyer receives payment at the end
 * of each period in which the interest rate exceeds the agreed strike prices.  An
 * example of a cap would be an agreement to receive a payment for each month the LIBOR
 * rate exceeds 2.5%.
 * Similiarly an interest rate floor is a derivative contract in which the buyer receives
 * at the end of each period in which the interest reate is below the agreed stike price.
 * Caps and floors can be used to hedge agaist interest reate fluctuations.
 * source - Wikipedia 2014
 *
 * @author Paul Bernard
 */
class CapFloor extends Instrument {

  override protected def isExpired: Boolean = ???
}

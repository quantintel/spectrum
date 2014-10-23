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

package org.quantintel.ql.instruments.swap

import org.quantintel.ql.instruments.Instrument

/**
 * A variance swap is an over-the-counter financial derivative that allows one to speculate
 * on or hedge risks associated with the magnitude of movement, i.e. volatility, of some
 * underlying product, like an exchange rate, interest rate, or stock index.
 *
 * One leg of the swap will pay an amount based upon the realized variance of the price
 * changes of the underlying product. Conventionally, these prices changes will be
 * daily log returns, based upon the most commonly used closing price.  The other leg
 * of the swap will pay a fixed amount, which is the strike, quoted at the deal's inception.
 * Thus the net payoff to the counterparties will be the difference between these two and will
 * be settled in cash at the expiration of the deal, though some cash payments will likely be
 * made along the way by one or the other counterparty to maintain agreed upon margin.
 * source = Wikipedia 2014
 *
 * @author Paul Bernard
 */
class VarianceSwap extends Instrument {

  override protected def isExpired: Boolean = ???
}

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

package org.quantintel.ql.instruments.options

import org.quantintel.ql.instruments.Instrument

/**
 * An Option is a financial derivative that represents a contract sold by one party (option
 * writer) to another party (option holder).  The contract offers the buyer the right,
 * but not the obligation, to buy (call) or sell (put) a security or other financial
 * asset at an agreed-upon price (the strike price) during a certain period of time or
 * on a specified date (the exercise date).
 *
 * @author Paul Bernard
 */
class Option extends Instrument {

  override protected def isExpired: Boolean = ???
}

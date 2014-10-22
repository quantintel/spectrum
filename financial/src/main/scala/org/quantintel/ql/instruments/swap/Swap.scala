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
 * Swap is the exchange of one security for another to change the maturity
 * (bonds), quality of issues(stocks or bonds), or because investment objectives
 * have changed.  Swaps have also grown to include currency swaps and interst
 * rate swaps
 * -source Investopedia 2014
 *
 * @author Paul Bernard
 */
class Swap extends Instrument {

  override protected def isExpired: Boolean = ???
}

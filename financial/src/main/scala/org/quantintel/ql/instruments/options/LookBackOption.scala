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

/**
 * Lookback options, are a type of exotic option with path dependency, amoung many other kind
 * of options.  The payoff depends on the optimal(maximum or minimum) underlying asset's price
 * occuring over the life of the option.  The option allows the holder to "look back" over time
 * to determine the payoff.  There exists two kinds of lookback options: the floating strike
 * and fixed strike.
 * source: Wikipedia 2014
 *
 * @author Paul Bernard
 */
class LookBackOption {

}

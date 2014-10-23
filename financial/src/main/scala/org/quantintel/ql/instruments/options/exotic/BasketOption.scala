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

package org.quantintel.ql.instruments.options.exotic

import org.quantintel.ql.instruments.options.Option

/**
 * A Basket option is a financial derivative, more specifically an exotic option whose underlying
 * is a (weighted) sum or averge of different assets that have been grouped together in a basket.
 * For example an index option, where a number of stocks have been gruped together in an index
 * and the option is based upon the price of the index.
 * source - Wikipedia  2014
 *
 * @author Paul Bernard
 */
class BasketOption extends Option {

}

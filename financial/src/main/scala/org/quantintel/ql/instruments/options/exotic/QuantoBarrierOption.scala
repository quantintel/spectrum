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

/**
 *
 * An exotic barrier option whose payoff is often paid in a currency other than the
 * underlying currency pair.  For instance a barrier option can have the USD/JPY pair
 * as its underlying exchange rate while its payoff is denominated in EUR.  The exhange
 * rate for the EUR/JPY is pre-specified.
 * This option has the features of both a quanto option and a barrier option.
 * source www.majdbakir.com 2014
 *
 * @author Paul Bernard
 */
class QuantoBarrierOption {

}

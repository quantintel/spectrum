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
 *
 * A Barrier Option is an exotic derivative typically an option on the underyling asset
 * whose price reaching the pre-set barrier level either springs the option into
 * existence or extinguishes an already exising option.
 * - Where the option springs into existence on the price of the underlying asset breaching a barrier,
 * it may be known as an "up and in", or "down and in" option
 * - Where the option is extinguished on the price of the underlying asset breaching a barrier, it
 * may be known as an "up and out", "knick-out" or "down and out" option.
 * source - Wikipedia 2014
 *
 * @author Paul Bernard
 */
class BarrierOption {

}

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
 * A standard otpion with no special or add-on features.  Generally, vanilla options have standard
 * and well-defined properties and trade actively.  This category of options includes only those
 * bearing the most common components, specifically expiration date and strike price.  In contrast
 * to exotic options, there are American-Style and European style options which belong to this
 * category and only come in the form of either a put or a call.
 * - source www.investment-and-finance.net 2014
 *
 * American Style option - An option that can be exercised at any time before, and including, expiration
 * date.
 * European Style option - An option that can only be exercised on expiration date.
 *
 * @author Paul Bernard
 */
class VanillaOption {

}

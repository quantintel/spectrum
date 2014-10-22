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

/**
 * Vanilla Swap is a standard swap structure in which swaps have generic or will-defined features,
 * expecially in relation to coupons, notional principal, swap legs, etc.  For example, a vanilla
 * interest rate swap is an agreement in which a company agrees to pay a stream of cash flows equal
 * to interest at a prespecified fixed reate on a notional amount for a specified period.  The company
 * receives, in return, a promise from a counterparty to pay a stream of cash flows computed as interest
 * at a floating rate on the same notional amount for the same period of time.  The form of a swap is
 * the simplets, embedding no add-ons or additional featurs.
 *
 * At inititation, most vanilla swaps have no economic value, i.e. neither counterparty would be
 * required to pay the other any amount at the early stage.
 *
 * Vanilla Swaps (opposite flavored swaps) are also know as basic swaps.
 * source majdbakir.com 2014
 *
 * @author Paul Bernard
 */
class VanillaSwap extends Swap {

}

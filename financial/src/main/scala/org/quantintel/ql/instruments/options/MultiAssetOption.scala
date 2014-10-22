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
 * An exotic option whose payout depends on the overall performance of more than one underlying
 * asset, as opposed to a standard vanilla option which typically involves a single underlying
 * asset.  In this sense, a two-asset option is a special case of a multi-asset option, where
 * the number of underlying assets is limited to to, and hence the option is said to be two
 * dimensional.
 *
 * A prime example of a multi-asset option is a cross-currency option that entails the exchange
 * of more than two currencies against a base currency.  The option buyer purchases the right to
 * receive designated currencies in exchange for a base currency, either at a preset exchange
 * rate or at the prevailing foreign exchange  rate.  Another example is an option on a basket
 * of stocks.  A basket call option may be based on three stocks.  If the value of the overall set
 * of stocks is above the strike price, it would be better for the option's holder to buy all the
 * stocks in the basket.
 *
 * In general, multi-asset options can be classified as American multi-asset options(multi-asset
 * American options) and European multi-asset options (multi-asset European options).  For a
 * European multi-asset option, there are three broad categories: rainbow options, basket options,
 * and quanto options.
 *
 * Source www.invest-and-finance.net 2014
 *
 * @author Paul Bernard
 */
class MultiAssetOption {

}

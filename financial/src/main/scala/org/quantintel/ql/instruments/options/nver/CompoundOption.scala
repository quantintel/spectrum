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

package org.quantintel.ql.instruments.options.nver

import org.quantintel.ql.instruments.options.Option

/**
 * A compound option or split-free option is an option on an option.  The exercise payoff
 * of a compound option involves the value of another option.  A compound option then has two
 * expiration dates and two strike prices.  Usually, compounded options are used for
 * currency or fixed income markets where insecurity exists regarding th eoption's risk
 * protection.  Another common business application that compound options are used for is to
 * hedge bids for business projects that may or may not be accpeted.
 *
 * @author Paul Bernard
 */
class CompoundOption extends Option {

}

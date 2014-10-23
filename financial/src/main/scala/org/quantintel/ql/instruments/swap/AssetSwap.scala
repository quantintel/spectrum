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
 *
 * An intrument representing the exchange of the flow of payments from a given security (the asset)
 * for a different set of cash flows.  An example of this is where an institution swaps the cash
 * flow on a US Government Bond for LIBOR minus a spread(say 20 basis points).  Such swaps usually
 * have stub period in order to bring the chronology of the cash flow into line with that of the
 * underlying bond.
 * source: Wikipedia 2014
 *
 * @author Paul Bernard
 */
class AssetSwap extends Swap {

}

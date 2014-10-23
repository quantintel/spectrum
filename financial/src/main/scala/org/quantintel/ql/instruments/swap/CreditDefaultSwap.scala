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
 * Credit Default Swap (also known as: CDS) is a swap designed to transfer the credit
 * exposure of fixed income products between parties.  A credit default swap is also
 * refered to a a credit derivative contract, where the purchaser of the swap makes
 * payments up until the maturity date of a contract.  Payments are made to the seller
 * of the swap.  In return, the seller agrees to pay off a third party debt if this party
 * defaults on the loan.  A CDS is considered insurance against non-payment.  A buyer of a CDS
 * might be speculating on the possibility that the third party will indeed default.
 * source- Investopedia
 *
 * @author Paul Bernard
 */
class CreditDefaultSwap extends Instrument {

  override protected def isExpired: Boolean = ???
}

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

package org.quantintel.ql.indexes.ibor

import org.quantintel.ql.quotes.Handle
import org.quantintel.ql.termstructures.YieldTermStructure
import org.quantintel.ql.time.Date

class Euribor365 extends IborIndex {

  override protected def forecastFixing(fixingDate: Date): Double = ???

  override def maturityDate(valueDate: Date): Date = ???

  override def termStructure(): Handle[YieldTermStructure] = ???

  class Euribor365_SW extends Euribor {}

  class Euribor365_2W extends Euribor {}
  class Euribor365_3W extends Euribor {}

  class Euribor365_1M extends Euribor {}
  class Euribor365_2M extends Euribor {}
  class Euribor365_3M extends Euribor {}
  class Euribor365_4M extends Euribor {}
  class Euribor365_5M extends Euribor {}
  class Euribor365_6M extends Euribor {}
  class Euribor365_7M extends Euribor {}
  class Euribor365_8M extends Euribor {}
  class Euribor365_9M extends Euribor {}
  class Euribor365_10M extends Euribor {}
  class Euribor365_11M extends Euribor {}

  class Euribor365_1Y extends Euribor {}
}

object Euribor365 {

  def apply() : Euribor365 = new Euribor365()


}

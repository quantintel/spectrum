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

/**
 * @author Paul Bernard
 */

/*
familyName: String, tenor: Period, fixingDays: Int, currency: Currency,
                 fixingCalendar: Calendar, convention: BusinessDayConventionEnum,
                 endOfMonth: Boolean, dayCounter: Boolean, h: Handle[YieldTermStructure]
 */
class EurLibor  extends IborIndex {

  override protected def forecastFixing(fixingDate: Date): Double = ???

  override def maturityDate(valueDate: Date): Date = ???

  override def termStructure(): Handle[YieldTermStructure] = ???

  /*
  class EuriborON extends EurLibor {}
  class EuriborSW extends EurLibor {}

  class Euribor2W extends EurLibor {}

  class Euribor1M extends EurLibor {}
  class Euribor2M extends EurLibor {}
  class Euribor3M extends EurLibor {}
  class Euribor4M extends EurLibor {}
  class Euribor5M extends EurLibor {}
  class Euribor6M extends EurLibor {}
  class Euribor7M extends EurLibor {}
  class Euribor8M extends EurLibor {}
  class Euribor9M extends EurLibor {}
  class Euribor10M extends EurLibor {}
  class Euribor11M extends EurLibor {}

  class Euribor1Y extends EurLibor {}
  */
  override def update(): Unit = ???
}

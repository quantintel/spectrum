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

package org.quantintel.ql.time.calendars

import org.quantintel.ql.time.Calendar
import org.scalatest.{Matchers, FlatSpec}

/**
 * @author Paul Bernard
 */
class CzechRepublicTest extends FlatSpec with Matchers {

  "1. Czech Republic Calendar - default apply" should "be Czech Republic" in {
    val cal : Calendar = CzechRepublic()
    assert(cal.name == "Prague stock exchange of Czech Republic")
  }

  import org.quantintel.ql.time.calendars.CzechRepublicEnum._

  "1. Czech Republic Calendar - apply(PSE)" should "be Czech Republic" in {
    val cal : Calendar = CzechRepublic(PSE)
    assert(cal.name == "Prague stock exchange of Czech Republic")
  }

}

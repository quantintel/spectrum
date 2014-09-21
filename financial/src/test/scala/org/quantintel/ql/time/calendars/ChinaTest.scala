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
 * @author Paul Beranrd
 */
class ChinaTest extends FlatSpec with Matchers {

  "1. China Calendar - default apply" should "be China" in {
    val cal : Calendar = China()
    assert(cal.name == "Shanghai stock exchange")
  }


  import org.quantintel.ql.time.calendars.ChinaEnum._

  "2. China Calendar - default apply" should "be China" in {
    val cal : Calendar = China(SSE)
    assert(cal.name == "Shanghai stock exchange")
  }

}

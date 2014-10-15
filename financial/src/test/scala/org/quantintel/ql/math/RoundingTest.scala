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

package org.quantintel.ql.math

import org.scalatest.{Matchers, FlatSpec}

/**
 * @author Paul Bernard
 */
class RoundingTest extends FlatSpec with Matchers {

  "integer values" should "in correct sequence" in {
    assert(RoundingMethods.NONE.id == 1)
    assert(RoundingMethods.UP.id == 2)
    assert(RoundingMethods.DOWN.id == 3)
    assert(RoundingMethods.CLOSEST.id == 4)
    assert(RoundingMethods.FLOOR.id == 5)
    assert(RoundingMethods.CEILING.id == 6)
  }

  "integer valueOf" should "in correct sequence" in {
    import org.quantintel.ql.math.RoundingMethods._
    assert(RoundingMethods.valueOf(1) == NONE)
    assert(RoundingMethods.valueOf(2) == UP)
    assert(RoundingMethods.valueOf(3)== DOWN)
    assert(RoundingMethods.valueOf(4) == CLOSEST)
    assert(RoundingMethods.valueOf(5)== FLOOR)
    assert(RoundingMethods.valueOf(6) == CEILING)
  }

  "No rounding" should "be no change" in {
    import org.quantintel.ql.math.RoundingMethods._
    assert(Rounding().round(50.12345)==50.12345)
    assert(Rounding(NONE, 5).round(50.12345)==50.12345)
  }

  "DOWN should" should "truncate past the point of precision" in {
    import org.quantintel.ql.math.RoundingMethods._
    assert(Rounding(DOWN, 5).round(50.1234599)==50.12345)
  }

  "UP should" should "round up the digit after the point of precision 1" in {
    import org.quantintel.ql.math.RoundingMethods._
    assert(Rounding(UP, 5).round(50.1234599)==50.12346)
  }

  "UP should" should "round up the digit after the point of precision 2" in {
    import org.quantintel.ql.math.RoundingMethods._
    assert(Rounding(UP, 5).round(50.1234533)==50.12346)
  }

  "CLOSEST should" should "round up the digit after the point of precision 50.12345" in {
    import org.quantintel.ql.math.RoundingMethods._
    assert(Rounding(CLOSEST, 5).round(50.1234533)==50.12345)
  }

  "CLOSEST should" should "round up the digit after the point of precision 50.12346" in {
    import org.quantintel.ql.math.RoundingMethods._
    assert(Rounding(CLOSEST, 5).round(50.1234553)==50.12346)
  }


  "FLOOR should" should "round up the digit after the point of precision 50.12346" in {
    import org.quantintel.ql.math.RoundingMethods._
    assert(Rounding(FLOOR, 5).round(50.1234553)==50.12346)
  }

  "FLOOR should" should "round up the digit after the point of precision -50.12345" in {
    import org.quantintel.ql.math.RoundingMethods._
    assert(Rounding(FLOOR, 5).round(-50.1234553)== -50.12345)
  }

  "CEILING should" should "round up the digit after the point of precision -50.12346" in {
    import org.quantintel.ql.math.RoundingMethods._
    assert(Rounding(CEILING, 5).round(-50.1234553)== -50.12346)
  }

  "CEILING should" should "round up the digit after the point of precision -50.12345" in {
    import org.quantintel.ql.math.RoundingMethods._
    assert(Rounding(CEILING, 5).round(50.1234553)== 50.12345)
  }

}

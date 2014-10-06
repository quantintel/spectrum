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

package org.quantintel.ql.currencies


import org.quantintel.ql.math.Rounding
import org.scalatest.{Matchers, FlatSpec}


/**
 * @author Paul Bernard
 */
class AfricaTest extends FlatSpec with Matchers {

  "1. Africa ZAR default constructor" should "equal" in {

    val cur = Africa()
    assert(cur.name == "South-African rand")
    assert(cur.code == "ZAR")
    assert(cur.numericCode == 710)
    assert(cur.symbol == "R")
    assert(cur.fractionSymbol == "")
    assert(cur.fractionPerUnit == 100)
    assert(cur.rounding.isInstanceOf[Rounding])
    assert(cur.formatString == "%3% %1$.2f")

  }

  "1. Africa ZAR with factory approach" should "equal" in {

    val cur = Africa(AfricaEnum.ZAR)
    assert(cur.name == "South-African rand")
    assert(cur.code == "ZAR")
    assert(cur.numericCode == 710)
    assert(cur.symbol == "R")
    assert(cur.fractionSymbol == "")
    assert(cur.fractionPerUnit == 100)
    assert(cur.rounding.isInstanceOf[Rounding])
    assert(cur.formatString == "%3% %1$.2f")

  }


}
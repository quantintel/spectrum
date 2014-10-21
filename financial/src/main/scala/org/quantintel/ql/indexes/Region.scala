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

package org.quantintel.ql.indexes



/**
 * @author Paul Bernard
 */
class Region(var name: String, var code: String) {

  def this() {
    this("","")
  }

  def CustomRegion(name: String, code: String): Region = new Region(name, code)
  def AustraliaRegion() : Region = new AustraliaRegion()
  def EURegion() : Region = new EURegion()
  def FranceRegion() : Region = new FranceRegion()
  def UKRegion() : Region = new UKRegion()
  def USRegion() : Region = new USRegion()
  def ZARRegion() : Region = new ZARRegion()

  class CustomRegion(name: String, code: String) extends Region {
  }

  class AustraliaRegion extends Region {
    name = "Australia"
    code = "AU"
  }

  class EURegion extends Region {
    name = "EU"
    code = "EU"
  }

  class FranceRegion extends Region {
    name = "France"
    code = "FR"
  }

  class UKRegion extends Region {
    name = "UK"
    code = "UK"

  }

  class USRegion extends Region {
    name = "US"
    code = "US"

  }

  class ZARRegion extends Region {
     name = "ZAR"
      code = "ZAR"
  }

}

object Region {

  def apply() : Region = new Region()
  def apply(name: String, code: String) = new Region(name, code)

  def == (r1: Region, r2: Region) : Boolean = r1.name == r2.name
  def != (r1: Region, r2: Region) : Boolean = r1.name != r2.name


}

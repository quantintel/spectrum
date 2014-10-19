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

object Region {

  def == (r1: Region, r2: Region) : Boolean = {
    r1.name.equals(r2.name)
  }

  def != (r1: Region, r2: Region) : Boolean = {
    !(r1.name.equals(r2.name))
  }

}

/**
 * @author Paul Bernard
 */
abstract class Region {

  protected var data : Data = null

  protected class Data(val name: String, val code: String)

  def name : String = data.name

  def code : String = data.code

}



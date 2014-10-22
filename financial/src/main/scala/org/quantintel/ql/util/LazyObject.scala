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

package org.quantintel.ql.util

/**
 * @author Paul Bernard
 */
abstract class LazyObject  extends Observer with Observable with Observability {

  protected var calculated: Boolean = false

  protected var frozen: Boolean = false

  protected def performCalculations()


  def recalculate (){
    val wasFrozen  : Boolean = frozen
    calculated = false
    frozen = false
    try {
      calculate()
    } finally {
        frozen = wasFrozen
        // TODO: notifyObservers
    }

  }

  def freeze() {
    frozen = true
  }

  def unfreeze() {
    frozen = false
  }

  protected def calculate() {
     if(!calculated && !frozen){
       calculated = true
       try {
         performCalculations()
       } catch {
         case e : ArithmeticException =>
           calculated = false
           throw e

       }

     }
  }

  def update(){
      calculated = false
  }




}

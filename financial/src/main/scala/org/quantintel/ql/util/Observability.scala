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
trait Observability extends Observable {

  val delegatedObservable: DefaultObservable = new DefaultObservable(this)

  override def addObserver(observer: Observer ) {
    delegatedObservable.addObserver(observer)
  }

  override def countObservers() : Int = {
    delegatedObservable.countObservers()
  }

  override def deleteObserver(observer: Observer) {
    delegatedObservable.deleteObserver(observer)
  }

  override def notifyObservers() {
    delegatedObservable.notifyObservers()
  }

  override def notifyObservers(arg: Any) {
    delegatedObservable.notifyObservers(arg)
  }

  override def deleteObservers() {
    delegatedObservable.deleteObservers()
  }

  override def getObservers: java.util.List[Observer]  =  {
    delegatedObservable.getObservers
  }


}

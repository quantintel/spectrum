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

import java.util.concurrent.CopyOnWriteArrayList
import java.util.{List => JList, Collections => JCollections}
import scala.collection.JavaConverters._

/**
 * @author Paul Bernard
 */
class DefaultObservable(observable: Observable) extends Observable {

  val observers: JList[Observer] = new CopyOnWriteArrayList[Observer]()


  override def addObserver(observer: Observer): Unit =
      observers.add(observer)

  override def countObservers(): Int =
      observers.size()

  override def deleteObserver(observer: Observer): Unit =
    observers.remove(observer)

  override def getObservers(): JList[Observer] =
    JCollections.unmodifiableList(this.observers)

  override def deleteObservers(): Unit =
    observers.clear

  override def notifyObservers(): Unit =
    notifyObservers(null)

  override def notifyObservers(arg: Any): Unit = {
    try {
      observers.asScala.foreach((o : Observer) => wrappedNotify(o, observable, arg))
    } catch {
      case e: Exception => { throw new Exception(DefaultObservable.CANNOT_NOTIFY_OBSERVERS, e)}
    }


  }

  def wrappedNotify(observer: Observer, observable: Observable, arg: Any): Unit = {
    observer.update
  }



}

object DefaultObservable {

  val OBSERVABLE_IS_NULL = "observable is null"
  val CANNOT_NOTIFY_OBSERVERS = "could not notify one or more observers"
}

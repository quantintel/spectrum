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

import scala.collection.JavaConverters._
import java.lang.ref.{WeakReference => JWeakReference}

/**
 * @author Paul Bernard
 */
class WeakReferenceObservable(observable: Observable) extends DefaultObservable(observable) {

  override def addObserver(referent: Observer) {
    super.addObserver(new WeakReferenceObserver(referent))
  }

  override def deleteObserver(observer: Observer){
    getObservers.asScala.foreach(
      (weakObserver : Observer) => {
        val weakReference: WeakReferenceObserver = weakObserver.asInstanceOf[WeakReferenceObserver]
        val referent : Observer = weakReference.get()
        if (referent == null || referent == observer) deleteWeakReference(weakReference)
      }
    )
  }

  private def deleteWeakReference(observer: WeakReferenceObserver)  {
    super.deleteObserver(observer)
  }


  private class WeakReferenceObserver(observer: Observer) extends JWeakReference(observer) with Observer {
    override def update(){
      val referent : Observer = get()
      if (referent != null) referent.update() else deleteWeakReference(this)
    }
  }

}

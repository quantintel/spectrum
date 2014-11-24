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

package org.quantintel.ql.quotes


import java.util

import org.quantintel.ql.util.{WeakReferenceObservable, Observer, Observable}

/**
 * @author Paul Bernard
 */
class Handle[T <: Observable](var observable: T) extends Observable {

  private var link: Link = new Link(this)
  private var isObserver : Boolean = false

  internalLinkTo(observable, registerAsObserver = true)

  def this() {
    this(null.asInstanceOf[T])
    this.link = new Link(this)
    this.isObserver = true
  }

  def this(observable: T, registerAsObserver: Boolean) {
     this(null.asInstanceOf[T])
     link = new Link(this)
     internalLinkTo(observable, registerAsObserver)
  }

  def empty() : Boolean = this.observable == null

  def currentLink : T = this.observable


  override def addObserver(observer: Observer): Unit = ???

  override def countObservers(): Int = ???

  override def deleteObserver(observer: Observer) : Unit = ???

  override def getObservers: util.List[Observer]  = ???

  override def deleteObservers() : Unit =  ???

  override def notifyObservers() : Unit =  ???

  override def notifyObservers(arg: Any) : Unit = ???


  def internalLinkTo(observable: T)  {
    this.internalLinkTo(observable, registerAsObserver = true)
  }

  def internalLinkTo(observable: T, registerAsObserver: Boolean)  {

    if(this.observable!=observable || this.isObserver!=registerAsObserver){
      if(this.observable!=null && this.isObserver){
          this.observable.deleteObserver(link)
      }
      this.observable = observable
      this.isObserver = registerAsObserver
      if(this.observable!=null && this.isObserver){
        this.observable.addObserver(link)
      }
      if(this.observable!=null) {
        this.observable.notifyObservers()
      }
    }
  }


  private class Link(observable: Observable) extends WeakReferenceObservable(observable) with Observer {

      def update(){
        if(observable!=null) super.notifyObservers()
      }
  }



}

object Handle {

  val EMPTY_HANDLE = "empty Handle cannot be dereferenced"

}

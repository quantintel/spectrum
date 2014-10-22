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


  override def addObserver(observer: Observer)= ???

  override def countObservers(): Int = ???

  override def deleteObserver(observer: Observer) =  ???

  override def getObservers: util.List[Observer] = ???

  override def deleteObservers()= ???

  override def notifyObservers() = ???

  override def notifyObservers(arg: Any) = ???


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

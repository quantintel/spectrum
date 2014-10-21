package org.quantintel.ql.util

import java.util.{List => JList}

/**
 * Created by paulbernard on 10/21/14.
 */
class ObservableValue[T](var value: T) extends Observable {

  def this(observable: ObservableValue[T]){
    this(observable.value)
  }

  def assign(value: T){
    this.value = value
  }

  def assign(observable: ObservableValue[T]): Unit ={
    value = observable.value
    delegatedObservable.notifyObservers
  }

  val delegatedObservable: Observable = new DefaultObservable(this)


  override def addObserver(observer: Observer): Unit =
      delegatedObservable.addObserver(observer)

  override def countObservers(): Int =
      delegatedObservable.countObservers()

  override def deleteObserver(observer: Observer): Unit =
      delegatedObservable.deleteObserver(observer)

  override def getObservers(): JList[Observer] =
      delegatedObservable.getObservers()

  override def deleteObservers(): Unit =
      delegatedObservable.deleteObservers()

  override def notifyObservers(): Unit =
      delegatedObservable.notifyObservers()

  override def notifyObservers(arg: Any): Unit =
      delegatedObservable.notifyObservers(arg)
}

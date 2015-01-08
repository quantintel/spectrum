package org.quantintel.lang.collections.distributed

/**
 * @author Paul Bernard
 */
trait Collection[K, V] {

  def get(key: K): V
  def getAll(keys: List[K]): List[V]
  def put(key: K, obj: V)
  def putIfAbsent(key: K, obj: V): Boolean
  def putAll(map : Map[K, V])
  def remove(key: K): Boolean
  def removeAll(keys: List[K])
  def removeAll()
  def clear()
  def getAndRemove(key: K) : V
  def contains(key: K)
  def replace(key: K, oldValue: V, newValue: V): Boolean
  def replace(key: K, value: V): Boolean

}

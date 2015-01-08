package org.quantintel.lang.collections.distributed.infinispan

import org.quantintel.lang.collections.distributed.Collection

/**
 * @author Paul Bernard
 */
class InfinispanCollectionImpl[K, V] extends Collection {

  //val cache  = new DefaultCacheManager().getCache()

  def get(key: K): V = ???

  def removeAll(keys: List[K]): Unit = ???

  def removeAll(): Unit = ???

  def replace(key: K, oldValue: V, newValue: V): Boolean = ???

  def replace(key: K, value: V): Boolean = ???

  def clear(): Unit = ???

  def put(key: K, obj: V): Unit = ???

  def getAndRemove(key: K): V = ???

  def remove(key: K): Boolean = ???

  def putIfAbsent(key: K, obj: V): Boolean = ???

  def contains(key: K): Unit = ???

  def putAll(map: Map[K, V]): Unit = ???

  def getAll(keys: List[K]): List[V] = ???
}

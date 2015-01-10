package org.quantintel.lang.collections.distributed.infinispan

import org.quantintel.lang.collections.distributed.Collection

/**
 * @author Paul Bernard
 */
class InfinispanCollectionImpl[K, V] extends Collection[K, V] {

  //val cache  = new DefaultCacheManager().getCache()
  def get(key: K): Option[V] = None

  def removeAll(keys: List[K]): Boolean = false

  def removeAll(): Unit = {}

  def replace(key: K, oldValue: V, newValue: V): Boolean = false

  def replace(key: K, value: V): Boolean = false

  def clear(): Unit = {}

  def put(key: K, obj: V): Unit = {}

  def getAndRemove(key: K): Option[V] = None

  def remove(key: K): Boolean = false

  def putIfAbsent(key: K, obj: V): Boolean = false

  def contains(key: K): Unit = {}

  def putAll(map: Map[K, V]): Unit = {}

  def getAll(keys: List[K]): Option[List[V]] = None

}

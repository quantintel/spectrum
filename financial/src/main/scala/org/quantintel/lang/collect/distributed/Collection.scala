package org.quantintel.lang.collect.distributed

import org.quantintel.ql.time.Calendar

/**
 * @author Paul Bernard
 */
trait Collection[K, V] {

  def get(key: K): Option[V]
  def put(key: K, obj: V)
  def putIfAbsent(key: K, obj: V) : Option[V]
  def putAll(map : Map[K, V])
  def remove(key: K): Option[V]
  def clear()
  def clearAsync()
  def containsKey(key: K) : Boolean
  def containsValue(value: V): Boolean
  def replace(key: K, oldValue: V, newValue: V): Boolean
  def replace(key: K, value: V): Option[V]

}

object Cache {

  import org.quantintel.lang.collect.distributed.infinispan.InfinispanCollectionImpl

  val calendar : Collection[String, Calendar] = new InfinispanCollectionImpl[String, Calendar]("calendar")


}

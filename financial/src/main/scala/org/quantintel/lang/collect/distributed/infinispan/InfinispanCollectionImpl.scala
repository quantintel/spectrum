package org.quantintel.lang.collect.distributed.infinispan

import org.infinispan.Cache
import org.infinispan.configuration.cache.{ConfigurationBuilder, Configuration}
import org.infinispan.manager.{EmbeddedCacheManager, DefaultCacheManager}
import org.quantintel.lang.collect.distributed.Collection
import collection.JavaConversions._


/**
 * @author Paul Bernard
 */
class InfinispanCollectionImpl[K, V](name: String) extends Collection[K, V] {

  val config : Configuration  = new ConfigurationBuilder()
    .jmxStatistics()
    .enable()
    .build()


  val manager: EmbeddedCacheManager = new DefaultCacheManager()
  manager.defineConfiguration(name, config)

  val cache : Cache[K, V] = manager.getCache[K, V](name)

  def get(key: K): Option[V] = {
    val v = cache.get(key)
    if (v==null) None else Some(v)
  }


  def replace(key: K, oldValue: V, newValue: V) : Boolean =  cache.replace(key, oldValue, newValue)


  def replace(key: K, value: V): Option[V] = {
    val v = cache.replace(key, value)
    if (v==null) None else Some(v)
  }

  def clear() { cache.clear() }

  def clearAsync() { cache.clearAsync() }

  def put(key: K, obj: V) {
    cache.put(key, obj)
  }

  def remove(key: K): Option[V] = {
    val v = cache.remove(key)
    if (v==null) None else Some(v)
  }

  def putIfAbsent(key: K, obj: V): Option[V] = {
    val v = cache.putIfAbsent(key, obj)
    if (v==null) None else Some(v)
  }

  def containsKey(key: K) : Boolean = cache.containsKey(key)

  def containsValue(value: V) : Boolean = cache.containsValue(value)

  def putAll(map: Map[K, V]) {
    cache.putAll(map)
  }



}

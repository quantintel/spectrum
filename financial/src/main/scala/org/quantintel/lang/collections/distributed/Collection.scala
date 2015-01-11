package org.quantintel.lang.collections.distributed

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

class SessionBasedCollection(sid: String){

  import org.quantintel.lang.collections.distributed.infinispan.InfinispanCollectionImpl

  val calendar : Collection[String, Calendar] = new InfinispanCollectionImpl[String, Calendar]("calendar")
  // add other object caches as required

}

object Cache {

  import scala.collection.mutable.{Map, HashMap}

    val sessionCaches : Map[String, SessionBasedCollection] = new HashMap[String, SessionBasedCollection]

    def get(sid: String) : SessionBasedCollection = {
      val v = sessionCaches.get(sid)
      v match {
        case Some(res) => { res }
        case None => {
          sessionCaches.put(sid, new SessionBasedCollection(sid))
          Cache.get(sid)
        }
      }
    }



}

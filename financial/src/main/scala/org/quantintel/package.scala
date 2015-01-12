package org

import org.quantintel.lang.collections.distributed.{Cache, SessionBasedCollection}

/**
 * @author Paul Bernard
 */
package object quantintel {

  /**
   * Returns a session given a session id
   * @param id session id
   * @return A session
   */
  def session(id: String) : SessionBasedCollection = Cache.get(id)

}

/*
 * Copyright (c) 2014-2015  Paul Bernard
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

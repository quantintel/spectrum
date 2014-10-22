/*
 * Copyright (c) 2014  Paul Bernard
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

package org.quantintel.ql.instruments.options

/**
 *
 * A cliquet option or 'ratchet option" is an exotic option consisting of a series
 * of consecutive forward start options.  The first is active immediately.  The
 * second becomes active when the first expires, ect.  Each option is struck at-th-
 * money when it becomes active.
 * A cliqet is, therefore, a series of at-the-money options but where the total
 * premium is determined in advance.  A cliquet can be thought of a series of "pre-
 * purchased" at-the-money options.  The payout on each option can either be paid at
 * the final maturity, or at th eend of each reset period.
 * source- Wikipedia 2014
 *
 * @author Paul Bernard
 */
class CliquetOption {

}

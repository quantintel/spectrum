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

package org.quantintel.ql.instruments.options.nver

import org.quantintel.ql.instruments.options.Option

/**
 * A Bermuda Option is a type of exotic option that cn be exercised only a a predetermined dates,
 * typically every month.  Bermuda options are a combination of American and European options.
 * American options are exercisable anytime between the purchase date and the date of expiration.
 * European options, conversely, are exercisable only at the date of expiration.  Bermuda options are
 * exercisable at the date of expiration, and on certain specified dates that occur bewteen the
 * purchase date and the date of expiration.  Other exotic options include binary options and quanity
 * adjusting options, often called quanto options for short.
 *
 * @author Paul Bernard
 */
class BermudanOption extends Option {

}

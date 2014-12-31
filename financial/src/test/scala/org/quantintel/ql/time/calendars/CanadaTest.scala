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

package org.quantintel.ql.time.calendars

import org.quantintel.ql.time.Calendar
import org.scalatest.{Matchers, FlatSpec}
import org.quantintel.ql.time.Date

/**
 * @author Paul Bernard
 * @author Peter Mularien
 */
class CanadaTest extends FlatSpec with Matchers {

  "1. Canada Calendar - default apply" should "be Canada" in {
    val cal : Calendar = Canada()
    assert(cal.name == "Canada")
  }

  import org.quantintel.ql.time.calendars.CanadaEnum._

  "2. Canada Calendar - parameterized apply(CANADA)" should "be Canada" in {
    val cal : Calendar = Canada(SETTLEMENT)
    assert(cal.name == "Canada")
  }

  "3. Canada Calendar - parameterized apply(TSX)" should "be TSX" in {
    val cal : Calendar = Canada(TSX)
    assert(cal.name == "TSX")
  }

  // the following holidays were manually selected to ensure rules are followed in exceptional
  // circumstances - we may perhaps come up with a better method in the future
  
  val cal : Calendar = Canada(SETTLEMENT)
  
  // holiday testing
  "Canada settlement calendar" must "observe the New Year's Holiday" in {
    // new year's - select dates
    assert(cal.isHoliday(CalendarTestFixture.NewYears_OnSaturday))
    assert(cal.isHoliday(new Date(1, 1, 1901)))
    // new year's Monday
    assert(cal.isHoliday(CalendarTestFixture.NewYears_OnMonday))
    // new year's Monday on the 2nd
    assert(cal.isHoliday(CalendarTestFixture.NewYears_ObservedOnMondayJan2))
    // make sure no other 2nd's are marked as holiday
    assert(!cal.isHoliday(new Date(2, 1, 2007)))
    // note - Jan 2, 2000 is a Sunday, so this will return true, even though it's not a holiday
    //    assert(!cal.isHoliday(new Date(2, 1, 2000)))
    assert(!cal.isHoliday(new Date(2, 1, 1901)))
  }
  
  it must "observe the Family Day Holiday" in {
    // 3rd Monday in Feb since 2008
    assert(cal.isHoliday(new Date(18, 2, 2008)))
    assert(cal.isHoliday(new Date(16, 2, 2009)))
    
    // make sure we aren't incorrectly capturing Mondays
    assert(!cal.isHoliday(new Date(23, 2, 2009)))
    // make sure we don't flag before 2008
    assert(!cal.isHoliday(new Date(19, 2, 2007)))
    // TODO: fill the rest
  }
  
  it must "observe the Good Friday Holiday" in {
    // 5 Apr 1999 is Easter Monday
    assert(cal.isHoliday(new Date(2, 4, 1999)))
  }
  
  it must "observe the Easter Holiday" in {
    assert(cal.isHoliday(new Date(5, 4, 1999)))
  }
  
  it must "observe the Victoria Day Holiday" in {
    // Monday on or preceding 24 May
    assert(cal.isHoliday(new Date(18, 5, 2009)))
    assert(cal.isHoliday(new Date(24, 5, 2010)))
  }
  
  it must "observe the Canada Day Holiday" in {
    // July 1 
    assert(cal.isHoliday(new Date(1, 7, 1996)))
    
    // or the following Monday (when on Sun)
    assert(cal.isHoliday(new Date(2, 7, 2007)))
    // but not July 2 in a year when July 1 is observed
    assert(!cal.isHoliday(new Date(2, 7, 1996)))
    
    // or the following Monday (when on Sat)
    assert(cal.isHoliday(new Date(3, 7, 2017)))
  }
  
  it must "observe the Provincial Day Holiday" in {
    assert(cal.isHoliday(new Date(3, 8, 1998)))
  }
  
  it must "observe the Labor Day Holiday" in {
    // first monday of september
    assert(cal.isHoliday(new Date(3, 9, 1990)))
  }
  
  it must "observe the Thanksgiving Day Holiday" in {
    // second monday of october
    assert(cal.isHoliday(new Date(13, 10, 2003)))
  }
  
  it must "observe the November 11th Holiday" in {
    assert(cal.isHoliday(new Date(11, 11, 2003)))
    // or the following Monday
    assert(cal.isHoliday(new Date(12, 11, 2001)))
  }
  
  it must "observe the Christmas Holiday" in {
    assert(cal.isHoliday(CalendarTestFixture.Christmas_OnThursday))
    // or the following Monday
    assert(cal.isHoliday(CalendarTestFixture.Christmas_ObservedOnMonday_FallOnSat))
  }

  it must "observe the Boxing Day Holiday" in {
    assert(cal.isHoliday(new Date(26, 12, 1997)))
    // or the following Monday
    assert(cal.isHoliday(new Date(28, 12, 1993)))
  }
}

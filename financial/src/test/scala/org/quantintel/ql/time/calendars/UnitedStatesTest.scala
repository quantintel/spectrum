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
import org.scalatest.{ Matchers, FlatSpec }
import org.quantintel.ql.time.Date

/**
 * Tests the United States calendar.
 *
 * @author Peter Mularien
 */
class UnitedStatesTest extends FlatSpec with Matchers {

  "US Calendar - default apply" should "be US Settlement" in {
    val cal: Calendar = UnitedStates()
    assert(cal.name == "US Settlement")
  }

  import org.quantintel.ql.time.calendars.UnitedStatesEnum._

  // US Settlement calendar
  {
    val cal: Calendar = UnitedStates(SETTLEMENT)
    "US Calendar - SETTLEMENT" should "be Settlement" in {
      assert(cal.name == "US Settlement")
    }

    it should "observe New Year's Day" in {
      // on a standard business day (Monday)
      assert(cal.isHoliday(CalendarTestFixture.NewYears_OnMonday))
      // observed on a Monday if it occurs on Sunday)
      assert(cal.isHoliday(CalendarTestFixture.NewYears_ObservedOnMondayJan2))
      // observed on the prev Friday if it occurs on Saturday
      assert(cal.isHoliday(CalendarTestFixture.NewYears_ObservedOnFridayDec31))
    }

    it should "observe Martin Luther King Jr.s Birthday" in {
      // third Monday in Jan
      assert(cal.isHoliday(new Date(19, 1, 2009)))
    }

    it should "observe Washington's Birthday" in {
      // third Monday in Feb
      assert(cal.isHoliday(new Date(16, 2, 2009)))
    }

    it should "observe Memorial Day" in {
      // last Monday in May
      assert(cal.isHoliday(new Date(30, 5, 2004)))
    }

    it should "observe Independence Day" in {
      // july 4
      assert(cal.isHoliday(new Date(4, 7, 2014)))
      // or july 5 if july 4 is Sunday
      assert(cal.isHoliday(new Date(5, 7, 1993)))
      // or july 3 if july 4 is Saturday
      assert(cal.isHoliday(new Date(3, 7, 1998)))
    }

    it should "observe Labor Day" in {
      // first Monday in Sep
      assert(cal.isHoliday(new Date(5, 9, 2005)))
    }

    it should "observe Columbus Day" in {
      // second Monday in Oct
      assert(cal.isHoliday(new Date(11, 10, 2010)))
    }

    it should "observe Veteran's Day" in {
      // Nov 11
      assert(cal.isHoliday(new Date(11, 11, 2014)))
      // Monday, if Nov 11 is Sun
      assert(cal.isHoliday(new Date(12, 11, 2001)))
      // Friday, if Nov 11 is Sat
      assert(cal.isHoliday(new Date(10, 11, 1995)))
    }

    it should "observe Thanksgiving Day" in {
      // 4th Thu in Nov
      assert(cal.isHoliday(new Date(27, 11, 2014)))
    }

    it should "observe Christmas Day" in {
      // Dec 25
      assert(cal.isHoliday(CalendarTestFixture.Christmas_OnThursday))
      // or the previous Friday, if on Sat
      assert(cal.isHoliday(CalendarTestFixture.Christmas_ObservedOnFriday_FallOnSat))
      // or the next Monday, if on Sun
      assert(cal.isHoliday(CalendarTestFixture.Christmas_ObservedOnMonday_FallOnSun))
    }
  }

  // NYSE calendar
  {
    val cal: Calendar = UnitedStates(NYSE)
    "US Calendar - NYSE" should "be New York stock exchange" in {
      assert(cal.name == "New York stock exchange")
    }
  }

  // US Government Bond Calendar
  {
    val cal: Calendar = UnitedStates(GOVERNMENTBOND)
    "US Calendar - GOVERNMENTBOND" should "be US government bond market" in {
      assert(cal.name == "US government bond market")
    }
  }

  {
    val cal: Calendar = UnitedStates(NERC)
    "US Calendar - NERC" should "be North American Energy Reliability Council" in {
      assert(cal.name == "North American Energy Reliability Council")
    }
  }

}

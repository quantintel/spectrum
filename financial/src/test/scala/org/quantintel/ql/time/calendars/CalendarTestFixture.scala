package org.quantintel.ql.time.calendars

import org.quantintel.ql.time.Date

/**
 * A test fixture containing constant dates which may be helpful in constructing test scenarios.
 * 
 * @author Peter Mularien
 */
object CalendarTestFixture {
  // helpful constants for fixed dates of New Year's (Jan 1) following certain rules
  val NewYears_OnSaturday = new Date(1, 1, 2000)
  val NewYears_OnMonday = new Date(1, 1, 2007)
  val NewYears_ObservedOnMondayJan2 = new Date(2, 1, 2011)
  // new year's in 2000 was observed on dec 31, 1999 
  val NewYears_ObservedOnFridayDec31 = new Date(31, 12, 1999)
  
  // helpful constants for fixed dates of Christmas (Dec 25)
  // observed on Monday in some countries, if it falls on Sunday
  val Christmas_ObservedOnMonday_FallOnSat = new Date(27, 12, 1993)
  // observed in Monday in some countries, if it falls on Saturday
  val Christmas_ObservedOnMonday_FallOnSun = new Date(26, 12, 2005)
  // observed in Friday in some countries, if it falls on Saturday
  val Christmas_ObservedOnFriday_FallOnSat = new Date(24, 12, 1993)
  val Christmas_OnThursday = new Date(25, 12, 1997)
  val Christmas_OnFriday = new Date(25, 12, 1992)
  val Christmas_OnSaturday = new Date(25, 12, 1993)
  val Christmas_OnSunday = new Date(25, 12, 1994)
}
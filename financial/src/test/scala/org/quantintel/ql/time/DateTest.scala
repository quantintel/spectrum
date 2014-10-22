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

package org.quantintel.ql.time

import org.quantintel.ql.time.Month._
import org.scalatest.{Matchers, FlatSpec}
import java.util.{Calendar => JCalendar, Locale => JLocale, Formatter => JFormatter, Date => JDate}



/**
 * @author Paul Bernard
 */
class DateTest extends FlatSpec with Matchers {

  // comparators

  "1. Date equality" should "equal" in {

    var d = Date(367)
    assert(d == Date(367))

  }

  "2. Date inequality" should "inequal" in {

    var d = Date(367)
    assert(d != Date(368))

  }

  "3. Date comparison <" should "less than" in {

    var d = Date(367)
    assert(d < Date(368))

  }

  "4. Date comparison >" should "more than" in {

    var d = Date(368)
    assert(d > Date(367))

  }

  "5. Date comparison >" should "less than" in {

    var d = Date(368)
    assert(d > Date(367))

  }

  "6. Date comparison >" should "more than" in {

    var d = Date(367)
    assert(d < Date(368))

  }

  "7. Date comparison ne" should "not equal" in {

    var d = Date(368)
    assert(d != Date(367))

  }

  "8. Date comparison ne" should "equal" in {

    var d = Date(367)
    assert(d == Date(367))

  }

  "9. Date comparison le at equal" should "less than or equal" in {

    var d = Date(367)
    assert(d <= Date(367))

  }

  "10. Date comparison le at less than" should "less than or equal" in {

    var d = Date(367)
    assert(d <= Date(368))

  }

  "11. Date comparison le at less than" should "less than or equal" in {

    var d = Date(368)
    assert(d >= Date(367))

  }

  "12. Date comparison ge at greater than" should "greater than or equal" in {

    var d = Date(367)
    assert(d >= Date(367))

  }

  "13. Date comparison ge at greater than" should "greater than or equal" in {

    var d = Date(368)
    assert(d >= Date(368))

  }

  "14. Date comparison ge at greater than" should "greater than or equal" in {

    var d = Date(367)
    assert(d <= Date(368))

  }

  "15. Date comparison isNull" should "null" in {
    var d = Date()
    assert(d isNull)
  }

  "16. Date comparison isNull" should "not null" in {
    var d = Date(368)
    assert(!(d isNull))
  }

  "17. Date comparison isNull" should "null" in {
    var d = Date()
    assert(d isNull)
  }

  "18. date inc" should "+1" in {
    var d = Date(Date.minimumSerialNumber)
    assert((d++)== Date(Date.minimumSerialNumber + 1))
  }

  "19. date dec" should "-1" in {
    var d = Date(Date.minimumSerialNumber + 1)
    assert((d--)==Date(Date.minimumSerialNumber))
  }

  "20. date inc beyond range" should "exception" in {
    var d = Date(Date.maximumSerialNumber)
    try {
      d++
    } catch {
        case e: Exception => assert(true)
    }
  }

  "21. date dec beyond range" should "exception" in {
    var d = Date(Date.minimumSerialNumber)
    try {
      d--
    } catch {
      case e: Exception => assert(true)
    }
  }


  "22. value of min" should "367" in {
    var d = Date.minimumSerialNumber
    assert(d==367)
  }

  "23. value of max" should "109574" in {
    var d = Date.maximumSerialNumber
    assert(d==109574)
  }

  "24. earliest date month" should "JANUARY" in {
    import org.quantintel.ql.time.Month._
    var d = Date(367)
    assert(d.month == JANUARY)
  }

  "24. earliest day of year" should "JANUARY 1" in {

    var d = Date(367)

    assert(d.dayOfMonth == 1)
  }

  "25. earliest year in excel based system but before supported serial number" should "exception" in {

    try {
      var d = Date(1)
      assert(d.year == 1900)
    } catch {
      case e: Exception => assert(true)
    }
  }

  "26 is leap year" should "be yes" in {
    //var d = new Date(1900)
    assert(Date.isLeapYr(1900)) // lotus 123, excel exception
  }

  "27 is not leap year" should "be no" in {
    assert(!Date.isLeapYr(1903))
  }

  "28 is a leap year" should "be yes" in {
    assert(Date.isLeapYr(1904))
  }


  "29. check a year 367" should "1901" in {

    var d = Date(367)
    assert(d.year == 1901)
  }

 "30. check a year 109574" should "2199" in {
    var d = Date(109574)
    assert(d.year == 2199)
  }

  "December 31st, 2199" should "109574" in {
    var  d = Date(31, 12, 2199)
    assert(d.serialNumber == 109574)
  }

  "December 31st, 2199 using enum" should "109574" in {
    var  d = Date(31, Month.DECEMBER, 2199)
    assert(d.serialNumber == 109574)
  }

  "legacy java date" should "" in {

    val c: JCalendar = JCalendar.getInstance()
    c.set(JCalendar.DAY_OF_MONTH, 31)
    c.set(JCalendar.MONTH, JCalendar.DECEMBER)
    c.set(JCalendar.YEAR, 2199)
    var date = Date(c.getTime)
    assert(date.serialNumber == 109574)
  }

  "get day of month" should "5" in {

    val c: JCalendar = JCalendar.getInstance()
    c.set(JCalendar.DAY_OF_MONTH, 31)
    c.set(JCalendar.MONTH, JCalendar.DECEMBER)
    c.set(JCalendar.YEAR, 2199)
    var date = Date(c.getTime)
    assert(date.dayOfMonth == 31)

  }

  "get month of year" should "1" in {

    val c: JCalendar = JCalendar.getInstance()
    c.set(JCalendar.DAY_OF_MONTH, 31)
    c.set(JCalendar.MONTH, JCalendar.DECEMBER)
    c.set(JCalendar.YEAR, 2199)
    var date = Date(c.getTime)
    assert(date.month.id== 12)

  }

  "get year" should "2199" in {

    val c: JCalendar = JCalendar.getInstance()
    c.set(JCalendar.DAY_OF_MONTH, 31)
    c.set(JCalendar.MONTH, JCalendar.DECEMBER)
    c.set(JCalendar.YEAR, 2199)
    var date = Date(c.getTime)
    assert(date.year== 2199)

  }

  "get day of week" should "be tuesday" in {

    val c: JCalendar = JCalendar.getInstance()
    c.set(JCalendar.DAY_OF_MONTH, 31)
    c.set(JCalendar.MONTH, JCalendar.DECEMBER)
    c.set(JCalendar.YEAR, 2199)
    var date = Date(c.getTime)
    assert(date.weekday== Weekday.TUESDAY)

  }

  "get is end of month" should "be yes" in {

    val c: JCalendar = JCalendar.getInstance()
    c.set(JCalendar.DAY_OF_MONTH, 31)
    c.set(JCalendar.MONTH, JCalendar.DECEMBER)
    c.set(JCalendar.YEAR, 2199)
    var date = Date(c.getTime)
    assert(date.isEndOfMonth)

  }

  "get is end of month" should "be no" in {

    val c: JCalendar = JCalendar.getInstance()
    c.set(JCalendar.DAY_OF_MONTH, 30)
    c.set(JCalendar.MONTH, JCalendar.DECEMBER)
    c.set(JCalendar.YEAR, 2199)
    val date = Date(c.getTime)
    assert(!date.isEndOfMonth)

  }

  "get is null date" should "be yes" in {

    var date = Date()
    assert(date.isNull)

  }

  "get is null date" should "be no" in {

    val c: JCalendar = JCalendar.getInstance()
    c.set(JCalendar.DAY_OF_MONTH, 30)
    c.set(JCalendar.MONTH, JCalendar.DECEMBER)
    c.set(JCalendar.YEAR, 2199)
    val date = Date(c.getTime)
    assert(!date.isNull)

  }

  "is today" should "be true" in {

    val c: JCalendar = JCalendar.getInstance()
    val date = Date(c.getTime)
    assert(date.isToday)

  }

  "is today" should "be false" in {

    val c: JCalendar = JCalendar.getInstance()
    c.set(JCalendar.DAY_OF_YEAR, c.get(JCalendar.DAY_OF_YEAR + 1))
    val date = Date(c.getTime)
    assert(!date.isToday)

  }

  "is the min date" should "1, Month.JANUARY, 1901" in {
    val c: JCalendar = JCalendar.getInstance()
    c.set(JCalendar.DAY_OF_MONTH, 1)
    c.set(JCalendar.MONTH, JCalendar.JANUARY)
    c.set(JCalendar.YEAR, 1901)
    val date = Date(c.getTime)
    assert(date.serialNumber== 367)
  }

  "inc date" should "be 368" in {
    val d = Date(367)
    (d++)
    assert(d.serialNumber == 368)
  }

  "dec date" should "be 367" in {
    val d = Date(368)
    (d--)
    assert(d.serialNumber == 367)
  }

  "long date formatting" should "" in {
    val c: JCalendar = JCalendar.getInstance()
    c.set(JCalendar.DAY_OF_MONTH, 1)
    c.set(JCalendar.MONTH, JCalendar.JANUARY)
    c.set(JCalendar.YEAR, 1901)
    val date = Date(c.getTime)
    assert(date.toString == "01/01/1901")
  }

  "object identity" should "true" in {
    val c: JCalendar = JCalendar.getInstance()
    c.set(JCalendar.DAY_OF_MONTH, 1)
    c.set(JCalendar.MONTH, JCalendar.JANUARY)
    c.set(JCalendar.YEAR, 1901)
    val date1 = Date(c.getTime)

    assert(c.equals(c))
  }

  "object identity" should "false" in {
    val c: JCalendar = JCalendar.getInstance()
    c.set(JCalendar.DAY_OF_MONTH, 1)
    c.set(JCalendar.MONTH, JCalendar.JANUARY)
    c.set(JCalendar.YEAR, 1901)
    val date1 = Date(c.getTime)

    val d: JCalendar = JCalendar.getInstance()
    d.set(JCalendar.DAY_OF_MONTH, 1)
    d.set(JCalendar.MONTH, JCalendar.JANUARY)
    d.set(JCalendar.YEAR, 1901)
    val date2 = Date(d.getTime)

    assert(date1 == date2)
  }

  "object comparison less than" should "-1" in {
    val c: JCalendar = JCalendar.getInstance()
    c.set(JCalendar.DAY_OF_MONTH, 1)
    c.set(JCalendar.MONTH, JCalendar.JANUARY)
    c.set(JCalendar.YEAR, 1901)
    val date1 = Date(c.getTime)

    val d: JCalendar = JCalendar.getInstance()
    d.set(JCalendar.DAY_OF_MONTH, 2)
    d.set(JCalendar.MONTH, JCalendar.JANUARY)
    d.set(JCalendar.YEAR, 1901)
    val date2 = Date(d.getTime)

    assert(true)

    assert(date1.compareTo(date2)== -1)

  }

  "object comparison less than" should "1" in {
    val c: JCalendar = JCalendar.getInstance()
    c.set(JCalendar.DAY_OF_MONTH, 2)
    c.set(JCalendar.MONTH, JCalendar.JANUARY)
    c.set(JCalendar.YEAR, 1901)
    val date1 = Date(c.getTime)

    val d: JCalendar = JCalendar.getInstance()
    d.set(JCalendar.DAY_OF_MONTH, 1)
    d.set(JCalendar.MONTH, JCalendar.JANUARY)
    d.set(JCalendar.YEAR, 1901)
    val date2 = Date(d.getTime)

    assert(true)

    assert(date1.compareTo(date2)== 1)

  }

  "object comparison less than" should "0" in {
    val c: JCalendar = JCalendar.getInstance()
    c.set(JCalendar.DAY_OF_MONTH, 1)
    c.set(JCalendar.MONTH, JCalendar.JANUARY)
    c.set(JCalendar.YEAR, 1901)
    val date1 = Date(c.getTime)

    val d: JCalendar = JCalendar.getInstance()
    d.set(JCalendar.DAY_OF_MONTH, 1)
    d.set(JCalendar.MONTH, JCalendar.JANUARY)
    d.set(JCalendar.YEAR, 1901)
    val date2 = Date(d.getTime)

    assert(true)

    assert(date1.compareTo(date2)== 0)

  }

  "addAssign" should " + 1 int" in {
    val c: JCalendar = JCalendar.getInstance()
    c.set(JCalendar.DAY_OF_MONTH, 1)
    c.set(JCalendar.MONTH, JCalendar.JANUARY)
    c.set(JCalendar.YEAR, 1901)
    val date1 = Date(c.getTime)

    date1 += 1
    assert(date1.dayOfMonth == 2)
  }

  "addAssign" should " + 1 one month forward" in {
    val c: JCalendar = JCalendar.getInstance()
    c.set(JCalendar.DAY_OF_MONTH, 1)
    c.set(JCalendar.MONTH, JCalendar.JANUARY)
    c.set(JCalendar.YEAR, 1901)
    val date1 = Date(c.getTime)

    date1 += Period.ONE_DAY_FORWARD
    assert(date1.dayOfMonth == 2)
  }

  "addAssign" should " + 1 one day forward" in {
    val c: JCalendar = JCalendar.getInstance()
    c.set(JCalendar.DAY_OF_MONTH, 1)
    c.set(JCalendar.MONTH, JCalendar.JANUARY)
    c.set(JCalendar.YEAR, 1901)
    val date1 = Date(c.getTime)

    date1 += Period.ONE_MONTH_FORWARD
    assert(date1.month == Month.FEBRUARY)
  }

  "addAssign" should " + 1 one year forward" in {
    val c: JCalendar = JCalendar.getInstance()
    c.set(JCalendar.DAY_OF_MONTH, 1)
    c.set(JCalendar.MONTH, JCalendar.JANUARY)
    c.set(JCalendar.YEAR, 1901)
    val date1 = Date(c.getTime)

    date1 += Period.ONE_YEAR_FORWARD
    assert(date1.year == 1902)
  }

  "lower bound" should "be 1" in {
    import org.quantintel.ql.time.Month._
    val ls: List[Date] = Date(1, JANUARY, 1980) :: Date(2, JANUARY, 1980) :: Date(3, JANUARY, 1980) :: Nil
    assert(Date.lowerBound(ls, Date(2, JANUARY, 1980)) == 1)
  }

  "lower bound" should "be not be 1" in {
    import org.quantintel.ql.time.Month._
    val ls: List[Date] = Date(1, JANUARY, 1980) :: Date(2, JANUARY, 1980) :: Date(3, JANUARY, 1980) :: Nil
    assert(Date.lowerBound(ls, Date(3, JANUARY, 1980)) != 1)
  }

  "upper bound" should "be 1" in {
    import org.quantintel.ql.time.Month._
    val ls: List[Date] = Date(1, JANUARY, 1980) :: Date(2, JANUARY, 1980) :: Date(3, JANUARY, 1980) :: Nil
    assert(Date.upperBound(ls, Date(2, JANUARY, 1980)) == 2)
  }

  "upper bound" should "be not be 1" in {
    import org.quantintel.ql.time.Month._
    val ls: List[Date] = Date(1, JANUARY, 1980) :: Date(2, JANUARY, 1980) :: Date(3, JANUARY, 1980) :: Nil
    assert(Date.upperBound(ls, Date(3, JANUARY, 1980)) != 1)
  }

}

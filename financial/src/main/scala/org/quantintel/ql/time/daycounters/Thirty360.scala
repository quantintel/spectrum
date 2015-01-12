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

package org.quantintel.ql.time.daycounters

import org.quantintel.ql.time.Date
import org.quantintel.ql.time.Month._

/**
 * Enumeration of supported 30/360 family daycount conventions. These enum values are used when 
 * constructing a new instance of the [[Thirty360]] class.
 * 
 * @author Paul Bernard
 * @author Peter Mularien 
 */
object Thirty360Convention extends Enumeration {

  type Thirty360Convention = Value
  /** "30/360 (Bond Basis)" */
  val USA = Value(1)
  /** "30/360 (Bond Basis)" */
  val BONDBASIS = Value(2)
  /** "30E/360 (Eurobond Basis)" */
  val EUROPEAN = Value(3)
  /** "30E/360 (Eurobond Basis)" */
  val EUROBONDBASIS = Value(4)
  /** "30/360 (Italian)" */
  val ITALIAN = Value(5)

  /** 30/360 (US) */
  val THIRTY360US = Value(10)
  /** 30/360 BMA */
  val BMA = Value(11)
  /** 30/360 SIA */
  val SIA = Value(12)
  /** 30E/360 ISDA */
  val ISDA = Value(13)
  /** 30E+/360 */
  val EP = Value(14)

  /** 30/360 ISDA */
  val THIRTY360ISDA = Value(15)
  /** 30/360 US (NASD) */
  val THIRTY360USNASD = Value(16)

  def valueOf(market: Int) : Thirty360Convention  = market match {
    case 1 => USA
    case 2 => BONDBASIS
    case 3 => EUROPEAN
    case 4 => EUROBONDBASIS
    case 5 => ITALIAN

    case 10 => THIRTY360US
    case 11 => BMA
    case 12 => SIA
    case 13 => ISDA
    case 14 => EP
    case 15 => THIRTY360ISDA
    case 16 => THIRTY360USNASD
    case _ => throw new Exception("Valid units = 1 to 5, 10 to 14")
  }

}


/**
 * There are several variants to the 30/360 day count method,
 * depending upon US, European or Italian conventions.
 *
 * US
 *  Also known as : 30/360 or Bond Basis
 *  - 30/360 "30-day months, end of month adjustments"
 *
 * EU
 *  Also known as: 30E/360, Eurobond Basis
 *
 * IT
 *  Primarily known as: Italian
 *
 * Implementation Notes:
 *
 * Italian: start or end dates occurring in the month of February
 * and are > 27 are set to 30
 *
 * Euro: start or end dates occurring on the 31st of a month are set
 * to 30th of the same month.
 *
 * US: start date of month is 31st set to 30th of the same month
 * end date is the 31st of a month and the starting date is < 30th
 * of month then ending date becomes equal to first of next month,
 * else the ending date is set to the 30th of the same month.
 *
 * @author Paul Bernard
 */
object Thirty360 {

  /** Default convention to be applied is 30/360 (Bond Basis). 
   *
   * To use other conventions, please use [[Thirty360.apply(convention)]].
   **/
  def apply() : DayCounter = new USA

  import org.quantintel.ql.time.daycounters.Thirty360Convention._

  /** Factory method used to create a day counter based on the supplied convention.
   *  
   * @param convention the convention to use
   * @return an 30/360 day counter based on the requested convention type
   */
  def apply(convention: Thirty360Convention) : DayCounter = {
    convention match {

      // 30/360
      case USA => new USA
      case BONDBASIS => new USA
      // 30E/360
      case EUROPEAN => new EU
      case EUROBONDBASIS => new EU
      // 30E+/360
      case EP => new EP
      case THIRTY360ISDA => new THIRTY360ISDA
      case THIRTY360USNASD => new THIRTY360USNASD

      // 30E/360 ISDA
      case ISDA => new ISDA
      // 30/360 US
      case THIRTY360US => new THIRTY360US
      // 30/360 SIA
      case SIA => new SIA
        // 30/360 BMA
      case BMA => new BMA
      // 30/360 IT
      case ITALIAN => new IT

      case _ => throw new Exception("unknown 30/360 convention")
    }
  }


  /** USA day count convention.
   *
   * Also known as:
   *  * 30/360 Bond Basis
   *
   * If the first date falls on the 31st, it is changed to the 30th.
   * If the second date falls on the 31st and the first date is earlier
   * than the 30th, then the second date is changed to the 1st of the next
   * month, otherwise it is changed to the 30th.
   */
  class USA extends DayCounter {

    override def name : String = "30/360 (Bond Basis)"

    override def yearFraction(dateStart : Date, dateEnd :Date, refStartDate: Date, refEndDate: Date) : Double = {
      dayCount(dateStart, dateEnd) / 360.0
    }

    override def dayCount(d1: Date, d2: Date): Long = {

      val dd1 : Int  = d1.dayOfMonth
      var dd2 : Int = d2.dayOfMonth

      val mm1: Int = d1.month.id
      var mm2: Int = d2.month.id

      val yy1 : Int = d1.year
      val yy2 : Int = d2.year

      if (dd2 == 31 && dd1 < 30) {
        dd2 = 1
        mm2= mm2 + 1
      }

      360*(yy2-yy1) + 30*(mm2-mm1-1) + Math.max(0, 30-dd1) + Math.min(30, dd2)

    }

  }

  class EU extends DayCounter {

    override def name : String  = "30E/360 (Eurobond Basis)"

    override def yearFraction(dateStart : Date, dateEnd :Date, refStartDate: Date, refEndDate: Date) : Double = {
      dayCount(dateStart, dateEnd) / 360.0
    }

    override def dayCount(d1: Date, d2: Date): Long = {

      val dd1 : Int = d1.dayOfMonth
      val dd2 : Int = d2.dayOfMonth
      val mm1 : Int = d1.month.id
      val mm2 : Int = d2.month.id
      val yy1 : Int = d1.year
      val yy2 : Int= d2.year

      360*(yy2-yy1) + 30*(mm2-mm1-1) + Math.max(0, 30-dd1) + Math.min(30, dd2)

    }

  }

  class IT extends DayCounter {

    override def name : String = "30/360 (Italian)"

    override def yearFraction(dateStart : Date, dateEnd :Date, refStartDate: Date, refEndDate: Date) : Double = {
      dayCount(dateStart, dateEnd) / 360.0
    }

    override def dayCount(d1: Date, d2: Date): Long = {

      var dd1 : Int = d1.dayOfMonth
      var dd2 : Int = d2.dayOfMonth
      val mm1 : Int = d1.month.id
      val mm2 : Int = d2.month.id
      val yy1 : Int = d1.year
      val yy2 : Int = d2.year

      if (mm1 == 2 && dd1 > 27) dd1 = 30
      if (mm2 == 2 && dd2 > 27) dd2 = 30


      360*(yy2-yy1) + 30*(mm2-mm1-1) + Math.max(0, 30-dd1) + Math.min(30, dd2)
    }

  }

  /**
   * If both the first date and the second date are the last day of February,
   * the second date is changed to the 30th.
   * If the first date is the last day of February, it is changed to the 30th.
   * If after the previous tests the second date is the 31st and the first date is
   * the 30th or the 31st, the second date is changed to the 30th.
   * If after the previous tests the first date is the 31st, it is changed to the 30th.
   */
  class THIRTY360US extends DayCounter {

    override def name : String = "30/360 US"

    override def yearFraction(dateStart : Date, dateEnd :Date, refStartDate: Date, refEndDate: Date) : Double = {
      dayCount(dateStart, dateEnd) / 360.0
    }

    override def dayCount(d1: Date, d2: Date): Long = {

      var dd1 : Int = d1.dayOfMonth
      var dd2 : Int = d2.dayOfMonth
      val mm1 : Int = d1.month.id
      val mm2 : Int = d2.month.id
      val yy1 : Int = d1.year
      val yy2 : Int = d2.year

      if ((mm1 == FEBRUARY.id && d1.isEndOfMonth) && (mm2 == FEBRUARY.id && d2.isEndOfMonth)) dd2 = 30
      if (mm1 == FEBRUARY.id && d1.isEndOfMonth) dd1 = 30
      if (dd2 == 31 && dd1 > 29) dd2 = 30
      if (dd1 == 31) dd1 = 30


      360*(yy2-yy1) + 30*(mm2-mm1-1) + Math.max(0, 30-dd1) + Math.min(30, dd2)
    }

  }

  /**
   * If the first date falls on the 31st, it is changed to the 30th.
   * If after the previous test the first day is the 30th and the second date falls on the 31st,
   * it is changed to the 30th.
   */
  class THIRTY360ISDA extends DayCounter {

    override def name : String = "30/360 ISDA"

    override def yearFraction(dateStart : Date, dateEnd :Date, refStartDate: Date, refEndDate: Date) : Double = {
      dayCount(dateStart, dateEnd) / 360.0
    }

    override def dayCount(d1: Date, d2: Date): Long = {

      var dd1: Int = d1.dayOfMonth
      var dd2: Int = d2.dayOfMonth
      val mm1: Int = d1.month.id
      val mm2: Int = d2.month.id
      val yy1: Int = d1.year
      val yy2: Int = d2.year

      if (dd1==31) dd1 =30
      if (dd1==30 && dd2==31) dd2 = 30


      (360 * (yy2 - yy1) + 30 * (mm2-mm1) + (dd2-dd1))
    }


  }

  /**
   * If the first date falls on the 31st, it is changed to the 30th.
   * If the second date falls on the 31st and the first date is earlier than the 30th, then the
   * second date is changed to the 1st of the next month, otherwise it is changed to the 30th.
   */
  class THIRTY360USNASD extends DayCounter {

    override def name : String = "30/360 US (NASD)"

    override def yearFraction(dateStart : Date, dateEnd :Date, refStartDate: Date, refEndDate: Date) : Double = {
      dayCount(dateStart, dateEnd) / 360.0
    }

    override def dayCount(d1: Date, d2: Date): Long = {

      var dd1: Int = d1.dayOfMonth
      var dd2: Int = d2.dayOfMonth
      val mm1: Int = d1.month.id
      var mm2: Int = d2.month.id
      val yy1: Int = d1.year
      val yy2: Int = d2.year

      if (dd1==31) dd1 = 30
      if (dd2==31 && dd1 < 30) {
        dd2 = 1
        mm2 = mm2 + 1
      } else if (dd2 == 31) dd2 = 30

      (360 * (yy2 - yy1) + 30 * (mm2-mm1) + (dd2-dd1))

    }

  }

  /**
   * Also known as 30/360 PSA
   * If the first date falls on the 31st or if it's the last day of February,
   * it is changed to the 30th.
   * If after the preceding test the first day is 30 and the second day is 31
   * then the second day is changed to the 30th.
   */
  class BMA extends DayCounter {

    override def name : String = "30/360 BMA"

    override def yearFraction(dateStart : Date, dateEnd :Date, refStartDate: Date, refEndDate: Date) : Double = {
      dayCount(dateStart, dateEnd) / 360.0
    }

    override def dayCount(d1: Date, d2: Date): Long = {

      var dd1 : Int = d1.dayOfMonth
      var dd2 : Int = d2.dayOfMonth
      val mm1 : Int = d1.month.id
      val mm2 : Int = d2.month.id
      val yy1 : Int = d1.year
      val yy2 : Int = d2.year

      if (dd1 ==31 || (mm1 == FEBRUARY.id && d1.isEndOfMonth)) dd1 = 30
      if (dd1 == 30 && dd2 == 31) dd2 = 30


      360*(yy2-yy1) + 30*(mm2-mm1-1) + Math.max(0, 30-dd1) + Math.min(30, dd2)
    }


  }

  /**
   * If the first date and the second date are the last day of February, the
   * second date is changed to the 30th.
   * If the first date falls on the 31st or if it's the last day of February, it
   * is changed to the 30th.
   * If after the preceding test the first day is the 30th and the second day
   * is the 31st then the second day is changed to the 30th.
   */
  class SIA extends DayCounter {

    override def name : String = "30/360 SIA"

    override def yearFraction(dateStart : Date, dateEnd :Date, refStartDate: Date, refEndDate: Date) : Double = {
      dayCount(dateStart, dateEnd) / 360.0
    }

    override def dayCount(d1: Date, d2: Date): Long = {

      var dd1 : Int = d1.dayOfMonth
      var dd2 : Int = d2.dayOfMonth
      val mm1 : Int = d1.month.id
      val mm2 : Int = d2.month.id
      val yy1 : Int = d1.year
      val yy2 : Int = d2.year

      if ((mm1 == FEBRUARY.id && d1.isEndOfMonth) && (mm2 == FEBRUARY.id && d2.isEndOfMonth)) dd2 = 30
      if (dd1 ==31 || (mm1 == FEBRUARY.id && d1.isEndOfMonth)) dd1 = 30
      if (dd1 == 30 && dd2 == 31) dd2 = 30


      360*(yy2-yy1) + 30*(mm2-mm1-1) + Math.max(0, 30-dd1) + Math.min(30, dd2)
    }

  }

  /**
   * 30E/360 ISDA
   * Also known as: 30/360 German
   * If the first date falls on the 31st or if it's the last day of February,
   * it is changed to the 30th.
   * If the second date falls on the 31st or if it's the last day of February,
   * it is changed to the 30th.
   */
  class ISDA extends DayCounter {

    override def name : String = "30E/360 ISDA"

    override def yearFraction(dateStart : Date, dateEnd :Date, refStartDate: Date, refEndDate: Date) : Double = {
      dayCount(dateStart, dateEnd) / 360.0

    }

    override def dayCount(d1: Date, d2: Date): Long = {

      var dd1 : Int = d1.dayOfMonth
      var dd2 : Int = d2.dayOfMonth
      val mm1 : Int = d1.month.id
      val mm2 : Int = d2.month.id
      val yy1 : Int = d1.year
      val yy2 : Int = d2.year

      if (dd1 == 31 || (mm1 == FEBRUARY.id && d1.isEndOfMonth)) dd1 = 30
      if (dd1 == 31 || (mm2 == FEBRUARY.id && d2.isEndOfMonth)) dd2 = 30

      360*(yy2-yy1) + 30*(mm2-mm1-1) + Math.max(0, 30-dd1) + Math.min(30, dd2)
    }

  }

  /**
   * 30E+/360 ISDA day count method.
   * 
   * If the first date falls on the 31st, it is changed to the 30th.
   * If the second date falls on the 31st, it is changed to the 1st and the
   * month is increased by one.
   */
  class EP extends DayCounter {

    override def name : String = "30E+/360"

    override def yearFraction(dateStart : Date, dateEnd :Date, refStartDate: Date, refEndDate: Date) : Double = {
      dayCount(dateStart, dateEnd) / 360.0
    }

    override def dayCount(d1: Date, d2: Date): Long = {

      var dd1 : Int = d1.dayOfMonth
      var dd2 : Int = d2.dayOfMonth
      val mm1 : Int = d1.month.id
      var mm2 : Int = d2.month.id
      val yy1 : Int = d1.year
      var yy2 : Int = d2.year

      if (dd1 == 31) dd1 = 30
      if (dd2 ==31) {
        dd2 = 1
        if(mm2 != 12){
          mm2 = mm2 + 1
        } else { // 1st of next year
          mm2 = 1
          yy2 = yy2 + 1
        }
      }

      360*(yy2-yy1) + 30*(mm2-mm1-1) + Math.max(0, 30-dd1) + Math.min(30, dd2)
    }




  }


}



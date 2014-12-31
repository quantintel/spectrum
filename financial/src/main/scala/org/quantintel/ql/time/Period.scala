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

import java.util.Formatter

import org.quantintel.ql.time.Frequency._
import org.quantintel.ql.time.TimeUnit.TimeUnit
import org.quantintel.ql.time.TimeUnit._
import java.util.Locale


/** 
 *  Intended to represent a logical time period, given a length and relevant
 *  number of units - for example, "3 weeks". Representing time periods as
 *  structured data allows us to perform computations without lossy or
 *  unnecessary conversion.
 *  
 *  Binary operators are supported, so that natural mathematical computations
 *  may be performed using time periods as the operands. Although the notion of
 *  a negative time period is supported by this construct, in practical use
 *  it may be confusing and use should be limited.
 *  
 *  @author Paul Bernard
 *  @author Peter Mularien
 */
object Period {


  val UNKNOWN_FREQUENCY = "unknown frequency"
  val UNKNOWN_TIME_UNIT = "unknown time unit"
  val INCOMPATIBLE_TIME_UNIT = "incompatible time unit"
  val UNDECIDABLE_COMPARISON = "undecidable comparison"
  val DIVISION_BY_ZERO_ERROR = "cannot be divided by zero"

  /** Represents a single year. */
  val ONE_YEAR_FORWARD = new Period(1, YEARS)
  /** Represents a single year in reverse (negative). */
  val ONE_YEAR_BACKWARD = new Period(-1, YEARS)
  /** Represents a single month. */
  val ONE_MONTH_FORWARD = new Period(1, MONTHS)
  /** Represents a single month in reverse (negative). */
  val ONE_MONTH_BACKWARD = new Period(-1, MONTHS)
  /** Represents a single day. */
  val ONE_DAY_FORWARD = new Period(1, DAYS)
  /** Represents a single day in reverse (negative). */
  val ONE_DAY_BACKWARD = new Period(-1, DAYS)

  /** Create a new period with the given length and time unit.
   *  @param l length of the period (in the given units)
   *  @param p the relevant [[TimeUnit]] for the period 
   */
  def apply(l : Int, p: TimeUnit) : Period = {
    new Period(l, p)
  }

  /** Negate the length of the given period.
   * @param p the period to negate
   * @return the negation of the given period
   */
  def negate (p: Period) : Period = {
    Period(-p.length, p.units)
  }

  def * (n: Int, p: Period) : Period = {
    Period(n*p.length, p.units)
  }

  def * (p: Period, n: Int) : Period = {
    Period(n*p.length, p.units)
  }

  def / (p: Period, n: Int) : Period = {
    Period(p.length/n, p.units)
  }

  def / (n: Int, p: Period) : Period = {
    Period(p.length/n, p.units)
  }

  def == (p1: Period, p2: Period) : Boolean = {
   !(Period.<(p1,p2) || p2 < p1)
  }

  def != (p1: Period, p2: Period) : Boolean = {
    !(p1 == p2)
  }

  def > (p1: Period, p2: Period): Boolean = {
    Period.<(p2,p1)
  }

  def <= (p1: Period, p2: Period) : Boolean = {
    !(p1 > p2)
  }

  def >= (p1: Period, p2: Period) : Boolean = {
    !Period.<(p1, p2)
  }

  def + (p1: Period, p2: Period): Period = {
    var result: Period = p1
    result += p2
    result
  }

  def - (p1: Period, p2: Period) : Period = {
    var result : Period = p1
    result -= p2
    result
  }

  def < (p1: Period, p2: Period) : Boolean = {

    if (p1.length == 0) return p2.length > 0
    if (p2.length == 0) return p1.length < 0

    if (p1.units == p2.units) return p1.length < p2.length
    if (p1.units == MONTHS && p2.units == YEARS) return p1.length < 12 * p2.length
    if (p1.units == YEARS && p2.units == MONTHS) return 12 * p1.length < p2.length
    if (p1.units == DAYS && p2.units == WEEKS) return p1.length < 7*p2.length
    if (p1.units == WEEKS && p2.units == DAYS) return 7*p1.length < p2.length

    val p1lim : (Int, Int) = daysMinMax(p1)
    val p2lim : (Int, Int) = daysMinMax(p2)

    if (p1lim._2 < p2lim._1) true
    else if (p1lim._1 > p2lim._2) false
    else throw new Exception("undecidable comparison between " + p1 + " and " + p2)

  }


  /** Given a Period, return the number of years it represents.
   *  @return the number of years in the given period
   *  @throws IllegalArgumentException [[UNDECIDABLE_COMPARISON]] if this period can't be represented in years 
   */
  def years (p: Period) : Double = {

    p.units match {
      case DAYS | WEEKS => throw new IllegalArgumentException(UNDECIDABLE_COMPARISON)
      case MONTHS => p.length / 12.0
      case YEARS => p.length
      case _ => throw new Exception(UNKNOWN_TIME_UNIT)
    }

  }

  /** Given a Period, return the number of months it represents.
   *  @return the number of months in the given period
   *  @throws IllegalArgumentException [[UNDECIDABLE_COMPARISON]] if this period can't be represented in months
   */
  def months (p: Period) : Double = {

    if (p.length == 0) return 0.0

    p.units match {
      case DAYS | WEEKS => throw new IllegalArgumentException(UNDECIDABLE_COMPARISON)
      case MONTHS => p.length
      case YEARS => p.length * 12.0
      case _ => throw new Exception(UNKNOWN_TIME_UNIT)
    }
  }

  /** Given a Period, return the number of weeks it represents.
   *  @return the number of weeks in the given period
   *  @throws IllegalArgumentException [[UNDECIDABLE_COMPARISON]] if this period can't be represented in weeks 
   */
  def weeks (p: Period): Double = {

    if (p.length == 0) return 0.0

    p.units match {
      case DAYS => p.length / 7.0
      case WEEKS => p.length
      case MONTHS | YEARS => throw new IllegalArgumentException(UNDECIDABLE_COMPARISON)
      case _ => throw new Exception(UNKNOWN_TIME_UNIT)
    }
  }

  /** Given a Period, return the number of days it represents.
   *  @return the number of days in the given period
   *  @throws IllegalArgumentException [[UNDECIDABLE_COMPARISON]] if this period can't be represented in days 
   */
  def days (p: Period) : Double = {

    if (p.length == 0) return 0.0

    p.units match {
      case DAYS => p.length
      case WEEKS => p.length * 7.0
      case MONTHS | YEARS  => throw new IllegalArgumentException(UNDECIDABLE_COMPARISON)
      case _ => throw new Exception(UNKNOWN_TIME_UNIT)
    }
  }

  // used to represent the minimum and maximum number of days which may exist
  // in the given time period unit
  private def daysMinMax (p: Period) : (Int, Int) = {
    p match {
      case DAYS => (p.length, p.length)
      case WEEKS => (7*p.length, 7*p.length)
      case MONTHS => (28*p.length, 31*p.length)
      case YEARS => (365*p.length, 366*p.length)
      case _ => throw new Exception("unknown time unit (" + p.units + ")")
    }
  }



}

import org.quantintel.ql.time.TimeUnit._

/**
 * @author Paul Bernard
 */
class Period  {

  var length: Int = 0
  var units: TimeUnit= null


  import org.quantintel.ql.time.Period._

  /** Create a new period with the given length and time unit.
   *  @param l length of the period (in the given units)
   *  @param u the relevant [[TimeUnit]] for the period 
   */
  def this(l : Int, u : TimeUnit) {
    this()
    length = l
    units = u
  }


  /** Used to construct a Period representing a given logical [[Frequency]]. Depending on the
   *  frequency, the length and [[TimeUnit]] of the Period will be constructed accordingly.
   * 
   * @param f the frequency to represent in the new Period instance
   */
  def this(f: Frequency)  {
    this()
    f match {
      case NO_FREQUENCY =>
        units = DAYS
        length = 0

      case ONCE =>
        units = YEARS
        length = 0

      case ANNUAL =>
        units = YEARS
        length = 1

      case SEMIANNUAL | EVERY_FOURTH_MONTH | QUARTERLY | BIMONTHLY | MONTHLY =>
        units = MONTHS
        length = 12/f.id

      case EVERY_FOURTH_WEEK | BIWEEKLY | WEEKLY =>
        units = WEEKS
        length = 52/f.id

      case DAILY =>
        units = DAYS
        length = 1

      case OTHER_FREQUENCY => throw new Exception("unknown frequency")
      case _ => throw new Exception("unknown frequency")


    }

  }


  /** Represent this Period as a [[Frequency]], if there is a direct mapping based on the
   *  current period length and [[TimeUnit]]. If no appropriate mapping exists, returns
   *  [[Frequency#OTHER_FREQUENCY]].
   *  
   *  @return this Period represented as a [[Frequency]], or [[Frequency#OTHER_FREQUENCY]]
   */
  def frequency : Frequency = {

    val length : Int = this.length.abs

    if (length==0){
      if(units == YEARS) return ONCE else NO_FREQUENCY
    }

    units match {
      case YEARS => if (length ==1) ANNUAL else OTHER_FREQUENCY
      case MONTHS => if (12%length == 0 && length <=12)
        Frequency.valueOf(12/length) else OTHER_FREQUENCY
      case WEEKS => if (length == 1) WEEKLY
        else if (length == 2) BIWEEKLY
        else if (length == 4) EVERY_FOURTH_WEEK
        else OTHER_FREQUENCY

      case DAYS => if (length==1) DAILY else OTHER_FREQUENCY
      case _ => throw new Exception("unknown time unit (" + units.id + ")")

    }
  }

  def += (p: Period) : Period = {

    if (length == 0){
      length = p.length
      units = p.units
    } else if (units == p.units){
      length = length + p.length
    } else {
      this.units match {
        case YEARS =>
          p.units match {
            case MONTHS =>
              units = p.units
              length = length*12 + p.length

            case WEEKS | DAYS => throw new IllegalArgumentException(INCOMPATIBLE_TIME_UNIT)
            case _ => throw new Exception(UNKNOWN_TIME_UNIT)
          }

        case MONTHS =>
          p.units match {
            case YEARS =>
              length = length + p.length * 12

            case WEEKS | DAYS => throw new IllegalArgumentException(INCOMPATIBLE_TIME_UNIT)
            case _ => throw new Exception(UNKNOWN_TIME_UNIT)
          }

        case WEEKS =>
          p.units match {
            case DAYS =>
              units = p.units
              length = length*7 + p.length

            case YEARS | MONTHS => throw new IllegalArgumentException(INCOMPATIBLE_TIME_UNIT)
            case _ => throw new Exception(UNKNOWN_TIME_UNIT)
          }

        case DAYS =>
          p.units match {
            case WEEKS => length = length + p.length*7
            case YEARS | MONTHS => throw new IllegalArgumentException(INCOMPATIBLE_TIME_UNIT)
            case _ => throw new Exception(UNKNOWN_TIME_UNIT)
          }

        case _ => new Exception(UNKNOWN_TIME_UNIT)
      }
    }

    this

  }

  def -= (p: Period): Period = {
    this += negate(p)
  }

  def /= (scalar: Int): Period = {

    require(scalar != 0, "cannot be divided by zero")

    if (length % scalar == 0) length = length / scalar
    else {
      var u : TimeUnit = units
      var l : Int = length
      u match {
        case YEARS =>
          u = MONTHS
          l = l * 12

        case WEEKS =>
          u = DAYS
          l = l * 7

      }
      require(l % scalar == 0, this + "cannot be divided by " + scalar)

      length = l/scalar
      units = u

    }

    this
  }


  /** Normalize this Period into a higher order Period, where possible. For example,
   *  if this Period represents "7 days", this method would normalize the Period to
   *  a higher order value of "1 week".
   */
  def normalize () {
    if(length!=0){
      units match {
        case DAYS =>
            if (!((length%7)==0)) {
              length /=7
              units = WEEKS
            }

        case MONTHS => if (!(length%12==0)){
              length /= 12
              units = YEARS
            }

        case WEEKS => // no op

        case YEARS =>  // no op

        case _ => throw new Exception("unknown time unit (" + units.id + ")")
      }
    }
  }

  def >= (p2: Period) : Boolean = {
    Period >= (this, p2)
  }

  def <= (p2: Period) : Boolean = {
    Period <= (this, p2)
  }

  def < (p2: Period) : Boolean = {
     Period < (this, p2)
  }

  def > (p2: Period) : Boolean = {
    Period > (this, p2)
  }

  def == (p2: Period) : Boolean = {
    Period == (this, p2)
  }

  def != (p2: Period) : Boolean = {
    Period != (this, p2)
  }

  def + (p2: Period) : Period = {
    Period + (this, p2)
  }

  def - (p2: Period) : Period = {
    Period - (this, p2)
  }

  def * (n: Int) : Period = {
    Period * (this, n)
  }

  def / (n: Int) : Period = {
    Period / (this, n)
  }



  private def minDays : Int = {
    units match {
      case YEARS => length * 365
      case MONTHS => length * 28
      case WEEKS => length * 7
      case DAYS => length
      case _ => throw new Exception(UNKNOWN_TIME_UNIT)
    }
  }

  private def maxDays : Int = {
    units match {
      case YEARS => length * 366
      case MONTHS => length * 31
      case WEEKS => length * 7
      case DAYS => length
      case _ => throw new Exception(UNKNOWN_TIME_UNIT)
    }

  }

  override def toString : String = getLongFormat

  def getLongFormat : String = getInternalLongFormat


  def getShortFormat : String = getInternalShortFormat


  def getInternalShortFormat : String = {
    val sb : java.lang.StringBuilder = new java.lang.StringBuilder
    val formatter = new java.util.Formatter(sb, Locale.US)
    val args : Array[Any] = Array( length, TimeUnit.getLShortFormat(units) )
    formatter.format("%d%s", args)
    sb.toString

  }

  def getInternalLongFormat: String = {
    var suffix: String = null
    if(this.length == 1) suffix = "" else suffix = "s"
    val sb = new java.lang.StringBuilder
    val formatter: java.util.Formatter = new java.util.Formatter(sb, Locale.US)
    val args : Array[Any] = Array(length, suffix)
    formatter.format("%d %s%s", args)
    sb.toString
  }


}

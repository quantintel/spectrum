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
 * Scala Finance is based in part on:
 *        QuantLib. http://quantlib.org/
 *
 */

package org.quantintel.ql.time

import org.quantintel.ql.time.Frequency._
import org.quantintel.ql.time.TimeUnit.TimeUnit

object Period {

  val UNKNOWN_FREQUENCY = "unknown frequency"
  val UNKNOWN_TIME_UNIT = "unknown time unit"
  val INCOMPATIBLE_TIME_UNIT = "incompatible time unit"
  val UNDECIDABLE_COMPARISON = "undecidable comparison"
  val DIVISION_BY_ZERO_ERROR = "cannot be divided by zero"

  import org.quantintel.ql.time.TimeUnit._

  val ONE_YEAR_FORWARD = new Period(1, YEARS)
  val ONE_YEAR_BACKWARD = new Period(-1, YEARS)
  val ONE_MONTH_FORWARD = new Period(1, MONTHS)
  val ONE_MONTH_BACKWARD = new Period(-1, MONTHS)
  val ONE_DAY_FORWARD = new Period(1, DAYS)
  val ONE_DAY_BACKWARD = new Period(-1, DAYS)

  def apply(length : Int, units : TimeUnit) = {
      new Period(length, units)
  }



}

import org.quantintel.ql.time.TimeUnit._

/**
 * @author Paul Bernard
 */
class Period (var length : Int, var units : TimeUnit)  {



  import org.quantintel.ql.time.Period._

  def this(f: Frequency)  {



    this(
      f match {
        case ONCE | NO_FREQUENCY => 0
        case ANNUAL =>  1
        case SEMIANNUAL | EVERY_FOURTH_MONTH | QUARTERLY | BIMONTHLY | MONTHLY =>  12 / f.id
        case EVERY_FOURTH_WEEK | BIWEEKLY | WEEKLY => 52 / f.id
        case DAILY => 1
        case _ => throw new Exception(Period.UNKNOWN_FREQUENCY)
      } ,
      f match {
        case ONCE | NO_FREQUENCY => DAYS
        case ANNUAL =>  YEARS
        case SEMIANNUAL | EVERY_FOURTH_MONTH | QUARTERLY | BIMONTHLY | MONTHLY =>  MONTHS
        case EVERY_FOURTH_WEEK | BIWEEKLY | WEEKLY => WEEKS
        case DAILY => DAYS
        case _ => throw new Exception(Period.UNKNOWN_FREQUENCY)
      })
  }

  override def clone: Period = Period(length, units)

  def negative = Period(-length, units)

  def addAssign(another: Period) : Period  = {
    if (length == 0){
      length = another.length
      units = another.units
    } else if (units == another.units){
      length = length + another.length
    } else {
      this.units match {
        case YEARS => {
          another.units match {
            case MONTHS => {
              units = another.units
              length = length*12 + another.length
            }
            case WEEKS | DAYS => throw new IllegalArgumentException(INCOMPATIBLE_TIME_UNIT)
            case _ => throw new Exception(UNKNOWN_TIME_UNIT)
          }
        }
        case MONTHS => {
          another.units match {
            case YEARS => {
              length = length + another.length * 12
            }
            case WEEKS | DAYS => throw new IllegalArgumentException(INCOMPATIBLE_TIME_UNIT)
            case _ => throw new Exception(UNKNOWN_TIME_UNIT)
          }
        }
        case WEEKS => {
          another.units match {
            case DAYS => {
              units = another.units
              length = length*7 + another.length
            }
            case YEARS | MONTHS => throw new IllegalArgumentException(INCOMPATIBLE_TIME_UNIT)
            case _ => throw new Exception(UNKNOWN_TIME_UNIT)
          }
        }
        case DAYS => {
          another.units match {
            case WEEKS => {
              length = length + another.length*7
            }
            case YEARS | MONTHS => throw new IllegalArgumentException(INCOMPATIBLE_TIME_UNIT)
            case _ => throw new Exception(UNKNOWN_TIME_UNIT)
          }
        }
        case _ => new Exception(UNKNOWN_TIME_UNIT)
      }
    }

    this

  }

  def subAssign(another: Period): Period = this.addAssign(another.clone.negative)

  def divAssign(scalar: Int): Period = {
    if (scalar == 0) throw new ArithmeticException(DIVISION_BY_ZERO_ERROR)

    if (length % scalar == 0) length = length / scalar
    else {
      units match {
        case YEARS => {
          units = MONTHS
          length = length * 12
        }
        case WEEKS => {
          units = DAYS
          length = length * 7
        }
      }
    }

    if (length % scalar == 0) length = length/scalar
    else throw new Exception("cannot be divided by " + scalar)

    this
  }

  def mul(scalar: Int): Period = Period(scalar * length, units)

  def add(another: Period) : Period = clone.addAssign(another)

  def sub(another: Period) : Period = clone.subAssign(another)

  def div(scalar: Int) : Period = clone.divAssign(scalar)

  def eq (another: Period): Boolean = equals(another)

  def neg(another: Period): Boolean = !equals(another)

  def gt(another: Period): Boolean = another.lt(this)

  def le(another: Period): Boolean = lt(another) || eq(another)

  def ge(another: Period): Boolean = another.le(this)

  def lt(another: Period) : Boolean = {

    if (units == another.units) return length < another.length
    if (units == MONTHS && another.units == YEARS) return length < 12 * another.length
    if (units == YEARS && another.units == MONTHS) return 12 * length < another.length
    if (units == DAYS && another.units == WEEKS) return length < 7 * another.length
    if (units == WEEKS && another.units == DAYS) return 7 * length < another.length

    val period1MinDays = minDays
    val period1MaxDays = maxDays
    val period2MinDays = another.minDays
    val period2MaxDays = another.maxDays

    if (period1MaxDays < period2MinDays) true
    else if (period1MinDays > period2MaxDays) false else throw new Exception(UNDECIDABLE_COMPARISON)


  }

  def frequency : Frequency = {

    val length : Int = Math.abs(this.length)

    if(length == 0) return NO_FREQUENCY

    units match {
      case YEARS => if (length == 1) return ANNUAL else OTHER_FREQUENCY
      case MONTHS => if (12 % length == 0 && length <=12)
          Frequency.valueOf(12 / length)
        else OTHER_FREQUENCY
      case WEEKS => {
        if (length == 1) WEEKLY
        else if (length == 2) BIWEEKLY
        else if (length == 4) EVERY_FOURTH_WEEK
        else OTHER_FREQUENCY
      }
      case DAYS => if (length ==1) DAILY else OTHER_FREQUENCY
      case _ => throw new Exception(UNKNOWN_TIME_UNIT)
    }

  }

  def years(p: Period) : Double = {

    p.units match {
      case DAYS | WEEKS => throw new IllegalArgumentException(UNDECIDABLE_COMPARISON)
      case MONTHS => p.length / 12.0
      case YEARS => p.length
      case _ => throw new Exception(UNKNOWN_TIME_UNIT)
    }

  }

  def months(p: Period) : Double = {

    if (p.length == 0) return 0.0

    p.units match {
      case DAYS | WEEKS => throw new IllegalArgumentException(UNDECIDABLE_COMPARISON)
      case MONTHS => p.length
      case YEARS => p.length * 12.0
      case _ => throw new Exception(UNKNOWN_TIME_UNIT)
    }
  }

  def weeks(p: Period): Double = {

    if (p.length == 0) return 0.0

    p.units match {
      case DAYS => p.length / 7.0
      case WEEKS => p.length
      case MONTHS | YEARS => throw new IllegalArgumentException(UNDECIDABLE_COMPARISON)
      case _ => throw new Exception(UNKNOWN_TIME_UNIT)
    }
  }

  def days (p: Period) : Double = {

    if (p.length == 0) return 0.0

    p.units match {
      case DAYS => p.length
      case WEEKS => p.length * 7.0
      case MONTHS | YEARS  => throw new IllegalArgumentException(UNDECIDABLE_COMPARISON)
      case _ => throw new Exception(UNKNOWN_TIME_UNIT)
    }
  }

  override def equals(obj : Any) : Boolean = {

    if (obj == this) true
    else if (obj == null) false
    else if (!obj.isInstanceOf[Period]) false
    else {
      obj.asInstanceOf[Period].fEquals(this)
    }

  }

  def fEquals(period : Period) : Boolean = {
    if (period.isInstanceOf[Period]) return false
    if (length != period.asInstanceOf[Period].length) return false
    if (units != period.asInstanceOf[Period].units) return false
    true
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





}

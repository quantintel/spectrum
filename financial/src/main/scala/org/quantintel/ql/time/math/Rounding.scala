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

package org.quantintel.ql.time.math


object RoundingMethods extends Enumeration  {


    type RoundingMethods = Value
    val NONE = Value(1)
    // do not round: return the number unmodified
    val UP = Value(2)
    /* the first decimal place past the precision will be
                            rounded up.  This differs from the OMG rule which
                            rounds up only if the decimal to be rounded is
                            greater than or equal to the rounding digit */
    val DOWN = Value(3)
    /* All decimal places past the precision will be
                            truncated */
    val CLOSETS = Value(4)
    /*The first decimal place past the precision
                            will be rounded up if greater than or equal
                            to the OMG round-up digit; this corresponds
                            to the OMG round-up rule.  When the rounding
                            digit is 5, the result will be the one
                            closets to the original number, hence the name */
    val FLOOR = Value(5)
    /* positive numbers will be rounded up and negative
                            numbers will be rounded down using the OMG round up
                            and round down rules */
    val CEILING = Value(6)
    /* positive numbers will be rounded down and negative
                            numbers will be rounded up using the OMG round up
                            and round down rules */

    def valueOf(methods: Int) = methods match {
      case 1 => NONE
      case 2 => UP
      case 3 => DOWN
      case 4 => CLOSETS
      case 5 => FLOOR
      case 6 => CEILING
      case _ => throw new Exception("Valid units = 1 to 6")
    }


}

import org.quantintel.ql.time.math.RoundingMethods._

class Rounding(precision: Int, method: RoundingMethods, digit: Int) {

  def round(value: Double) : Double = {
    if (method == NONE) value

    val mult: Double = Math.pow(10.0, precision)
    val neg: Boolean = (value < 0.0)
    var lvalue : Double = Math.abs(value) * mult
    var integral : Double = lvalue.asInstanceOf[Int]
    val modVal : Double = (lvalue-lvalue.asInstanceOf[Int])
    lvalue = lvalue - modVal

    method match {
      case DOWN => {
         // no op
      }
      case UP => {
        lvalue = lvalue + 1.0
      }
      case CLOSETS => {
        if (modVal >= (digit/10.0)){
          lvalue = lvalue + 1.0
        }
      }
      case FLOOR => {
        if (!neg){
          if (modVal >= (digit/10.0)){
            lvalue = lvalue + 1.0
          }
        }
      }
      case CEILING => {
        if (neg) {
          if (modVal>= (digit/10.0)){
            lvalue = lvalue + 1.0
          }
        }
      }
      case _ => {}
    }

    if (neg) -(lvalue/mult) else lvalue/mult

  }


}

/**
 * @author Paul Bernard
 */
object Rounding {

  import org.quantintel.ql.time.math.RoundingMethods._

  def apply(precision: Int, method: RoundingMethods, digit: Int) : Rounding = {
    method match {
      case NONE => new None(precision, method, digit)
      case UP => new UpRounding(precision, method, digit)
      case DOWN => new DownRounding(precision, method, digit)
      case CLOSETS => new ClosestRounding(precision, method, digit)
      case FLOOR => new FloorTruncation(precision, method, digit)
      case CEILING => new CeilingTruncation(precision, method, digit)
      case _ => throw new Exception("unknown rounding method")
    }
  }


  class None(precision: Int, method: RoundingMethods, digit: Int)
    extends Rounding (precision, method, digit) {}

  class UpRounding(precision: Int, method: RoundingMethods, digit: Int)
    extends Rounding (precision, method, digit) {}

  class DownRounding(precision: Int, method: RoundingMethods, digit: Int)
    extends Rounding (precision, method, digit) {}

  class ClosestRounding(precision: Int, method: RoundingMethods, digit: Int)
    extends Rounding (precision, method, digit) {}

  class CeilingTruncation(precision: Int, method: RoundingMethods, digit: Int)
    extends Rounding (precision, method, digit) {}

  class FloorTruncation(precision: Int, method: RoundingMethods, digit: Int)
    extends Rounding (precision, method, digit) {}


}

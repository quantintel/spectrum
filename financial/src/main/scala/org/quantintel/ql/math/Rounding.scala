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

package org.quantintel.ql.math


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
    val CLOSEST = Value(4)
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
      case 4 => CLOSEST
      case 5 => FLOOR
      case 6 => CEILING
      case _ => throw new Exception("Valid units = 1 to 6")
    }


}

import org.quantintel.ql.math.RoundingMethods._

class Rounding {

  var precision : Int = 0
  var method: RoundingMethods = NONE
  var digit: Int = 0

  def this (prec: Int, mthd: RoundingMethods, dig: Int){
    this
    precision = prec
    method = mthd
    digit = dig
  }

  def this(precision: Int) {
    this(precision, CLOSEST, 5)
  }

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
      case CLOSEST => {
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

  import org.quantintel.ql.math.RoundingMethods._

  def apply(method: RoundingMethods, precision: Int) : Rounding = {
    method match {
      case NONE => new Rounding()
      case UP => new UpRounding(precision)
      case DOWN => new DownRounding(precision)
      case CLOSEST => new ClosestRounding(precision)
      case CEILING => new CeilingTruncation(precision)
      case FLOOR => new FloorTruncation(precision)
      case _ => new Rounding()
    }
  }


  class UpRounding extends Rounding  {

    def this (prec: Int, dig: Int) {
      this
      precision = prec
      method = UP
      digit = dig
    }

    def this (precision: Int) {
      this(precision, 5)
    }

  }

  class DownRounding extends Rounding {

    def this(prec: Int, dig: Int) {
      this
      precision = prec
      method = DOWN
      digit = dig
    }

    def this(precision: Int){
      this(precision, 5)
    }

  }


  class ClosestRounding extends Rounding {

    def this(prec: Int, dig: Int) {
      this
      precision = prec
      method = CLOSEST
      digit = dig
    }

    def this(precision: Int){
      this(precision, 5)
    }

  }


  class CeilingTruncation extends Rounding {

    def this(prec: Int, dig: Int) {
      this
      precision = prec
      method = CEILING
      digit = dig
    }

    def this(precision: Int){
      this(precision, 5)
    }

  }

  class FloorTruncation extends Rounding {

    def this(prec: Int, dig: Int) {
      this
      precision = prec
      method = FLOOR
      digit = dig
    }

    def this(precision: Int){
      this(precision, 5)
    }


  }


}

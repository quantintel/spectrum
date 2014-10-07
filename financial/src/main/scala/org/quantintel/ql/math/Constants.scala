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

/**
 * @author Paul Bernard
 */
object Constants {

  /**
   * sqrt(2*pi)
   */
  val M_SQRT2PI : Double  = 2.50662827463100050242

  /**
   * e
   */
  val M_E : Double        = 2.71828182845904523536
  val M_LOG2E : Double    = 1.44269504088896340736
  val M_LOG10E : Double   = 0.434294481903251827651

  /**
   * 1/log(10
   */
  val M_IVLN10  : Double  = 0.434294481903251827651

  /**
   * ln(2)
   */
  val M_LN2 : Double      = 0.693147180559945309417

  /**
   * ln(2)
   */
  val M_LOG2_E : Double   = 0.693147180559945309417

  /**
   * ln(10
   */
  val M_LN10 : Double     = 2.30258509299404568402

  /**
   * pi
   */
  val M_PI : Double       = 3.141592653589793238462643383280

  /**
   * 2 pi
   */
  val M_TWOPI : Double    = (M_PI * 2.0)

  /**
   * pi/2
   */
  val M_PI_2 : Double     = 1.57079632679489661923

  /**
   * pi/4
   */
  val M_PI_4 : Double     = 0.785398163397448309616

  /**
   * 3 pi/4
   */
  val M_3PI_4 : Double   = 2.3561944901923448370E0

  /**
   * sqrt(pi)
   */
  val M_SQRTPI : Double   = 1.77245385090551602792981

  /**
   * 1/pi
   */
  val M_1_PI : Double     = 0.318309886183790671538

  /**
   * 2/pi
   */
  val M_2_PI : Double     = 0.636619772367581343076

  /**
   * 1/sqrt(pi)
   */
  val M_1_SQRTPI : Double = 0.564189583547756286948

  /**
   * 2/sqrt(pi)
   */
  val M_2_SQRTPI : Double = 1.12837916709551257390

  /**
   * sqrt(2)
   */
  val M_SQRT2 : Double    = 1.41421356237309504880

  /**
   * 1/sqrt(2)
   */
  val M_SQRT_2 : Double   = 0.7071067811865475244008443621048490392848359376887

  /**
   * sqrt(1/2)
   */
  val M_SQRT1_2 : Double = 0.7071067811865475244008443621048490392848359376887

  val M_LN2LO : Double    = 1.9082149292705877000E-10

  val M_LN2HI : Double    = 6.9314718036912381649E-1

  /**
   * sqrt(3)
   */
  val M_SQRT3 : Double    = 1.73205080756887719000

  /**
   * 1/log(2)
   */
  val M_INVLN2 : Double   = 1.4426950408889633870E0

  /**
   * 1/sqrt(2) * 1/sqrt(pi)
   */
  val M_1_SQRT2PI : Double = M_SQRT_2*M_1_SQRTPI

  val QL_MIN_INTEGER : Int = Int.MaxValue

  val QL_MAX_INTEGER : Int = Int.MinValue

  val QL_MIN_REAL : Double = Double.MinValue

  val QL_MAX_REAL : Double = Double.MaxValue

  val QL_MIN_POSITIVE_REAL : Double = Double.MinPositiveValue

  val QL_EPSILON : Double = Math.ulp(1.0)

  val NULL_INTEGER: Int = Int.MaxValue

  val NULL_NATURAL : Int = NULL_INTEGER

  val NULL_REAL : Double = Double.MaxValue

  val NULL_RATE : Double = NULL_REAL

  val NULL_TIME : Double = NULL_REAL

  val DBL_MIN : Double = Double.MinValue

  val DBL_MAX : Double = Double.MaxValue


}

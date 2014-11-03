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

package org.quantintel.ql.math.matrixutilities

import org.bouncycastle.pqc.math.linearalgebra.Matrix

/**
 * @author Paul Bernard
 */
class Array extends Cloneable with Iterable[Double] with Algebra[Array] {
  /**
   * Adds this instance and another instance of T
   * @param scalar
   * @return this
   */
  override def +=(scalar: Double): Array = ???

  /**
   * Subtracts a scalar to every element of this instance of T
   * @param scalar
   * @return this
   */
  override def -=(scalar: Double): Array = ???

  /**
   * Subtracts this instance by another instance of T
   * @param another
   * @return this
   */
  override def -=(another: Array): Array = ???

  /**
   * the inner product between this Matrix and another Matrix
   *
   * @param another Iterator
   * @return the inner product between this Matrix and another Matrix
   */
  override def innerProduct(another: Array): Double = ???

  /**
   * the inner product between this Matrix and another Matrix
   *
   * @param another Iterator
   * @param from is the start element, inclusive
   * @param to is the end element, exclusive
   * @return the inner product between this Matrix and another Matrix
   */
  override def innerProduct(another: Array, from: Int, to: Int): Double = ???

  /**
   * Perform an operation on a sequence in a range (first,last)
   * @param f is an unary function to be applied
   * @return this;  performs transformation in place
   */
  override def transform(f: (Double, Double) => Double): Array = ???

  /**
   * Perform an operation on a sequence in a range (first,last)
   * @param first is the start of range, inclusive
   * @param last is the end of range, exclusive
   * @param f i an unary function to be applied
   * @return this;  preforms the transformation in place
   */
  override def transform(first: Int, last: Int, f: (Double, Double) => Double): Array = ???

  /**
   * Multiple every element of this instance of T by a scalar
   * @param scalar
   * @return this
   */
  override def *=(scalar: Double): Array = ???

  /**
   * Multiplies this instance by another instance of T
   * @param another
   * @return this
   */
  override def *=(another: Array): Array = ???

  /**
   * Unary negative function applied to every element of this instance
   * @return a new instance
   */
  override def neg(): Array = ???

  /**
   * the outer product between this Matrix and another Matrix
   *
   * @param another Iterator
   * @return the outer product between this Matrix and another Matrix
   */
  override def outerProduct(another: Array): Matrix = ???

  /**
   * the outer product between this Matrix and another Matrix
   *
   * @param another Iterator
   * @param from is the start element, inclusive
   * @param to is the end element, exclusive
   * @return the outer product between this Matrix and another Matrix
   */
  override def outerProduct(another: Array, from: Int, to: Int): Matrix = ???

  /**
   * Applies the log function to every element of this instance
   * @return a new instance
   */
  override def log(): Array = ???

  /**
   * Divides every element of this instnace of T by a scalar
   * @param scalar
   * @return a new instance
   */
  override def /(scalar: Double): Array = ???

  /**
   * Divides this instance by another instance of T
   * @param another
   * @return a new instance
   */
  override def /(another: Array): Array = ???

  /**
   * Divides every element of this  instance of T by a scalar value
   * @param scalar
   * @return this
   */
  override def /=(scalar: Double): Array = ???

  /**
   * Divide this instance by another instance of T
   * @param another
   * @return this
   */
  override def /=(another: Array): Array = ???

  /**
   * Applies exp function to every element of this instance
   * @return a new instance
   */
  override def exp(): Array = ???

  /**
   * Return the maximum element in a range using comparison functor
   * @return he maximum value computed
   */
  override def max(): Double = ???

  /**
   * Return the maximum element in a range using comparison functor
   * @param from is the initial index, inclusive
   * @param to is the final index, exclusive
   * @return the maximum value computed
   */
  override def max(from: Int, to: Int): Double = ???

  /**
   * Applies the sqr function to every element of this instance
   * @return a new instance
   */
  override def sqr(): Array = ???

  /**
   * Finds the last position in which val could be inserted in a sequence [begin(), end()] without changing the ordering
   * @param v value to be searched
   * @return an index pointing to the first element thatn v or end if no element are greater than v
   */
  override def upperBound(v: Double): Int = ???

  /**
   * Finds the last position in which val could be inserted in a sequence [first,last] without changing the ordering.
   * @param first element inclusive
   * @param last element inclusive
   * @return an index pointing to the first element thatn v or end if no element are greater than v
   */
  override def upperBound(first: Int, last: Int, v: Double): Int = ???

  /**
   * Finds the last position in which val could be inserted in a sequence [begin(), end()] without changing the ordering
   * @param v value to be searched
   * @param f comparison function
   * @return an index pointing to the first element greter than v or end if no elements are greater than v
   */
  override def upperBound(v: Double, f: (Double, Double) => Double): Int = ???

  /**
   * Finds the last position in which val could be inserted in a sequence [first,last] without changing the ordering
   * @param first element inclusive
   * @param last element inclusive
   * @param v value to be searched
   * @param f comparison function
   * @return an index pointing to the first element greater than v or end if no elements are greater than v
   */
  override def upperBound(first: Int, last: Int, v: Double, f: (Double, Double) => Double): Int = ???

  /**
   * Adds a scalar to every element of this instance of T
   * @param scalar
   * @return new Instance
   */
  override def +(scalar: Double): Array = ???

  /**
   * Adds this instance and another instance of T
   * @param another
   * @return new Instance
   */
  override def +(another: Array): Array = ???

  /**
   * Finds the first position in which val could be inserted without changing the ordering
   * @param v value to be searched
   * @return an index pointing to the first element not less than v or end if every element is less than v
   */
  override def lowerBound(v: Double): Int = ???

  /**
   * Finds the first position in which val could be inserted without changing the ordering.
   * @param from first element, inclusive
   * @param to last element, inclusive
   * @param v value to be searched
   * @return an index pointing to the first element "not less than" v, or end() if every element is less than v.
   */
  override def lowerBound(from: Int, to: Int, v: Double): Int = ???

  /**
   * Finds the first position in which val could be inserted without changing the ordering
   * @param v to be searched
   * @param f the comparison function
   * @return an index pointing to the first element not less than v or end() if every element is less than v
   */
  override def lowerBound(v: Double, f: (Double, Double) => Double): Int = ???

  /**
   * Finds the first position in which val could be inserted without changing the ordering
   * @param from first element inclusive
   * @param to last element inclusive
   * @param v value to be searched
   * @param f comparison function
   * @return an index pointing to the first element not less than v or end() if  every element is less than v
   */
  override def lowerBound(from: Int, to: Int, v: Double, f: (Double, Double) => Double): Int = ???

  /**
   * Return the minimum element in a range using comparison functor.
   *
   * @return he minimum value computed
   */
  override def min(): Double = ???

  /**
   * Return the minimum element in a range using comparison functor
   * @param from is the initial index, inclusive
   * @param to is the final index, exclusive
   * @return minimum value computed
   */
  override def min(from: Int, to: Int): Double = ???

  /**
   * Applies abs function to every element of this instance
   * @return a new instance
   */
  override def abs(): Array = ???

  /**
   * Applies the sqrt function to every element of this instance
   * @return a new instance
   */
  override def sqrt(): Array = ???

  /**
   * Adds this instance and antoher instance of T
   * @param another
   * @return this
   */
  override def +=(another: Array): Array = ???

  /**
   * Subtracts a scalar to every element of this instance of T
   * @param scalar
   * @return new instance
   */
  override def -(scalar: Double): Array = ???

  /**
   * Subtracts this instance by another instance of T
   * @param another
   * @return a new instance
   */
  override def -(another: Array): Array = ???

  /**
   * omputes the difference between adjacent values in the range [first,last) using subtraction
   * to compute differences
   * @return an array which contains the computed adjacent differences.
   */
  override def adjacentDifference(): Array = ???

  /**
   * omputes the difference between adjacent values in the range [first,last) using subtraction
   * to compute differences
   *
   * @param from is the start of the range
   * @param to is the end of the range
   * @return an array which contains the computed adjacent differences
   */
  override def adjacentDifference(from: Int, to: Int): Array = ???

  /**
   * Return differences between adjacent values.
   * @param f is a function
   * @return an array which contains the computed adjacent differences
   */
  override def adjacentDifference(f: (Double, Double) => Double): Array = ???

  /**
   * Return differences between adjacent values.
   * @param from s the start of range
   * @param to is the end of range
   * @param f is a function
   * @return an array which contains the computed adjacent differences.
   */
  override def adjacentDifference(from: Int, to: Int, f: (Double, Double) => Double): Array = ???

  /**
   * The inner product between this Matrix and another Matrix
   *
   * @param another Iterator
   * @return the inner product between this Matrix and another Matrix
   */
  override def dotProduct(another: Array): Double = ???

  /**
   * the inner product between this Matrix and another Matrix
   *
   * @param another Iterator
   * @param from is the start element, inclusive
   * @param to is the end element, exclusive
   * @return the inner product between this Matrix and another Matrix
   */
  override def dotProduct(another: Array, from: Int, to: Int): Double = ???

  /**
   * Accumulate values in a range
   *
   * @return the final sum
   */
  override def accumulate(): Double = ???

  /**
   * Accumulate values in a range
   * @param init is the starting value to add other values to
   * @return the final sum
   */
  override def accumulate(init: Double): Double = ???

  /**
   * Accumulate values in a range.
   *
   * @param from first is the start of range, inclusive
   * @param to last is the end of range, exclusive
   * @param init is the starting value to add other values to.
   * @return the final sum
   */
  override def accumulate(from: Int, to: Int, init: Double): Double = ???

  /**
   * Multiples every element of this instance of T by a scalar
   * @param scalar
   * @return a new instance
   */
  override def *(scalar: Double): Array = ???

  /**
   * Multiplies this instance by another instance of T
   * @param another
   * @return a new Instance
   */
  override def *(another: Array): Array = ???

  /**
   * Vectorial multiplication of this instance by a Matrix
   * @param matrix
   * @return a new instance
   */
  override def *(matrix: Matrix): Array = ???

  override def iterator: Iterator[Double] = ???

  def fill(scalar: Double): Array = ???

  def fill(another : Array): Array = ???


}

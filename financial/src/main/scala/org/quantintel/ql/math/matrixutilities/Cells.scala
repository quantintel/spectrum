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

import org.quantintel.ql.math.matrixutilities.internal.Address
import org.quantintel.ql.math.matrixutilities.internal.Flags.Flags
import org.quantintel.ql.math.matrixutilities.internal.Flags._


/**
 * @author Paul Bernard
 */
class Cells [T <: Address] (rows: Int, cols: Int, addr: T) extends Cloneable {

  protected var size: Int = rows * cols

  def this(row: Int, cols: Int, data: Array, addr: T) {
    this(rows, cols, data)
    if(data.size != addr.rows * addr.cols) throw new Exception("declared dimension do not match underlying storage size")
  }

  def flags(): Set[Flags] = addr.flags()

  def requireFlags(required: Set[Flags], variable: String): Unit = {
    if(required.contains(FORTRAN) != addr.isFortran()){
      val name : String = if (variable==null) "variable"
        else this.getClass.getSimpleName + " " + variable
      val message : String = String.format(Cells.FORTRAN_ADDRESSING_EXPECTED, name)
    }
  }

  override def clone(): Cells = {
    try {
      super.clone().asInstanceOf[Cells]
    } catch {
      case e: CloneNotSupportedException => {
        throw new Exception(e)
      }
    }

  }

}

object Cells {

  val FORTRAN_ADDRESSING_EXPECTED = "variable \"%s\" should be FORTRAN-style addressing"
  val INVALID_ARGUMENTS = "invalid arguments"
  val WRONG_BUFFER_LENGTH = "wrong buffer length"
  val MATRIX_IS_INCOMPATIBLE = "matrix is incompatible"
  val ARRAY_IS_INCOMPATIBLE = "array is incompatible"
  val ITERATOR_IS_INCOMPATIBLE = "iterator is incompatible"
  val NOT_ENOUGH_STORAGE = "not enough storage area for operation"
  val MATRIX_MUST_BE_SQUARE = "matrix must be square"
  val MATRIX_MUST_BE_SYMMETRIC = "matrix must be symmetric"
  val MATRIX_IS_SINGULAR = "matrix is singular"
  val NON_CONTIGUOUS_DATA = "Operation not supported on non-contiguous data"

}

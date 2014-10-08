package org.quantintel.lang

import scala.math.BigDecimal

/**
 * @author Paul Bernard
 */
package object numeric {

  implicit def unboxInt(i: java.lang.Integer) = i.intValue

  implicit class ExtendedDouble(d: Double) {
    def rounded(l: Int) = {
      BigDecimal(d).setScale(l, BigDecimal.RoundingMode.HALF_UP).toDouble
    }
  }

}

package org.quantintel.ql.time.daycounters


import org.quantintel.ql.time.daycounters.Thirty360Convention._
import org.scalatest.{FlatSpec, Matchers}

/**
 *
 * Created by Paul Bernard on 8/2/14.
 *
 * 30E/360
 *
 * 30-day months, end of month adjustments
 *
 * 1. 01/15/07 - 01/30/07
 * 2. 01/15/07 - 01/30/07
 * 3. 01/15/07 - 02/15/07
 * 4. 01/15/07 - 07/15/07
 * 5. 09/30/07 - 03/31/08
 * 6. 09/30/07 - 10/31/07
 * 7. 09/30/07 - 09/30/08
 * 8. 01/15/07 - 01/31/07
 * 9. 01/31/07 - 02/28/07
 * 10. 02/28/07 - 03/31/07
 * 11. 08/31/06 - 02/28/07
 * 12. 02/28/07 - 08/31/07
 * 13. 02/14/07 - 02/28/07
 * 14. 02/26/07 - 02/29/08
 * 15. 02/29/08 - 02/28/09
 * 16. 02/29/08 - 03/30/08
 * 17. 02/29/08 - 03/31/08
 * 18. 02/28/07 - 03/05/07
 * 19. 10/31/07 - 11/28/07
 * 20. 08/31/07 - 02/29/08
 * 21. 02/29/08 - 08/31/08
 * 22. 08/31/08 - 02/28/09
 * 23. 02/28/09 - 08/31/09
 *
 */
class ThirtyE360Test extends FlatSpec with Matchers {


  import org.quantintel.lang.numeric._
  import org.quantintel.ql.time.Date
  import org.quantintel.ql.time.daycounters.Thirty360Convention.EUROPEAN

  "1. 01/15/07 - 01/30/07" should "be 0.041666667" in {

    val d1 = new Date(15, 1, 2007)
    val d2 = new Date(30, 1, 2007)

    val yf :Double = Thirty360(EUROPEAN).yearFraction(d1, d2)

    assert(yf.rounded(9) == 0.041666667)

    
    
  }

  "2. 01/15/07 - 01/30/07" should "be 0.041666667" in {

    val d1 = new Date(15, 1, 2007)
    val d2 = new Date(30, 1, 2007)

    val yf :Double = Thirty360(EUROPEAN).yearFraction(d1, d2)

    assert(yf.rounded(9) == 0.041666667)

  }



  "3. 01/15/07 - 02/15/07" should "be .083333333" in {

    val d1 = new Date(15, 1, 2007)
    val d2 = new Date(15, 2, 2007)

    val yf :Double = Thirty360(EUROPEAN).yearFraction(d1, d2)

    assert(yf.rounded(9) == 0.083333333)

  }

  "4. 01/15/07 - 07/15/07" should "be 0.500000000" in {

    val d1 = new Date(15, 1, 2007)
    val d2 = new Date(15, 7, 2007)

    val yf :Double = Thirty360(EUROPEAN).yearFraction(d1, d2)

    assert(yf.rounded(9) == 0.500000000)

  }

  "5. 09/30/07 - 03/31/08" should "be 0.500000000" in {

    val d1 = new Date(30, 9, 2007)
    val d2 = new Date(31, 3, 2008)

    val yf :Double = Thirty360(EUROPEAN).yearFraction(d1, d2)

    assert(yf.rounded(9) == 0.500000000)

  }

  "6. 09/30/07 - 10/31/07" should "be 0.083333333" in {

    val d1 = new Date(30, 9, 2007)
    val d2 = new Date(31, 10, 2007)

    val yf :Double = Thirty360(EUROPEAN).yearFraction(d1, d2)

    assert(yf.rounded(9) == 0.083333333)

  }

  "7. 09/30/07 - 09/30/08" should "be 1.000000000" in {

    val d1 = new Date(30, 9, 2007)
    val d2 = new Date(30, 9, 2008)

    val yf :Double = Thirty360(EUROPEAN).yearFraction(d1, d2)

    assert(yf.rounded(9) == 1.000000000)

  }

  "8. 01/15/07 - 01/31/07" should "be 0.041666667" in {

    val d1 = new Date(15, 1, 2007)
    val d2 = new Date(31, 1, 2007)

    val yf :Double = Thirty360(EUROPEAN).yearFraction(d1, d2)

    assert(yf.rounded(9) == 0.041666667)

  }

  "9. 01/31/07 - 02/28/07" should "be  0.077777778" in {

    val d1 = new Date(31, 1, 2007)
    val d2 = new Date(28, 2, 2007)

    val yf :Double = Thirty360(EUROPEAN).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.077777778)

  }

  "10. 02/28/07 - 03/31/07" should "be 0.088888889" in {

    val d1 = new Date(28, 2, 2007)
    val d2 = new Date(31, 3, 2007)

    val yf :Double = Thirty360(EUROPEAN).yearFraction(d1, d2)

    assert(yf.rounded(9) == 0.088888889)

  }

  "11. 08/31/06 - 02/28/07" should "be 0.494444444" in {

    val d1 = new Date(31, 8, 2006)
    val d2 = new Date(28, 2, 2007)

    val yf :Double = Thirty360(EUROPEAN).yearFraction(d1, d2)

    assert(yf.rounded(9) == 0.494444444)

  }

  "12. 02/28/07 - 08/31/07" should "be 182" in {

    val d1 = new Date(28, 2, 2007)
    val d2 = new Date(31, 8, 2007)

    val yf :Double = Thirty360(EUROPEAN).yearFraction(d1, d2)

    assert(yf.rounded(9) == (182/360d).rounded(9))

  }

  "13. 02/14/07 - 02/28/07" should "be 14" in {

    val d1 = new Date(14, 2, 2007)
    val d2 = new Date(28, 2, 2007)

    val yf :Double = Thirty360(EUROPEAN).yearFraction(d1, d2)

    assert(yf.rounded(9) == (14/360d).rounded(9))

  }

  "14. 02/26/07 - 02/29/08" should "be 363" in {

    val d1 = new Date(26, 2, 2007)
    val d2 = new Date(29, 2, 2008)

    val yf :Double = Thirty360(EUROPEAN).yearFraction(d1, d2)

    assert(yf.rounded(9) == (363/360d).rounded(9))

  }

  "15. 02/29/08 - 02/28/09" should "be 0.997222222" in {

    val d1 = new Date(29, 2, 2008)
    val d2 = new Date(28, 2, 2009)

    val yf :Double = Thirty360(EUROPEAN).yearFraction(d1, d2)

    assert(yf.rounded(9) == 0.997222222)

  }

  "16. 02/29/08 - 03/30/08" should "be 31" in {

    val d1 = new Date(29, 2, 2008)
    val d2 = new Date(30, 3, 2008)

    val yf :Double = Thirty360(EUROPEAN).yearFraction(d1, d2)

    assert(yf.rounded(9) == (31/360d).rounded(9))

  }

  "17. 02/29/08 - 03/31/08" should "be 31" in {

    val d1 = new Date(29, 2, 2008)
    val d2 = new Date(31, 3, 2008)

    val yf :Double = Thirty360(EUROPEAN).yearFraction(d1, d2)

    assert(yf.rounded(9) == (31/360d).rounded(9))

  }

  "18. 02/28/07 - 03/05/07" should "be 7" in {

    val d1 = new Date(28, 2, 2007)
    val d2 = new Date(5, 3, 2007)

    val yf :Double = Thirty360(EUROPEAN).yearFraction(d1, d2)

    assert(yf.rounded(9) == (7/360d).rounded(9))

  }

  "19. 10/31/07 - 11/28/07" should "be 28" in {

    val d1 = new Date(31, 10, 2007)
    val d2 = new Date(28, 11, 2007)

    val yf :Double = Thirty360(EUROPEAN).yearFraction(d1, d2)

    assert(yf.rounded(9) == (28/360d).rounded(9))

  }

  "20. 08/31/07 - 02/29/08" should "be 179" in {

    val d1 = new Date(31, 8, 2007)
    val d2 = new Date(29, 2, 2008)

    val yf :Double = Thirty360(EUROPEAN).yearFraction(d1, d2)

    assert(yf.rounded(9) == (179/360d).rounded(9))

  }

  "21. 02/29/08 - 08/31/08" should "be 181" in {

    val d1 = new Date(29, 2, 2008)
    val d2 = new Date(31, 8, 2008)

    val yf :Double = Thirty360(EUROPEAN).yearFraction(d1, d2)

    assert(yf.rounded(9) == (181/360d).rounded(9))

  }

  "22. 08/31/08 - 02/28/09" should "be 178" in {

    val d1 = new Date(31, 8, 2008)
    val d2 = new Date(28, 2, 2009)

    val yf :Double = Thirty360(EUROPEAN).yearFraction(d1, d2)

    assert(yf.rounded(9) == (178/360d).rounded(9))

  }

  "23. 02/28/09 - 08/31/09" should "be 182" in {

    val d1 = new Date(28, 2, 2009)
    val d2 = new Date(31, 8, 2009)

    val yf :Double = Thirty360(EUROPEAN).yearFraction(d1, d2)

    assert(yf.rounded(9) == (182/360d).rounded(9))

  }

}

package org.quantintel.ql.time.daycounters

import org.scalatest.{FlatSpec, Matchers}

/** Test for the 30E+/360 day count method. Notable difference between 30/360 is the handling of months with
 *  31 days in them.
 * 
 * @author Paul Bernard
 * @author Peter Mularien
 */
class ThirtyEP360Test extends FlatSpec with Matchers {


  import org.quantintel.lang.numeric._
  import org.quantintel.ql.time.Date
  import org.quantintel.ql.time.daycounters.Thirty360Convention.EP


  "1. 01/31/94 - 03/16/1994" should "be 0.127777778" in {

    val d1 = Date(31, 1, 1992)
    val d2 = Date(16, 3, 1992)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) == 0.127777778)
    
  }

  "2. 01//01/93 - 02/21/93" should "be 0.138888889" in {



    val d1 = new Date(1, 1, 1993)
    val d2 = new Date(21, 2, 1993)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.138888889)

  }

  "3. 02/01/93 - 03/01/93" should "be 0.083333333" in {


    val d1 = new Date(1,2,1993)
    val d2 = new Date(1, 3, 1993)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.083333333)


  }


  "4. 02/01/96 - 03/01/96" should "be 0.083333333" in {



    val d1 = new Date(1, 2, 1992)
    val d2 = new Date(1, 3, 1992)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.083333333)


  }

  "5. 01/01/93 - 01/01/94" should "be 1.000000000" in {



    val d1 = new Date(1,1, 1993)
    val d2 = new Date(1,1, 1994)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  1.000000000)

  }


  "6. 01/15/93 - 02/01/93" should "be 0.044444444" in {



    val d1 = new Date(15, 1, 1993)
    val d2 = new Date(1, 2, 1993)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.044444444)


  }


  "7. 02/15/93 - 04/01/93" should "be 46" in {

    val d1 = new Date(15, 2, 1993)
    val d2 = new Date(1,4, 1993)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  (46/360d).rounded(9))


  }

  "8. 03/31/93 to 04/30/93" should "be 0.083333333" in {


    val d1 = new Date(31, 3, 1993)
    val d2 = new Date(30, 4, 1993)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.083333333)

  }

  "9. 03/31/93 - 12/31/93" should "be 0.750000000" in {


    val d1 = new Date(31, 3, 1993)
    val d2 = new Date(31, 12, 1993)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

/* Different from 30/360:
    0  (3)
    30 (4)
    30 (5)
    30 (6)
    30 (7)
    30 (8)
    30 (9)
    30 (10)
    30 (11)
    30 (12)
    1 (1) <-- here
    = 271 / 360 
    */  

    assert(yf.rounded(9) ==  0.752777778)

  }

  "10. 03/15/93 - 06/15/93" should "be 0.250000000" in {


    val d1 = new Date(15, 3, 1993)
    val d2 = new Date(15, 6, 1993)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.250000000)
  }

  "11. 11/01/93 - 03/01/94" should "be 0.333333333" in {


    val d1 = new Date(1, 11, 1993)
    val d2 = new Date(1, 3, 1994)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.333333333)
  }

  "12. 12/31/93 - 02/01/94" should "be 0.086111111" in {



    val d1 = new Date(31, 12, 1993)
    val d2 = new Date(1, 2, 1994)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.086111111)

  }

  "13. 07/15/93 - 09/15/93" should "be 0.166666667" in {



    val d1 = new Date(15, 7, 1993)
    val d2 = new Date(15, 9, 1993)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.166666667)


  }

  "14. 08/21/93 - 04/11/94" should "be 0.638888889" in {



    val d1 = new Date(21, 8, 1993)
    val d2 = new Date(11, 4, 1994)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.638888889)

  }

  "15. 03/31/93 - 04/01/93" should "be 0.002777778" in {



    val d1 = new Date(31, 3, 1993)
    val d2 = new Date(1, 4, 1993)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.002777778)

  }


  "16. 12/15/93 - 12/31/93" should "be 0.044444444" in {



    val d1 = new Date(15, 12, 1993)
    val d2 = new Date(31, 12, 1993)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.044444444)
  }



  "17. 12/15/93 - 12/30/93" should "be 0.041666667" in {

    val d1 = new Date(15, 12, 1993)
    val d2 = new Date(30, 12, 1993)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.041666667)
  }


  "18. 01/15/07 - 2/15/07" should "be 0.083333333" in {

    val d1 = new Date(15, 1, 2007)
    val d2 = new Date(15, 2, 2007)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.083333333)

  }

  "19. 01/15/07 - 07/15/07" should "be 0.500000000" in {

    val d1 = new Date(15, 1, 2007)
    val d2 = new Date(15, 7, 2007)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.500000000)

  }

  "20. 09/30/07 - 03/31/08" should "be  0.500000000" in {

    val d1 = new Date(30, 9, 2007)
    val d2 = new Date(31, 3, 2008)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)
/* Different from 30/360:
    0  (9)
    30 (10)
    30 (11)
    30 (12)
    30 (1)
    30 (2)
    30 (3)
    1 (4) <-- here
    = 181 / 360 
    */  
    assert(yf.rounded(9) ==  0.502777778)

  }

  "21. 09/30/07 - 10/31/07" should "be 0.083333333" in {

    val d1 = new Date(30, 9, 2007)
    val d2 = new Date(31, 10, 2007)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)
/* Different from 30/360:
    0  (9)
    30 (10)
    1  (11) <-- here
    = 31 / 360 
    */  
    assert(yf.rounded(9) ==  0.086111111)

  }

  "22. 09/30/07 - 09/30/08" should "be 1.000000000" in {

    val d1 = new Date(30, 9, 2007)
    val d2 = new Date(30, 9, 2008)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  1.000000000)

  }

  "23. 01/15/07 - 01/31/07" should "be 0.044444444" in {

    val d1 = new Date(15, 1, 2007)
    val d2 = new Date(31, 1, 2007)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.044444444)

  }

  "24. 01/31/07 - 02/28/07" should "be 0.077777778" in {

    val d1 = new Date(31, 1, 2007)
    val d2 = new Date(28, 2, 2007)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.077777778)

  }

  "25. 02/28/07 - 03/31/07" should "be 0.091666667" in {

    val d1 = new Date(28, 2,2007)
    val d2 = new Date(31, 3, 2007)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.091666667)

  }

  "26. 08/31/06 - 02/28/07" should "be 0.494444444" in {

    val d1 = new Date(31, 8, 2006)
    val d2 = new Date(28, 2, 2007)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.494444444)

  }

  "27. 02/28/07 - 08/31/07" should "be 0.500000000" in {

    val d1 = new Date(28, 2, 2007)
    val d2 = new Date(31, 8, 2007)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.508333333)

  }

  "28. 02/14/07 - 02/28/07" should "be 0.038888889" in {

    val d1 = new Date(14, 2, 2007)
    val d2 = new Date(28, 2, 2007)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.038888889)

  }

  "29. 02/26/07 - 02/29/08" should "be 1.008333333" in {

    val d1 = new Date(26, 2, 2007)
    val d2 = new Date(29, 2, 2008)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  1.008333333)

  }

  "30. 02/29/08 - 02/28/09" should "be 0.997222222" in {

    val d1 = new Date(29, 2, 2008)
    val d2 = new Date(28, 2, 2009)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.997222222)

  }

  "31. 02/29/08 - 03/30/08" should "be 0.086111111" in {

    val d1 = new Date(29, 2, 2008)
    val d2 = new Date(30, 3, 2008)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.086111111)

  }

  "32. 02/29/08 - 03/31/08" should "be 0.088888889" in {

    val d1 = new Date(29, 2, 2008)
    val d2 = new Date(31, 3, 2008)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.088888889)

  }

  "33. 02/28/07 - 03/05/07" should "be 0.019444444" in {

    val d1 = new Date(28, 2, 2007)
    val d2 = new Date(5, 3, 2007)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.019444444)

  }

  "34. 10/31/07 - 11/28/07" should "be 0.077777778" in {

    val d1 = new Date(31, 10, 2007)
    val d2 = new Date(28, 11, 2007)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.077777778)

  }

  "35. 08/31/07 - 02/29/08" should "be 0.497222222" in {

    val d1 = new Date(31, 8, 2007)
    val d2 = new Date(29, 2, 2008)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.497222222)

  }

  "36. 02/29/08 - 08/31/08" should "be 0.505555556" in {
    val d1 = new Date(29, 2, 2008)
    val d2 = new Date(31, 8, 2008)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.505555556)

  }

  "37. 08/31/08 - 02/28/09" should "be 0.494444444" in {

    val d1 = new Date(31, 8, 2008)
    val d2 = new Date(28, 2, 2009)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.494444444)

  }

  "38. 02/28/09 - 08/31/09" should "be 0.508333333" in {

    val d1 = new Date(28, 2, 2009)
    val d2 = new Date(31, 8, 2009)

    val yf :Double = Thirty360(EP).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.508333333)

  }

}

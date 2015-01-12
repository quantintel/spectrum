package org.quantintel.ql.time.daycounters
import org.scalatest.{FlatSpec, Matchers}
import org.quantintel.ql.time.Date

/**
 * Test for ACT/365L (ISMA-Year) method. Validates behavior on a variety of specifically
 * chosen date ranges.
 *
 * 1.  01/31/90 - 03/16/91
 * 2.  05/06/94 - 10/30/94
 * 3.  01/01/93 - 02/21/93
 * 4.  02/01/93 - 03/01/93
 * 5.  02/01/96 - 03/01/96
 * 6.  01/01/93 - 01/01/94
 * 7.  01/15/93 - 02/01/93
 * 8.  02/15/93 - 04/01/93
 * 9.  03/31/93 - 04/30/93
 * 10. 03/31/93 - 12/31/93
 * 11. 03/15/93 - 06/15/93
 * 12. 11/01/93 - 03/01/94
 * 13. 12/31/93 - 02/01/94
 * 14. 07/15/93 - 09/15/93
 * 15. 08/21/93 - 04/11/94
 * 16. 03/31/93 - 04/01/93
 * 17. 12/15/93 - 12/31/93
 * 18. 12/15/93 - 12/30/93
 *
 * @author Paul Bernard
 * @author Peter Mularien
 */
class Act365LTest extends FlatSpec with Matchers {

  import org.quantintel.lang.numeric._
  import org.quantintel.ql.time.daycounters.Actual365Convention.ACT365L

  "1. 01/31/1990 - 03/16/1991" should "be 1.120547945" in {

    val d1 = new Date(31, 1, 1990)
    val d2 = new Date(16, 3, 1991)

    val yf :Double = Actual365(ACT365L).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  1.120547945)

  }


  "2. 05/06/1994 - 10/30/1994" should "be 0.484931507" in {


    val d1 = new Date(6, 5, 1994)
    val d2 = new Date(30, 10, 1994)

    val yf :Double = Actual365(ACT365L).yearFraction(d1, d2, null, null)

    assert(yf.rounded(9) ==  0.484931507)
  }



  "3. 01//01/1993 - 02/21/1993" should "be 0.139726027" in {



    val d1 = new Date(1, 1, 1993)
    val d2 = new Date(21, 2, 1993)

    val yf :Double = Actual365(ACT365L).yearFraction(d1, d2, null, null)

    assert(yf.rounded(9) ==  0.139726027)

  }

  "4. 02/01/1993 - 03/01/1993" should "be 0.076712329" in {

    val d1 = new Date(1, 2, 1993)
    val d2 = new Date(1, 3, 1993)

    val yf :Double = Actual365(ACT365L).yearFraction(d1, d2, null, null)

    assert(yf.rounded(9) ==  0.076712329)

  }


  "5. 02/01/1996 - 03/01/1996" should "be 0.079452055" in {


    val d1 = new Date(1,2, 1996)
    val d2 = new Date(1, 3, 1996)

    // 1996 is a leap year, so 366 days in the year = 29/366
    val yf :Double = Actual365(ACT365L).yearFraction(d1, d2, null, null)

    assert(yf.rounded(9) ==  0.079234973)


  }

  "6. 01/01/1993 - 01/01/1994" should "be 1.000000000" in {


    val d1 = new Date(1, 1, 1993)
    val d2 = new Date(1, 1, 1994)

    val yf :Double = Actual365(ACT365L).yearFraction(d1, d2, null, null)

    assert(yf.rounded(9) ==  1.000000000)

  }


  "7. 01/15/1993 - 02/01/1993" should "be 0.046575342" in {


    val d1 = new Date(15, 1, 1993)
    val d2 = new Date(1, 2, 1993)

    val yf :Double = Actual365(ACT365L).yearFraction(d1, d2, null, null)

    assert(yf.rounded(9) ==  0.046575342)


  }

  "8. 02/15/1993 - 04/01/1993" should "be 0.123287671" in {


    val d1 = new Date(15, 2, 1993)
    val d2 = new Date(1, 4, 1993)

    val yf :Double = Actual365(ACT365L).yearFraction(d1, d2, null, null)

    assert(yf.rounded(9) ==  0.123287671)


  }

  "9. 03/31/1993 to 04/30/1993" should "be 0.082191781" in {

    val d1 = new Date(31, 3, 1993)
    val d2 = new Date(30, 4, 1993)

    val yf :Double = Actual365(ACT365L).yearFraction(d1, d2, null, null)

    assert(yf.rounded(9) ==   0.082191781)

  }

  "10. 03/31/1993 - 12/31/1993" should "be 0.753424658" in {


    val d1 = new Date(31, 3, 1993)
    val d2 = new Date(31, 12, 1993)

    val yf :Double = Actual365(ACT365L).yearFraction(d1, d2, null, null)

    assert(yf.rounded(9) ==  0.753424658)

  }

  "11. 03/15/1993 - 06/15/1993" should "be 0.252054795" in {


    val d1 = new Date(15, 3, 1993)
    val d2 = new Date(15, 6, 1993)

    val yf :Double = Actual365(ACT365L).yearFraction(d1, d2, null, null)

    assert(yf.rounded(9) ==  0.252054795)
  }

  "12. 11/01/1993 - 03/01/1994" should "be 0.328767123" in {


    val d1 = new Date(1, 11, 1993)
    val d2 = new Date(1, 3, 1994)

    val yf :Double = Actual365(ACT365L).yearFraction(d1, d2, null, null)

    assert(yf.rounded(9) ==  0.328767123)
  }

  "13. 12/31/1993 - 02/01/1994" should "be 0.087671233" in {


    val d1 = new Date(31, 12, 1993)
    val d2 = new Date(1, 2, 1994)

    val yf :Double = Actual365(ACT365L).yearFraction(d1, d2, null, null)

    assert(yf.rounded(9) ==  0.087671233)

  }

  "14. 07/15/1993 - 09/15/1993" should "be 0.169863014" in {


    val d1 = new Date(15, 7, 1993)
    val d2 = new Date(15, 9, 1993)

    val yf :Double = Actual365(ACT365L).yearFraction(d1, d2, null, null)

    assert(yf.rounded(9) ==  0.169863014)


  }

  "15. 08/21/1993 - 04/11/1994" should "be 0.638356164" in {


    val d1 = new Date(21, 8, 1993)
    val d2 = new Date(11, 4, 1994)

    val yf :Double = Actual365(ACT365L).yearFraction(d1, d2, null, null)

    assert(yf.rounded(9) ==  0.638356164)

  }

  "16. 03/31/1993 - 04/01/1993" should "be 0.002739726" in {


    val d1 = new Date(31, 3, 1993)
    val d2 = new Date(1, 4, 1993)

    val yf :Double = Actual365(ACT365L).yearFraction(d1, d2, null, null)

    assert(yf.rounded(9) ==  0.002739726)

  }

  "17. 12/15/1993 - 12/31/1993" should "be 0.043835616" in {


    val d1 = new Date(15, 12, 1993)
    val d2 = new Date(31, 12, 1993)

    val yf :Double = Actual365(ACT365L).yearFraction(d1, d2, null, null)

    assert(yf.rounded(9) ==  0.043835616)
  }

  "18. 12/15/1993 - 12/30/1993" should "be 0.041095890" in {


    val d1 = new Date(15, 12, 1993)
    val d2 = new Date(30, 12, 1993)

    val yf :Double = Actual365(ACT365L).yearFraction(d1, d2, null, null)

    assert(yf.rounded(9) ==  0.041095890)
  }
}

package org.quantintel.ql.time.daycounters
import org.scalatest.{FlatSpec, Matchers}

/**
 * Created by Paul Bernard on 8/4/14.
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
 */
class Act365L_ extends FlatSpec with Matchers {




/*

  "1. 01/31/1990 - 03/16/1991" should "be 1.120547945" in {

    val priorCouponDate = new DateTime(1990, 1, 31, 12, 0, 0, 0)
    val settlementDate = new DateTime(1991, 3, 16, 12, 0, 0, 0)

    assert(Act365L(priorCouponDate, settlementDate).right.get.rounded(9) ==  1.120547945)

  }


  "2. 05/06/1994 - 10/30/1994" should "be 0.484931507" in {


    val priorCouponDate = new DateTime(1994, 5, 6, 12, 0, 0, 0)
    val settlementDate = new DateTime(1994, 10, 30, 12, 0, 0, 0)

    assert(Act365L(priorCouponDate, settlementDate).right.get.rounded(9) ==  0.484931507)
  }



  "3. 01//01/1993 - 02/21/1993" should "be 0.139726027" in {



    val priorCouponDate = new DateTime(1993, 1, 1, 12, 0, 0, 0)
    val settlementDate = new DateTime(1993, 2, 21, 12, 0, 0, 0)

    assert(Act365L(priorCouponDate, settlementDate).right.get.rounded(9) ==  0.139726027)

  }

  "4. 02/01/1993 - 03/01/1993" should "be 0.076712329" in {

    val priorCouponDate = new DateTime(1993, 2, 1, 12, 0, 0, 0)
    val settlementDate = new DateTime(1993, 3, 1, 12, 0, 0, 0)

    assert(Act365L(priorCouponDate, settlementDate).right.get.rounded(9) ==  0.076712329)

  }


  "5. 02/01/1996 - 03/01/1996" should "be 0.079234973" in {


    val priorCouponDate = new DateTime(1996, 2, 1, 12, 0, 0, 0)
    val settlementDate = new DateTime(1996, 3, 1, 12, 0, 0, 0)

    assert(Act365L(priorCouponDate, settlementDate).right.get.rounded(9) ==  0.079234973)


  }

  "6. 01/01/1993 - 01/01/1994" should "be 1.000000000" in {


    val priorCouponDate = new DateTime(1993, 1, 1, 12, 0, 0, 0)
    val settlementDate = new DateTime(1994, 1, 1, 12, 0, 0, 0)

    assert(Act365L(priorCouponDate, settlementDate).right.get.rounded(9) ==  1.000000000)

  }


  "7. 01/15/1993 - 02/01/1993" should "be 0.046575342" in {


    val priorCouponDate = new DateTime(1993, 1, 15, 12, 0, 0, 0)
    val settlementDate = new DateTime(1993, 2, 1, 12, 0, 0, 0)

    assert(Act365L(priorCouponDate, settlementDate).right.get.rounded(9) ==  0.046575342)


  }

  "8. 02/15/1993 - 04/01/1993" should "be 0.123287671" in {


    val priorCouponDate = new DateTime(1993, 2, 15, 12, 0, 0, 0)
    val settlementDate = new DateTime(1993, 4, 1, 12, 0, 0, 0)

    assert(Act365L(priorCouponDate, settlementDate).right.get.rounded(9) ==  0.123287671)


  }

  "9. 03/31/1993 to 04/30/1993" should "be 0.082191781" in {

    val priorCouponDate = new DateTime(1993, 3, 31, 12, 0, 0, 0)
    val settlementDate = new DateTime(1993, 4, 30, 12, 0, 0, 0)

    assert(Act365L(priorCouponDate, settlementDate).right.get.rounded(9) ==  0.082191781)

  }

  "10. 03/31/1993 - 12/31/1993" should "be 0.753424658" in {


    val priorCouponDate = new DateTime(1993, 3, 31, 12, 0, 0, 0)
    val settlementDate = new DateTime(1993, 12, 31, 12, 0, 0, 0)

    assert(Act365L(priorCouponDate, settlementDate).right.get.rounded(9) ==  0.753424658)

  }

  "11. 03/15/1993 - 06/15/1993" should "be 0.252054795" in {


    val priorCouponDate = new DateTime(1993, 3, 15, 12, 0, 0, 0)
    val settlementDate = new DateTime(1993, 6, 15, 12, 0, 0, 0)

    assert(Act365L(priorCouponDate, settlementDate).right.get.rounded(9) ==  0.252054795)
  }

  "12. 11/01/1993 - 03/01/1994" should "be 0.328767123" in {


    val priorCouponDate = new DateTime(1993, 11, 1, 12, 0, 0, 0)
    val settlementDate = new DateTime(1994, 3, 1, 12, 0, 0, 0)

    assert(Act365L(priorCouponDate, settlementDate).right.get.rounded(9) ==  0.328767123)
  }

  "13. 12/31/1993 - 02/01/1994" should "be 0.087671233" in {


    val priorCouponDate = new DateTime(1993, 12, 31, 12, 0, 0, 0)
    val settlementDate = new DateTime(1994, 2, 1, 12, 0, 0, 0)

    assert(Act365L(priorCouponDate, settlementDate).right.get.rounded(9) ==  0.087671233)

  }

  "14. 07/15/1993 - 09/15/1993" should "be 0.169863014" in {


    val priorCouponDate = new DateTime(1993, 7, 15, 12, 0, 0, 0)
    val settlementDate = new DateTime(1993, 9, 15, 12, 0, 0, 0)

    assert(Act365L(priorCouponDate, settlementDate).right.get.rounded(9) ==  0.169863014)


  }

  "15. 08/21/1993 - 04/11/1994" should "be 0.638356164" in {


    val priorCouponDate = new DateTime(1993, 8, 21, 12, 0, 0, 0)
    val settlementDate = new DateTime(1994, 4, 11, 12, 0, 0, 0)

    assert(Act365L(priorCouponDate, settlementDate).right.get.rounded(9) ==  0.638356164)

  }

  "16. 03/31/1993 - 04/01/1993" should "be 0.002739726" in {


    val priorCouponDate = new DateTime(1993, 3, 31, 12, 0, 0, 0)
    val settlementDate = new DateTime(1993, 4, 1, 12, 0, 0, 0)

    assert(Act365L(priorCouponDate, settlementDate).right.get.rounded(9) ==  0.002739726)

  }

  "17. 12/15/1993 - 12/31/1993" should "be 0.043835616" in {


    val priorCouponDate = new DateTime(1993, 12, 15, 12, 0, 0, 0)
    val settlementDate = new DateTime(1993, 12, 31, 12, 0, 0, 0)

    assert(Act365L(priorCouponDate, settlementDate).right.get.rounded(9) ==  0.043835616)
  }

  "18. 12/15/1993 - 12/30/1993" should "be 0.041095890" in {


    val priorCouponDate = new DateTime(1993, 12, 15, 12, 0, 0, 0)
    val settlementDate = new DateTime(1993, 12, 30, 12, 0, 0, 0)

    assert(Act365L(priorCouponDate, settlementDate).right.get.rounded(9) ==  0.041095890)
  }
*/
}

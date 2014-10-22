package org.quantintel.ql.time.daycounters

import org.scalatest.{FlatSpec, Matchers}

/**
 *
 * Created by Paul Bernard on 8/2/14.
 *
 * 30E/360 ISDA
 *
 * 1.  01/15/07 - 01/30/07
 * 2.  01/15/07 - 02/15/07
 * 3.  01/15/07 - 07/15/07
 * 4.  09/30/07 - 03/31/08
 * 5.  09/30/07 - 10/31/07
 * 6.  09/30/07 - 09/30/08
 * 7.  01/15/07 - 01/31/07
 * 8.  01/31/07 - 02/28/07
 * 9.  02/28/07 - 03/31/07
 * 10. 08/31/06 - 02/28/07
 * 11. 02/28/07 - 08/31/07
 * 12. 02/14/07 - 02/28/07
 * 13. 02/26/07 - 02/29/08
 * 14. 02/29/08 - 02/28/09
 * 15. 02/29/08 - 03/30/08
 * 16. 02/29/08 - 03/31/08
 * 17. 02/28/07 - 03/05/07
 * 18. 10/31/07 - 11/28/07
 * 19. 08/31/07 - 02/29/08
 * 20. 02/29/08 - 08/31/08
 * 21. 08/31/08 - 02/28/09
 * 22. 02/28/09 - 08/31/09
 *
 */
class ThirtyE360ISDA extends FlatSpec with Matchers {
/*
  "1. 01/15/07 - 01/30/07" should "be 15" in {

    val priorCouponDate = new DateTime(2007, 1, 15, 12, 0, 0, 0)
    val settlementDate = new DateTime(2007, 1, 30, 12, 0, 0, 0)
    val maturityDate = new DateTime(2009, 2, 28, 12, 0, 0, 0)

    assert(ThirtyE360ISDA(priorCouponDate, settlementDate, maturityDate).right.get == 15)

  }



  "2. 01/15/07 - 02/15/07" should "be 30" in {

    val priorCouponDate = new DateTime(2007, 1, 15, 12, 0, 0, 0)
    val settlementDate = new DateTime(2007, 2, 15, 12, 0, 0, 0)
    val maturityDate = new DateTime(2009, 2, 28, 12, 0, 0, 0)

    assert(ThirtyE360ISDA(priorCouponDate, settlementDate, maturityDate).right.get == 30)

  }

  "3. 01/15/07 - 07/15/07" should "be 180" in {

    val priorCouponDate = new DateTime(2007, 1, 15, 12, 0, 0, 0)
    val settlementDate = new DateTime(2007, 7, 15, 12, 0, 0, 0)
    val maturityDate = new DateTime(2009, 2, 28, 12, 0, 0, 0)

    assert(ThirtyE360ISDA(priorCouponDate, settlementDate, maturityDate).right.get == 180)

  }

  "4. 09/30/07 - 03/31/08" should "be 180" in {

    val priorCouponDate = new DateTime(2007, 9, 30, 12, 0, 0, 0)
    val settlementDate = new DateTime(2008, 3, 31, 12, 0, 0, 0)
    val maturityDate = new DateTime(2009, 2, 28, 12, 0, 0, 0)

    assert(ThirtyE360ISDA(priorCouponDate, settlementDate, maturityDate).right.get == 180)

  }

  "5. 09/30/07 - 10/31/07" should "be 30" in {

    val priorCouponDate = new DateTime(2007, 9, 30, 12, 0, 0, 0)
    val settlementDate = new DateTime(2007, 10, 31, 12, 0, 0, 0)
    val maturityDate = new DateTime(2009, 2, 28, 12, 0, 0, 0)

    assert(ThirtyE360ISDA(priorCouponDate, settlementDate, maturityDate).right.get == 30)

  }

  "6. 09/30/07 - 09/30/08" should "be 360" in {

    val priorCouponDate = new DateTime(2007, 9, 30, 12, 0, 0, 0)
    val settlementDate = new DateTime(2008, 9, 30, 12, 0, 0, 0)
    val maturityDate = new DateTime(2009, 2, 28, 12, 0, 0, 0)

    assert(ThirtyE360ISDA(priorCouponDate, settlementDate, maturityDate).right.get == 360)

  }

  "7. 01/15/07 - 01/31/07" should "be 15" in {

    val priorCouponDate = new DateTime(2007, 1, 15, 12, 0, 0, 0)
    val settlementDate = new DateTime(2007, 1, 31, 12, 0, 0, 0)
    val maturityDate = new DateTime(2009, 2, 28, 12, 0, 0, 0)

    assert(ThirtyE360ISDA(priorCouponDate, settlementDate, maturityDate).right.get == 15)

  }

  "8. 01/31/07 - 02/28/07" should "be 30" in {

    val priorCouponDate = new DateTime(2007, 1, 31, 12, 0, 0, 0)
    val settlementDate = new DateTime(2007, 2, 28, 12, 0, 0, 0)
    val maturityDate = new DateTime(2009, 2, 28, 12, 0, 0, 0)

    assert(ThirtyE360ISDA(priorCouponDate, settlementDate, maturityDate).right.get == 30)

  }

  "9. 02/28/07 - 03/31/07" should "be 30" in {

    val priorCouponDate = new DateTime(2007, 2, 28, 12, 0, 0, 0)
    val settlementDate = new DateTime(2007, 3, 31, 12, 0, 0, 0)
    val maturityDate = new DateTime(2009, 2, 28, 12, 0, 0, 0)

    assert(ThirtyE360ISDA(priorCouponDate, settlementDate, maturityDate).right.get == 30)

  }

  "10. 08/31/06 - 02/28/07" should "be 180" in {

    val priorCouponDate = new DateTime(2006, 8, 31, 12, 0, 0, 0)
    val settlementDate = new DateTime(2007, 2, 28, 12, 0, 0, 0)
    val maturityDate = new DateTime(2009, 2, 28, 12, 0, 0, 0)

    assert(ThirtyE360ISDA(priorCouponDate, settlementDate, maturityDate).right.get == 180)

  }

  "11. 02/28/07 - 08/31/07" should "be 180" in {

    val priorCouponDate = new DateTime(2007, 2, 28, 12, 0, 0, 0)
    val settlementDate = new DateTime(2007, 8, 31, 12, 0, 0, 0)
    val maturityDate = new DateTime(2009, 2, 28, 12, 0, 0, 0)

    assert(ThirtyE360ISDA(priorCouponDate, settlementDate, maturityDate).right.get == 180)

  }

  "12. 02/14/07 - 02/28/07" should "be 16" in {

    val priorCouponDate = new DateTime(2007, 2, 14, 12, 0, 0, 0)
    val settlementDate = new DateTime(2007, 2, 28, 12, 0, 0, 0)
    val maturityDate = new DateTime(2009, 2, 28, 12, 0, 0, 0)

    assert(ThirtyE360ISDA(priorCouponDate, settlementDate, maturityDate).right.get == 16)

  }

  "13. 02/26/07 - 02/29/08" should "be 364" in {

    val priorCouponDate = new DateTime(2007, 2, 26, 12, 0, 0, 0)
    val settlementDate = new DateTime(2008, 2, 29, 12, 0, 0, 0)
    val maturityDate = new DateTime(2009, 2, 28, 12, 0, 0, 0)

    assert(ThirtyE360ISDA(priorCouponDate, settlementDate, maturityDate).right.get == 364)

  }

  "14. 02/29/08 - 02/28/09" should "be 358" in {

    val priorCouponDate = new DateTime(2008, 2, 29, 12, 0, 0, 0)
    val settlementDate = new DateTime(2009, 2, 28, 12, 0, 0, 0)
    val maturityDate = new DateTime(2009, 2, 28, 12, 0, 0, 0)


    assert(ThirtyE360ISDA(priorCouponDate, settlementDate, maturityDate).right.get == 358)

  }

  "15. 02/29/08 - 03/30/08" should "be 30" in {

    val priorCouponDate = new DateTime(2008, 2, 29, 12, 0, 0, 0)
    val settlementDate = new DateTime(2008, 3, 30, 12, 0, 0, 0)
    val maturityDate = new DateTime(2009, 2, 28, 12, 0, 0, 0)

    assert(ThirtyE360ISDA(priorCouponDate, settlementDate, maturityDate).right.get == 30)

  }

  "16. 02/29/08 - 03/31/08" should "be 30" in {

    val priorCouponDate = new DateTime(2008, 2, 29, 12, 0, 0, 0)
    val settlementDate = new DateTime(2008, 3, 31, 12, 0, 0, 0)
    val maturityDate = new DateTime(2009, 2, 28, 12, 0, 0, 0)

    assert(ThirtyE360ISDA(priorCouponDate, settlementDate, maturityDate).right.get == 30)

  }

  "17. 02/28/07 - 03/05/07" should "be 5" in {

    val priorCouponDate = new DateTime(2007, 2, 28, 12, 0, 0, 0)
    val settlementDate = new DateTime(2007, 3, 5, 12, 0, 0, 0)
    val maturityDate = new DateTime(2009, 2, 28, 12, 0, 0, 0)

    assert(ThirtyE360ISDA(priorCouponDate, settlementDate, maturityDate).right.get == 5)

  }

  "18. 10/31/07 - 11/28/07" should "be 28" in {

    val priorCouponDate = new DateTime(2007, 10, 31, 12, 0, 0, 0)
    val settlementDate = new DateTime(2007, 11, 28, 12, 0, 0, 0)
    val maturityDate = new DateTime(2009, 2, 28, 12, 0, 0, 0)

    assert(ThirtyE360ISDA(priorCouponDate, settlementDate, maturityDate).right.get == 28)

  }

  "19. 08/31/07 - 02/29/08" should "be 180" in {

    val priorCouponDate = new DateTime(2007, 8, 31, 12, 0, 0, 0)
    val settlementDate = new DateTime(2008, 2, 29, 12, 0, 0, 0)
    val maturityDate = new DateTime(2009, 2, 28, 12, 0, 0, 0)

    assert(ThirtyE360ISDA(priorCouponDate, settlementDate, maturityDate).right.get == 180)

  }


  "20. 02/29/08 - 08/31/08" should "be 180" in {

    val priorCouponDate = new DateTime(2008, 2, 29, 12, 0, 0, 0)
    val settlementDate = new DateTime(2008, 8, 31, 12, 0, 0, 0)
    val maturityDate = new DateTime(2009, 2, 28, 12, 0, 0, 0)

    assert(ThirtyE360ISDA(priorCouponDate, settlementDate, maturityDate).right.get == 180)

  }

  "21. 08/31/08 - 02/28/09" should "be 178" in {

    val priorCouponDate = new DateTime(2008, 8, 31, 12, 0, 0, 0)
    val settlementDate = new DateTime(2009, 2, 28, 12, 0, 0, 0)
    val maturityDate = new DateTime(2009, 2, 28, 12, 0, 0, 0)

    assert(ThirtyE360ISDA(priorCouponDate, settlementDate, maturityDate).right.get == 178)

  }

  "22. 02/28/09 - 08/31/09" should "be 180" in {

    val priorCouponDate = new DateTime(2009, 2, 28, 12, 0, 0, 0)
    val settlementDate = new DateTime(2009, 8, 31, 12, 0, 0, 0)
    val maturityDate = new DateTime(2009, 2, 28, 12, 0, 0, 0)

    assert(ThirtyE360ISDA(priorCouponDate, settlementDate, maturityDate).right.get == 180)

  }
*/

}

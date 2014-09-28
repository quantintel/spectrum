package org.quantintel.ql.time.daycounters

import org.scalatest.{FlatSpec, Matchers}

/**
 * Created by Paul Bernard on 8/2/14.
 *
 * Actual/Actual ICMA
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
 */
class ActActISMATest extends FlatSpec with Matchers {

  import org.quantintel.lang.numeric._
  import org.quantintel.ql.time.Date
  import org.quantintel.ql.time.daycounters.ActualActualConvention.ISMA

  val nd = new Date()

  "1. 01/31/1990 - 03/16/1991" should "be 1.083333333" in {


    val d1 = new Date(31, 1, 1990)
    val d2 = new Date(16, 3, 1991)


    val yf :Double = ActualActual(ISMA).yearFraction(d1, d2)

    assert(yf.rounded(9) == 1.083333333)

  }


  "2. 05/06/1994 - 10/30/1994" should "be 0.5" in {


    val d1 = new Date(6, 5, 1994)
    val d2 = new Date(30, 10, 1994)

    val yf :Double = ActualActual(ISMA).yearFraction(d1, d2)

    assert(yf.rounded(9) ==  0.5)
  }



    "3. 01//01/93 - 02/21/93" should "be 51" in {



      val d1 = new Date(1, 1, 1993)
      val d2 = new Date(21, 2, 1993)

      val yf :Double = ActualActual(ISMA).yearFraction(d1, d2)

      assert(yf.rounded(9) ==   0.166666667)

    }



   "4. 02/01/93 - 03/01/93" should "be 0.083333333" in {



     val d1 = new Date(1, 2, 1993)
     val d2 = new Date(1, 3, 1993)

     val yf :Double = ActualActual(ISMA).yearFraction(d1, d2)

     assert(yf.rounded(9) ==   0.083333333)


   }


   "5. 02/01/96 - 03/01/96" should "be 0.083333333" in {


     val d1 = new Date(1, 2, 1992)
     val d2 = new Date(1, 3, 1992)

     val yf :Double = ActualActual(ISMA).yearFraction(d1, d2)

     assert(yf.rounded(9) ==   0.083333333)


   }

   "6. 01/01/93 - 01/01/94" should "be 365" in {


     val d1 = new Date(1, 1, 1993)
     val d2 = new Date(1, 1, 1994)

     val yf :Double = ActualActual(ISMA).yearFraction(d1, d2)

     assert(yf.rounded(9) ==   (365/365d))

   }


   "7. 01/15/93 - 02/01/93" should "be 0.083333333" in {


     val d1 = new Date(15, 1, 1993)
     val d2 = new Date(1, 2, 1993)

     val yf :Double = ActualActual(ISMA).yearFraction(d1, d2)

     assert(yf.rounded(9) ==  0.083333333)


   }

   "8. 02/15/93 - 04/01/93" should "be 0.083333333" in {


     val d1 = new Date(15, 2, 1993)
     val d2 = new Date(1, 4, 1993)

     val yf :Double = ActualActual(ISMA).yearFraction(d1, d2)

     assert(yf.rounded(9) ==   0.083333333)


   }

   "9. 03/31/93 to 04/30/93" should "be 0.083333333" in {

     val d1 = new Date(31, 3, 1993)
     val d2 = new Date(30, 4, 1993)

     val yf :Double = ActualActual(ISMA).yearFraction(d1, d2)

     assert(yf.rounded(9) == 0.083333333)

   }

   "10. 03/31/93 - 12/31/93" should "be 0.75" in {


     val d1 = new Date(31, 3, 1993)
     val d2 = new Date(31, 12, 1993)

     val yf :Double = ActualActual(ISMA).yearFraction(d1, d2)

     assert(yf.rounded(9) ==  0.75)

   }

   "11. 03/15/93 - 06/15/93" should "be 0.25" in {


     val d1 = new Date(15, 3, 1993)
     val d2 = new Date(15, 6, 1993)

     val yf :Double = ActualActual(ISMA).yearFraction(d1, d2)

     assert(yf.rounded(9) ==  0.25)
   }

   "12. 11/01/93 - 03/01/94" should "be 0.333333333" in {


     val d1 = new Date(1, 11, 1993)
     val d2 = new Date(1, 3, 1994)

     val yf :Double = ActualActual(ISMA).yearFraction(d1, d2)

     assert(yf.rounded(9) ==   0.333333333)
   }

   "13. 12/31/93 - 02/01/94" should "be 0.083333333" in {


     val d1 = new Date(31, 12, 1993)
     val d2 = new Date(1, 2, 1994)

     val yf :Double = ActualActual(ISMA).yearFraction(d1, d2)

     assert(yf.rounded(9) ==  0.083333333)

   }

   "14. 07/15/93 - 09/15/93" should "be 0.166666667" in {


     val d1 = new Date(15, 7, 1993)
     val d2 = new Date(15, 9, 1993)

     val yf :Double = ActualActual(ISMA).yearFraction(d1, d2)

     assert(yf.rounded(9) == 0.166666667)


   }

   "15. 08/21/93 - 04/11/94" should "be 0.666666667" in {


     val d1 = new Date(21, 8, 1993)
     val d2 = new Date(11, 4, 1994)

     val yf :Double = ActualActual(ISMA).yearFraction(d1, d2)

     assert(yf.rounded(9) == 0.666666667)

   }

   "16. 03/31/93 - 04/01/93" should "be 0.002739726" in {


     val d1 = new Date(31, 3, 1993)
     val d2 = new Date(1, 4, 1993)

     val yf :Double = ActualActual(ISMA).yearFraction(d1, d2)

     assert(yf.rounded(9) == 0.002739726)

   }

   "17. 12/15/93 - 12/31/93" should "be 0.083333333" in {


     val d1 = new Date(15, 12, 1993)
     val d2 = new Date(31, 12, 1993)

     val yf :Double = ActualActual(ISMA).yearFraction(d1, d2)

     assert(yf.rounded(9) ==  0.083333333)
   }

   "18. 12/15/93 - 12/30/93" should "be 15" in {


     val d1 = new Date(15, 12, 1993)
     val d2 = new Date(30, 12, 1993)

     val yf :Double = ActualActual(ISMA).yearFraction(d1, d2)

     assert(yf.rounded(9) == 0.04109589)
   }



}

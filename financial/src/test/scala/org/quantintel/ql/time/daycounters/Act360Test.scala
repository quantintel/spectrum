package org.quantintel.ql.time.daycounters

import org.quantintel.ql.time.Date
import org.scalatest.{FlatSpec, Matchers}


/**
 * Created by Paul Bernard on 8/4/14.
 *
 * ACT/360
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
class Act360Test extends FlatSpec with Matchers {

  import org.quantintel.lang.numeric._

  "1. 01/31/1990 - 03/16/1991" should "be 1.136111111" in {

    val d1 = Date(31, 1, 1990)
    val d2 = Date(16, 3, 1991)


    val yf :Double = Actual360().yearFraction(d1, d2)

    assert(yf.rounded(9) ==  1.136111111)

  }



  "2. 05/06/1994 - 10/30/1994" should "be 0.491666667" in {


    val d1 = new Date(6, 5, 1994)
    val d2 = new Date(30, 10, 1994)

    val yf :Double = Actual360().yearFraction(d1, d2, null, null)

    assert(yf.rounded(9) ==  0.491666667)
  }



   "3. 01//01/1993 - 02/21/1993" should "be 0.141666667" in {



     val d1 = new Date(1, 1, 1993)
     val d2 = new Date(21, 2, 1993)

     val yf :Double = Actual360().yearFraction(d1, d2, null, null)

     assert(yf.rounded(9) ==  0.141666667)

   }



 "4. 02/01/1993 - 03/01/1993" should "be 0.077777778" in {

   val d1 = new Date(1, 2, 1993)
   val d2 = new Date(1, 3, 1993)

   val yf :Double = Actual360().yearFraction(d1, d2, null, null)

   assert(yf.rounded(9) == 0.077777778)

 }


  "5. 02/01/1996 - 03/01/1996" should "be 0.080555556" in {


    val d1 = new Date(1, 2, 1996)
    val d2 = new Date(1, 3, 1996)

    val yf :Double = Actual360().yearFraction(d1, d2, null, null)

    assert(yf.rounded(9) == 0.080555556)


  }


 "6. 01/01/1993 - 01/01/1994" should "be 1.013888889" in {


   val d1 = new Date(1, 1, 1993)
   val d2 = new Date(1, 1, 1994)

   val yf :Double = Actual360().yearFraction(d1, d2, null, null)

   assert(yf.rounded(9) == 1.013888889)

 }


 "7. 01/15/1993 - 02/01/1993" should "be 0.047222222" in {


   val d1 = new Date(15, 1, 1993)
   val d2 = new Date(1, 2, 1993)

   val yf :Double = Actual360().yearFraction(d1, d2, null, null)

   assert(yf.rounded(9) == 0.047222222)


 }



 "8. 02/15/1993 - 04/01/1993" should "be 0.125000000" in {


   val d1 = new Date(15, 2, 1993)
   val d2 = new Date(1, 4, 1993)

   val yf :Double = Actual360().yearFraction(d1, d2, null, null)

   assert(yf.rounded(9) == 0.125000000)


 }

 "9. 03/31/1993 to 04/30/1993" should "be 0.083333333" in {

   val d1 = new Date(31, 3, 1993)
   val d2 = new Date(30, 4, 1993)

   val yf :Double = Actual360().yearFraction(d1, d2, null, null)

   assert(yf.rounded(9) == 0.083333333)

 }



 "10. 03/31/1993 - 12/31/1993" should "be 0.763888889" in {


   val d1 = new Date(31, 3, 1993)
   val d2 = new Date(31, 12, 1993)

   val yf :Double = Actual360().yearFraction(d1, d2, null, null)

   assert(yf.rounded(9) == 0.763888889)

 }

 "11. 03/15/1993 - 06/15/1993" should "be 0.255555556" in {


   val d1 = new Date(15, 3, 1993)
   val d2 = new Date(15, 6, 1993)

   val yf :Double = Actual360().yearFraction(d1, d2, null, null)

   assert(yf.rounded(9) == 0.255555556)
 }


 "12. 11/01/1993 - 03/01/1994" should "be 0.333333333" in {


   val d1 = new Date(1, 11, 1993)
   val d2 = new Date(1, 3, 1994)

   val yf :Double = Actual360().yearFraction(d1, d2, null, null)

   assert(yf.rounded(9) == 0.333333333)
 }

 "13. 12/31/1993 - 02/01/1994" should "be 0.088888889" in {


   val d1 = new Date(31, 12, 1993)
   val d2 = new Date(1, 2, 1994)

   val yf :Double = Actual360().yearFraction(d1, d2, null, null)

   assert(yf.rounded(9) == 0.088888889)

 }



 "14. 07/15/1993 - 09/15/1993" should "be 0.172222222" in {


   val d1 = new Date(15, 7, 1993)
   val d2 = new Date(15, 9, 1993)

   val yf :Double = Actual360().yearFraction(d1, d2, null, null)

   assert(yf.rounded(9) == 0.172222222)


 }

 "15. 08/21/1993 - 04/11/1994" should "be 0.647222222" in {


   val d1 = new Date(21, 8, 1993)
   val d2 = new Date(11, 4, 1994)

   val yf :Double = Actual360().yearFraction(d1, d2, null, null)

   assert(yf.rounded(9) == 0.647222222)

 }



"16. 03/31/1993 - 04/01/1993" should "be 0.002777778" in {


 val d1 = new Date(31, 3, 1993)
 val d2 = new Date(1, 4, 1993)

  val yf :Double = Actual360().yearFraction(d1, d2, null, null)

  assert(yf.rounded(9) == 0.002777778)

}

"17. 12/15/1993 - 12/31/1993" should "be 0.044444444" in {


 val d1 = new Date(15, 12, 1993)
 val d2 = new Date(31, 12, 1993)

  val yf :Double = Actual360().yearFraction(d1, d2, null, null)

  assert(yf.rounded(9) == 0.044444444)
}

"18. 12/15/1993 - 12/30/1993" should "be 0.041666667" in {


 val d1 = new Date(15, 12, 1993)
 val d2 = new Date(30, 12, 1993)

  val yf :Double = Actual360().yearFraction(d1, d2, null, null)

  assert(yf.rounded(9) == 0.041666667)
}


}

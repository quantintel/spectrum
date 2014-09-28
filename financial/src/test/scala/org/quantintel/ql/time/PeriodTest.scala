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

package org.quantintel.ql.time

import org.scalatest.{Matchers, FlatSpec}
import org.quantintel.ql.time.TimeUnit._

/**
 * @author Paul Bernard
 */
class PeriodTest extends FlatSpec with Matchers {



  "1. Period Equality Years" should "true" in {
    var p1 = Period(1, YEARS)
    var p2 = Period(1, YEARS)
    assert(p1 == p2)
  }

  "1a. Period Equality Years" should "true" in {
    var p1 = Period(1, YEARS)
    var p2 = Period(2, YEARS)
    assert((p1 == p2) == false)
  }






  "2. Period inequality Years" should "true" in {
    var p1 = Period(1, YEARS)
    var p2 = Period(2, YEARS)
    assert(p1 != p2)
  }


  "2a. Period inequality Years" should "true" in {
    var p1 = Period(1, YEARS)
    var p2 = Period(1, YEARS)
    assert((p1 != p2) == false)
  }





  "3. Period equality months" should "true" in {
    var p1 = Period(1, MONTHS)
    var p2 = Period(1, MONTHS)
    assert(p1 == p2)
  }


  "4. Period equality months" should "true" in {
    var p1 = Period(1, MONTHS)
    var p2 = Period(2, MONTHS)
    assert((p1 == p2) == false)
  }

  "3a. Period iequality months" should "true" in {
    var p1 = Period(1, MONTHS)
    var p2 = Period(2, MONTHS)
    assert(p1 != p2)
  }


  "4a. Period iequality months" should "true" in {
    var p1 = Period(1, MONTHS)
    var p2 = Period(1, MONTHS)
    assert((p1 != p2) == false)
  }





  "5. Period equality weeks" should "true" in {
    var p1 = Period(1, WEEKS)
    var p2 = Period(1, WEEKS)
    assert(p1 == p2)
  }

  "6. Period equality weeks" should "true" in {
    var p1 = Period(1, WEEKS)
    var p2 = Period(2, WEEKS)
    assert((p1 == p2) == false)
  }

  "5. Period inequality weeks" should "true" in {
    var p1 = Period(1, WEEKS)
    var p2 = Period(2, WEEKS)
    assert(p1 != p2)
  }

  "6. Period inequality weeks" should "true" in {
    var p1 = Period(1, WEEKS)
    var p2 = Period(1, WEEKS)
    assert((p1 != p2) == false)
  }



  "7. Period equality days" should "true" in {
    var p1 = Period(1, DAYS)
    var p2 = Period(1, DAYS)
    assert(p1 == p2)
  }

  "8. Period equality days" should "true" in {
    var p1 = Period(1, DAYS)
    var p2 = Period(2, DAYS)
    assert((p1 == p2) == false)
  }

  "7a. Period inequality days" should "true" in {
    var p1 = Period(1, DAYS)
    var p2 = Period(2, DAYS)
    assert(p1 != p2)
  }

  "8a. Period inequality days" should "true" in {
    var p1 = Period(1, DAYS)
    var p2 = Period(1, DAYS)
    assert((p1 != p2) == false)
  }



  "9. Period < years" should "true" in {
    var p1 = Period(1, YEARS)
    var p2 = Period(1, YEARS)
    assert((p1 < p2) == false)
  }

  "10. Period < years" should "true" in {
    var p1 = Period(2, YEARS)
    var p2 = Period(1, YEARS)
    assert((p1 < p2) == false)
  }

  "10a. Period < years" should "true" in {
    var p1 = Period(1, YEARS)
    var p2 = Period(2, YEARS)
    assert(p1 < p2)
  }



  "11. Period < months" should "true" in {
    var p1 = Period(1, MONTHS)
    var p2 = Period(1, MONTHS)
    assert((p1 < p2) == false)
  }

  "12. Period < months" should "true" in {
    var p1 = Period(2, MONTHS)
    var p2 = Period(1, MONTHS)
    assert((p1 < p2) == false)
  }

  "12a. Period < months" should "true" in {
    var p1 = Period(1, MONTHS)
    var p2 = Period(2, MONTHS)
    assert(p1 < p2)
  }




  "13. Period < weeks" should "true" in {
    var p1 = Period(1, WEEKS)
    var p2 = Period(1, WEEKS)
    assert((p1 < p2) == false)
  }

  "14. Period < weeks" should "true" in {
    var p1 = Period(2, WEEKS)
    var p2 = Period(1, WEEKS)
    assert((p1 < p2) == false)
  }


  "15. Period < weeks" should "true" in {
    var p1 = Period(1, WEEKS)
    var p2 = Period(2, WEEKS)
    assert(p1 < p2)
  }





  "16. Period < days" should "true" in {
    var p1 = Period(1, DAYS)
    var p2 = Period(1, DAYS)
    assert((p1 < p2) == false)
  }

  "17. Period < days" should "true" in {
    var p1 = Period(2, DAYS)
    var p2 = Period(1, DAYS)
    assert((p1 < p2) == false)
  }


  "18. Period < days" should "true" in {
    var p1 = Period(1, DAYS)
    var p2 = Period(2, DAYS)
    assert(p1 < p2)
  }






  "19. Period + years" should "true" in {
    var p1 = Period(1, YEARS)
    var p2 = Period(2, YEARS)
    var p3 = Period(3, YEARS)
    assert((p1 + p2) == p3)
  }

  "20. Period + months" should "true" in {
    var p1 = Period(1, MONTHS)
    var p2 = Period(2, MONTHS)
    var p3 = Period(3, MONTHS)
    assert((p1 + p2) == p3)
  }

  "21. Period + weeks" should "true" in {
    var p1 = Period(1, WEEKS)
    var p2 = Period(2, WEEKS)
    var p3 = Period(3, WEEKS)
    assert((p1 + p2) == p3)
  }

  "21. Period + days" should "true" in {
    var p1 = Period(1, DAYS)
    var p2 = Period(2, DAYS)
    var p3 = Period(3, DAYS)
    assert((p1 + p2) == p3)
  }





  "22. Period - years" should "true" in {
    var p1 = Period(5, YEARS)
    var p2 = Period(2, YEARS)
    var p3 = Period(3, YEARS)
    assert((p1 - p2) == p3)
  }

  "23. Period - months" should "true" in {
    var p1 = Period(5, MONTHS)
    var p2 = Period(2, MONTHS)
    var p3 = Period(3, MONTHS)
    assert((p1 - p2) == p3)
  }

  "24. Period - weeks" should "true" in {
    var p1 = Period(5, WEEKS)
    var p2 = Period(2, WEEKS)
    var p3 = Period(3, WEEKS)
    assert((p1 - p2) == p3)
  }

  "25. Period - days" should "true" in {
    var p1 = Period(5, DAYS)
    var p2 = Period(2, DAYS)
    var p3 = Period(3, DAYS)
    assert((p1 - p2) == p3)
  }






  "26. Period * years" should "true" in {
    var p1 = Period(5, YEARS)
    var p2 = Period(10, YEARS)
    assert(p1 * 2 == p2)
  }

  "27. Period * months" should "true" in {
    var p1 = Period(5, MONTHS)
    var p2 = Period(10, MONTHS)
    assert(p1 * 2 == p2)
  }

  "28. Period * weeks" should "true" in {
    var p1 = Period(5, WEEKS)
    var p2 = Period(10, WEEKS)
    assert(p1 * 2 == p2)
  }

  "29. Period * days" should "true" in {
    var p1 = Period(5, DAYS)
    var p2 = Period(10, DAYS)
    assert(p1 * 2 == p2)
  }



  "30. Period * years" should "true" in {
    var p1 = Period(10, YEARS)
    var p2 = Period(5, YEARS)
    assert(p1 / 2 == p2)
  }

  "31. Period * months" should "true" in {
    var p1 = Period(10, MONTHS)
    var p2 = Period(5, MONTHS)
    assert(p1 / 2 == p2)
  }

  "32. Period * weeks" should "true" in {
    var p1 = Period(10, WEEKS)
    var p2 = Period(5, WEEKS)
    assert(p1 / 2 == p2)
  }

  "33. Period * days" should "true" in {
    var p1 = Period(10, DAYS)
    var p2 = Period(5, DAYS)
    assert(p1 / 2 == p2)
  }


  "34. Period negate" should "true" in {
    var p1 = Period(10, DAYS)
    var p2 = Period(-10, DAYS)
    assert(Period.negate(p1) == p2)
  }




  "35. Period > years" should "true" in {
    var p1 = Period(1, YEARS)
    var p2 = Period(1, YEARS)
    assert((p1 > p2) == false)
  }

  "36. Period > years" should "true" in {
    var p1 = Period(1, YEARS)
    var p2 = Period(2, YEARS)
    assert((p1 > p2) == false)
  }

  "37 Period > years" should "true" in {
    var p1 = Period(2, YEARS)
    var p2 = Period(1, YEARS)
    assert(p1 > p2)
  }



  "38. Period > months" should "true" in {
    var p1 = Period(1, MONTHS)
    var p2 = Period(1, MONTHS)
    assert((p1 > p2) == false)
  }

  "39. Period > months" should "true" in {
    var p1 = Period(1, MONTHS)
    var p2 = Period(2, MONTHS)
    assert((p1 > p2) == false)
  }

  "40. Period > months" should "true" in {
    var p1 = Period(2, MONTHS)
    var p2 = Period(1, MONTHS)
    assert(p1 > p2)
  }




  "41. Period > weeks" should "true" in {
    var p1 = Period(1, WEEKS)
    var p2 = Period(1, WEEKS)
    assert((p1 > p2) == false)
  }

  "42. Period > weeks" should "true" in {
    var p1 = Period(1, WEEKS)
    var p2 = Period(2, WEEKS)
    assert((p1 > p2) == false)
  }


  "43. Period > weeks" should "true" in {
    var p1 = Period(2, WEEKS)
    var p2 = Period(1, WEEKS)
    assert(p1 > p2)
  }





  "44. Period > days" should "true" in {
    var p1 = Period(1, DAYS)
    var p2 = Period(1, DAYS)
    assert((p1 > p2) == false)
  }

  "45. Period > days" should "true" in {
    var p1 = Period(1, DAYS)
    var p2 = Period(2, DAYS)
    assert((p1 > p2) == false)
  }


  "46. Period > days" should "true" in {
    var p1 = Period(2, DAYS)
    var p2 = Period(1, DAYS)
    assert(p1 > p2)
  }



  "47. Period += days" should "true" in {
    var p1 = Period(2, DAYS)
    var p2 = Period(1, DAYS)
    p1 += p2
    assert(p1.length == 3)
  }

  "48. Period -= days" should "true" in {
    var p1 = Period(2, DAYS)
    var p2 = Period(1, DAYS)
    p1 -= p2
    assert(p1.length == 1)
  }

  "49. Period /= 5" should "true" in {
    var p1 = Period(10, DAYS)
    var p2 = 2
    p1 /= p2
    assert(p1.length == 5)
  }

  "50. Period /= 5" should "true" in {
    var p1 = Period(10, DAYS)
    var p2 = 2
    p1 /= p2
    assert(p1.length == 5)
  }




}

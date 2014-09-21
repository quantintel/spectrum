package org.quantintel.ql.time.calendars


import org.quantintel.ql.time.Calendar
import org.scalatest.{Matchers, FlatSpec}

class BrazilTest extends FlatSpec with Matchers {

  "1. Brazil Calendar - default apply" should "be Brazil" in {
    val cal : Calendar = Australia()
    assert(cal.name == "Australia")
  }

  import org.quantintel.ql.time.calendars.BrazilEnum._

  "2. Brazil Calendar - parameterized apply(BRAZIL)" should "be Brazil" in {
    val cal : Calendar = Brazil(SETTLEMENT)
    assert(cal.name == "Brazil")
  }

  "3. Brazil Calendar - parameterized apply(BOVESPA)" should "be BOVESPA" in {
    val cal : Calendar = Brazil(BOVESPA)
    assert(cal.name == "BOVESPA")
  }

}

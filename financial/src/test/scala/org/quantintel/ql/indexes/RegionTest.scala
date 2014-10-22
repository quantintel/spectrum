package org.quantintel.ql.indexes

import org.scalatest.{Matchers, FlatSpec}

/**
 * @author Paul Bernard
 */
class RegionTest extends FlatSpec with Matchers {


  "Australia name" should "equal Australia" in {

    assert(Region().AustraliaRegion().name == "Australia")
    assert(Region().AustraliaRegion().code == "AU")

  }

  "EU name" should "equal UE" in {

    assert(Region().EURegion().name == "EU")
    assert(Region().EURegion().code == "EU")

  }

  "France name" should "equal France" in {

    assert(Region().FranceRegion().name == "France")
    assert(Region().FranceRegion().code == "FR")

  }

  "UK name" should "equal UK" in {

    assert(Region().UKRegion().name == "UK")
    assert(Region().UKRegion().code == "UK")

  }

  "US name" should "equal US" in {

    assert(Region().USRegion().name == "US")
    assert(Region().USRegion().code == "US")

  }

  "ZAR name" should "equal ZAR" in {

    assert(Region().ZARRegion().name == "ZAR")
    assert(Region().ZARRegion().code == "ZAR")

  }

  "Custom name" should "equal Custom" in {

    val reg: Region = Region().CustomRegion("one", "two")

    assert(reg.name == "one")
    assert(reg.code == "two")

  }

}

package org.quantintel.lang.collections

import org.scalatest.{Matchers, FlatSpec}


/**
 * @author Paul Bernard
 */
class CollectionsTest extends FlatSpec with Matchers {

  "insert at 0" should "is head" in {
    val col1 =  "a" :: "b" :: "c" :: Nil
    val col2 = insertAt("foo", 0, col1)
    assert(col2.head  == "foo")
    assert(col1.head == "a")
  }

  "insert in middle" should "be in middle" in {
    val col1 =  "a" :: "b" :: "c" :: Nil
    val col2 = insertAt("foo", 1, col1)
    assert(col2.indexOf("foo") == 1)
    assert(col2.indexOf("a") == 0)
    assert(col2.indexOf("b") == 2)
    assert(col2.indexOf("c") == 3)
    assert(col2.indexOf("not in list") == -1)
  }



}

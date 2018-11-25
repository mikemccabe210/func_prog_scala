package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  lazy val genHeap: Gen[H] = for {
  k <- Gen.choose(3,100)
  m <- oneOf(const(empty),genHeap)
  } yield insert(k, m)

//  lazy val genHeap: Gen[H] = for {
//    k <- arbitrary[A]
//    m <- oneOf(const(empty),genHeap)
//  } yield insert(k, m)

  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

  property("gen1") = forAll { (h: H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  property("addRemove") = forAll { (h: H) =>
    isEmpty(h) == isEmpty(deleteMin(insert(3, h)))
  }


  property("removeOrder") = forAll { (h:H) =>
    (findMin(insert(1,insert(0,h))) < findMin(deleteMin(insert(1,insert(0,h)))))
  }

 property("meld") = forAll { (h1:H, h2:H) =>
   findMin(h1) == findMin(meld(h1,h2)) || findMin(h2) == findMin(meld(h1,h2))
 }

}

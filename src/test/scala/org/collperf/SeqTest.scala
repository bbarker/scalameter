package org.collperf



import collection._



class SeqTest extends PerformanceTest.Executor.MinTime with PerformanceTest.Reporter.Html {

  val largesizes = Gen.range("size")(500000, 5000000, 125000)

  val lists = for {
    size <- largesizes
  } yield (0 until size).toList

  val arrays = for {
    size <- largesizes
  } yield (0 until size).toArray

  val vectors = for {
    size <- largesizes
  } yield (0 until size).toVector

  val mutablelists = for {
    size <- largesizes
  } yield mutable.LinkedList(0 until size: _*)

  val arraybuffers = for {
    size <- largesizes
  } yield mutable.ArrayBuffer(0 until size: _*)


  /* Large sequences */

  performance of "Large-Seq" in {

    measure method "filter" in {
      using(lists) curve("List") apply {
        _.filter(_ % 2 == 0)
      }

      using(arrays) curve("Array") apply {
        _.filter(_ % 2 == 0)
      }

      using(vectors) curve("Vector") apply {
        _.filter(_ % 2 == 0)
      }

      using(mutablelists) curve("LinkedList") apply {
        _.filter(_ % 2 == 0)
      }

      using(arraybuffers) curve("ArrayBuffer") apply {
        _.filter(_ % 2 == 0)
      }
    }

    measure method "reduce" in {
      using(lists) curve("List") apply {
        _.reduce(_ + _)
      }

      using(arrays) curve("Array") apply {
        _.reduce(_ + _)
      }

      using(vectors) curve("Vector") apply {
        _.reduce(_ + _)
      }

      using(mutablelists) curve("LinkedList") apply {
        _.reduce(_ + _)
      }

      using(arraybuffers) curve("ArrayBuffer") apply {
        _.reduce(_ + _)
      }
    }

    measure method "groupBy" in {
      using(lists) curve("List") apply {
        _.groupBy(_ % 10)
      }

      using(arrays) curve("Array") apply {
        _.groupBy(_ % 10)
      }

      using(vectors) curve("Vector") apply {
        _.groupBy(_ % 10)
      }

      using(mutablelists) curve("LinkedList") apply {
        _.groupBy(_ % 10)
      }

      using(arraybuffers) curve("ArrayBuffer") apply {
        _.groupBy(_ % 10)
      }
    }

  }

}


















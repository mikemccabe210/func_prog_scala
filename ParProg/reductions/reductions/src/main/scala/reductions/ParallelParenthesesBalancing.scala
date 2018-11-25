package reductions

import scala.annotation._
import org.scalameter._
import common._

object ParallelParenthesesBalancingRunner {

  @volatile var seqResult = false

  @volatile var parResult = false

  val standardConfig = config(
    Key.exec.minWarmupRuns -> 40,
    Key.exec.maxWarmupRuns -> 80,
    Key.exec.benchRuns -> 120,
    Key.verbose -> true
  ) withWarmer(new Warmer.Default)

  def main(args: Array[String]): Unit = {
    val length = 100000000
    val chars = new Array[Char](length)
    val threshold = 10000
    val seqtime = standardConfig measure {
      seqResult = ParallelParenthesesBalancing.balance(chars)
    }
    println(s"sequential result = $seqResult")
    println(s"sequential balancing time: $seqtime ms")

    val fjtime = standardConfig measure {
      parResult = ParallelParenthesesBalancing.parBalance(chars, threshold)
    }
    println(s"parallel result = $parResult")
    println(s"parallel balancing time: $fjtime ms")
    println(s"speedup: ${seqtime / fjtime}")
  }
}

object ParallelParenthesesBalancing {

  /** Returns `true` iff the parentheses in the input `chars` are balanced.
   */
  def balance(chars: Array[Char]): Boolean = {
    def balanceAcc(chars: Array[Char], openAcc: Int): Int = {
      if (openAcc < 0) -1
      else if (chars.isEmpty) openAcc
      else if (chars.head == '(') balanceAcc(chars.tail, openAcc + 1)
      else if (chars.head == ')') balanceAcc(chars.tail, openAcc - 1)
      else balanceAcc(chars.tail, openAcc)
    }
    balanceAcc(chars, 0) == 0
  }

  /** Returns `true` iff the parentheses in the input `chars` are balanced.
   */
  def parBalance(chars: Array[Char], threshold: Int): Boolean = {

    def traverse(idx: Int, until: Int, open: Int, closed: Int): /*: ???*/ (Int, Int) = {
      var i = idx
      var o = open
      var c = closed

      while (i < until) {
        if (chars(i) == '(' ) o = o + 1
        else if (chars(i) == ')') c = c + 1
        i += 1
      }
      (o, c)
    }

    def reduce(from: Int, until: Int): Int = {
      if (until - from <= threshold) {
        val (o, c) = traverse(from, until, 0, 0)
        o - c
      }
      else {
        val (l,(ro, rc)) = parallel(reduce(from, (from + until)/2), traverse((from + until)/2, until, 0, 0))
        if (l < 0) -99999
        else l + ro - rc
      }
    }
    reduce(0, chars.length) == 0
  }

  // For those who want more:
  // Prove that your reduction operator is associative!

}

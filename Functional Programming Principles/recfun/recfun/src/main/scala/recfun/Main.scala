package recfun

import scala.annotation.tailrec

//import scala.reflect.internal.TreeInfo.IsFalse

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
    * Exercise 1
    */
  def pascal(c: Int, r: Int): Int = {

    if (c == 0 || c == r) 1
    else pascal(c - 1, r - 1) + pascal(c, r - 1)

  }

  /**
    * Exercise 2
    */

  def balance(chars: List[Char]): Boolean = {
    @tailrec
    def balanceIter(chars: List[Char], count_open: Int): Int = {
      if (chars.isEmpty || count_open < 0) count_open
      else {
        if (chars.head == '(') balanceIter(chars.tail, count_open + 1)
        else if (chars.head == ')') balanceIter(chars.tail, count_open - 1)
        else balanceIter(chars.tail, count_open)
      }

    }
    balanceIter(chars, 0) == 0
  }

  /**
    * Exercise 3
    */
  def countChange(money: Int, coins: List[Int]): Int = {

    def countIter(money: Int, coins: List[Int], coin_count: Int): Int = {
      val remaining = money - coin_count * coins.head
      if (coin_count == 0) {
        countChange(money, coins.tail)
      }
      else if (remaining > 0) {
        countIter(money, coins, coin_count - 1) + countChange(remaining, coins.tail)
      }
      else if (remaining == 0) {
        countIter(money, coins, coin_count - 1) + 1
      }
      else {
        0
      }
    }

    if (money == 0 || coins.isEmpty) {
      0
    }
    else if (coins.tail.isEmpty) {
      if (money % coins.head == 0) 1
      else 0
    }
    else {
      countIter(money, coins, money / coins.head)

    }


  }
}

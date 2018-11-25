import forcomp.Anagrams._

def occLen(occ: Occurrences): Int = occ.foldLeft(0)((z: Int, a: (Char, Int)) => z + a._2)

def sentenceBuilder(occ: Occurrences, max_len: Int): List[Sentence] = occ match {
  case Nil => List(List())
  case _ =>
    if (max_len == 0) {
      List()
    }
    else {
      for {
        combo <- combinations(occ)
        word <- dictionaryByOccurrences.getOrElse(combo, List())
        val rec = sentenceBuilder(subtract(occ, combo), max_len - occLen(combo))
        other <- if (rec.nonEmpty) rec else List(List())
              if word.length() + other.mkString.length() <= max_len
      } yield word :: other
    }
}


val key = sentenceOccurrences(List("Linux","rulez"))



val test = comb(key, occLen(key))


for (x <- test if x.mkString.length == occLen(key)) yield x

val combos = combinations(key)

val check = for (c <- combos if dictionaryByOccurrences.getOrElse(c, List()).nonEmpty) yield dictionaryByOccurrences.getOrElse(c, List())

dictionaryByOccurrences.getOrElse(List(('c',1), ('o',1), ('w',1)),List())



for (a <- 1 to 10; b <- List(List())) yield a






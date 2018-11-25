val a = Seq(1,2,3,4,5)

var groups = a.groupBy((x: Int) => x % 2)

for (m <- a){
  groups = groups.updated(m, groups.getOrElse(m, Seq()))
}
groups


println("b")


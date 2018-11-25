package kmeans
import kmeans.Point

object Taco{
  points = Seq(kmeans.Point(0, 0, 1), (0,0, -1), (0,1,0), (0,10,0))
  and 'oldMeans' == GenSeq((0, -1, 0), (0, 2, 0)) and 'eta' == 12.25
}

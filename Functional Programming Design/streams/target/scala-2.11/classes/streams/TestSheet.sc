
val a = Vector((1,2),(2,3),(4,5))



val b = Stream(Stream(1,2,3), Stream(4,5,6)).flatten


b(1)


a map (_._1)

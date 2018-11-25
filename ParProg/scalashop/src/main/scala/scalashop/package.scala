
import common._

package object scalashop {

  /** The value of every pixel is represented as a 32 bit integer. */
  type RGBA = Int

  /** Returns the red component. */
  def red(c: RGBA): Int = (0xff000000 & c) >>> 24

  /** Returns the green component. */
  def green(c: RGBA): Int = (0x00ff0000 & c) >>> 16

  /** Returns the blue component. */
  def blue(c: RGBA): Int = (0x0000ff00 & c) >>> 8

  /** Returns the alpha component. */
  def alpha(c: RGBA): Int = (0x000000ff & c) >>> 0

  /** Used to create an RGBA value from separate components. */
  def rgba(r: Int, g: Int, b: Int, a: Int): RGBA = {
    (r << 24) | (g << 16) | (b << 8) | (a << 0)
  }

  /** Restricts the integer into the specified range. */
  def clamp(v: Int, min: Int, max: Int): Int = {
    if (v < min) min
    else if (v > max) max
    else v
  }

  /** Image is a two-dimensional matrix of pixel values. */
  class Img(val width: Int, val height: Int, private val data: Array[RGBA]) {
    def this(w: Int, h: Int) = this(w, h, new Array(w * h))
    def apply(x: Int, y: Int): RGBA = data(y * width + x)
    def update(x: Int, y: Int, c: RGBA): Unit = data(y * width + x) = c
  }


  def avgPixels(pixels: Set[RGBA]): RGBA = {
      val avg_values = pixels.foldRight((0,0,0,0))((x: RGBA, y: Tuple4[Int, Int, Int, Int]) =>
        (y._1 + red(x), y._2 + green(x),y._3 + blue(x), y._4+alpha(x)))
      rgba(avg_values._1/pixels.size, avg_values._2/pixels.size,avg_values._3/pixels.size,avg_values._4/pixels.size)
  }
  /** Computes the blurred RGBA value of a single pixel of the input image. */
  def boxBlurKernel(src: Img, x: Int, y: Int, radius: Int): RGBA = {
    // TODO implement using while loops
    var i_x = -radius
    var i_y = -radius
    var pixelSet = Set[RGBA]()
    while (i_x <= radius) {
      i_y = -radius
      while (i_y <= radius) {
        pixelSet = pixelSet + src(clamp(x + i_x, 0, src.width - 1), clamp(y + i_y, 0, src.height - 1))
//        println(clamp(x + i_x, 0, src.width - 1), clamp(y + i_y, 0, src.height - 1))
        i_y = i_y + 1
      }
      i_x = i_x + 1

    }
//    pixelSet.foldRight(0)((x: RGBA, y: RGBA) => x + y)/pixelSet.size
avgPixels(pixelSet)
  }

}

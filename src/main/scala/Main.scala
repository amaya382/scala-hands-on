import sbt.io.IO
import java.io.File
import org.json4s._
import org.json4s.jackson.JsonMethods._


object Main {
  def main(args: Array[String]): Unit = {
    val str = readFile("sample.json")
    println(str)

    val ints = parseIntArrayJSON(str)
    println(ints)
  }

  def readFile(fileName: String): String =
    IO.read(new File(fileName))

  def parseIntArrayJSON(jsonString: String): Seq[Int] = {
    val json = parse(jsonString)
    for {
      JArray(array) <- json
      JInt(x) <- array
    } yield x.toInt
  }
}
import sbt.io.IO
import java.io.File
import org.json4s._
import org.json4s.jackson.JsonMethods._
import org.json4s.JsonDSL._


object Main {
  def main(args: Array[String]): Unit = {
    val str = readFile("sample.json")
    println(str)

    val ints = parseIntArrayJSON(str)
    println(ints)

    val fizzBuzzed = toFizzBuzz(ints)
    println(fizzBuzzed)

    val fizzBuzzedJSON = toJSONFormat(fizzBuzzed)
    println(fizzBuzzedJSON)
  }

  def readFile(fileName: String): String =
    IO.read(new File(fileName))

  def parseIntArrayJSON(jsonString: String): List[Int] = {
    val json = parse(jsonString)
    for {
      JArray(array) <- json
      JInt(x) <- array
    } yield x.toInt
  }

  def toFizzBuzz(ints: List[Int]): List[String] =
    for {i <- ints}
      yield i match {
        case x if x % 15 == 0 => "FizzBuzz"
        case x if x % 3 == 0 => "Fizz"
        case x if x % 5 == 0 => "Buzz"
        case x => x.toString
      }

  def toJSONFormat(list: List[String]): String =
    compact(render(list))
}

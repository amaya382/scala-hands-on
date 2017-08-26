import sbt.io.IO
import java.io.File


object Main {
  def main(args: Array[String]): Unit = {
    val str = readFile("sample.json")
    println(str)
  }

  def readFile(fileName: String): String =
    IO.read(new File(fileName))
}
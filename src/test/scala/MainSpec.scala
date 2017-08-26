import java.io.File
import org.json4s._
import org.json4s.jackson.JsonMethods
import org.scalatest._
import sbt.io.IO


class MainSpec extends FlatSpec {
  "FzzBuzzed sample.json" should "have the same elements as answer.json" in {
    val sampleString = IO.read(new File("sample.json"))
    val sampleJSON = JsonMethods.parse(sampleString)
    val sample = for {
      JArray(arr) <- sampleJSON
      JInt(x) <- arr
    } yield x.toInt
    val fizzBuzzedSample = Main.toFizzBuzz(sample)

    val answerString = IO.read(new File("answer.json"))
    val answerJSON = JsonMethods.parse(answerString)
    val answer = for {
      JArray(arr) <- answerJSON
      JString(x) <- arr
    } yield x

    assert(fizzBuzzedSample.sameElements(answer))
  }
}

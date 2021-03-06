package fp

import java.text.SimpleDateFormat
import java.util.Date

object Main extends App {
  override def main (args: Array[String]) {

    val format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")

    val network = Seq[Vector[Vector[Double] => Double]](
      Vector(Perceptron.activation(Vector(0.3, 0.3, 0.3), _, 0.2, Neuron.sigmoid), Perceptron.activation(Vector(0.3, 0.3, 0.3), _, 0.2, Neuron.sigmoid)),
      Vector(Perceptron.activation(Vector(0.3, 0.3, 0.3), _, 0.2, Neuron.sigmoid))
    )

    scala.io.Source.fromFile("src/main/resources/data2.csv")
      .getLines()
      .toList
      .par
      .foreach{ l =>
        val splits = l.split(",")
        val output = Network.feedForward(Vector(splits(0).toDouble, splits(1).toDouble, splits(2).toDouble), network)
        println(s"Output with result $output in ${format.format(new Date(System.currentTimeMillis()))}")
      }
  }
}

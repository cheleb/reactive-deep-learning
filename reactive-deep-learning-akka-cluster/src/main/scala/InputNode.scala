import Node.Input
import akka.actor.Props
import akka.contrib.pattern.ClusterSharding

object InputNode {
  def props(): Props = Props[InputNode]
  val shardName: String = "InputNode"
}

class InputNode() extends HasOutputs {
  override def receive = run orElse addOutput

  val shardRegion = ClusterSharding(context.system).shardRegion(Edge.shardName)

  def run: Receive = {
    case Input(_, f) => outputs.foreach(shardRegion ! Input(_, f))
  }
}
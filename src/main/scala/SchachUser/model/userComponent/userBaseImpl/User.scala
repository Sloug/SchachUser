package SchachUser.model.userComponent.userBaseImpl

import SchachUser.model.userComponent.UserInterface
import SchachUser.model.userComponent.userBaseImpl.UserState.UserState


case class User(name: String, black: Boolean, var myTurn: Boolean, var state: UserState = UserState.ALIVE) extends UserInterface {
  def this(name: String, black: Boolean, myTurn: Boolean) = this(name, black, myTurn, UserState.ALIVE)
  def this(name: String, black: Boolean) = this(name, black, if(black) false else true)

  var roundsLeft:Option[Int] = Option.empty

  override def yourTurn: Unit = myTurn = true

  override def othersTurn: Unit = myTurn = false

  override def defeated: Unit = state = UserState.LOST

  override def won: Unit = state = UserState.WON

  override def startRemi(rounds: Int): Unit = roundsLeft = Option.apply(rounds)

  override def remi: Unit = if (roundsLeft.isDefined && roundsLeft.get.equals(0)) state = UserState.REMI
}

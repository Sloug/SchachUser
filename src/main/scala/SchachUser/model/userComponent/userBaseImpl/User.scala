package SchachUser.model.userComponent.userBaseImpl

import SchachUser.model.userComponent.UserInterface
import SchachUser.model.userComponent.userBaseImpl.UserState.UserState


case class User(name: String, black: Boolean, myTurn: Boolean, state: UserState = UserState.ALIVE) extends UserInterface {
  def this(name: String, black: Boolean, myTurn: Boolean) = this(name, black, myTurn, UserState.ALIVE)
  def this(name: String, black: Boolean) = this(name, black, if(black) false else true)

  var roundsLeft:Option[Int] = Option.empty

  override def nextRound: UserInterface = copy(name, black, !myTurn, state)

  override def defeated: UserInterface = copy(name, black, myTurn, UserState.LOST)

  override def won: UserInterface = copy(name, black, myTurn, UserState.WON)

  override def startRemi(rounds: Int): Unit = roundsLeft = Option.apply(rounds)

  override def remi: UserInterface = copy(name, black, myTurn, UserState.REMI)//if (roundsLeft.isDefined && roundsLeft.get.equals(0)) state = UserState.REMI
}

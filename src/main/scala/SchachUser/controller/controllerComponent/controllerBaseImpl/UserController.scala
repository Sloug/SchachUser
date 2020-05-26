package SchachUser.controller.controllerComponent.controllerBaseImpl

import SchachUser.controller.controllerComponent.UserControllerInterface
import SchachUser.model.userComponent.UserInterface
import SchachUser.model.userComponent.userBaseImpl.User

case class UserController(var userWhite: UserInterface, var userBlack: UserInterface) extends UserControllerInterface {
  def this(nameWhite: String, nameBlack: String) = this(new User(nameWhite, false), new User(nameBlack, true))
  def this() = this(new User("abc", false), new User("def", true))

  override def names: (String, String) = (userWhite.name, userBlack.name)

  override def nextRound: Unit = {
    userWhite = userWhite.nextRound
    userBlack = userBlack.nextRound
  }

  override def whitesTurn: Boolean = userWhite.myTurn //2 user!!

//  override def state: _root_.SchachUser.model.userComponent.userBaseImpl.UserState.UserState = userWhite.state

//  override def setRemi: Unit = ???
//
//  override def setWonLost: Unit = ???
  override def undoRound: Unit = nextRound

  override def whoseTurn: String = if(userWhite.myTurn) "white" else "black"
}

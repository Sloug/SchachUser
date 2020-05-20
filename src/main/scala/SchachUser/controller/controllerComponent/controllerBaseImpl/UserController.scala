package SchachUser.controller.controllerComponent.controllerBaseImpl

import SchachUser.controller.controllerComponent.UserControllerInterface
import SchachUser.model.userComponent.userBaseImpl.User

case class UserController(var user: User) extends UserControllerInterface {

  override def name: String = user.name

  override def colour: Boolean = user.black

  override def doRound: Boolean = if (user.myTurn) {
    user.myTurn = false
    true
  } else false

  override def myTurn: Boolean = user.myTurn

  override def state: _root_.SchachUser.model.userComponent.userBaseImpl.UserState.UserState = user.state
}

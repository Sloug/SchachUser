package SchachUser.controller.controllerComponent

import SchachUser.model.userComponent.userBaseImpl.UserState.UserState

trait UserControllerInterface {
  def name: String

  def colour: Boolean

  def doRound: Boolean

  def myTurn: Boolean

  def state: UserState
}

package SchachUser.controller.controllerComponent

import SchachUser.model.userComponent.userBaseImpl.UserState.UserState


//names
//whos turn
//next (only choose if move was valid, if promotion after promotion)
trait UserControllerInterface {
  def names: (String, String)

  def nextRound: Unit // maybe return state

  def undoRound: Unit

  def whitesTurn: Boolean

  def whoseTurn: String
}

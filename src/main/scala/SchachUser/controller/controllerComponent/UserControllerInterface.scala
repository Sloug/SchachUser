package SchachUser.controller.controllerComponent

import SchachUser.model.userComponent.userBaseImpl.UserState.UserState


//names
//whos turn
//next (only choose if move was valid, if promotion after promotion)
trait UserControllerInterface {
  def names: (String, String) //2

  def colour: Boolean //weg

  def nextRound: Boolean // maybe return state

  def myTurn: Boolean

  def state: UserState //weg

  def setRemi

  def setWonLost
}

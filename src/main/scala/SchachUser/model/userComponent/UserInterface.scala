package SchachUser.model.userComponent

import SchachUser.model.userComponent.userBaseImpl.UserState.UserState

trait UserInterface {
  def name: String

  def isBlack: Boolean

  def myTurn: Boolean

  def getState: UserState

  def nextRound: UserInterface

  def defeated: UserInterface

  def won: UserInterface

  def startRemi(rounds: Int)

  def remi: UserInterface
}

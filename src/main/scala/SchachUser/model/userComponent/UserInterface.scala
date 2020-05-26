package SchachUser.model.userComponent

trait UserInterface {
  def name: String

  def myTurn: Boolean

  def nextRound: UserInterface

  def defeated: UserInterface

  def won: UserInterface

  def startRemi(rounds: Int)

  def remi: UserInterface
}

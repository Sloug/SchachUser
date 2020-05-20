package SchachUser.model.userComponent

trait UserInterface {

  def yourTurn

  def othersTurn

  def defeated

  def won

  def startRemi(rounds: Int)

  def remi
}

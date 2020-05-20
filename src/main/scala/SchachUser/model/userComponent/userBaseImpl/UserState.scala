package SchachUser.model.userComponent.userBaseImpl

object UserState extends Enumeration {
  type UserState = Value
  val ALIVE, LOST, WON, REMI = Value

  val map = Map[UserState, String](
    ALIVE -> "Player still alive",
    LOST -> "Player lost the game",
    WON -> "Player won the game",
    REMI -> "Player ended up with remi"
  )

  def message(userState: UserState) = {
    map(userState)
  }
}

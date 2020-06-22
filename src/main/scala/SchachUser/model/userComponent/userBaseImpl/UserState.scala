package SchachUser.model.userComponent.userBaseImpl

import play.api.libs.json.{Reads, Writes}

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

//  implicit val readsUserState = Reads.enumNameReads(UserState)
//  implicit val writesUserState = Writes.enumNameWrites
}


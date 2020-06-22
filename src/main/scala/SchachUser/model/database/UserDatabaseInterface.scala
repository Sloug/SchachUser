package SchachUser.model.database

import SchachUser.model.userComponent.UserInterface
import SchachUser.model.userComponent.userBaseImpl.User
import SchachUser.model.userComponent.userBaseImpl.UserState.UserState

import scala.util.Try

trait UserDatabaseInterface {
  def create(user: UserInterface): Try[Unit]
  def read(name: String): Try[UserInterface]
  def update(user: UserInterface): Try[Unit]
  def delete(name: String): Try[Unit]
  def initStorage: Try[Unit]
}


case class UserDatabaseContainer(name: String, black: Boolean, myTurn: Boolean, state: UserState) {
  def this(user: UserInterface) = this(user.name, user.isBlack, user.myTurn, user.getState)

  def toUserInterface: UserInterface = {
    User(name, black, myTurn, state)
  }
}

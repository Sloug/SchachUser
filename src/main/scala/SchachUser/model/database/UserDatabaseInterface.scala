package SchachUser.model.database

import SchachUser.model.userComponent.UserInterface
import SchachUser.model.userComponent.userBaseImpl.User
import SchachUser.model.userComponent.userBaseImpl.UserState.UserState

import scala.concurrent.Future
import scala.util.Try

trait UserDatabaseInterface {
  def create(user: UserInterface): Future[Unit]
  def read(name: String): Future[UserInterface]
  def update(user: UserInterface): Future[Unit]
  def delete(name: String): Future[Unit]
  def initStorage: Future[Unit]
}


case class UserDatabaseContainer(name: String, black: Boolean, myTurn: Boolean, state: UserState) {
  def this(user: UserInterface) = this(user.name, user.isBlack, user.myTurn, user.getState)

  def toUserInterface: UserInterface = {
    User(name, black, myTurn, state)
  }
}

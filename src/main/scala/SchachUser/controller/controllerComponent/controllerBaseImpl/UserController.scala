package SchachUser.controller.controllerComponent.controllerBaseImpl

import SchachUser.controller.controllerComponent.{SafeConfigInjection, UserControllerInterface}
import SchachUser.model.fileIoComponent.FileIO
import SchachUser.model.userComponent.UserInterface
import SchachUser.model.userComponent.userBaseImpl.User
import com.google.inject.Guice

case class UserController(var userWhite: UserInterface, var userBlack: UserInterface) extends UserControllerInterface {
  def this(nameWhite: String, nameBlack: String) = this(new User(nameWhite, false), new User(nameBlack, true))
  def this() = this(UserController.newWhiteUser, UserController.newBlackUser)

  val injector = Guice.createInjector(new SafeConfigInjection)
  val fileIo = injector.getInstance(classOf[FileIO])

  override def names: (String, String) = (userWhite.name, userBlack.name)

  override def nextRound: Unit = {
    userWhite = userWhite.nextRound
    userBlack = userBlack.nextRound
  }

  override def whitesTurn: Boolean = userWhite.myTurn //2 user!!

//  override def state: _root_.SchachUser.model.userComponent.userBaseImpl.UserState.UserState = userWhite.state

//  override def setRemi: Unit = ???
//
//  override def setWonLost: Unit = ???
  override def undoRound: Unit = nextRound

  override def whoseTurn: String = if(userWhite.myTurn) "white" else "black"

  override def save: Unit = {
    fileIo.save(userWhite)
    fileIo.save(userBlack)
  }

  override def load: Unit = {
    userWhite = fileIo.load(UserController.NameWhitePlayer)
    userBlack = fileIo.load(UserController.NameBlackPlayer)
  }

  override def restartGame: Unit = {
    userWhite = UserController.newWhiteUser
    userBlack = UserController.newBlackUser
  }
}

object UserController {
  val NameWhitePlayer = "white"
  val NameBlackPlayer = "black"
  val newWhiteUser = new User(NameWhitePlayer, false)
  val newBlackUser = new User(NameBlackPlayer, true)
}

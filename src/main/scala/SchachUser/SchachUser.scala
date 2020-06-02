package SchachUser

import controller.controllerComponent.controllerBaseImpl.UserController
import aview.SchachUserHttpServer

object SchachUser {
  val userController = new UserController()
  val server = SchachUserHttpServer(userController)
  var shutdown = false
  def main(args: Array[String]): Unit = {
    while ( {
      !shutdown
    }) Thread.sleep(1000)

    server.shutdownWebServer
  }

  def shutdownServer():Unit = shutdown = true
}

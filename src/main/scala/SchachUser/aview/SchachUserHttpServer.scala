package SchachUser.aview

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport
import scala.concurrent.Future
import SchachUser.controller.controllerComponent.UserControllerInterface

import SchachUser.SchachUser

case class SchachUserHttpServer(userControllerInterface: UserControllerInterface) extends PlayJsonSupport {
  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
//   needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher

  val route: Route = concat(
    get{
      path("user" / "whoseTurn") {
        complete(userControllerInterface.whoseTurn)
      }
    },
    get{
      path("user" / "names") {
        complete(userControllerInterface.names.toString())
      }
    },
    get{
      path("user" / "whitesTurn") {
        complete(userControllerInterface.whitesTurn.toString)
      }
    },
    post{
      path("user" / "nextRound") {
        userControllerInterface.nextRound
        complete("")
      }
    },
    post{
      path("user" / "undoRound") {
        userControllerInterface.undoRound
        complete("")
      }
    },
    post{
      path("user" / "save") {
        complete("")
      }
    },
    post{
      path("user" / "load") {
        complete("")
      }
    },
    post{
      path("user" / "shutDown") {
        SchachUser.shutdown = true
        complete("")
      }
    },
  )


  val bindingFuture: Future[Http.ServerBinding] = Http().bindAndHandle(route, "localhost", 7070)

  def shutdownWebServer() : Unit = {
    bindingFuture
      .flatMap(_.unbind()) // trigger unbinding from the port
      .onComplete(_ => system.terminate()) // and shutdown when done
  }
}

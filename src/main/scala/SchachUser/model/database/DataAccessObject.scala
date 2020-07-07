package SchachUser.model.database

import SchachUser.model.userComponent.UserInterface
import com.google.inject.Guice
import scala.concurrent.ExecutionContext.Implicits.global

import scala.util.{Failure, Success}

object DataAccessObject {
  val injector = Guice.createInjector(new DatabaseTypeInjection)
  val database = injector.getInstance(classOf[UserDatabaseInterface])
  val x = database.initStorage
  x.onComplete {
    case Success(_) => println("Good")
    case Failure(e) => println(e.getMessage)


  }

  def create(user: UserInterface): Unit = {
    while(true){
      val a = database.create(user)
      a.onComplete {
        case Success(_) => println("Success")
        case Failure(e) => println(e.getMessage)
      }
    }
  }

  def read(name: String): UserInterface = {
    while (true) {
      val user = database.read(name)
      user.onComplete {
        case Success(user) => {
          println("Success")
          return user
        }
        case Failure(e) => {
          println("error: " + e.getMessage)
          System.exit(1)
        }
      }
    }
    null
  }

  def update(user: UserInterface): Unit = {
    val a = database.update(user)
    a.onComplete {
      case Success(_) => println("Success")
      case Failure(e) => println(e.getMessage)
    }
  }

  def delete(name: String): Unit = {
    val a = database.delete(name)
    a.onComplete {
      case Success(_) => println("Success")
      case Failure(e) => println(e.getMessage)
    }
  }
}

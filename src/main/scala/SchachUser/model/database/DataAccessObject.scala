package SchachUser.model.database

import SchachUser.model.userComponent.UserInterface
import com.google.inject.Guice

object DataAccessObject {
  val injector = Guice.createInjector(new DatabaseTypeInjection)
  val database = injector.getInstance(classOf[UserDatabaseInterface])
  val x = database.initStorage
  if (x.isFailure)
    println(x.failed.get.getMessage)
  else
  println("Good")
//  while(database.initStorage.isFailure){}

  def create(user: UserInterface): Unit = {
    while(database.create(user).isFailure){}
  }

  def read(name: String): UserInterface = {
    while(true) {
      val user = database.read(name)
      if(user.isSuccess) return user.get
    }
    null
  }

  def update(user: UserInterface): Unit = while(database.update(user).isFailure){}

  def delete(name: String): Unit = while(database.delete(name).isFailure){}
}

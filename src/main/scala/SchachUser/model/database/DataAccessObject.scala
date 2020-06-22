package SchachUser.model.database

import SchachUser.model.userComponent.UserInterface
import com.google.inject.Guice

object DataAccessObject {
  val injector = Guice.createInjector(new DatabaseTypeInjection)
  val database = injector.getInstance(classOf[UserDatabaseInterface])
  database.initStorage

  def create(user: UserInterface): Unit = {
    while(database.create(user).isFailure){}
  }

  def read(name: String): UserInterface = {
    while(true) {
      val field = database.read(name)
      if(field.isSuccess) return field.get
    }
    null
  }

  def update(user: UserInterface): Unit = while(database.update(user).isFailure){}

  def delete(name: String): Unit = while(database.delete(name).isFailure){}
}

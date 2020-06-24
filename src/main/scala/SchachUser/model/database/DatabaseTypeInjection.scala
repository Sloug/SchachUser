package SchachUser.model.database

import SchachUser.model.database.mongoDB.UserDatabasexMongoDB
import SchachUser.model.database.slick.UserDatabaseMySQL
import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule

class DatabaseTypeInjection extends AbstractModule with ScalaModule {
  override def configure(): Unit = {
    bind[UserDatabaseInterface].to[UserDatabasexMongoDB]
  }

}

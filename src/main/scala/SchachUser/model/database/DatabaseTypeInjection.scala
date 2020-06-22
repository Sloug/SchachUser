package SchachUser.model.database

import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule

class DatabaseTypeInjection extends AbstractModule with ScalaModule {
  override def configure(): Unit = {
    bind[UserDatabaseInterface].to[UserDatabaseContainer]
  }

}

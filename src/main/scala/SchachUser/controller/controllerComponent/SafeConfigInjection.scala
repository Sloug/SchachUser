package SchachUser.controller.controllerComponent

import SchachUser.model.fileIoComponent.FileIO
import SchachUser.model.fileIoComponent.fileIoDatabaseImpl.FileIODatabase
import com.google.inject.AbstractModule
import net.codingwell.scalaguice.ScalaModule

class SafeConfigInjection extends AbstractModule with ScalaModule {
  override def configure(): Unit = {
    bind[FileIO].to[FileIODatabase]
  }
}

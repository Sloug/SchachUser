package SchachUser.model.fileIoComponent.fileIoDatabaseImpl

import SchachUser.model.database.DataAccessObject
import SchachUser.model.fileIoComponent.FileIO
import SchachUser.model.userComponent.UserInterface

class FileIODatabase extends FileIO {
  val dao = DataAccessObject
  override def load(name:String): UserInterface = dao.read(name)

  override def save(user: UserInterface): Unit = dao.create(user)
}

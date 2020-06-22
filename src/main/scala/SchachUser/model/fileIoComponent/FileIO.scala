package SchachUser.model.fileIoComponent

import SchachUser.model.userComponent.UserInterface

trait FileIO {
  def load(name:String):UserInterface

  def save(user: UserInterface):Unit
}

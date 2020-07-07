package SchachUser.model.database.slick

import SchachUser.model.database.{UserDatabaseContainer, UserDatabaseInterface}
import SchachUser.model.userComponent.UserInterface
import SchachUser.model.userComponent.userBaseImpl.{User, UserState}
import SchachUser.model.userComponent.userBaseImpl.UserState.UserState
import slick.lifted.Tag
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import scala.util.Try

class UserDatabaseMySQL extends UserDatabaseInterface {
  val userDatabase = TableQuery[PersistanceMapping]
  val db = Database.forConfig("mysql")

  override def create(user: UserInterface): Future[Unit] = {
    val insertAction = userDatabase.insertOrUpdate(new UserDatabaseContainer(user))
    Future.fromTry(Try(Await.result(db.run(insertAction), Duration.Inf)))
  }

  override def read(name: String): Future[UserInterface] = {
    val readAction = userDatabase.filter(_.name === name).result
    Future.fromTry(Try(Await.result(db.run(readAction), Duration.Inf).map(x => x.toUserInterface).head))
  }

  override def update(user: UserInterface): Future[Unit] = {
    val insertAction = userDatabase.insertOrUpdate(new UserDatabaseContainer(user))
    Future.fromTry(Try(Await.result(db.run(insertAction), Duration.Inf)))
  }

  override def delete(name: String): Future[Unit] = {
    val deleteAction = userDatabase.filter(_.name === name).delete
    Future.fromTry(Try(Await.result(db.run(deleteAction), Duration.Inf)))
  }

  override def initStorage: Future[Unit] = {
    val createTableAction = userDatabase.schema.create
    Future.fromTry(Try(Await.result(db.run(createTableAction), Duration.Inf)))
  }
}

class PersistanceMapping(tag:Tag) extends Table[UserDatabaseContainer](tag, "player") {
  implicit val userStateMapper = MappedColumnType.base[UserState, String] (
    e => e.toString,
    s => UserState.withName(s)
  )
  def name = column[String]("name", O.PrimaryKey)
  def black = column[Boolean]("black")
  def myTurn = column[Boolean]("my_turn")
  def state = column[UserState]("state")
  override def * = (name, black, myTurn, state) <> (create, extract)
  def create(t: (String, Boolean, Boolean, UserState)): UserDatabaseContainer = UserDatabaseContainer(t._1, t._2, t._3, t._4)
  def extract(f: UserDatabaseContainer): Option[(String, Boolean, Boolean, UserState)] = Some((f.name, f.black, f.myTurn, f.state))
}
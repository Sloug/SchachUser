package SchachUser.model.database.mongoDB

import SchachUser.model.database.{UserDatabaseContainer, UserDatabaseInterface}
import SchachUser.model.userComponent.UserInterface
import SchachUser.model.userComponent.userBaseImpl.UserState
import org.mongodb.scala._
import org.mongodb.scala.model.Filters._

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.util.Try

class UserDatabasexMongoDB extends UserDatabaseInterface {
  val client: MongoClient = MongoClient()
  val database: MongoDatabase = client.getDatabase("mongoDB")


  override def create(user: UserInterface): Try[Unit] = {
    val collection: MongoCollection[Document] = database.getCollection("player")
    val playerDatabase = new UserDatabaseContainer(user)
    val document: Document = Document("name" -> playerDatabase.name, "black" -> playerDatabase.black,
      "myTurn" -> playerDatabase.myTurn, "state" -> playerDatabase.state.toString)

    val filterDocument: Document = Document("name" -> user.name)
    val g = Await.result(collection.countDocuments(filterDocument).toFuture(), Duration.Inf)
    println("size is: " + g)
    Try(
      if(g == 0) {
        println("create")
        Await.result(collection.insertOne(document).toFuture(), Duration.Inf)
      }
    else {
      println("Already created")
      Await.result(collection.updateOne(filterDocument, document).toFuture(), Duration.Inf)
    })
  }

  override def read(name: String): Try[UserInterface] = {
    val collection: MongoCollection[Document] = database.getCollection("player")

    println("read") //DEBUG
    Try({
      val a = Await.result(collection.find(equal("name", name)).first().map(document =>
        UserDatabaseContainer(document.get("name").get.asString().getValue, document.get("black").get.asBoolean().getValue,
          document.get("myTurn").get.asBoolean().getValue, UserState.withName(document.get("state").get.asString().getValue))).toFuture(), Duration.Inf)
      println(a + " " + a.size)
      a.head.toUserInterface
    })
  }

  override def update(user: UserInterface): Try[Unit] = {
    val collection: MongoCollection[Document] = database.getCollection("player")
    val playerDatabase = new UserDatabaseContainer(user)
    val document: Document = Document("name" -> playerDatabase.name, "black" -> playerDatabase.black,
      "myTurn" -> playerDatabase.myTurn, "state" -> playerDatabase.state.toString)
    val filterDocument: Document = Document("name" -> user.name)

    Try(Await.result(collection.updateOne(filterDocument, document).toFuture(), Duration.Inf))
  }

  override def delete(name: String): Try[Unit] = {
    val collection: MongoCollection[Document] = database.getCollection("player")
    val filterDocument: Document = Document("name" -> name)

    Try(Await.result(collection.deleteOne(filterDocument).toFuture(), Duration.Inf))
  }

  override def initStorage: Try[Unit] = {
    Try(Await.result(database.createCollection("player").toFuture(), Duration.Inf))
  }
}

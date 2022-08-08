import Client.{JsonPlaceholderClient, RequestTemplate}
import FlieSaver.{FileHandler, PostFileSaver}
import service.PostSavingService

import scala.concurrent.Await
import scala.concurrent.duration.{Duration, SECONDS}
import scala.util.{Failure, Success, Try}

object Main {

  def main(args: Array[String]): Unit = {
    val client = new JsonPlaceholderClient(new RequestTemplate)
    val fileSaver = new PostFileSaver(new FileHandler)
    val service = new PostSavingService(client, fileSaver)

    val savedPostsIds = service.fetchAndSavePosts()

    Try { Await.result(savedPostsIds, Duration(5, SECONDS)) } match {
      case Success(result) => println(s"Successfully fetched and saved ${result.size} posts")
      case Failure(exception) => throw exception
    }
  }
}

import Client.{JsonPlaceholderClient, RequestTemplate}
import FlieSaver.{FileHandler, PostFileSaver}
import service.PostSavingService
import validator.DirectoryValidator

import scala.concurrent.Await
import scala.concurrent.duration.{Duration, SECONDS}
import scala.util.{Failure, Success, Try}

object Main {

  def main(args: Array[String]): Unit = {
    val client = new JsonPlaceholderClient(new RequestTemplate)
    val validator = new DirectoryValidator
    val fileSaver = new PostFileSaver(new FileHandler, validator)
    val service = new PostSavingService(client, fileSaver)


    val directory = args.headOption

    directory.exists(validator.isValidDirectory)

    if (!directory.exists(validator.isValidDirectory))
      println("No valid directory for saving provided. Posts will be saved in source directory")

    val savedPostsIds = service.fetchAndSavePosts(directory)

    Try { Await.result(savedPostsIds, Duration(5, SECONDS)) } match {
      case Success(result) => println(s"Successfully fetched and saved ${result.size} posts")
      case Failure(exception) => throw exception
    }
  }
}

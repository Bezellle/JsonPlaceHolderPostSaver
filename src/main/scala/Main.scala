import Client.{JsonPlaceholderClient, RequestTemplate}
import FlieSaver.{FileHandler, FileSaver}
import service.PostSavingService
import validator.DirectoryValidator

import java.nio.file.{Path, Paths}
import scala.concurrent.Await
import scala.concurrent.duration.{Duration, SECONDS}
import scala.util.{Failure, Success, Try}


object Main {

  case class ProgramArguments(numberOfPosts: Int, directory: Option[Path])



  def main(args: Array[String]): Unit = {
    val client = new JsonPlaceholderClient(new RequestTemplate)
    val validator = new DirectoryValidator
    val fileSaver = new FileSaver(new FileHandler, validator)
    val service = new PostSavingService(client, fileSaver)


    val programArguments = parseProgramArguments(args)

//    if (!directory.exists(validator.isValidDirectory))
//      println("No valid directory for saving provided. Posts will be saved in source directory")

//    val savedPostsIds = service.fetchAndSavePosts(directory)

//    Try { Await.result(savedPostsIds, Duration(5, SECONDS)) } match {
//      case Success(result) => println(s"Successfully fetched and saved ${result.size} posts")
//      case Failure(exception) => throw exception
//    }
  }

  private def parseProgramArguments(args: Array[String]): ProgramArguments = {
    args match {
      case Array(number) => ProgramArguments(number.toInt, None)
      case Array(number, directory) => ProgramArguments(number.toInt, Some(Paths.get(directory)))
      case _ => throw new RuntimeException("Incorrect number of arguments")
    }
  }
}

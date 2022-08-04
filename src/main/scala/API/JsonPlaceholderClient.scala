package API

import Domain.Post
import Exception.JsonPlaceHolderException
import io.circe.generic.auto._
import io.circe.parser

object JsonPlaceholderClient extends DefaultClient {

  def getAllPosts() = {
     getRequest("https://jsonplaceholder.typicode.com/posts") match {
      case Right(response) if response.statusCode == 200 => decodeResponse(response.text().stripMargin)
      case Left(errorDetails: RequestErrorDetails) => throw JsonPlaceHolderException(errorDetails)
    }
  }

  private def decodeResponse(text: String): List[Post] = {
    parser.decode[List[Post]](text) match {
      case Right(decodedText) => decodedText
      case Left(error) => throw new RuntimeException(error)
    }
  }

}

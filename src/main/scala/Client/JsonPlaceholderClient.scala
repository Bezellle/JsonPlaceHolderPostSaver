package Client

import Client.Exception.JsonPlaceHolderException
import Client.JsonPlaceholderClient.BaseUrl
import Domain.{Comment, Post, PostId}
import requests.Response

class JsonPlaceholderClient(requestTemplate: RequestTemplate) extends DefaultClient {

  def getAllPosts: List[Post] =
     requestTemplate.getRequest("https://jsonplaceholder.typicode.com/posts") match {
      case response: Response if response.is2xx => serialize[List[Post]](response.text())
      case response: Response => throw JsonPlaceHolderException(response)
    }

  def getCommentsForPost(postId: PostId): List[Comment] =
    requestTemplate.getRequest(s"$BaseUrl/comments?postId=${postId.id}") match {
      case response: Response if response.is2xx => serialize[List[Comment]](response.text())
      case response: Response => throw JsonPlaceHolderException(response)
    }
}

object JsonPlaceholderClient {
  val BaseUrl = "https://jsonplaceholder.typicode.com"
}

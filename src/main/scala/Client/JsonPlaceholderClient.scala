package Client

import Client.Exception.JsonPlaceHolderException
import Client.JsonPlaceholderClient.BaseUrl
import Client.Response.CommentResponse
import Domain.Post.{Post, PostId}
import requests.Response

class JsonPlaceholderClient(requestTemplate: RequestTemplate) extends DefaultClient {

  def getAllPosts: List[Post] =
    requestTemplate.getRequest(s"$BaseUrl/posts") match {
      case response: Response if response.is2xx => serialize[List[Post]](response.text())
      case response: Response => throw JsonPlaceHolderException(response)
    }

  def getCommentsForPost(postId: PostId): List[CommentResponse] =
    requestTemplate.getRequest(s"$BaseUrl/comments?postId=${postId.id}") match {
      case response: Response if response.is2xx => serialize[List[CommentResponse]](response.text())
      case response: Response => throw JsonPlaceHolderException(response)
    }
}

object JsonPlaceholderClient {
  val BaseUrl = "https://jsonplaceholder.typicode.com"
}

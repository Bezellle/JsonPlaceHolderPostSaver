package Client

import Client.Exception.JsonPlaceHolderException
import Client.JsonPlaceholderClient.BaseUrl
import Client.Response.{CommentResponse, CommentResponseMapper}
import Domain.{Comment, Post, PostId}
import requests.Response

class JsonPlaceholderClient(requestTemplate: RequestTemplate) extends DefaultClient {

  def getAllPosts: List[Post] =
    requestTemplate.getRequest(s"$BaseUrl/posts") match {
      case response: Response if response.is2xx => serialize[List[Post]](response.text())
      case response: Response => throw JsonPlaceHolderException(response)
    }

  def getCommentsForPost(postId: PostId): List[Comment] =
    requestTemplate.getRequest(s"$BaseUrl/comments?postId=${postId.id}") match {
      case response: Response if response.is2xx => serialize[List[CommentResponse]](response.text())
        .map(CommentResponseMapper.toComment)
      case response: Response => throw JsonPlaceHolderException(response)
    }
}

object JsonPlaceholderClient {
  val BaseUrl = "https://jsonplaceholder.typicode.com"
}

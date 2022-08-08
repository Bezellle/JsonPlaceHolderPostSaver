package Client

import Client.Exception.JsonPlaceHolderException
import Domain.Post
import requests.Response

class JsonPlaceholderClient(requestTemplate: RequestTemplate) extends DefaultClient {

  def getAllPosts: List[Post] =
     requestTemplate.getRequest("https://jsonplaceholder.typicode.com/posts") match {
      case response: Response if response.is2xx => serialize[List[Post]](response.text())
      case response: Response => throw JsonPlaceHolderException(response)
    }

}

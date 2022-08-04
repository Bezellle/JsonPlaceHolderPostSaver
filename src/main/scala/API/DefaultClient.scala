package API

import requests.Response

trait DefaultClient {

  def getRequest(url: String): Either[RequestErrorDetails, Response] = {
     requests.get(url) match {
      case response: Response if response.is2xx => Right(response)
      case response: Response   => Left(RequestErrorDetails(response.statusCode, response.statusMessage))
    }
  }

}

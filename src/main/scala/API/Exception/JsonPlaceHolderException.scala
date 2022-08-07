package API.Exception

import requests.Response

final case class JsonPlaceHolderException(failedResponse: Response)
  extends RuntimeException(s"JsonPlaceHolder respond with - ${failedResponse.statusCode} - ${failedResponse.statusMessage}")

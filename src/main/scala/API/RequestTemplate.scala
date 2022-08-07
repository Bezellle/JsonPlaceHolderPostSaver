package API

import requests.Response

class RequestTemplate {

  def getRequest(url: String): Response =
    requests.get(url)

}

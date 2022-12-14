package Client

import Client.RequestTemplate.TIMEOUT_MILLS
import requests.Response

class RequestTemplate {

  def getRequest(url: String): Response =
    requests.get(url, connectTimeout = TIMEOUT_MILLS)

}

object RequestTemplate {
  val TIMEOUT_MILLS = 5000
}

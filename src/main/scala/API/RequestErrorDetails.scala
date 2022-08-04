package API

case class RequestErrorDetails(statusCode: Int, message: String) {
  override def toString: String = s"API Request Error - $statusCode - $message"
}

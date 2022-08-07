package API

import org.json4s.jackson.JsonMethods.parse
import org.json4s.{DefaultFormats, Formats}

trait DefaultClient {

  implicit val formats: Formats = DefaultFormats

  def serialize[T: Manifest](responseBody: String): T =
    parse(responseBody).extract[T]
}

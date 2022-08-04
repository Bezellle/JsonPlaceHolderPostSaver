package Exception

import API.RequestErrorDetails

final case class JsonPlaceHolderException(errorDetails: RequestErrorDetails)
  extends RuntimeException(errorDetails.toString)

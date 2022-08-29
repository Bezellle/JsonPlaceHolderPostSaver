package Comment.ResponseMapper

import Client.Response.CommentResponse
import Domain.Comment.{Comment, CommentId, Email}
import Domain.Post.PostId

import scala.util.matching.Regex

object CommentResponseMapper {

  val EmailPattern: Regex = """^(\w+)@(\w+(.\w+)+)$""".r

  def toComment(commentResponse: CommentResponse): Comment =
    Domain.Comment.Comment(
      PostId(commentResponse.postId),
      CommentId(commentResponse.id),
      commentResponse.name,
      toEmail(commentResponse.email),
      commentResponse.body
    )

  private def toEmail(emailString: String): Email =
    emailString match {
      case EmailPattern(name, domain) => Email(name, domain)
      case _ => throw new RuntimeException("Non valid email")
    }
}

package FlieSaver

import Domain.{Post, PostId}
import org.json4s.jackson.Serialization.write
import org.json4s.{DefaultFormats, Formats}
import validator.DirectoryValidator


class PostFileSaver(fileHandler: FileHandler, validator: DirectoryValidator) {

  private implicit val formats: Formats = DefaultFormats

  def savePost(post: Post, directory: Option[String]): PostId = {
    fileHandler.saveFile(composeFileName(post, directory), write(post))
    post.id
  }

  private def composeFileName(post: Post, directory: Option[String]): String =
    directory
      .filter(dir => validator.isValidDirectory(dir))
      .map(dir => s"$dir/${post.id.id}.json")
      .getOrElse(s"${post.id.id}.json")
}

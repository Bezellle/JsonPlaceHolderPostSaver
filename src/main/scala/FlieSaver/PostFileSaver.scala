package FlieSaver

import Domain.Post
import org.json4s.{DefaultFormats, Formats}
import org.json4s.jackson.Serialization.write


class PostFileSaver(fileHandler: FileHandler) {

  private implicit val formats: Formats = DefaultFormats

  def savePost(post: Post): Unit =
    fileHandler.saveFile(composeFileName(post), write(post))

  private def composeFileName(post: Post) =
    s"${post.id.id}.json"
}

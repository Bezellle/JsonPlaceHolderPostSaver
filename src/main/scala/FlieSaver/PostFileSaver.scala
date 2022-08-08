package FlieSaver

import Domain.{Post, PostId}
import org.json4s.jackson.Serialization.write
import org.json4s.{DefaultFormats, Formats}


class PostFileSaver(fileHandler: FileHandler) {

  private implicit val formats: Formats = DefaultFormats

  def savePost(post: Post): PostId = {
      fileHandler.saveFile(composeFileName(post), write(post))
      post.id
    }

  private def composeFileName(post: Post) =
    s"${post.id.id}.json"
}

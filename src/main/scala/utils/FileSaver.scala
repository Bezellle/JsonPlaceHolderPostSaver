package utils

import Domain.Post.{Post, PostId}
import org.json4s.jackson.Serialization.write
import org.json4s.{DefaultFormats, Formats}
import validator.DirectoryValidator

import java.nio.file.{Files, Path}


class FileSaver(fileHandler: FileHandler, validator: DirectoryValidator) {

  private implicit val formats: Formats = DefaultFormats

  def savePost(post: Post, directory: Path): PostId = {
    Files.write(directory, write(post).getBytes)
    post.id
  }
}

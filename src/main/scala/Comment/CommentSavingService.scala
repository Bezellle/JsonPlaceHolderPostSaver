package Comment

import Client.JsonPlaceholderClient
import Comment.ResponseMapper.CommentResponseMapper
import Domain.Comment.Comment
import utils.FileSaver

import java.nio.file.Path

abstract class CommentSavingService(client: JsonPlaceholderClient, fileSaver: FileSaver) {

  def fetchAndSaveComments(numberOfPosts: Int, directory: Option[Path]) =
    fetchComments(numberOfPosts)
      .groupBy(_.email.mailDomain)

  private def fetchComments(numberOfPosts: Int): List[Comment] =
    client.getAllPosts.take(numberOfPosts)
      .flatMap(post => client.getCommentsForPost(post.id))
      .map(CommentResponseMapper.toComment)

}

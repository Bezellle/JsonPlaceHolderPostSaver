package service

import Client.JsonPlaceholderClient
import FlieSaver.FileSaver

import java.nio.file.Path

abstract class CommentSavingService(client: JsonPlaceholderClient, fileSaver: FileSaver) {

  def fetchAndSaveComments(numberOfPosts: Int, directory: Option[Path]) = {
    val posts = client.getAllPosts.take(numberOfPosts)
      .flatMap(post => client.getCommentsForPost(post.id))
      .groupBy(_.email.mailDomain)

  }

  def ex
}

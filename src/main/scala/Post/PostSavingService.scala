package Post

import Client.JsonPlaceholderClient
import Domain.Post.{Post, PostId}
import utils.FileSaver

import java.nio.file.Paths
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future


class PostSavingService (jsonPlaceholderClient: JsonPlaceholderClient, postFileSaver: FileSaver) {

  def fetchAndSavePosts(directory: Option[String]): Future[List[PostId]] =
   savePostsInParallel(fetchPosts, directory)

  private def fetchPosts: List[Post] =
    jsonPlaceholderClient.getAllPosts

  private def savePostsInParallel(posts: List[Post], directory: Option[String]): Future[List[PostId]] =
    Future.traverse(posts)(post => Future(postFileSaver.savePost(post, Paths.get(directory.get))))

}

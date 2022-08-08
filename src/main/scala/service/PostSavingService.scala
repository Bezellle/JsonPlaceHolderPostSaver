package service

import API.JsonPlaceholderClient
import Domain.{Post, PostId}
import FlieSaver.PostFileSaver

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class PostSavingService (jsonPlaceholderClient: JsonPlaceholderClient, postFileSaver: PostFileSaver){

  def fetchAndSavePosts(): Future[List[PostId]] =
   savePostsInParallel(fetchPosts)

  private def fetchPosts: List[Post] =
    jsonPlaceholderClient.getAllPosts

  private def savePostsInParallel(posts: List[Post]): Future[List[PostId]] = {
    Future.traverse(posts)(post => Future(postFileSaver.savePost(post)))
  }
}

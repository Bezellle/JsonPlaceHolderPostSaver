package service

import Client.JsonPlaceholderClient
import Domain.Post.{PostId, UserId}
import Post.PostSavingService
import TestUtils.UnitSpec
import org.scalatest.concurrent.Eventually.eventually
import utils.FileSaver

import scala.concurrent.ExecutionContext.Implicits.global

class PostSavingServiceTest extends UnitSpec {

  private val client = stub[JsonPlaceholderClient]
  private val fileSaver = mock[FileSaver]

  val service = new PostSavingService(client, fileSaver)

  "service" should "fetch all products and trigger saving posts" in {
    Given("List of posts")
    val posts = List(
      Domain.Post.Post(UserId(1), PostId(1), "test title", "test body"),
      Domain.Post.Post(UserId(1), PostId(2), "test title2", "test body2")
    )
    val directory = None

    (client.getAllPosts _).when().returns(posts)
    (fileSaver.savePost _).expects(posts.head, directory).returns(posts.head.id)
    (fileSaver.savePost _).expects(posts(1), directory).returns(posts(1).id)

    When("service methods invoked")
    val result = service.fetchAndSavePosts(directory)

    Then("Ids of all posts should be returned")
    eventually {
      result.map { postsIDs => assert(postsIDs == posts.map(_.id)) }
    }
  }
}

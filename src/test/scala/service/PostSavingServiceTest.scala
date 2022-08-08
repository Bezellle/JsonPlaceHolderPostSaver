package service

import API.JsonPlaceholderClient
import Domain.{Post, PostId, UserId}
import FlieSaver.PostFileSaver
import org.scalamock.scalatest.MockFactory
import org.scalatest.GivenWhenThen
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class PostSavingServiceTest extends AsyncFlatSpec with MockFactory with GivenWhenThen {

  private val client = stub[JsonPlaceholderClient]
  private val fileSaver = mock[PostFileSaver]

  val service = new PostSavingService(client, fileSaver)

  "service" should "fetch all products and trigger saving posts" in {
    Given("List of posts")
    val posts = List(
      Post(UserId(1), PostId(1), "test title", "test body"),
      Post(UserId(1), PostId(2), "test title2", "test body2")
    )

    (client.getAllPosts _).when().returns(posts)
    (fileSaver.savePost _).expects(posts.head).returns(posts.head.id)
    (fileSaver.savePost _).expects(posts(1)).returns(posts(1).id)

    When("service methods invoked")
    val result = service.fetchAndSavePosts()

    Then("Ids of all posts should be returned")
    result.map { postsIDs => postsIDs shouldBe posts.map(_.id) }

  }
}

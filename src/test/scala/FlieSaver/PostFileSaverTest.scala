package FlieSaver

import Domain.{Post, PostId, UserId}
import TestUtils.UnitSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import validator.DirectoryValidator

class PostFileSaverTest extends UnitSpec {

  private val fileHandler = mock[FileHandler]
  private val validator = mock[DirectoryValidator]
  private val saver = new FileSaver(fileHandler, validator)

  it should "invoke file saving without file directory" in {
    Given("post")
    val post = Post(UserId(1), PostId(1), "test title", "test body")
    val postJson = """{"userId":1,"id":1,"title":"test title","body":"test body"}"""

    fileHandler.saveFile _ expects ("1.json", postJson)

    When("invoking PostFileSaver")
    val id = saver.savePost(post, None)

    Then("post should be saved")
    id shouldBe post.id
  }

  it should "invoke file saving with valid file directory" in {
    Given("post with directory")
    val post = Post(UserId(1), PostId(1), "test title", "test body")
    val postJson = """{"userId":1,"id":1,"title":"test title","body":"test body"}"""
    val directory = "/dir"

    validator.isValidDirectory _ expects directory returns true
    fileHandler.saveFile _ expects (directory + "/1.json", postJson)

    When("invoking PostFileSaver")
    val id = saver.savePost(post, Some(directory))

    Then("post should be saved")
    id shouldBe post.id
  }

  it should "invoke file saving with non valid file directory" in {
    Given("post with directory")
    val post = Post(UserId(1), PostId(1), "test title", "test body")
    val postJson = """{"userId":1,"id":1,"title":"test title","body":"test body"}"""
    val directory = "/dir"

    validator.isValidDirectory _ expects directory returns false
    fileHandler.saveFile _ expects ("1.json", postJson)

    When("invoking PostFileSaver")
    val id = saver.savePost(post, Some(directory))

    Then("post should be saved")
    id shouldBe post.id
  }

}

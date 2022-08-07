package FlieSaver

import Domain.{Post, PostId, UserId}
import TestUtils.UnitSpec
import org.scalatest.matchers.must.Matchers.{be, noException}

class PostFileSaverTest extends UnitSpec {

  private val fileHandler = mock[FileHandler]
  private val saver = new PostFileSaver(fileHandler)

  it should "invoke file saving" in {
    Given("post")
    val post = Post(UserId(1), PostId(1), "test title", "test body")
    val postJson = """{"userId":1,"id":1,"title":"test title","body":"test body"}"""

    fileHandler.saveFile _ expects ("1.json", postJson)

    When("invoking PostFileSaver")
    noException should be thrownBy saver.savePost(post)

    Then("post should be saved")
  }

}

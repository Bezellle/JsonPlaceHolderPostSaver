package Client

import Domain.{Post, PostId, UserId}
import TestUtils.UnitSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper


class DefaultClientTest extends UnitSpec {

  class DefaultClientImpl extends DefaultClient

  val client = new DefaultClientImpl()

  "client" should "serialize request body" in {
    Given("response text as Json string")
    val responseText = "[{\"userId\": 1,\"id\": 1,\"title\": \"test title\",\"body\": \"test body\"}]"

    When("serializing request")
    val serializedResponse = client.serialize[List[Post]](responseText)

    Then("response should be properly serialized")
    serializedResponse shouldBe List(Post(UserId(1), PostId(1), "test title", "test body"))
  }

}

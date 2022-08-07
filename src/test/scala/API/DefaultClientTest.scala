package API

import Domain.{Post, PostId, UserId}
import TestUtils.UnitSpec
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper


class DefaultClientTest extends UnitSpec {

  class DefaultClientImpl extends DefaultClient

  val client = new DefaultClientImpl()

  "client" should "serialize request body" in {
    Given("valid 200 Response with stub url and body")
    val responseText = "[{\"userId\": 1,\"id\": 1,\"title\": \"test title\",\"body\": \"test body\"}]"

    When("sending request")
    val serializedResponse = client.serialize[List[Post]](responseText)

    Then("right side either with given response should be returned")
    serializedResponse shouldBe List(Post(UserId(1), PostId(1), "test title", "test body"))
  }

}

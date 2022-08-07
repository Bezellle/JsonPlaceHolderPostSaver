package API

import org.scalamock.scalatest.MockFactory
import org.scalatest.funsuite.AnyFunSuite
import requests.Response
import geny.Bytes
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import org.scalatest.{EitherValues, GivenWhenThen}


class DefaultClientTest extends AnyFunSuite with MockFactory with GivenWhenThen with EitherValues {

  class DefaultClientImpl extends DefaultClient

  val client = new DefaultClientImpl()

  test("sending get request") {
    Given("valid 200 Response with stub url and body")
    val testUrl = "www.testowy.url"
    val responseText = "[{\"userId\": 1,\"id\": 1,\"title\": \"test title\",\"body\": \"test body\"}]"
    val responseStub = Response(testUrl, 200, "ok", new Bytes(responseText.getBytes), Map(), None)

    implicit val mockRequester = mockFunction[String, Response]
    mockRequester.expects(testUrl).returns(responseStub)

    When("sending request")
    val response = client.getRequest(testUrl)

    Then("right side either with given response should be returned")
    response.isRight equals true
    response.value.text() shouldBe responseText
  }

  test("")
}

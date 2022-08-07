package API

import API.Exception.JsonPlaceHolderException
import API.JsonPlaceholderClientTest.JSPPostsUrl
import Domain.{Post, PostId, UserId}
import TestUtils.UnitSpec
import org.scalatest.matchers.must.Matchers.have
import org.scalatest.matchers.should.Matchers.{convertToAnyShouldWrapper, the}
import requests.Response


class JsonPlaceholderClientTest extends UnitSpec {

  private val requestTemplate = mock[RequestTemplate]
  private val client = new JsonPlaceholderClient(requestTemplate)


  "client" should "send get request and receive correct response" in {
    Given("response with 200 status code and response text")
    val responseText = """[{"userId": 1,"id": 1,"title": "test title","body": "test body"}]"""
    val givenResponse = Response(JSPPostsUrl, 200, "OK", new geny.Bytes(responseText.getBytes), Map(), None)

    requestTemplate.getRequest _ expects JSPPostsUrl returning givenResponse

    When("making request")
    val postResponse = client.getAllPosts

    Then("correct list of posts should be received")
    postResponse shouldBe List(Post(UserId(1), PostId(1), "test title", "test body"))
  }

  "client" should "throw exception for nan 2xx response" in {
    Given("response with 404 status code")
    val givenResponse = Response(JSPPostsUrl, 404, "Not Found", null, Map(), None)
    val exceptionMessage = "JsonPlaceHolder respond with - 404 - Not Found"

    requestTemplate.getRequest _ expects JSPPostsUrl returning givenResponse

    When("making request")

    Then("")
    the[JsonPlaceHolderException] thrownBy client.getAllPosts should have message exceptionMessage
  }

}

object JsonPlaceholderClientTest {
  val JSPPostsUrl = "https://jsonplaceholder.typicode.com/posts"
}

package Client

import Client.Exception.JsonPlaceHolderException
import Client.JsonPlaceholderClientTest.JSPPostsUrl
import Domain.Post.{Post, PostId, UserId}
import TestUtils.UnitSpec
import com.fasterxml.jackson.core.JsonParseException
import org.json4s.MappingException
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
    postResponse shouldBe List(Domain.Post.Post(UserId(1), PostId(1), "test title", "test body"))
  }

  "client" should "throw exception for non 2xx response" in {
    Given("response with 404 status code")
    val givenResponse = Response(JSPPostsUrl, 404, "Not Found", null, Map(), None)
    val exceptionMessage = "JsonPlaceHolder respond with - 404 - Not Found"

    requestTemplate.getRequest _ expects JSPPostsUrl returning givenResponse

    When("making request")

    Then("")
    the[JsonPlaceHolderException] thrownBy client.getAllPosts should have message exceptionMessage
  }

  "client" should "fail at serializing request body without Id" in {
    Given("response text without id as Json string")
    val responseText = "[{\"userId\": 1,\"title\": \"test title\",\"body\": \"test body\"}]"
    val givenResponse = Response(JSPPostsUrl, 200, "OK", new geny.Bytes(responseText.getBytes), Map(), None)

    requestTemplate.getRequest _ expects JSPPostsUrl returning givenResponse

    When("serializing request")

    Then("MappingException should be thrown")
    the[MappingException] thrownBy client.getAllPosts
  }

  "client" should "fail at serializing request body with null as Id" in {
    Given("response text with null id as Json string")
    val responseText = "[{\"userId\": 1,,\"id\": null,\"title\": \"test title\",\"body\": \"test body\"}]"
    val givenResponse = Response(JSPPostsUrl, 200, "OK", new geny.Bytes(responseText.getBytes), Map(), None)

    requestTemplate.getRequest _ expects JSPPostsUrl returning givenResponse

    When("serializing request")

    Then("JsonParseException should be thrown")
    the[JsonParseException] thrownBy client.getAllPosts
  }

}

object JsonPlaceholderClientTest {
  val JSPPostsUrl = "https://jsonplaceholder.typicode.com/posts"
}

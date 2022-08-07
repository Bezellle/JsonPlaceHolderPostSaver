import API.{JsonPlaceholderClient, RequestTemplate}
import Domain.Post

object Main {
  def main(args: Array[String]): Unit = {
    println("Hello world!")
    val client = new JsonPlaceholderClient(new RequestTemplate)
    println(client.getAllPosts)
  }
}

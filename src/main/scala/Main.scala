import API.JsonPlaceholderClient

object Main {
  def main(args: Array[String]): Unit = {
    println("Hello world!")
    JsonPlaceholderClient.getAllPosts()
  }
}

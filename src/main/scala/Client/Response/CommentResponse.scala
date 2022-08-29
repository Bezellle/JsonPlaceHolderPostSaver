package Client.Response

case class CommentResponse(postId: Int,
                           id: String,
                           name: String,
                           email: String,
                           body: String)

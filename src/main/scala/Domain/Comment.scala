package Domain

case class Comment(
                  postId: PostId,
                  id: CommentId,
                  name: String,
                  email: Email,
                  body: String
                  )

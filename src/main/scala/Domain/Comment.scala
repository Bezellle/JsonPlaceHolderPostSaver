package Domain

case class Comment(
                  postId: PostId,
                  id: String,
                  name: String,
                  email: String,
                  body: String
                  )

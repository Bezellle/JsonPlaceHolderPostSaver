package Domain.Comment

import Domain.Post.PostId

case class Comment(
                    postId: PostId,
                    id: CommentId,
                    name: String,
                    email: Email,
                    body: String
                  )

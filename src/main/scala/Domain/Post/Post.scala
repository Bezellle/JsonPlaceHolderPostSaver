package Domain.Post

case class Post(
                 userId: UserId,
                 id: PostId,
                 title: String,
                 body: String
               )

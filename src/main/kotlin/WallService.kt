object WallService {
    private var nextPostId = 1
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var reports = emptyArray<Report>()

    fun getComments() : Array<Comment>{
        return comments
    }
    fun createComment(postId: Int, comment: Comment): Comment {
        for ((index) in posts) {
            if (posts[index].id == postId) {
                comment.postId = postId
                comments += comment
                return comment
            }
        }
        throw PostNotFoundException("Post with $postId not found")
    }

    fun clear() {
        posts = emptyArray()
        comments = emptyArray()
        reports = emptyArray()
        nextPostId = 0
    }

    fun add(post: Post): Post {
        posts += post.copy(id = nextPostId++)
        return posts.last()
    }

    fun update(newPost: Post): Boolean {
        for ((index, post) in posts.withIndex()) {
            if (newPost.id == post.id) {
                posts[index] = post.copy(
                    ownerId = newPost.ownerId,
                    fromId = newPost.fromId,
                    text = newPost.text,
                    likes = newPost.likes,
                    comments = newPost.comments,
                    reposts = newPost.reposts,
                    isPinned = newPost.isPinned,
                    friendsOnly = newPost.friendsOnly,
                    postType = newPost.postType,
                    markedAsAds = newPost.markedAsAds
                )
                return true
            }
        }
        return false
    }

    fun reportComment (
        ownerId: Int,
        commentId: Int,
        reason: Int
    ) : Int {
        for (post in posts)  {
            if (ownerId == post.id) {
                println(comments.size)
                for (comment in comments) {
                    println(comment.id)
                    if (comment.id == commentId) {
                        if (reason in 0..8) {
                            reports.plus(Report(ownerId, commentId, reason))
                            return 1
                        }
                        throw KeyNotFoundException("Код не существует")
                    }
                }
                throw CommentNotFoundException("Комментарий не найден")
            }
        }
        throw UserNotFoundException("Пользователь не найден")
    }
}
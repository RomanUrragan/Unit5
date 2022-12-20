var nextCommentId: Int = 1

class Comment(
    var postId: Int = 0,
    var fromId: Int = 0,
    var date: Long = System.currentTimeMillis(),
    var text: String = "Some text",
    var donut: Donut = Donut(),
    var replyToUser: Int = 0,
    var attachments: Array<Attachment> = emptyArray(),
    var parentsStack: Array<Int> = emptyArray(),
    var commentThread: CommentThread = CommentThread(),
    var isDeleted: Boolean = false
) {
    var id = nextCommentId++
}

data class CommentThread(
    val count: Int = 0,
    val items: Array<Comment> = emptyArray(),
    val canPost: Boolean = true,
    val showReplyButton: Boolean = true,
    val groupsCanPost: Boolean = true
)
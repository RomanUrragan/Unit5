private var nextCommentId: Int = 1

class Comment(
    var postId: Int = 0,
    val fromId: Int = 0,
    val date: Long = System.currentTimeMillis(),
    val text: String = "Some text",
    val donut: Donut = Donut(),
    val replyToUser: Int = 0,
    val attachments: Array<Attachment> = emptyArray(),
    val parentsStack: Array<Int> = emptyArray(),
    val commentThread: CommentThread = CommentThread()
) {

    val id = nextCommentId++
}

data class CommentThread(
    val count: Int = 0,
    val items: Array<Comment> = emptyArray(),
    val canPost: Boolean = true,
    val showReplyButton: Boolean = true,
    val groupsCanPost: Boolean = true
)
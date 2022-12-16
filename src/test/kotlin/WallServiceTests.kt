import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class WallServiceTests {
    private val post = Post(
        text = "Some text",
    )

    @Before
    fun clearBeforeTest() {
        WallService.clear()
    }

    @Test
    fun testAdd() {
        val result = WallService.add(post)
        assertNotEquals(post.id + 1, result.id)
        assertEquals(post.text, result.text)
    }

    @Test
    fun updateTrue() {
        val addedPost = WallService.add(post)
        val updatedPost = Post(
            id = addedPost.id,
            text = "Some new text"
        )
        val resultTrue = WallService.update(updatedPost)
        assertTrue(resultTrue)

    }

    @Test
    fun updateFalse() {
        val addedPost = WallService.add(post)
        val resultFalse = WallService.update(Post(id = addedPost.id + 1))
        assertFalse(resultFalse)
    }

    @Test
    fun createCommentPostIDChanged() {
        WallService.add(Post(id = 1))
        WallService.add(Post(id = 2))
        WallService.add(Post(id = 3))
        val postId = 2
        val comment = WallService.createComment(postId, Comment())
        val commentPostId = comment.postId
        assertEquals(postId, commentPostId)
    }

    @Test
    fun createdCommentAddedToArray() {
        val post = WallService.add(Post(id = 1))
        val comment = WallService.createComment(post.id, Comment())
        val result = WallService.getComments().size
        assertEquals(1, result)
    }

    @Test(expected = PostNotFoundException::class)
    fun createCommentShouldThrowException() {
        WallService.add(Post(id = 1))
        WallService.add(Post(id = 2))
        WallService.add(Post(id = 3))
        val postId = 4
        WallService.createComment(postId, Comment())
    }

    @Test
    fun reportComment() {
        val post = WallService.add(Post())
        val comment = WallService.createComment(post.id, Comment())
        val result = WallService.reportComment(post.id, comment.id, 1)
        assertEquals(1, result)
    }

    @Test(expected = UserNotFoundException::class)
    fun reportCommentThrowUserNotFoundException() {
        WallService.add(Post(id = 1))
        val comment = WallService.createComment(post.id, Comment())
        WallService.reportComment(post.id + 1, comment.id, 1)
    }

    @Test(expected = CommentNotFoundException::class)
    fun reportCommentThrowCommentNotFoundException() {
        WallService.add(Post(id = 1))
        val comment = WallService.createComment(post.id, Comment())
        WallService.reportComment(post.id, comment.id + 1, 1)
    }

    @Test(expected = KeyNotFoundException::class)
    fun reportCommentThrowKeyNotFoundException() {
        WallService.add(Post(id = 1))
        val comment = WallService.createComment(post.id, Comment())
        WallService.reportComment(post.id, comment.id, 9)
    }
}
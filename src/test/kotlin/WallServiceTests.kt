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
        assertNotEquals(post.id, result.id)
        assertEquals(post.text, result.text)
    }

    @Test
    fun update() {
        val addedPost = WallService.add(post)
        val updatedPost = Post(
            id = addedPost.id,
            text = "Some new text"
        )
        val resultTrue = WallService.update(updatedPost)
        assertTrue(resultTrue)
        val resultFalse = WallService.update(Post(id = addedPost.id + 1))
        assertFalse(resultFalse)
    }
}
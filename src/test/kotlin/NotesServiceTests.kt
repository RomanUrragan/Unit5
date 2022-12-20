import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import java.lang.Thread.sleep

class NotesServiceTests {

    @Before
    fun clearBeforeTest() {
        NoteService.clear()
    }

    @Test
    fun noteIsAddedTest() {
        val ownerId = 100
        NoteService.add(ownerId)
        val result = NoteService.getNotes()[1]?.ownerId
        assertEquals(ownerId, result)
    }

    @Test
    fun delete() {
        NoteService.add(100)
        NoteService.add(101)
        NoteService.add(102)
        NoteService.delete(2)
        assertFalse(NoteService.getNotes().containsKey(2))
    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteThrowException() {
        NoteService.add(100)
        NoteService.add(101)
        NoteService.add(102)
        NoteService.delete(4)
    }

    @Test
    fun editTest() {
        val noteId: Int = NoteService.add(
            100,
            title = "title", privacyView = "All", privacyComment = "All", text = "Old Text"
        )
        NoteService.edit(noteId, title = "title", text = "New Text", privacyView = "All", privacyComment = "All")
        val note: Note? = NoteService.getNotes()[noteId]
        if (note != null) {
            assertEquals("New Text", note.text)
        }
    }

    @Test(expected = NoteNotFoundException::class)
    fun editThrowException() {
        NoteService.edit(1, title = "title", text = "New Text", privacyView = "All", privacyComment = "All")
    }

    @Test
    fun getTestSorted(){
        NoteService.add(100) //id = 1
        sleep(300)
        NoteService.add(101) //id = 2
        sleep(300)
        NoteService.add(100) //id = 3
        sleep(300)
        NoteService.add(102) //id = 4
        sleep(300)
        NoteService.add(100) //id = 5
        sleep(300)
        NoteService.add(103) //id = 6
        val notes:  List<Note> = NoteService.get(100, true)
        assertTrue(notes[0].id == 1 && notes[1].id == 3 && notes[2].id == 5)
    }

    @Test
    fun getTestSortedByDescending(){
        NoteService.add(100) //id = 1
        sleep(300)
        NoteService.add(101) //id = 2
        sleep(300)
        NoteService.add(100) //id = 3
        sleep(300)
        NoteService.add(102) //id = 4
        sleep(300)
        NoteService.add(100) //id = 5
        sleep(300)
        NoteService.add(103) //id = 6
        val notes:  List<Note> = NoteService.get(100, false)
        assertTrue(notes[0].id == 5 && notes[1].id == 3 && notes[2].id == 1)
    }

    @Test
    fun getById(){
        NoteService.add(100)
        val expectedNote = NoteService.getNotes()[1]
        val resultNote: Note = NoteService.getById(1)
        assertEquals(expectedNote, resultNote)
    }
    @Test(expected = NoteNotFoundException::class)
    fun getByIdThrowException(){
        val resultNote: Note = NoteService.getById(1)
    }

    @Test
    fun createComment(){
        NoteService.add(100)
        NoteService.createComment(1, 200)
        val comment: Comment = NoteService.getAllComments().first()
        assertTrue(comment.id == 1 && comment.postId == 1 && comment.fromId == 200)
    }

    @Test(expected = NoteNotFoundException::class)
    fun createCommentThrowException(){
        NoteService.createComment(1, 200)
        val comment: Comment = NoteService.getAllComments().first()
    }

    @Test
    fun deleteComment(){
        NoteService.add(100)
        NoteService.createComment(1,100)
        NoteService.deleteComment(1)
        val comment: Comment = NoteService.getAllComments().first()
        assertTrue(comment.isDeleted && comment.id == 1)
    }

    @Test(expected = CommentNotFoundException::class)
    fun deleteCommentThrowException(){
        NoteService.deleteComment(1)
    }


    @Test
    fun editComment(){
        NoteService.add(100)
        NoteService.createComment(1,100, message = "Old Text")
        NoteService.editComment(1, "New Text")
        val comment = NoteService.getAllComments().first()
        assertEquals("New Text", comment.text)
    }

    @Test(expected = CommentNotFoundException::class)
    fun editCommentThrowException(){
        NoteService.editComment(1, "New Text")
    }

    @Test(expected = CommentNotFoundException::class)
    fun editDeletedComment(){
        NoteService.add(100)
        NoteService.createComment(1,100, message = "Old Text")
        NoteService.deleteComment(1)
        NoteService.editComment(1, "New Text")
    }

    @Test
    fun getCommentsSortedByDescending(){
        NoteService.add(100)
        NoteService.createComment(1,100, message = "Old Comment")
        sleep(300)
        NoteService.add(100)
        NoteService.createComment(1,200, message = "Young Comment")
        sleep(300)
        NoteService.add(200)
        NoteService.createComment(2,200, message = "Ignoring Comment")
        sleep(300)
        val list: List<Comment> = NoteService.getComments(1)
        var firstComment = list.first()
        var secondComment = list.last()
        assertTrue("Young Comment" == firstComment.text && "Old Comment" == secondComment.text)
    }

    @Test
    fun getCommentsSorted(){
        NoteService.add(100)
        NoteService.createComment(1,100, message = "Old Comment")
        NoteService.add(100)
        NoteService.createComment(1,200, message = "Young Comment")
        NoteService.add(200)
        NoteService.createComment(1,200, message = "Deleted Comment")
        NoteService.deleteComment(3)
        val list: List<Comment> = NoteService.getComments(1, false)
        var firstComment = list.first()
        var secondComment = list.last()
        assertTrue("Old Comment" == firstComment.text && "Young Comment" == secondComment.text)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getCommentThrowException(){
        NoteService.getComments(1, false)
    }

    @Test
    fun restoreComments(){
        NoteService.add(100)
        NoteService.createComment(1, 200)
        NoteService.deleteComment(1)
        NoteService.restoreComments(1)
        val comment: Comment = NoteService.getAllComments().first()
        assertFalse(comment.isDeleted)
    }

    @Test(expected = CommentNotFoundException::class)
    fun restoreCommentsThrowException(){
        NoteService.restoreComments(1)
    }
}
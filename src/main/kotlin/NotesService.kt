import java.util.*
import kotlin.collections.HashSet

object NoteService {
    private var notes = TreeMap<Int, Note>() //карта с заметками, где ключ - id заметки
    private var comments = HashSet<Comment>() //множество комментариев
    private var nextId = 1

    fun getNotes(): TreeMap<Int, Note> {
        return notes
    }

    fun getAllComments(): HashSet<Comment>{
        return comments
    }

    fun clear() {
        notes = TreeMap<Int, Note>()
        comments = HashSet<Comment>()
        nextId = 1
        nextCommentId = 1
    }

    //создает заметку типа Note, добавляет в карту заметок, возвращает индекс добавленной заметки
    fun add(
        ownerId: Int,
        title: String = "Заголовок заметки",
        text: String = "Текст заметки",
        date: Long = System.currentTimeMillis(),
        privacyView: String = "all",
        privacyComment: String = "all"
    ): Int {
        val note = Note(
            id = nextId,
            ownerId = ownerId,
            title = title,
            text = text,
            date = date,
            privacyView = privacyView,
            privacyComment = privacyComment
        )
        notes[nextId] = note
        return nextId++
    }

    //удаляет записку с заданным ID. В случае успеха возвращает 1. Если ID не существует, выбрасывает ошибку
    fun delete(noteId: Int): Int {
        if (notes.containsKey(noteId)) {
            comments.removeAll(getComments(noteId).toSet())
            notes.remove(noteId)

            return 1
        }
        throw NoteNotFoundException("Заметка с таким Id не найдена")
    }

    //изменяет записку с заданным ID согласно переданным параметрам. Если ID не существует, выбрасывает ошибку
    fun edit(
        noteId: Int,
        title: String,
        text: String,
        privacyView: String,
        privacyComment: String
    ) {
        if (notes.containsKey(noteId)) {
            val newNote: Note = notes[noteId]!!.copy(
                title = title,
                text = text,
                privacyView = privacyView,
                privacyComment = privacyComment
            )
            notes[noteId] = newNote
        } else throw NoteNotFoundException("Записка с таким id не существует")
    }

    //возвращает отсортированный список заметок пользователя. ID пользователя передается параметром.
    fun get(userId: Int, sort: Boolean): List<Note> {
        var ownerNotes: List<Note> = mutableListOf()
        for (note in notes) {
            if (note.value.ownerId == userId) {
                ownerNotes += note.value
            }
        }
        return if (sort) {
            ownerNotes.sortedBy { it.date }
        } else {
            ownerNotes.sortedByDescending { it.date }
        }
    }

    //возвращает заметку по ее Id, выбрасываешт ошибку, если id е существует
    fun getById(noteId: Int): Note {
        when {
            notes[noteId] == null -> {
                throw NoteNotFoundException("Заметка с таким Id не найдена")
            }

            else -> {
                return notes[noteId]!!
            }
        }
    }

    //создает комментарий к заметке, возвращает id созданного комментария
    fun createComment(
        noteId: Int,
        fromId: Int,
        replyTo: Int = 0,
        message: String = "Текст комментария",
    ): Int {
        if (notes.containsKey(noteId)) {
            val comment: Comment = Comment(postId = noteId, fromId = fromId, replyToUser = replyTo, text = message)
            comments.add(comment)
            return comment.id
        } else throw NoteNotFoundException("Заметка с таким Id не найдена")
    }

    //удаляет комментарий, возввращает 1 в случае успешного удаления
    fun deleteComment(commentId: Int): Int {
        for (comment in comments) {
            if (comment.id == commentId && !comment.isDeleted) {
                comment.isDeleted = true
                return 1
            }
        }
        throw CommentNotFoundException("Комментарий с таким id не существует")
    }

    //редактирует комментарий, возвращает 1 в случае успеха
    fun editComment(commentId: Int, message: String): Int {
        for (comment in comments) {
            if (comment.id == commentId && !comment.isDeleted) {
                comment.text = message
                return 1
            }
        }
        throw CommentNotFoundException("Комментарий с таким id не существует")
    }

    //возвращает массив объектов Comment
    fun getComments(noteId: Int, sort: Boolean = true): List<Comment> {
        if (!notes.containsKey(noteId)) {
            throw NoteNotFoundException("Записка с таким id не существует")
        }
        var noteComments: List<Comment> = mutableListOf()
        for (comment in comments) {
            if (comment.postId == noteId && !comment.isDeleted) {
                noteComments += comment
            }
        }
        return when {
            sort -> {
                noteComments.sortedByDescending { it.date }
            }

            else -> {
                noteComments.sortedBy { it.date }
            }
        }
    }

    //Восстанавливает комментарий, в случае успеха возвращает 1
    fun restoreComments(commentId: Int): Int {
        for (comment in comments) {
            if (comment.id == commentId) {
                comment.isDeleted = false
                return 1
            }
        }
        throw CommentNotFoundException("Комментария с таким id не существует")
    }

}
data class Note(
    val id: Int,
    val ownerId: Int,
    val title: String = "Заголовок заметки",
    val text: String = "Текст заметки",
    val date: Long,
    val comments: Int = 0,
    val privacyView: String = "all",
    val privacyComment: String = "all"
)
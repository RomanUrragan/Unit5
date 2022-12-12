data class Post(
    val id: Int = 0,
    val ownerId: Int = 0,
    val fromId: Int = 0,
    val date: Long = System.currentTimeMillis(),
    val text: String = "",
    val likes: Likes = Likes(),
    val comments: Comments = Comments(),
    val reposts: Reposts = Reposts(),
    val isPinned: Boolean = false,
    val friendsOnly: Boolean = false,
    val postType: String = "post",
    val markedAsAds: Boolean = false
)
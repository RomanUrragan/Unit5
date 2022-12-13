const val AUDIO: String = "Audio"
const val PHOTO: String = "Photo"
const val VIDEO: String = "Video"
const val GRAFFITI: String = "Graffiti"
const val ALBUM: String = "Album"

abstract class Attachment() {
    abstract val type: String
}

class AudioAttachment(
    override val type: String = AUDIO,
    val audio: Audio
) : Attachment()

class Audio(
    val id: Int = 0,
    val owner_id: Int = 0,
    val artist: String = "Неизвестен",
    val title: String = "Без названия",
    val duration: Int = 0,
    val url: String = "",
    val lyricsId: Int? = null,
    val albumId: Int? = null,
    val genreId: Int? = null,
    val date: Long = System.currentTimeMillis(),
    val noSearch: Boolean = false,
    val isHq: Boolean = false,
)

class PhotoAttachment(
    override val type: String = PHOTO,
    val photo: Photo
) : Attachment()

class Photo(
    val id: Int = 0,
    val albumId: Int = 0,
    val ownerId: Int = 0,
    val userId: Int = ownerId,
    val text: String = "",
    val date: Long = System.currentTimeMillis(),
    val sizes: Array<PhotoSizes> = emptyArray<PhotoSizes>(),
    val width: Int = 2551,
    val height: Int = 1795
) {
    class PhotoSizes(
        val type: String = "",
        val url: String = "",
        val width: Int = 1795,
        val height: Int = 2551
    )
}

class VideoAttachment(
    override val type: String = VIDEO,
    val video: Video
) : Attachment()

class Video(
    val VIDEO: String = "video",
    val MUSIC_VIDEO: String = "music_video",
    val MOVIE: String = "movie",
    val id: Int = 0,
    val ownerId: Int = 0,
    val title: String = "",
    val description: String = "",
    val duration: String = "",
    val image: Array<VideoImage> = emptyArray(),
    val firstFrame: Array<VideoFirstFrame> = emptyArray(),
    val date: Long = System.currentTimeMillis(),
    val addingDate: Long = System.currentTimeMillis(),
    val views: Int = 0,
    val localViews: Int? = 0,
    val comments: Int = 0,
    val player: String = "flash",
    val canAdd: Boolean = true,
    val isPrivate: Boolean = true,
    val isFavorite: Boolean = true,
    val canComment: Boolean = true,
    val canEdit: Boolean = true,
    val canLike: Boolean = true,
    val canRepost: Boolean = true,
    val canSubscribe: Boolean = true,
    val canAddToFaves: Boolean = true,
    val canAttachLink: Boolean = true,
    val width: Int = 2551,
    val height: Int = 1795,
    val userId: Int = 0,
    val converting: Boolean = true,
    val added: Boolean = false,
    val isSubscribed: Boolean = false,
    val repeat: Boolean = false,
    val type: String = VIDEO,
    val balance: Int = 0,
    val liveStatus: String = "",
    val live: Boolean = false,
    val upcoming: Boolean = false,
    val spectators: Int = 0,
    val likes: Likes = Likes(),
    val reposts: Reposts = Reposts()
) {
    class VideoImage(
        val width: Int = 2551,
        val height: Int = 1795,
        val url: String = "",
        val withPadding: Int = 1
    )

    class VideoFirstFrame(
        val width: Int = 2551,
        val height: Int = 1795,
        val url: String = "",
    )
}

class GraffitiAttachment(
    override val type: String = GRAFFITI,
    val graffiti: Graffiti
) : Attachment()

class Graffiti(
    val id: Int = 0,
    val ownerId: Int = 0,
    val photo130: String = "",
    val photo604: String = ""
)

class AlbumAttachment(
    override val type: String = ALBUM,
    val album: Album
) : Attachment()

class Album(
    val id: Int = 0,
    val thumb: Photo = Photo(),
    val ownerId: Int = 0,
    val title: String = "",
    val description: String = "",
    val created: Long = System.currentTimeMillis(),
    val updated: Long = System.currentTimeMillis(),
    val size: Int = 0
)
object WallService {

    private var posts = emptyArray<Post>()
    private var nextId = 1

    fun clear() {
        posts = emptyArray()
        nextId = 0
    }


    fun add(post: Post): Post {
        posts += post.copy(id = nextId++)
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

}
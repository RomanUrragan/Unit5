### Опущенные параметры:
Уровень приватности контролируют String объекты privacyView и PrivacyComment. Int объекты privacy и comment_privacy не используются.


Также опущены переменные:

data class Note: read_comments, text_wiki

Метод get: note_ids, offset, count

Метод getById: need_Wiki, owner_id

Метод createComment: owner_id, guid. Добавлен fromId

Метод deleteComment: owner_id

Метод getComments: owner_id, offset, count

Метод restoreComment: owner_id

Метод getFriendsNotes()
import ru.netology.models.Chat
import ru.netology.models.Message
import ru.netology.services.ChatService
import ru.netology.services.DaoService

fun main() {

}

public val Chat.lastReadMessage: Int
    get() = this.getMessages().size -
            getMessages().asSequence()
                .filter { it.isRead }
                .toList()
                .size - 1

public val ChatService.unreadChatCount: Int
    get() = this.getChats().map { messageList ->  messageList.getMessages().filter { !it.isRead } }.size

public fun ChatService.getChatsWithLastMessage() = this.getChats().filter { it.lastReadMessage > -1 }

public fun ChatService.getChatCountMessage(idContact: Int, idLastMessage: Int, countMessages: Int): List<Message>
 {
    return (this
            .getChatById(idContact)
            .getMessages().asSequence()
            .filter { it.id > idLastMessage  }
            .take(countMessages)
            .map { m -> m.apply { isRead = true} }
            .toList()
            ) ?: emptyList()

}


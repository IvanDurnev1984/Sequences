package ru.netology.services

import ru.netology.models.Chat

class ChatService {
    private var chats: DaoService<Chat> = DaoService()

    fun getChats() = chats.read()

    fun getChatById(idContact: Int) =
        getChats()
            .filter { it.idContact == idContact }
            .get(0)



    fun addMessage(idContact: Int, content: String) {
        val list = getChats().filter { it.idContact == idContact }
        if (list.size == 1) {
            val chat = list.get(0)
            chat.createMessage(content)
            chats.update(chat.id, chat)
        } else {
            val chat = Chat(id = chats.read().size, idContact = idContact)
            chat.createMessage(content)
            chats.create(chat)
        }
    }

    fun removeMessage(id: Int, idMessage: Int) {
        chats.readById(id)?.deleteMessage(idMessage)
        val mess =chats.readById(id)?.getMessages()
        if (mess == null) {
            removeChat(id)
        }

    }

    fun removeChat(id: Int) {
        chats.delete(id)
    }
}


package ru.netology.models

import ru.netology.services.DaoService

data class Chat (
    val id: Int,
    val idContact: Int
        ) {

    private var messages: DaoService<Message> = DaoService()

    fun createMessage(content: String) {
        val message = Message(id = messages.read().size, content = content)
        messages.create(message)
    }

    fun updateMessage(id: Int, content: String) {
        val message = messages.readById(id)
                              ?.copy(content = content)

        message?.let { messages.update(id, it) }
    }

    fun deleteMessage(id: Int) {
        messages.delete(id)
    }

    fun getMessages(): List<Message> = messages.read()


}


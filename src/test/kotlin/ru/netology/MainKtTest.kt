package ru.netology

import getChatCountMessage
import org.junit.Assert.*
import org.junit.Test
import ru.netology.services.ChatService

class MainKtTest {
    private val chatService = ChatService()

    @Test
    fun getMessageCount() {

        chatService.addMessage(idContact = 23, content = "Test text 1")
        chatService.addMessage(idContact = 23, content = "Test text 2")
        chatService.addMessage(idContact = 23, content = "Test text 3")

        chatService.addMessage(idContact = 23, content = "Test text 23-1")
        chatService.addMessage(idContact = 23, content = "Test text 23-2")
        chatService.addMessage(idContact = 23, content = "Test text 23-3")

        chatService.addMessage(idContact = 23, content = "Test text 23-4")
        chatService.addMessage(idContact = 23, content = "Test text 23-5")
        chatService.addMessage(idContact = 23, content = "Test text 23-6")

        var result = chatService
            .getChatById(idContact = 23)
            .getMessages()
            .filter { it.id == 1 }
            .get(0)
            .isRead


        chatService.getChatCountMessage(idContact = 23, idLastMessage = 0, countMessages = 3)

        result = !result && chatService
            .getChatById(idContact = 23)
            .getMessages()
            .filter { it.id == 1 }
            .get(0)
            .isRead

        val list = chatService.getChatCountMessage(idContact = 23, idLastMessage = 3, countMessages = 3)
        result = result && list.get(1).isRead

        assertTrue(result)
    }
}
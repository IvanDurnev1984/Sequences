package ru.netology.models

data class Message(
    val id: Int = 0,
    var isRead: Boolean = false,
    val content: String = ""
        ) {
}
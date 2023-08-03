package com.wizuurd.artparser.client.vk

import api.longpoll.bots.LongPollBot
import api.longpoll.bots.exceptions.VkApiException
import api.longpoll.bots.model.events.messages.MessageNew
import api.longpoll.bots.model.objects.basic.Message
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component


@Component
class VkClient(
    @Value("\${app.vk.token}")
    private val token: String
): LongPollBot() {

    override fun getAccessToken() = token

    override fun onMessageNew(messageNew: MessageNew?) {
        try {
            val message: Message = messageNew!!.message
            if (message.hasText()) {
                val response = "Hello! Received your message: " + message.text
                vk.messages.send()
                    .setPeerId(message.peerId)
                    .setMessage(response)
                    .execute()
            }
        } catch (e: VkApiException) {
            e.printStackTrace()
        }
    }
}
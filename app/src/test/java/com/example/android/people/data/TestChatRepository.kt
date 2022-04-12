package com.example.android.people.data

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * This is like [DefaultChatRepository] except:
 * - The initial chat history can be supplied as a constructor parameter.
 * - It does not wait 5 seconds to receive a reply.
 */
class TestChatRepository(private val chats: Map<Long, Chat>) : ChatRepository {

    var activatedId = 0L

    var bubbleId = 0L

    override fun getContacts(): LiveData<List<Contact>> {
        return MutableLiveData<List<Contact>>().apply {
            value = chats.values.map { it.contact }
        }
    }

    override fun findContact(id: Long): LiveData<Contact?> {
        return MutableLiveData<Contact>().apply {
            value = Contact.CONTACTS.find { it.id == id }
        }
    }

    override fun findMessages(id: Long): LiveData<List<Message>> {
        val chat = chats.getValue(id)
        return object : LiveData<List<Message>>() {

            private val listener = { messages: List<Message> ->
                postValue(messages)
            }

            override fun onActive() {
                value = chat.messages
                chat.addListener(listener)
            }

            override fun onInactive() {
                chat.removeListener(listener)
            }
        }
    }

    override fun sendMessage(id: Long, text: String, photoUri: Uri?, photoMimeType: String?) {
        val chat = chats.getValue(id)
        chat.addMessage(Message.Builder().apply {
            sender = 0L // User
            this.text = text
            timestamp = System.currentTimeMillis()
            this.photo = photoUri
            this.photoMimeType = photoMimeType
        })
        chat.addMessage(chat.contact.reply(text))
    }

    override fun updateNotification(id: Long) {
    }

    override fun activateChat(id: Long) {
        activatedId = id
    }

    override fun deactivateChat(id: Long) {
        activatedId = 0L
    }

    override fun showAsBubble(id: Long) {
        bubbleId = id
    }

    override fun canBubble(id: Long): Boolean {
        return true
    }
}

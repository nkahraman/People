package com.example.android.people.ui.chat

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.people.data.Chat
import com.example.android.people.data.Contact
import com.example.android.people.data.TestChatRepository
import com.example.android.people.observedValue
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class ChatViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dummyContacts = Contact.CONTACTS

    private lateinit var viewModel: ChatViewModel
    private lateinit var repository: TestChatRepository

    @Before
    fun createViewModel() {
        repository = TestChatRepository(dummyContacts.map { contact ->
            contact.id to Chat(contact)
        }.toMap())
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            viewModel = ChatViewModel(ApplicationProvider.getApplicationContext(), repository)
        }
    }

    @Test
    fun hasContactAndMessages() {
        viewModel.setChatId(1L)
        viewModel.foreground = true
        assertThat(viewModel.contact.observedValue()).isEqualTo(dummyContacts.find { it.id == 1L })
        assertThat(viewModel.messages.observedValue()).hasSize(2)
        assertThat(repository.activatedId).isEqualTo(1L)
    }

    @Test
    fun sendAndReceiveReply() {
        viewModel.setChatId(1L)
        viewModel.send("a")
        val messages = viewModel.messages.observedValue()
        assertThat(messages).hasSize(4)
        assertThat(messages[2].text).isEqualTo("a")
        assertThat(messages[3].text).isEqualTo("Meow")
    }

    @Test
    fun showAsBubble() {
        viewModel.setChatId(1L)
        assertThat(repository.bubbleId).isEqualTo(0L)
        viewModel.showAsBubble()
        assertThat(repository.bubbleId).isEqualTo(1L)
    }

}

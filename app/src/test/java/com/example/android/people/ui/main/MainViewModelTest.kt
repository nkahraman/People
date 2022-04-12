package com.example.android.people.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.people.data.Chat
import com.example.android.people.data.Contact
import com.example.android.people.data.TestChatRepository
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dummyContacts = Contact.CONTACTS

    private fun createViewModel(): MainViewModel {
        var viewModel: MainViewModel? = null
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            viewModel = MainViewModel(
                ApplicationProvider.getApplicationContext(),
                TestChatRepository(dummyContacts.map { contact ->
                    contact.id to Chat(contact)
                }.toMap())
            )
        }
        return viewModel!!
    }

    @Test
    fun hasListOfContacts() {
        val viewModel = createViewModel()
        val contacts = viewModel.contacts.value
        assertThat(contacts).isEqualTo(dummyContacts)
    }

}

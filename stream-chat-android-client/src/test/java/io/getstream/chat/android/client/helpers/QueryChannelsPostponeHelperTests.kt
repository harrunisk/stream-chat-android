package io.getstream.chat.android.client.helpers

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.getstream.chat.android.client.Mother
import io.getstream.chat.android.client.api.ChatApi
import io.getstream.chat.android.client.clientstate.ClientState
import io.getstream.chat.android.client.clientstate.ClientStateService
import io.getstream.chat.android.test.asCall
import io.getstream.chat.android.test.randomString
import org.amshove.kluent.When
import org.amshove.kluent.calling
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class QueryChannelsPostponeHelperTests {

    private lateinit var api: ChatApi
    private lateinit var clientStateService: ClientStateService

    private lateinit var sut: QueryChannelsPostponeHelper

    @BeforeEach
    fun setUp() {
        api = mock()
        clientStateService = mock()
        sut = QueryChannelsPostponeHelper(api, clientStateService, DELAY_DURATION, ATTEMPTS_COUNT)
    }

    @Test
    fun `Given authorized user state When query channel Should return channel from api`() {
        val expectedResult = Mother.randomChannel()
        When calling api.queryChannel(any(), any(), any()) doReturn expectedResult.asCall()
        When calling clientStateService.state doReturn ClientState.User.Authorized.Connected(
            randomString(),
            Mother.randomUser(),
            randomString()
        )

        val result = sut.queryChannel("channelType", "channelId", mock()).execute().data()

        verify(api).queryChannel(any(), any(), any())
        result shouldBeEqualTo expectedResult
    }

    @Test
    fun `Given idle state When query channel Should throw an error`() {
        When calling clientStateService.state doReturn ClientState.Idle

        assertThrows<IllegalStateException> {
            sut.queryChannel("channelType", "channelId", mock())
        }
    }

    @Test
    fun `Given long pending state When query channel Should throw an error`() {
        When calling clientStateService.state doReturn ClientState.User.Pending.WithoutToken(Mother.randomUser())

        assertThrows<IllegalStateException> {
            sut.queryChannel("channelType", "channelId", mock())
        }
    }

    @Test
    fun `Given pending state and authorized then When query channel Should query to api and return result`() {
        val expectedResult = Mother.randomChannel()
        whenever(api.queryChannel(any(), any(), any())).thenReturn(expectedResult.asCall())
        whenever(clientStateService.state)
            .thenReturn(ClientState.User.Pending.WithoutToken(mock()))
            .thenReturn(ClientState.User.Authorized.Connected("connId", mock(), "token"))

        val result = sut.queryChannel("channelType", "channelId", mock()).execute().data()

        verify(api).queryChannel(any(), any(), any())
        result shouldBeEqualTo expectedResult
    }

    companion object {
        private const val ATTEMPTS_COUNT = 2
        private const val DELAY_DURATION = 30L
    }
}
